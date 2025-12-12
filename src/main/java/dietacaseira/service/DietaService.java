package dietacaseira.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import dietacaseira.repository.DietaRepository;
import dietacaseira.model.Dieta;
import dietacaseira.model.ItemDieta;
import dietacaseira.model.Pet;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    @Autowired
    private NutricaoService nutricaoService;

    @Transactional
    public Dieta criarDieta(Pet pet, String tipo, List<ItemDieta> itens) {
        
        // 1. Validação de Segurança
        if (pet == null || pet.getId() == null) {
            throw new IllegalArgumentException("Erro: O Pet deve existir no banco de dados.");
        }

        // 2. Cria o objeto Pai (Dieta)
        Dieta novaDieta = new Dieta();
        novaDieta.setPet(pet);
        novaDieta.setTipo(tipo); // Agora usamos "tipo" (ex: "Manutenção", "Perda de Peso")
        novaDieta.setDataCriacao(java.sql.Date.valueOf(LocalDate.now()));

        double somaCalorias = 0.0;

        // 3. Processa cada Item (Filhos)
        for (ItemDieta item : itens) {
            item.setDieta(novaDieta); // Vincula ao pai

            if (item.getIngrediente() != null) {
                // O banco usa DECIMAL(10,4) para quantidade
                double quantidadeGramas = item.getQuantidade().doubleValue();
                
                // O ingrediente tem energia_kcal (DECIMAL)
                double kcalPor100g = item.getIngrediente().getEnergiaKcal().doubleValue();
                
                // Cálculo: (Qtd / 100) * Kcal
                double caloriaItem = (quantidadeGramas / 100) * kcalPor100g;
                
                somaCalorias += caloriaItem;
            }
        }

        // 4. Preenche o total_calorico (DECIMAL no banco)
        novaDieta.setTotalCalorico(BigDecimal.valueOf(somaCalorias));
        novaDieta.setItens(itens);

        // 5. Salva (Cascade salva os itens junto)
        return dietaRepository.save(novaDieta);
    }

    public List<Dieta> listarPorPet(Pet pet) {
        return dietaRepository.findByPet(pet);
    }
    
    public void deletar(Integer id) {
        dietaRepository.deleteById(id);
    }
}
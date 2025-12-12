package dietacaseira.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; // Importante!
import dietacaseira.repository.PetRepository;
import dietacaseira.model.Pet;
import dietacaseira.model.MedicoVeterinario;
import java.util.List;
import java.math.BigDecimal;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    // AQUI ESTÁ A CORREÇÃO: O Spring traz o serviço pronto para você
    @Autowired
    private NutricaoService nutricaoService; 

    public Pet salvar(Pet pet) {
        if (pet.getPeso() != null && pet.getPeso().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O peso do pet deve ser maior que zero.");
        }
        return petRepository.save(pet);
    }

    // ... (outros métodos listar/deletar iguais ao anterior) ...
    public List<Pet> listarPorMedico(MedicoVeterinario medico) {
        return petRepository.findByMedico(medico);
    }
    
    public void deletar(Integer id) {
        petRepository.deleteById(id);
    }

    // USO DO OUTRO SERVICE
    public double obterNecessidadeCalorica(Pet pet) {
        // NÃO use "new NutricaoService()". Use a variável injetada lá em cima.
        return nutricaoService.calcularMER(pet);
    }
}
package dietacaseira.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import dietacaseira.repository.IngredienteRepository;
import dietacaseira.model.Ingrediente;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // Listar tudo (Para preencher tabelas ou combobox na tela)
    public List<Ingrediente> listarTodos() {
        return ingredienteRepository.findAll();
    }

    // Buscar para o "Auto-complete" (ex: usuário digita "Fran" -> aparece "Frango")
    public List<Ingrediente> buscarPorNome(String termo) {
        return ingredienteRepository.findByNomeContainingIgnoreCase(termo);
    }
    
    // Salvar novos ingredientes (caso você crie uma tela de cadastro de alimentos)
    public Ingrediente salvar(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    // Buscar um específico pelo ID
    public Ingrediente buscarPorId(Integer id) {
        Optional<Ingrediente> opt = ingredienteRepository.findById(id);
        return opt.orElse(null);
    }
}
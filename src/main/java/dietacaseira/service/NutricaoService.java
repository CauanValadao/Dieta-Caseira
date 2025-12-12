package dietacaseira.service;

import org.springframework.stereotype.Service;

import dietacaseira.model.Pet;

@Service
public class NutricaoService { 
    // --- LÓGICA DE NEGÓCIO (CÁLCULOS NUTRICIONAIS) ---

    /**
     * Calcula o RER (Requisito Energético de Repouso).
     * Fórmula padrão para cães/gatos: 70 * (Peso em kg ^ 0.75)
     */
    public double calcularRER(Pet pet) {
        if (pet.getPeso() == null) return 0.0;

        double peso = pet.getPeso().doubleValue();
        // Math.pow(base, expoente) faz o cálculo de potência
        return 70 * Math.pow(peso, 0.75);
    }

    /**
     * Calcula o MER (Requisito Energético de Manutenção).
     * Fórmula: RER * Fator de Atividade
     */
    public double calcularMER(Pet pet) {
        double rer = calcularRER(pet);
        double fator = obterFatorMER(pet);
        
        return rer * fator;
    }

    /**
     * Define o fator multiplicador baseado na fase da vida e condição.
     * Esses valores são baseados em tabelas nutricionais veterinárias (ex: FEDIAF/NRC).
     */
    private double obterFatorMER(Pet pet) {
        if (pet.getFaseVida() == null) return 1.0; // Padrão seguro

        String fase = pet.getFaseVida().toLowerCase();
        // Tratamento de nulo para condição de saúde
        String condicao = (pet.getCondicaoSaude() != null) ? pet.getCondicaoSaude().toLowerCase() : "";
        String especie = (pet.getEspecie() != null) ? pet.getEspecie().toString().toLowerCase() : "";

        // Lógica simplificada para CÃES (pode ser expandida para Gatos depois)
        if (especie.equals("canino") || especie.contains("cão")) {
            
            // Filhotes (Crescimento exige muita energia)
            if (fase.contains("filhote") || fase.contains("crescimento")) {
                if (fase.contains("menos de 4 meses")) return 3.0;
                return 2.0; 
            }

            // Adultos
            if (condicao.contains("castrado")) return 1.6; // Castrados precisam de menos caloria
            if (condicao.contains("obeso")) return 1.0;    // Obesos: Dieta restritiva (apenas o RER ou pouco mais)
            if (condicao.contains("trabalho") || condicao.contains("ativo")) return 2.0; // Cães atletas
            
            return 1.8; // Adulto inteiro (não castrado) padrão
        }

        // Se for Gato (Exemplo rápido)
        if (especie.equals("felino") || especie.contains("gato")) {
            if (condicao.contains("castrado")) return 1.2;
            if (condicao.contains("obeso")) return 0.8; 
            return 1.4;
        }

        return 1.0; // Valor padrão se não identificar nada
    }
}

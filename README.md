# Projeto Dieta Caseira - Nutrição Veterinária

O **Projeto Dieta Caseira** é um sistema desenvolvido para auxiliar discentes e docentes do curso de Medicina Veterinária na elaboração, cálculo e análise de dietas caseiras para cães e gatos[cite: 8].

O objetivo principal é automatizar a complexidade matemática da formulação de dietas, garantindo segurança nutricional através da comparação automática com diretrizes internacionais.

## 🎯 Visão Geral do Produto
O software soluciona a dificuldade de balancear manualmente dietas naturais, permitindo:

* **Gestão de Pacientes:** Cadastro de perfis de pets considerando espécie, fase da vida (filhote, adulto, sênior) e condições de saúde específicas[cite: 14, 27].
* **Cálculo Energético Preciso:** Determinação automática da Necessidade Energética de Repouso (RER) e Manutenção (MER) baseada no peso metabólico e fatores de atividade[cite: 14, 28].
* **Formulação de Receitas:** Interface para criação de dietas diárias ou semanais, com adição dinâmica de ingredientes[cite: 14, 18].
* **Análise Nutricional Comparativa:** O sistema cruza os dados da receita com as tabelas **FEDIAF** e **AAFCO**, destacando visualmente deficiências ou excessos de nutrientes[cite: 14, 22, 23].
* **Sugestão de Ajustes:** Identificação automática de desbalanços e sugestão de correção via ingredientes ou suplementação[cite: 14, 25].
* **Relatórios Profissionais:** Geração de documentos distintos: um simplificado para o tutor (com modo de preparo) e um técnico para o veterinário (com perfil bromatológico completo)[cite: 14, 31].

## 👥 Público-Alvo
O sistema é projetado para o contexto acadêmico da **UESC (Universidade Estadual de Santa Cruz)**, atendendo:
1.  **Médicos Veterinários e Docentes:** Para uso clínico e educacional[cite: 8, 15].
2.  **Discentes:** Como ferramenta de aprendizado em nutrição animal[cite: 8].
3.  **Tutores:** Como beneficiários finais dos relatórios de dieta[cite: 15].

## 📚 Documentação
A documentação completa de engenharia de software encontra-se na pasta `/docs`:
* **DRF (Documento de Requisitos Funcionais):** Detalhamento de todas as regras de negócio e requisitos do sistema.
* **Casos de Uso:** Fluxos de interação dos atores (Veterinário, Administrador, Usuário).
* **Diagramas:** Modelagem UML do sistema.

## 🏗 Status do Desenvolvimento
Atualmente, o projeto encontra-se na fase de **Prototipação** (MVP), focando na implementação do núcleo de cálculo e cadastro base.

---
*Projeto interdisciplinar envolvendo Ciência da Computação e Medicina Veterinária.*

## ⚠️ Compilação - Nota sobre JDK

Se você obtiver um erro como "java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTag :: UNKNOWN" ao executar `mvn clean package`, isso normalmente significa que você está compilando com JDK 24 e Lombok (usado no projeto) não é compatível com essa versão do JDK.

Solução recomendada: instale e use JDK 21 (LTS) para compilar/executar o projeto.

Instalação e configuração (Windows):

- Instalar Temurin (OpenJDK 21) com winget (requer Windows Package Manager):
```powershell
winget install -e --id EclipseAdoptium.Temurin.21.JDK
```
- Verificar instalações de JDK na máquina (exemplos):
```powershell
# lista as pastas em Program Files\Java
Get-ChildItem 'C:\Program Files\Java' -Directory
# ou em Eclipse Adoptium
Get-ChildItem 'C:\Program Files\Eclipse Adoptium' -Directory
```
- Definir `JAVA_HOME` temporariamente nesta sessão (PowerShell):
```powershell
$Env:JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot'
$Env:Path = "$Env:JAVA_HOME\bin;$Env:Path"
java -version
javac -version
mvn -v
```
- Definir `JAVA_HOME` permanentemente (usuário) — abra um novo terminal após isso:
```cmd
setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
setx PATH "%JAVA_HOME%\bin;%PATH%"
```

Após definir `JAVA_HOME`, verifique as versões e faça build:
```powershell
java -version
javac -version
mvn -v
mvn clean package
```

Alternativa: se preferir manter JDK 24, atualize Lombok para uma versão que ofereça suporte a JDK 24 (se disponível), ou remova o uso de Lombok e gere getters/setters manualmente.
Se ao executar `mvn clean package` você encontrar erros relacionados ao Lombok (ex.: "java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTag :: UNKNOWN"), isso provavelmente está relacionado ao Lombok acessando APIs internas de `javac`. As opções são:

- Use JDK 21 (recomendado) conforme instruções acima.
- Tentar atualizar a versão do Lombok no `pom.xml` para uma versão com suporte a JDK 24, se houver.
- Remover Lombok e gerar manualmente os getters/setters, ou usar outra abordagem de processamento de anotações que não dependa de APIs internas do `javac`.

Comandos úteis para rodar o aplicativo (maven):
```powershell
# Executa a aplicação via JavaFX (plugin OpenJFX)
mvn javafx:run
# Ou executar via Spring Boot (aplicação Spring + JavaFX integrada)
mvn spring-boot:run
```

Para executar especificamente a classe `Launcher` (útil para testes locais), use o plugin `exec`. No PowerShell, envolva a propriedade em aspas para evitar problemas de parsing:

```powershell
mvn "-Dexec.mainClass=dietacaseira.Launcher" exec:java
```

Ou, alternativamente em CMD/PowerShell sem aspas (quando o shell não estiver interpretando os caracteres):

```powershell
mvn -Dexec.mainClass=dietacaseira.Launcher exec:java
```

Também é possível executar o JAR reempacotado do Spring Boot direto (após `mvn package`):

```powershell
java -jar target/dieta-caseira-1.0-SNAPSHOT.jar
```

# TaskManager

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21.0.2-4285F4?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![SQLite](https://img.shields.io/badge/SQLite-3.45.3.0-003B57?style=for-the-badge&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5.10.2-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)

Gerenciador de tarefas desenvolvido em Java para a Residência em TIC - IREDE.
O projeto reúne uma versão de terminal e uma interface gráfica JavaFX, com
persistência em SQLite, testes automatizados e exemplos de Programação
Orientada a Objetos.

## Escopo

O projeto foi organizado em duas formas de uso:

1. **Modo console:** fluxo simples para cadastrar, listar, concluir e remover
   tarefas em memória.
2. **Modo gráfico:** interface JavaFX com FXML e CSS para cadastrar, listar,
   editar, concluir e remover tarefas persistidas em SQLite.

Além das operações de tarefas, o código demonstra encapsulamento, enum,
herança, generics com curingas, tratamento de exceções e acesso seguro ao
banco de dados usando `PreparedStatement`.

## Funcionalidades

- Cadastro de tarefas com título e descrição.
- Listagem ordenada por identificador.
- Edição de tarefas pela interface gráfica.
- Conclusão de tarefas com controle de status.
- Remoção com confirmação na interface gráfica.
- Validação de entradas obrigatórias.
- Exceções específicas para regras de negócio.
- Persistência em SQLite com criação automática da tabela.
- Transações para salvar lotes de tarefas com `commit` e `rollback`.
- Testes de regras de negócio e persistência usando SQLite em memória.
- Compatibilidade entre execução pelo terminal, IntelliJ IDEA e NetBeans.

## Tecnologias e conceitos

| Tecnologia ou conceito | Aplicação no projeto |
| --- | --- |
| Java 17 | Linguagem principal e compilação do projeto |
| Maven | Dependências, testes e execução |
| JavaFX 21 | Interface gráfica |
| FXML e CSS | Estrutura e estilo da interface |
| SQLite | Banco de dados local |
| JDBC | Conexão e operações de persistência |
| `PreparedStatement` | Execução segura das instruções SQL |
| JUnit 5 | Testes automatizados |
| Generics | `TaskManager<T extends Tarefa>` e wildcards |
| MVC | Separação entre aplicação, controle, modelo e visualização |

## Arquitetura

```text
Usuário
   |
   +--> Menu / JavaFX
           |
           +--> Controllers
                   |
                   +--> Tarefa e regras de negócio
                   |
                   +--> TarefaRepository
                           |
                           +--> SQLite
```

## Estrutura do projeto

```text
taskManager/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/br/org/irede/taskmanager/
│   │   │   ├── app/
│   │   │   │   ├── Launcher.java
│   │   │   │   ├── Main.java
│   │   │   │   └── MainApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── TaskController.java
│   │   │   │   ├── TarefaFormDialog.java
│   │   │   │   └── TarefaFxController.java
│   │   │   ├── database/
│   │   │   │   ├── Conexao.java
│   │   │   │   └── DatabaseInitializer.java
│   │   │   ├── exception/
│   │   │   │   ├── EntradaInvalidaException.java
│   │   │   │   ├── TarefaJaConcluidaException.java
│   │   │   │   └── TarefaNaoEncontradaException.java
│   │   │   ├── model/
│   │   │   │   ├── StatusTarefa.java
│   │   │   │   ├── Tarefa.java
│   │   │   │   ├── TarefaPrioritaria.java
│   │   │   │   └── TaskManager.java
│   │   │   ├── repository/
│   │   │   │   └── TarefaRepository.java
│   │   │   ├── utils/
│   │   │   │   └── Formatador.java
│   │   │   └── view/
│   │   │       └── Menu.java
│   │   └── resources/view/
│   │       ├── estilo.css
│   │       └── principal.fxml
│   └── test/java/br/org/irede/taskmanager/
│       ├── model/TaskManagerTest.java
│       └── repository/TarefaRepositoryTest.java
└── taskmanager.db                         # criado na execução
```

## Pré-requisitos

- JDK 17 ou superior.
- Maven 3.x.
- IntelliJ IDEA, Apache NetBeans ou outra IDE compatível com Maven.

O projeto está configurado para JavaFX no Windows, com o classificador
`win` definido no `pom.xml`.

## Execução pelo terminal

### Interface gráfica

```bash
mvn clean javafx:run
```

Também é possível usar:

```bash
mvn clean compile exec:java
```

A classe principal da interface é:

```text
br.org.irede.taskmanager.app.Launcher
```

Ela inicializa o banco e abre a aplicação JavaFX.

### Modo console

```bash
mvn compile exec:java -Dexec.mainClass=br.org.irede.taskmanager.app.Main
```

O menu de terminal possui as opções de adicionar, listar, concluir, remover e
encerrar a aplicação.

## Execução no IntelliJ IDEA

1. Abra a pasta do projeto ou o arquivo `pom.xml`.
2. Aguarde o carregamento das dependências Maven.
3. Configure o Project SDK como Java 17 ou superior.
4. Para a interface, execute `Launcher.java`.
5. Para o console, execute `Main.java`.

Outra opção é abrir o terminal integrado e executar `mvn clean javafx:run`.

## Execução no NetBeans

1. Abra o projeto como projeto Maven.
2. Clique com o botão direito no projeto.
3. Selecione **Run Maven** ou **Custom Goals**.
4. Execute o objetivo:

```text
clean javafx:run
```

Para rodar os testes, use a opção **Test** do projeto ou execute `mvn test` no
terminal integrado.

## Testes automatizados

Os testes ficam em `src/test/java` e cobrem:

- inclusão e consulta de tarefas genéricas;
- aceitação de `TarefaPrioritaria`;
- conclusão única de uma tarefa;
- busca e remoção de identificadores inexistentes;
- uso de listas com wildcards;
- CRUD completo no SQLite;
- geração de identificadores pelo banco;
- commit e rollback de operações transacionais;
- cenário de planejamento de um churrasco com compras e pedido de comida.

Execute a suíte completa com:

```bash
mvn test
```

Os testes de persistência usam `jdbc:sqlite::memory:` e não alteram o banco
principal do projeto.

## Banco de dados

Na primeira execução da interface gráfica, o arquivo `taskmanager.db` é criado
na raiz do projeto. A tabela `tarefas` é inicializada automaticamente com os
campos `id`, `titulo`, `descricao` e `status`.

## Autor

**Marcos Rafael Alves**

Projeto acadêmico desenvolvido para a disciplina de Java da Residência em TIC
- IREDE.

## Licença

Projeto desenvolvido para fins acadêmicos e educacionais.

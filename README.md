# 📋 Task Manager

![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Maven-3.x-red)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-brightgreen)
![License](https://img.shields.io/badge/Licença-Educacional-blue)

Sistema de Gerenciamento de Tarefas desenvolvido em **Java**, utilizando os conceitos de **Programação Orientada a Objetos (POO)** e arquitetura **MVC (Model-View-Controller)**.

Este projeto foi desenvolvido como atividade prática da disciplina de **Java** da **Residência em TIC - IREDE**, tendo como objetivo consolidar os conhecimentos adquiridos durante o curso por meio do desenvolvimento de uma aplicação em modo console.

---

# 🎯 Objetivo

Desenvolver uma aplicação capaz de gerenciar tarefas através do terminal, permitindo ao usuário realizar operações de cadastro, consulta, atualização e remoção de tarefas.

Além das funcionalidades propostas, o projeto aplica conceitos importantes da linguagem Java, como:

- Programação Orientada a Objetos
- Encapsulamento
- Enum
- Collections
- Tratamento de Exceções
- Organização em Packages
- Arquitetura MVC

---

# 🚀 Funcionalidades

O sistema permite:

- ✅ Cadastrar tarefas
- ✅ Listar todas as tarefas
- ✅ Concluir tarefas
- ✅ Remover tarefas
- ✅ Validar entradas do usuário
- ✅ Tratar exceções personalizadas
- ✅ Interface em modo Console

---

# 💻 Tecnologias Utilizadas

| Tecnologia | Versão |
|------------|---------|
| Java | 17 |
| Maven | 3.x |
| Apache NetBeans | IDE oficial utilizada no curso |
| IntelliJ IDEA | Compatível |

---

# 📁 Estrutura do Projeto

```
taskManager
│
├── src
│   └── main
│       └── java
│           └── br
│               └── org
│                   └── irede
│                       └── taskmanager
│
│                           ├── app
│                           │     └── Main.java
│                           │
│                           ├── controller
│                           │     └── TaskController.java
│                           │
│                           ├── model
│                           │     ├── Tarefa.java
│                           │     └── StatusTarefa.java
│                           │
│                           ├── view
│                           │     └── Menu.java
│                           │
│                           ├── exception
│                           │     ├── EntradaInvalidaException.java
│                           │     ├── TarefaNaoEncontradaException.java
│                           │     └── TarefaJaConcluidaException.java
│                           │
│                           └── utils
│                                 └── Formatador.java
│
├── pom.xml
└── README.md
```

---

# 🏛 Arquitetura

O projeto foi desenvolvido seguindo a arquitetura **MVC (Model-View-Controller)**.

```
                 Usuário
                    │
                    ▼
              Menu (View)
                    │
                    ▼
      TaskController (Controller)
                    │
                    ▼
            Tarefa (Model)
```

## Model

Responsável pelos dados da aplicação.

Classes:

- Tarefa
- StatusTarefa

---

## View

Responsável pela interação com o usuário.

Classe:

- Menu

---

## Controller

Responsável por toda a lógica do sistema.

Classe:

- TaskController

---

## Exception

Responsável pelo tratamento das exceções personalizadas.

Classes:

- EntradaInvalidaException
- TarefaNaoEncontradaException
- TarefaJaConcluidaException

---

## Utils

Responsável pelos métodos auxiliares.

Classe:

- Formatador

---

# ▶ Como Executar

## Clonar o projeto

```bash
git clone https://github.com/seu-usuario/taskManager.git
```

ou apenas abra o projeto diretamente no Apache NetBeans ou IntelliJ IDEA.

---

## Compilar

```bash
mvn clean compile
```

---

## Executar

```bash
mvn exec:java
```

ou

```bash
mvn clean compile exec:java
```

Também é possível executar diretamente a classe:

```
Main.java
```

---

# 🖥 Exemplo Completo de Utilização

Ao iniciar a aplicação, será apresentado o menu principal.

```
========================================
           TASK MANAGER
========================================

1 - Adicionar tarefa
2 - Listar tarefas
3 - Concluir tarefa
4 - Remover tarefa
0 - Sair

Escolha uma opção:
```

---

## 1️⃣ Adicionar uma tarefa

```
Escolha uma opção: 1

========================================
          NOVA TAREFA
========================================

Digite o título:

Estudar Java

Digite a descrição:

Revisar Programação Orientada a Objetos.

Tarefa cadastrada com sucesso!

ID da tarefa: 1
```

---

## 2️⃣ Adicionar uma segunda tarefa

```
Escolha uma opção: 1

========================================
          NOVA TAREFA
========================================

Digite o título:

Desenvolver Task Manager

Digite a descrição:

Implementar o projeto final da disciplina.

Tarefa cadastrada com sucesso!

ID da tarefa: 2
```

---

## 3️⃣ Listar tarefas

```
Escolha uma opção: 2

========================================
        LISTA DE TAREFAS
========================================

ID: 1

Título: Estudar Java

Descrição: Revisar Programação Orientada a Objetos.

Status: Pendente

----------------------------------------

ID: 2

Título: Desenvolver Task Manager

Descrição: Implementar o projeto final da disciplina.

Status: Pendente
```

---

## 4️⃣ Concluir uma tarefa

```
Escolha uma opção: 3

========================================
       CONCLUIR TAREFA
========================================

Digite o ID da tarefa:

1

Tarefa concluída com sucesso!
```

---

## 5️⃣ Listar novamente

```
Escolha uma opção: 2

========================================
        LISTA DE TAREFAS
========================================

ID: 1

Título: Estudar Java

Descrição: Revisar Programação Orientada a Objetos.

Status: Concluída

----------------------------------------

ID: 2

Título: Desenvolver Task Manager

Descrição: Implementar o projeto final da disciplina.

Status: Pendente
```

---

## 6️⃣ Remover uma tarefa

```
Escolha uma opção: 4

========================================
        REMOVER TAREFA
========================================

Digite o ID da tarefa:

2

Tarefa removida com sucesso!
```

---

## 7️⃣ Listagem após remoção

```
Escolha uma opção: 2

========================================
        LISTA DE TAREFAS
========================================

ID: 1

Título: Estudar Java

Descrição: Revisar Programação Orientada a Objetos.

Status: Concluída
```

---

## 8️⃣ Exemplo de erro

Tentando concluir uma tarefa inexistente.

```
Escolha uma opção: 3

Digite o ID da tarefa:

10

Erro: Nenhuma tarefa foi encontrada com o ID 10.
```

---

## 9️⃣ Exemplo de validação

Tentando cadastrar uma tarefa sem informar o título.

```
Escolha uma opção: 1

Digite o título:


Digite a descrição:

Teste

Erro: O título da tarefa não pode ficar vazio.
```

---

## 🔟 Encerrando a aplicação

```
Escolha uma opção: 0

Task Manager encerrado.

Obrigado por utilizar o sistema!
```

---

# 🔄 Fluxo da Aplicação

```
Início
   │
   ▼
Menu Principal
   │
   ├──────────────► Adicionar Tarefa
   │
   ├──────────────► Listar Tarefas
   │
   ├──────────────► Concluir Tarefa
   │
   ├──────────────► Remover Tarefa
   │
   └──────────────► Encerrar Sistema
```

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento foram utilizados os seguintes conceitos:

## Programação Orientada a Objetos

- Classes
- Objetos
- Encapsulamento
- Construtores
- Métodos
- Sobrescrita de métodos (`toString()`)

---

## Organização em Pacotes

Separação da aplicação em:

- app
- controller
- model
- view
- exception
- utils

---

## Collections

Utilização da classe:

```
ArrayList
```

para armazenamento das tarefas em memória.

---

## Enum

Utilização da enumeração:

```
StatusTarefa
```

Estados disponíveis:

- PENDENTE
- CONCLUIDA

---

## Tratamento de Exceções

Foram implementadas exceções personalizadas para melhorar o controle dos erros da aplicação.

- EntradaInvalidaException
- TarefaNaoEncontradaException
- TarefaJaConcluidaException

---

# 📌 Funcionamento Interno

A aplicação segue o seguinte fluxo:

```
Usuário

↓

Menu

↓

TaskController

↓

Tarefa

↓

Resposta ao usuário
```

O modo console utiliza uma lista em memória (`ArrayList<Tarefa>`). A interface
JavaFX utiliza o `TarefaRepository` para persistir as tarefas em SQLite por
meio de JDBC.

---

# 🔮 Melhorias Futuras

Este projeto poderá ser expandido com novas funcionalidades, como:

- Salvamento em arquivos
- Pesquisa por título
- Pesquisa por status
- Prioridade das tarefas
- Datas de vencimento
- API REST utilizando Spring Boot
- Autenticação de usuários
- Relatórios de tarefas

---

# 👨‍💻 Autor

**MARCOS RAFAEL ALVES**

Análista de Sistemas | Pós-Graduando em Testes de Software | Análise de Testes de Software Júníor

Projeto desenvolvido para a disciplina de **Java** da **Residência em - IREDE**.

---

# 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos e educacionais.

Sua utilização é livre para estudos e aperfeiçoamento da linguagem Java.

---

⭐ Caso este projeto tenha sido útil para seus estudos, deixe uma estrela no repositório!

---

# Versão Intermediária

Esta versão também contém a evolução solicitada no módulo intermediário:

- `TaskManager<T extends Tarefa>` com `?`, `? extends T` e `? super T`;
- interface gráfica JavaFX com FXML e CSS;
- persistência SQLite usando JDBC e `PreparedStatement`;
- testes automatizados com JUnit 5;
- `TarefaPrioritaria` como exemplo de subclasse aceita pelo Generic.

O projeto mantém os dois modos de execução: `Main.java` para o fluxo em
console e `Launcher.java` para a interface JavaFX. A aplicação gráfica permite
cadastrar, listar, editar, concluir e remover tarefas persistidas no banco.

## Executar a interface gráfica

```bash
mvn clean javafx:run
```

Ou:

```bash
mvn clean compile exec:java
```

O banco `taskmanager.db` e a tabela `tarefas` são criados automaticamente na
primeira execução.

## Executar os testes

```bash
mvn test
```

# 📋 Task Manager

Sistema de Gerenciamento de Tarefas desenvolvido em **Java** utilizando **Programação Orientada a Objetos (POO)** e arquitetura **MVC (Model-View-Controller)**.

Este projeto foi desenvolvido como atividade prática da disciplina de Java da **Residência em TIC - IREDE**, com o objetivo de aplicar os principais conceitos estudados durante o curso.

---

# 🎯 Objetivo

Desenvolver uma aplicação em modo console capaz de gerenciar tarefas, permitindo ao usuário:

- Cadastrar tarefas
- Listar tarefas
- Marcar tarefas como concluídas
- Remover tarefas

Além das funcionalidades, o projeto busca aplicar conceitos de:

- Programação Orientada a Objetos
- Encapsulamento
- Organização em pacotes (Packages)
- Tratamento de Exceções
- Collections
- Enum
- Arquitetura MVC

---

# 🚀 Funcionalidades

✔ Adicionar tarefa

✔ Listar todas as tarefas

✔ Concluir tarefa

✔ Remover tarefa

✔ Validação de entradas

✔ Tratamento de exceções

✔ Interface interativa em console

---

# 💻 Tecnologias Utilizadas

| Tecnologia | Versão |
|------------|---------|
| Java | 17 |
| Maven | 3.x |
| Apache NetBeans | IDE utilizada no desenvolvimento |
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
│                           ├── exception
│                           │     ├── EntradaInvalidaException.java
│                           │     ├── TarefaNaoEncontradaException.java
│                           │     └── TarefaJaConcluidaException.java
│                           │
│                           ├── utils
│                           │     └── Formatador.java
│                           │
│                           └── view
│                                 └── Menu.java
│
├── pom.xml
└── README.md
```

---

# 🏛 Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)**.

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

### Model

Representa os dados da aplicação.

Classes:

- Tarefa
- StatusTarefa

---

### View

Responsável pela interação com o usuário através do console.

Classe:

- Menu

---

### Controller

Responsável por controlar toda a lógica do sistema.

Classe:

- TaskController

---

### Exceptions

Responsável pelo tratamento das exceções personalizadas.

Classes:

- EntradaInvalidaException
- TarefaNaoEncontradaException
- TarefaJaConcluidaException

---

### Utils

Possui métodos auxiliares utilizados durante a execução.

Classe:

- Formatador

---

# ▶ Como Executar

Clone ou abra o projeto no Apache NetBeans ou IntelliJ IDEA.

### Compilar

```bash
mvn clean compile
```

### Executar

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

# 🖥 Exemplo de Execução

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

## Cadastro de tarefa

```
Digite o título:

Estudar Java

Digite a descrição:

Revisar Programação Orientada a Objetos

Tarefa cadastrada com sucesso.
```

---

## Listagem

```
ID: 1

Título: Estudar Java

Descrição: Revisar Programação Orientada a Objetos

Status: Pendente
```

---

## Conclusão

```
Digite o ID:

1

Tarefa concluída com sucesso.
```

---

# 📚 Conceitos de Programação Aplicados

Durante o desenvolvimento foram aplicados os seguintes conceitos:

## Programação Orientada a Objetos

- Classes
- Objetos
- Encapsulamento
- Métodos
- Construtores

---

## Organização em Pacotes

Separação da aplicação em:

- app
- controller
- model
- view
- utils
- exception

---

## Enum

Utilização da enumeração:

```
StatusTarefa
```

com os estados:

- PENDENTE
- CONCLUIDA

---

## Collections

Utilização de:

```
ArrayList
```

para armazenamento das tarefas em memória.

---

## Tratamento de Exceções

Exceções personalizadas:

- EntradaInvalidaException
- TarefaNaoEncontradaException
- TarefaJaConcluidaException

---

# 📌 Funcionamento

O usuário interage com o sistema através do Menu.

O Menu envia as solicitações para o Controller.

O Controller realiza as operações utilizando os objetos da camada Model.

Quando necessário, exceções personalizadas são lançadas para informar situações de erro.

Todo o processamento ocorre em memória utilizando uma lista de tarefas.

---

# 🔮 Melhorias Futuras

O projeto poderá ser expandido com:

- Persistência em banco de dados
- Salvamento em arquivo
- Edição de tarefas
- Prioridade das tarefas
- Datas de vencimento
- Pesquisa por título
- Interface gráfica JavaFX
- API REST com Spring Boot

---

# 👨‍💻 Autor

**Gustavo Affonso Bovo**

Projeto desenvolvido para a disciplina de Java da **Residência em TIC - IREDE**.

---

# 📄 Licença

Projeto desenvolvido exclusivamente para fins acadêmicos e de aprendizagem.

# Task Manager - Sistema de Gerenciamento de Tarefas

## Visão Geral

**Task Manager** é uma aplicação console desenvolvida em **Java** que implementa um sistema de gerenciamento de tarefas com suporte a criação, listagem, conclusão e remoção de tarefas. O projeto foi desenvolvido para demonstrar conceitos fundamentais de orientação a objetos, tratamento de exceções e design patterns em Java.

**Disciplina:** IREDE - Java  
**Instituição:** Instituição de Educação  
**Versão:** 1.0-SNAPSHOT

---

## Funcionalidades

### ✅ Funcionalidades Principais

1. **Adicionar Tarefa**
   - Cria nova tarefa com título e descrição
   - Atribui ID único automaticamente
   - Status padrão: Pendente

2. **Listar Tarefas**
   - Exibe todas as tarefas cadastradas
   - Mostra ID, título, descrição e status
   - Formatação clara e legível

3. **Concluir Tarefa**
   - Marca tarefa como concluída pelo ID
   - Validação de ID existente
   - Exceção customizada para tarefas já concluídas

4. **Remover Tarefa**
   - Remove tarefa pelo ID
   - Validação de existência

5. **Menu Interativo**
   - Interface amigável baseada em console
   - Opções numéricas para navegação
   - Mensagens de feedback ao usuário

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| Java | 21 LTS | Linguagem de programação |
| Maven | 3.x | Gerenciador de dependências e build |
| JDK | 21+ | Java Development Kit |

---

## Requisitos

### Requisitos de Sistema
- **Java Development Kit (JDK):** 21 ou superior
- **Maven:** 3.6.0 ou superior
- **Sistema Operacional:** Windows, macOS ou Linux

### Verificar Instalação
```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version
```

---

## Estrutura do Projeto

```
taskManager/
├── src/
│   └── main/
│       └── java/
│           └── br/org/irede/taskmanager/
│               ├── app/
│               │   └── Main.java                 # Classe de entrada
│               ├── model/
│               │   ├── Tarefa.java              # Modelo de tarefa
│               │   └── StatusTarefa.java        # Enum de status
│               ├── controller/
│               │   └── TaskController.java      # Lógica de negócio
│               ├── view/
│               │   └── Menu.java                # Interface com usuário
│               ├── exception/
│               │   ├── EntradaInvalidaException.java
│               │   ├── TarefaJaConcluidaException.java
│               └── utils/
│                   └── Formatador.java          # Utilitários de formatação
├── pom.xml                                      # Configuração Maven
└── README.md                                    # Este arquivo
```

---

## Como Compilar e Executar

### 1. Clonar ou Baixar o Projeto
```bash
cd taskManager
```

### 2. Compilar o Projeto
```bash
mvn clean compile
```

### 3. Executar o Projeto
```bash
mvn exec:java
```

### Ou compilar e executar em um único comando
```bash
mvn clean compile exec:java
```

---

## Guia de Uso

### Menu Principal
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

### Exemplo de Uso

#### 1. Adicionar Tarefa
```
Escolha uma opção: 1

========================================
          NOVA TAREFA
========================================
Digite o título: Fazer coxinhas
Digite a descrição: Criando o melhor salgado do mundo

Tarefa cadastrada com sucesso. ID: 1
```

#### 2. Listar Tarefas
```
Escolha uma opção: 2

========================================
          LISTA DE TAREFAS
========================================

ID: 1
Título: Fazer coxinhas
Descrição: Criando o melhor salgado do mundo
Status: Pendente
```

#### 3. Concluir Tarefa
```
Escolha uma opção: 3

========================================
          CONCLUIR TAREFA
========================================
Digite o ID da tarefa: 1

Tarefa concluída com sucesso.
```

#### 4. Remover Tarefa
```
Escolha uma opção: 4

========================================
          REMOVER TAREFA
========================================
Digite o ID da tarefa: 1

Tarefa removida com sucesso.
```

---

## Conceitos de Java Implementados

### 1. **Orientação a Objetos**
- Classes e objetos
- Encapsulamento (getters/setters)
- Herança (exceções customizadas)
- Polimorfismo

### 2. **Tratamento de Exceções**
- Exceções customizadas (`EntradaInvalidaException`, `TarefaJaConcluidaException`, `TarefaNaoEncontradaException`)
- Try-catch para captura de erros
- Mensagens de erro significativas

### 3. **Collections**
- ArrayList para armazenamento de tarefas
- Iteração com enhanced for loop

### 4. **Enum**
- `StatusTarefa` com valores: PENDENTE, CONCLUIDA

### 5. **Design Patterns**
- **MVC (Model-View-Controller):** Separação de responsabilidades
- **DAO (Data Access Object):** Persistência de dados
- **Singleton:** Instância única do controller

### 6. **Boas Práticas**
- Nomes descritivos em português
- Documentação de classes
- Formatação clara da saída
- Validação de entrada

---

## Arquitetura da Aplicação

```
┌─────────────────────┐
│   Main.java         │  ← Ponto de entrada
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   Menu.java         │  ← Interface com usuário
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ TaskController.java │  ← Lógica de negócio
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   Tarefa.java       │  ← Modelo de dados
│ StatusTarefa.java   │
└─────────────────────┘
```

---

## Tratamento de Exceções

### Exceções Customizadas

#### 1. `EntradaInvalidaException`
Lançada quando a entrada do usuário é inválida ou fora do esperado.
```java
throw new EntradaInvalidaException("Opção inválida. Tente novamente.");
```

#### 2. `TarefaJaConcluidaException`
Lançada quando tenta-se concluir uma tarefa já concluída.
```java
throw new TarefaJaConcluidaException(id);
```

#### 3. `TarefaNaoEncontradaException`
Lançada quando a tarefa solicitada não existe.
```java
throw new TarefaNaoEncontradaException(id);
```

---

## Estrutura de Classes

### Classe `Tarefa`
```java
public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    
    // Construtores, getters, setters...
}
```

### Enum `StatusTarefa`
```java
public enum StatusTarefa {
    PENDENTE("Pendente"),
    CONCLUIDA("Concluída");
}
```

### Classe `TaskController`
Gerencia operações CRUD das tarefas:
- `adicionarTarefa(String titulo, String descricao)`
- `listarTarefas()`
- `concluirTarefa(int id)`
- `removerTarefa(int id)`

---

## Dados Persistidos em Memória

As tarefas são armazenadas em uma `ArrayList` durante a execução. Ao fechar a aplicação, os dados não são salvos. Para persistência permanente, seria necessário integrar:
- Banco de dados (MySQL, PostgreSQL)
- Arquivo (JSON, XML, CSV)
- API REST

---

## Possíveis Melhorias Futuras

- [ ] Persistência em banco de dados
- [ ] Edição de tarefas existentes
- [ ] Filtros (por status, data, etc.)
- [ ] Prioridades de tarefas
- [ ] Datas de vencimento
- [ ] API REST com Spring Boot
- [ ] Interface gráfica (Swing/JavaFX)
- [ ] Autenticação de usuários
- [ ] Testes unitários (JUnit)

---

## Testes

Para adicionar testes ao projeto, você pode usar:

### Maven + JUnit
```bash
mvn test
```

### Exemplo de Teste Unitário
```java
@Test
public void testAdicionarTarefa() {
    TaskController controller = new TaskController();
    controller.adicionarTarefa("Teste", "Descrição teste");
    assertEquals(1, controller.listarTarefas().size());
}
```

---

## Troubleshooting

### Problema: "release version 21 not supported"
**Solução:** Instale JDK 21 LTS ou superior

### Problema: "Maven not found"
**Solução:** Configure a variável de ambiente `PATH` para incluir o bin do Maven

### Problema: Caracteres especiais não aparecem corretamente
**Solução:** Verifique o encoding do console (UTF-8 recomendado)

---

## Autor

Desenvolvido para a disciplina de Java - IREDE

---

## Licença

Este projeto é fornecido para fins educacionais.

---

## Contribuições

Sugestões e melhorias são bem-vindas! Entre em contato com o instrutor para propostas de melhoria.

---

## Referências Bibliográficas

- **Oracle Java Documentation:** https://docs.oracle.com/en/java/
- **Maven Documentation:** https://maven.apache.org/
- **Clean Code:** Robert C. Martin
- **Effective Java:** Joshua Bloch

---

**Última atualização:** Julho de 2026  
**Versão:** 1.0
# taskManagerIrede

package br.org.irede.taskmanager.repository;

import br.org.irede.taskmanager.database.Conexao;
import br.org.irede.taskmanager.database.DatabaseInitializer;
import br.org.irede.taskmanager.model.Tarefa;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TarefaRepositoryTest {

    @Test
    void deveExecutarCrudComSQLiteEmMemoria() throws Exception {
        try (Connection conexao = Conexao.conectarTeste()) {
            DatabaseInitializer.inicializar(conexao);
            TarefaRepository repository = new TarefaRepository(conexao);
            Tarefa tarefa = new Tarefa(0, "Estudar", "JDBC");

            repository.salvar(tarefa);

            assertTrue(tarefa.getId() > 0);
            assertEquals(1, repository.listar().size());

            tarefa.editar("Estudar Java", "JDBC e Generics");
            repository.atualizar(tarefa);
            assertEquals(
                    "Estudar Java",
                    repository.listar().get(0).getTitulo()
            );

            repository.excluir(tarefa.getId());
            assertTrue(repository.listar().isEmpty());
        }
    }
}

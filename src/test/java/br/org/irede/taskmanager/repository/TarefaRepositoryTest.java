package br.org.irede.taskmanager.repository;

import br.org.irede.taskmanager.database.Conexao;
import br.org.irede.taskmanager.database.DatabaseInitializer;
import br.org.irede.taskmanager.model.Tarefa;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void deveConfirmarOuDesfazerLoteEmUmaTransacao() throws Exception {
        try (Connection conexao = Conexao.conectarTeste()) {
            DatabaseInitializer.inicializar(conexao);
            TarefaRepository repository = new TarefaRepository(conexao);

            repository.salvarComTransacao(List.of(
                    new Tarefa(0, "Primeira tarefa", "Descrição"),
                    new Tarefa(0, "Segunda tarefa", "Descrição")
            ));

            assertEquals(2, repository.listar().size());
        }

        try (Connection conexao = Conexao.conectarTeste()) {
            DatabaseInitializer.inicializar(conexao);
            TarefaRepository repository = new TarefaRepository(conexao);
            Tarefa tarefaInvalida = new Tarefa(0, null, "Descrição");

            assertThrows(
                    SQLException.class,
                    () -> repository.salvarComTransacao(List.of(
                            new Tarefa(0, "Tarefa válida", "Descrição"),
                            tarefaInvalida
                    ))
            );

            assertTrue(repository.listar().isEmpty());
        }
    }
}

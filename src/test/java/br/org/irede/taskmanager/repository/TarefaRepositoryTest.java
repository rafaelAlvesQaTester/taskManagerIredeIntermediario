package br.org.irede.taskmanager.repository;

import br.org.irede.taskmanager.database.Conexao;
import br.org.irede.taskmanager.database.DatabaseInitializer;
import br.org.irede.taskmanager.model.Tarefa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TarefaRepositoryTest {

    private Connection conexao;
    private TarefaRepository repository;

    @BeforeEach
    void prepararBanco() throws SQLException {
        conexao = Conexao.conectarTeste();
        DatabaseInitializer.inicializar(conexao);
        repository = new TarefaRepository(conexao);
    }

    @AfterEach
    void fecharBanco() throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }

    @Test
    void deveExecutarCrudComSQLiteEmMemoria() throws Exception {
        Tarefa tarefa = new Tarefa(0, "Estudar", "JDBC");

        repository.salvar(tarefa);

        assertTrue(tarefa.getId() > 0);
        assertEquals(1, repository.listar().size());

        tarefa.editar("Estudar Java", "JDBC e Generics");
        repository.atualizar(tarefa);
        assertEquals("Estudar Java", repository.listar().get(0).getTitulo());

        repository.excluir(tarefa.getId());
        assertTrue(repository.listar().isEmpty());
    }

    @Test
    void deveConfirmarLoteEmUmaTransacao() throws Exception {
        repository.salvarComTransacao(List.of(
                new Tarefa(0, "Primeira tarefa", "Descrição"),
                new Tarefa(0, "Segunda tarefa", "Descrição")
        ));

        assertEquals(2, repository.listar().size());
    }

    @Test
    void deveDesfazerTodaATransacaoQuandoUmaTarefaFalha() throws Exception {
        Tarefa tarefaInvalida = new Tarefa(0, null, "Descrição");

        assertThrows(SQLException.class, () -> repository.salvarComTransacao(
                List.of(
                        new Tarefa(0, "Tarefa válida", "Descrição"),
                        tarefaInvalida
                )
        ));

        assertTrue(repository.listar().isEmpty());
        assertTrue(conexao.getAutoCommit());
    }

    @Test
    void devePersistirStatusConcluido() throws Exception {
        Tarefa tarefa = new Tarefa(0, "Estudar", "JUnit");
        repository.salvar(tarefa);
        tarefa.concluir();
        repository.atualizar(tarefa);

        assertTrue(repository.listar().get(0).isConcluida());
    }

    @Test
    void deveArmazenarTextoSemExecutaLoComoSql() throws Exception {
        String titulo = "Tarefa'); DROP TABLE tarefas; --";
        repository.salvar(new Tarefa(0, titulo, "Descrição"));

        assertEquals(titulo, repository.listar().get(0).getTitulo());
        assertEquals(1, repository.listar().size());
    }
}
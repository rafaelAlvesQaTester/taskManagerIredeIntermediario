package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.database.Conexao;
import br.org.irede.taskmanager.database.DatabaseInitializer;
import br.org.irede.taskmanager.model.Tarefa;
import br.org.irede.taskmanager.repository.TarefaRepository;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TarefaFxControllerTest {

    @Test
    void deveManterEstadoPendenteQuandoAtualizacaoFalha() throws Exception {
        try (Connection conexao = Conexao.conectarTeste()) {
            DatabaseInitializer.inicializar(conexao);
            Tarefa tarefaExibida = new Tarefa(1, "Estudar", "JDBC");
            TarefaRepository repository = new TarefaRepository(conexao);
            TarefaFxController controller = new TarefaFxController(repository);

            conexao.close();

            assertThrows(
                    SQLException.class,
                    () -> controller.salvarConclusao(tarefaExibida)
            );
            assertFalse(tarefaExibida.isConcluida());
        }
    }
}
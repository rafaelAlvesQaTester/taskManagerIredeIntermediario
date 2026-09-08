package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TarefaTest {

    @Test
    void deveCriarTarefaComStatusPendente() {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        assertEquals(1, tarefa.getId());
        assertEquals("Estudar Java", tarefa.getTitulo());
        assertEquals("Estudar JUnit", tarefa.getDescricao());
        assertFalse(tarefa.isConcluida());
    }

    @Test
    void deveAlterarDadosDaTarefa() {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.editar(
                "Estudar JavaFX",
                "Estudar interface gráfica"
        );
        tarefa.setConcluida(true);

        assertEquals("Estudar JavaFX", tarefa.getTitulo());
        assertEquals("Estudar interface gráfica", tarefa.getDescricao());
        assertTrue(tarefa.isConcluida());
    }

    @Test
    void deveAlterarIdDaTarefa() {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.setId(10);

        assertEquals(10, tarefa.getId());
    }

    @Test
    void deveRepresentarTarefaPendenteComoTexto() {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        String resultado = tarefa.toString();

        assertTrue(resultado.contains("Estudar Java"));
        assertTrue(resultado.contains("Estudar JUnit"));
        assertTrue(resultado.contains("Pendente"));
    }

    @Test
    void deveRepresentarTarefaConcluidaComoTexto() {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.setConcluida(true);

        assertTrue(tarefa.toString().contains("Concluída"));
    }

    @Test
    void deveConcluirTarefaUmaUnicaVez() throws Exception {
        Tarefa tarefa = new Tarefa(
                1,
                "Estudar Java",
                "Estudar JUnit"
        );

        tarefa.concluir();

        assertTrue(tarefa.isConcluida());
        assertThrows(
                TarefaJaConcluidaException.class,
                tarefa::concluir
        );
    }
}

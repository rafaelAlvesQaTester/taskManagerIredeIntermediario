package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TarefaTest {

    @Test
    void deveSalvarTituloDescricaoETarefaConcluida() {
        Tarefa tarefa = new Tarefa(1, "Fazer churrasco", "Comprar carnes");
        tarefa.setConcluida(true);

        assertDoesNotThrow(tarefa::validar);
        assertEquals(StatusTarefa.CONCLUIDA, tarefa.getStatus());
    }

    @Test
    void deveAdvertirQuandoTituloEstiverVazioComDescricaoEConcluida() {
        Tarefa tarefa = new Tarefa(1, "", "Comprar carnes");
        tarefa.setConcluida(true);

        EntradaInvalidaException exception = assertThrows(
                EntradaInvalidaException.class,
                tarefa::validar
        );

        assertEquals("Informe o titulo da tarefa.", exception.getMessage());
    }

    @Test
    void deveAdvertirQuandoTituloEDescricaoEstiveremVaziosEConcluida() {
        Tarefa tarefa = new Tarefa(1, "", "");
        tarefa.setConcluida(true);

        EntradaInvalidaException exception = assertThrows(
                EntradaInvalidaException.class,
                tarefa::validar
        );

        assertEquals("Informe o titulo da tarefa.", exception.getMessage());
    }

    @Test
    void deveAdvertirQuandoTituloEDescricaoEstiveremVaziosEPendente() {
        Tarefa tarefa = new Tarefa(1, "", "");

        EntradaInvalidaException exception = assertThrows(
                EntradaInvalidaException.class,
                tarefa::validar
        );

        assertEquals("Informe o titulo da tarefa.", exception.getMessage());
        assertEquals(StatusTarefa.PENDENTE, tarefa.getStatus());
    }

    @Test
    void deveAceitarSomenteTituloComTarefaPendente() {
        Tarefa tarefa = new Tarefa(1, "Fazer churrasco", "");

        assertDoesNotThrow(tarefa::validar);
        assertEquals(StatusTarefa.PENDENTE, tarefa.getStatus());
    }

    @Test
    void deveAceitarTituloEDescricaoSemSelecionarConclusao() {
        Tarefa tarefa = new Tarefa(
                1,
                "Fazer churrasco",
                "Comprar carnes e bebidas"
        );

        assertDoesNotThrow(tarefa::validar);
        assertEquals(StatusTarefa.PENDENTE, tarefa.getStatus());
    }
}
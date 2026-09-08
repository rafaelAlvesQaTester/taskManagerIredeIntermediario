package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;
import br.org.irede.taskmanager.exception.TarefaNaoEncontradaException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskManagerTest {

    @Test
    void deveAdicionarEListarTarefasDoTipoGenerico() {
        TaskManager<Tarefa> manager = new TaskManager<>();
        Tarefa tarefa = new Tarefa(1, "Estudar", "Revisar Generics");

        manager.adicionarTarefa(tarefa);

        assertEquals(List.of(tarefa), manager.obterTarefas());
        assertEquals(1, TaskManager.quantidade(manager.obterTarefas()));
    }

    @Test
    void deveAceitarSubclasseDeTarefa() {
        TaskManager<TarefaPrioritaria> manager = new TaskManager<>();
        TarefaPrioritaria tarefa = new TarefaPrioritaria(
                1,
                "Entrega",
                "Finalizar projeto",
                "Alta"
        );

        manager.adicionarTarefa(tarefa);

        assertEquals("Alta", manager.obterTarefas().get(0).getPrioridade());
    }

    @Test
    void deveConcluirTarefaApenasUmaVez() throws Exception {
        TaskManager<Tarefa> manager = new TaskManager<>();
        manager.adicionarTarefa(new Tarefa(1, "Tarefa", "Descrição"));

        manager.concluirTarefa(1);

        assertThrows(
                TarefaJaConcluidaException.class,
                () -> manager.concluirTarefa(1)
        );
    }

    @Test
    void deveCopiarTarefasUsandoCuringas() {
        TaskManager<TarefaPrioritaria> manager = new TaskManager<>();
        TarefaPrioritaria tarefa = new TarefaPrioritaria(
                1,
                "Tarefa",
                "Descrição",
                "Alta"
        );
        List<TarefaPrioritaria> origem = List.of(tarefa);
        List<Tarefa> destino = new ArrayList<>();

        manager.copiarPara(destino, origem);

        assertEquals(List.of(tarefa), destino);
    }

    @Test
    void devePlanejarChurrascoComListaDeComprasEPedido() {
        TaskManager<Tarefa> manager = new TaskManager<>();

        Tarefa comprarMercado = new Tarefa(
                1,
                "Fazer churrasco",
                "Passar no mercado e comprar carnes, bebidas, carvão e descartáveis"
        );
        Tarefa pedirIfood = new Tarefa(
                2,
                "Pedir Ifood",
                "Pedir comida japonesa e da promoção"
        );

        manager.adicionarTarefa(comprarMercado);
        manager.adicionarTarefa(pedirIfood);

        assertEquals(2, manager.obterTarefas().size());
        assertEquals(
                "Passar no mercado e comprar carnes, bebidas, carvão e descartáveis",
                manager.obterTarefas().get(0).getDescricao()
        );
        assertEquals(
                "Pedir comida japonesa e da promoção",
                manager.obterTarefas().get(1).getDescricao()
        );
    }

    @Test
    void deveInformarQuandoIdNaoExiste() {
        TaskManager<Tarefa> manager = new TaskManager<>();

        assertThrows(
                TarefaNaoEncontradaException.class,
                () -> manager.removerTarefa(99)
        );
    }

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

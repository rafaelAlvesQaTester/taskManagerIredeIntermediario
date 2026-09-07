package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;
import br.org.irede.taskmanager.exception.TarefaNaoEncontradaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager<T extends Tarefa> {

    private final ArrayList<T> tarefas;

    public TaskManager() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(T tarefa) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        for (T tarefa : tarefas) {
            System.out.println(tarefa);
            System.out.println("----------------------------------------");
        }
    }

    public List<T> obterTarefas() {
        return Collections.unmodifiableList(new ArrayList<>(tarefas));
    }

    public T buscarTarefaPorId(int id)
            throws TarefaNaoEncontradaException {

        for (T tarefa : tarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }

        throw new TarefaNaoEncontradaException(id);
    }

    public void concluirTarefa(int id)
            throws TarefaNaoEncontradaException,
            TarefaJaConcluidaException {

        buscarTarefaPorId(id).concluir();
    }

    public void removerTarefa(int id)
            throws TarefaNaoEncontradaException {

        tarefas.remove(buscarTarefaPorId(id));
    }

    public void adicionarTodos(List<? extends T> novasTarefas) {
        tarefas.addAll(novasTarefas);
    }

    public static int quantidade(List<?> lista) {
        return lista.size();
    }

    public void copiarPara(
            List<? super T> destino,
            List<? extends T> origem
    ) {
        destino.addAll(origem);
    }
}

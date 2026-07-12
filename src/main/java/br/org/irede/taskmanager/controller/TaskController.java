package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;
import br.org.irede.taskmanager.exception.TarefaNaoEncontradaException;
import br.org.irede.taskmanager.model.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskController {

    private final List<Tarefa> tarefas;
    private int proximoId;

    public TaskController() {
        tarefas = new ArrayList<>();
        proximoId = 1;
    }

    public Tarefa adicionarTarefa(String titulo, String descricao)
            throws EntradaInvalidaException {

        validarTexto(titulo, "O título da tarefa não pode ficar vazio.");
        validarTexto(descricao, "A descrição da tarefa não pode ficar vazia.");

        Tarefa novaTarefa = new Tarefa(
                proximoId,
                titulo.trim(),
                descricao.trim()
        );

        tarefas.add(novaTarefa);
        proximoId++;

        return novaTarefa;
    }

    public List<Tarefa> listarTarefas() {
        return Collections.unmodifiableList(
                new ArrayList<>(tarefas)
        );
    }

    public Tarefa buscarTarefaPorId(int id)
            throws TarefaNaoEncontradaException {

        for (Tarefa tarefa : tarefas) {

            if (tarefa.getId() == id) {
                return tarefa;
            }
        }

        throw new TarefaNaoEncontradaException(id);
    }

    public void concluirTarefa(int id)
            throws TarefaNaoEncontradaException,
            TarefaJaConcluidaException {

        Tarefa tarefa = buscarTarefaPorId(id);
        tarefa.concluir();
    }

    public void removerTarefa(int id)
            throws TarefaNaoEncontradaException {

        Tarefa tarefa = buscarTarefaPorId(id);
        tarefas.remove(tarefa);
    }

    private void validarTexto(String texto, String mensagem)
            throws EntradaInvalidaException {

        if (texto == null || texto.trim().isEmpty()) {
            throw new EntradaInvalidaException(mensagem);
        }
    }
}
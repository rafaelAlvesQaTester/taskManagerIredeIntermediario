package br.org.irede.taskmanager.exception;

public class TarefaNaoEncontradaException extends Exception {

    public TarefaNaoEncontradaException(int id) {
        super("Tarefa com ID " + id + " não encontrada.");
    }
}
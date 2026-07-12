package br.org.irede.taskmanager.exception;

public class TarefaJaConcluidaException extends Exception {

    public TarefaJaConcluidaException(int id) {
        super("A tarefa de ID " + id + " já está concluída.");
    }
}

package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;

public class Tarefa {

    private final int id;
    private final String titulo;
    private final String descricao;
    private StatusTarefa status;

    public Tarefa(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = StatusTarefa.PENDENTE;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void concluir() throws TarefaJaConcluidaException {

        if (status == StatusTarefa.CONCLUIDA) {
            throw new TarefaJaConcluidaException(id);
        }

        status = StatusTarefa.CONCLUIDA;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
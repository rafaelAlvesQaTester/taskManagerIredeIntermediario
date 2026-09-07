package br.org.irede.taskmanager.model;

import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
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

    public void setId(int id) {
        this.id = id;
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

    public boolean isConcluida() {
        return status == StatusTarefa.CONCLUIDA;
    }

    public void setConcluida(boolean concluida) {
        status = concluida
                ? StatusTarefa.CONCLUIDA
                : StatusTarefa.PENDENTE;
    }

    public void editar(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void concluir() throws TarefaJaConcluidaException {

        if (status == StatusTarefa.CONCLUIDA) {
            throw new TarefaJaConcluidaException(id);
        }

        status = StatusTarefa.CONCLUIDA;
    }

    @Override
    public String toString() {
        String situacao;

        if (status == StatusTarefa.CONCLUIDA) {
            situacao = "Concluída";
        } else {
            situacao = "Pendente";
        }

        return "ID: " + id +
                "\nTítulo: " + titulo +
                "\nDescrição: " + descricao +
                "\nStatus: " + situacao;
    }
}
package br.org.irede.taskmanager.model;

public class TarefaPrioritaria extends Tarefa {

    private final String prioridade;

    public TarefaPrioritaria(
            int id,
            String titulo,
            String descricao,
            String prioridade
    ) {
        super(id, titulo, descricao);
        this.prioridade = prioridade;
    }

    public String getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPrioridade: " + prioridade;
    }
}

package br.org.irede.taskmanager.utils;

import br.org.irede.taskmanager.model.Tarefa;

public class Formatador {

    private Formatador() {
    }

    public static String criarCabecalho(String titulo) {

        return "\n========================================\n"
                + "          " + titulo + "\n"
                + "========================================";
    }

    public static String formatarTarefa(Tarefa tarefa) {

        return String.format(
                """
                
                ID: %d
                Título: %s
                Descrição: %s
                Status: %s
                ----------------------------------------
                """,
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus().getDescricao()
        );
    }
}
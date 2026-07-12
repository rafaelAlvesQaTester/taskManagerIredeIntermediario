package br.org.irede.taskmanager.view;

import br.org.irede.taskmanager.controller.TaskController;
import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import br.org.irede.taskmanager.exception.TarefaJaConcluidaException;
import br.org.irede.taskmanager.exception.TarefaNaoEncontradaException;
import br.org.irede.taskmanager.model.Tarefa;
import br.org.irede.taskmanager.utils.Formatador;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final TaskController controller;
    private final Scanner scanner;

    public Menu(TaskController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {

        boolean executando = true;

        while (executando) {

            mostrarOpcoes();

            try {

                int opcao = lerInteiro("Escolha uma opção: ");

                switch (opcao) {

                    case 1:
                        adicionarTarefa();
                        break;

                    case 2:
                        listarTarefas();
                        break;

                    case 3:
                        concluirTarefa();
                        break;

                    case 4:
                        removerTarefa();
                        break;

                    case 0:
                        executando = false;
                        System.out.println("\nTaskManager encerrado.");
                        break;

                    default:
                        System.out.println("\nOpção inválida.");
                }

            } catch (EntradaInvalidaException
                     | TarefaNaoEncontradaException
                     | TarefaJaConcluidaException exception) {

                System.out.println("\nErro: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private void mostrarOpcoes() {

        System.out.println(
                Formatador.criarCabecalho("TASK MANAGER")
        );

        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Concluir tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("0 - Sair");
    }

    private void adicionarTarefa()
            throws EntradaInvalidaException {

        System.out.println(
                Formatador.criarCabecalho("NOVA TAREFA")
        );

        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        Tarefa tarefa = controller.adicionarTarefa(
                titulo,
                descricao
        );

        System.out.println(
                "\nTarefa cadastrada com sucesso. ID: "
                        + tarefa.getId()
        );
    }

    private void listarTarefas() {

        System.out.println(
                Formatador.criarCabecalho("LISTA DE TAREFAS")
        );

        List<Tarefa> tarefas = controller.listarTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa cadastrada.");
            return;
        }

        for (Tarefa tarefa : tarefas) {
            System.out.println(
                    Formatador.formatarTarefa(tarefa)
            );
        }
    }

    private void concluirTarefa()
            throws EntradaInvalidaException,
            TarefaNaoEncontradaException,
            TarefaJaConcluidaException {

        System.out.println(
                Formatador.criarCabecalho("CONCLUIR TAREFA")
        );

        int id = lerInteiro("Digite o ID da tarefa: ");

        controller.concluirTarefa(id);

        System.out.println(
                "\nTarefa concluída com sucesso."
        );
    }

    private void removerTarefa()
            throws EntradaInvalidaException,
            TarefaNaoEncontradaException {

        System.out.println(
                Formatador.criarCabecalho("REMOVER TAREFA")
        );

        int id = lerInteiro("Digite o ID da tarefa: ");

        controller.removerTarefa(id);

        System.out.println(
                "\nTarefa removida com sucesso."
        );
    }

    private int lerInteiro(String mensagem)
            throws EntradaInvalidaException {

        System.out.print(mensagem);
        String entrada = scanner.nextLine();

        try {
            return Integer.parseInt(entrada);

        } catch (NumberFormatException exception) {

            throw new EntradaInvalidaException(
                    "Digite um número inteiro válido."
            );
        }
    }
}
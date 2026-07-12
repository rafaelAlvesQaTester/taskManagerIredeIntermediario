package br.org.irede.taskmanager.app;

import br.org.irede.taskmanager.controller.TaskController;
import br.org.irede.taskmanager.view.Menu;

public class Main {

    public static void main(String[] args) {

        TaskController controller = new TaskController();

        Menu menu = new Menu(controller);

        menu.exibir();
    }
}
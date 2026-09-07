package br.org.irede.taskmanager.app;

import br.org.irede.taskmanager.database.DatabaseInitializer;

import java.sql.SQLException;

public final class Launcher {

    private Launcher() {
    }

    public static void main(String[] args) {
        try {
            DatabaseInitializer.inicializar();
            MainApplication.main(args);
        } catch (SQLException exception) {
            System.err.println("Erro ao inicializar o banco de dados: "
                    + exception.getMessage());
        }
    }
}

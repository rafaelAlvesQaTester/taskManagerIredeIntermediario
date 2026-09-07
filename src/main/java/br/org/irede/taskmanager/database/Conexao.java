package br.org.irede.taskmanager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

    private static final String URL = "jdbc:sqlite:taskmanager.db";

    private Conexao() {
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static Connection conectarTeste() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }
}

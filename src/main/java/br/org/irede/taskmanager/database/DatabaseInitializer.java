package br.org.irede.taskmanager.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    public static void inicializar() throws SQLException {
        try (Connection conexao = Conexao.conectar()) {
            inicializar(conexao);
        }
    }

    public static void inicializar(Connection conexao) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS tarefas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    descricao TEXT NOT NULL,
                    status TEXT NOT NULL
                )
                """;

        try (Statement statement = conexao.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}

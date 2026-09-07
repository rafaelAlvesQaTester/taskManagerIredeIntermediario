package br.org.irede.taskmanager.repository;

import br.org.irede.taskmanager.database.Conexao;
import br.org.irede.taskmanager.model.StatusTarefa;
import br.org.irede.taskmanager.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

    private final Connection conexao;

    public TarefaRepository() throws SQLException {
        this(Conexao.conectar());
    }

    public TarefaRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Tarefa tarefa) throws SQLException {
        String sql = """
                INSERT INTO tarefas (titulo, descricao, status)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement statement = conexao.prepareStatement(
                sql,
                java.sql.Statement.RETURN_GENERATED_KEYS
        )) {
            preencher(statement, tarefa, 1);
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    tarefa.setId(keys.getInt(1));
                }
            }
        }
    }

    public void salvarComTransacao(List<? extends Tarefa> tarefas)
            throws SQLException {
        boolean autoCommitOriginal = conexao.getAutoCommit();
        conexao.setAutoCommit(false);

        try {
            for (Tarefa tarefa : tarefas) {
                salvar(tarefa);
            }
            conexao.commit();
        } catch (SQLException exception) {
            conexao.rollback();
            throw exception;
        } finally {
            conexao.setAutoCommit(autoCommitOriginal);
        }
    }

    public List<Tarefa> listar() throws SQLException {
        String sql = """
                SELECT id, titulo, descricao, status
                FROM tarefas
                ORDER BY id
                """;
        List<Tarefa> tarefas = new ArrayList<>();

        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tarefa tarefa = new Tarefa(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descricao")
                );
                tarefa.setConcluida(
                        StatusTarefa.CONCLUIDA.name().equals(
                                resultSet.getString("status")
                        )
                );
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    public void atualizar(Tarefa tarefa) throws SQLException {
        String sql = """
                UPDATE tarefas
                SET titulo = ?, descricao = ?, status = ?
                WHERE id = ?
                """;

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            preencher(statement, tarefa, 1);
            statement.setInt(4, tarefa.getId());
            statement.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private void preencher(
            PreparedStatement statement,
            Tarefa tarefa,
            int indiceInicial
    ) throws SQLException {
        statement.setString(indiceInicial, tarefa.getTitulo());
        statement.setString(indiceInicial + 1, tarefa.getDescricao());
        statement.setString(indiceInicial + 2, tarefa.getStatus().name());
    }
}

package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.model.Tarefa;
import br.org.irede.taskmanager.repository.TarefaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class TarefaFxController {

    @FXML
    private TableView<Tarefa> tabelaTarefas;

    @FXML
    private TableColumn<Tarefa, Number> colunaId;

    @FXML
    private TableColumn<Tarefa, String> colunaTitulo;

    @FXML
    private TableColumn<Tarefa, String> colunaDescricao;

    @FXML
    private TableColumn<Tarefa, Boolean> colunaConcluida;

    private final ObservableList<Tarefa> tarefas =
            FXCollections.observableArrayList();

    private TarefaRepository repository;

    @FXML
    public void initialize() {
        try {
            repository = new TarefaRepository();
            configurarTabela();
            carregarTarefas();
        } catch (SQLException exception) {
            mostrarErro("Erro ao abrir o banco de dados.", exception);
        }
    }

    private void configurarTabela() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );
        colunaConcluida.setCellValueFactory(
                new PropertyValueFactory<>("concluida")
        );
        tabelaTarefas.setItems(tarefas);
    }

    @FXML
    public void carregarTarefas() {
        if (repository == null) {
            return;
        }

        try {
            tarefas.setAll(repository.listar());
        } catch (SQLException exception) {
            mostrarErro("Erro ao carregar tarefas.", exception);
        }
    }

    @FXML
    private void novaTarefa() {
        abrirFormulario(null);
    }

    @FXML
    private void editarTarefa() {
        Tarefa selecionada = tabelaTarefas.getSelectionModel()
                .getSelectedItem();

        if (selecionada == null) {
            mostrarAviso("Selecione uma tarefa para editar.");
            return;
        }

        abrirFormulario(selecionada);
    }

    @FXML
    private void concluirTarefa() {
        Tarefa selecionada = tarefaSelecionada("Selecione uma tarefa.");
        if (selecionada == null) {
            return;
        }

        try {
            selecionada.concluir();
            repository.atualizar(selecionada);
            carregarTarefas();
        } catch (Exception exception) {
            mostrarErro("Erro ao concluir tarefa.", exception);
        }
    }

    @FXML
    private void removerTarefa() {
        Tarefa selecionada = tarefaSelecionada(
                "Selecione uma tarefa para remover."
        );
        if (selecionada == null) {
            return;
        }

        Alert confirmacao = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Deseja remover '" + selecionada.getTitulo() + "'?",
                ButtonType.CANCEL,
                ButtonType.OK
        );

        if (confirmacao.showAndWait().orElse(ButtonType.CANCEL)
                == ButtonType.OK) {
            try {
                repository.excluir(selecionada.getId());
                carregarTarefas();
            } catch (SQLException exception) {
                mostrarErro("Erro ao remover tarefa.", exception);
            }
        }
    }

    private Tarefa tarefaSelecionada(String mensagem) {
        Tarefa selecionada = tabelaTarefas.getSelectionModel()
                .getSelectedItem();
        if (selecionada == null) {
            mostrarAviso(mensagem);
        }
        return selecionada;
    }

    private void abrirFormulario(Tarefa tarefa) {
        TarefaFormDialog dialog = new TarefaFormDialog(tarefa);
        if (dialog.show()) {
            try {
                if (tarefa == null) {
                    repository.salvar(dialog.getTarefa());
                } else {
                    repository.atualizar(dialog.getTarefa());
                }
                carregarTarefas();
            } catch (SQLException exception) {
                mostrarErro("Erro ao salvar tarefa.", exception);
            }
        }
    }

    private void mostrarAviso(String mensagem) {
        new Alert(Alert.AlertType.WARNING, mensagem).showAndWait();
    }

    private void mostrarErro(String mensagem, Exception exception) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(mensagem);
        alerta.setContentText(exception.getMessage());
        alerta.showAndWait();
    }
}

package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import br.org.irede.taskmanager.model.Tarefa;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TarefaFormDialog {

    private final Stage stage = new Stage();
    private final TextField campoTitulo = new TextField();
    private final TextArea campoDescricao = new TextArea();
    private final CheckBox checkConcluida = new CheckBox("Tarefa concluída");
    private final Tarefa tarefa;
    private boolean salvo;

    public TarefaFormDialog(Tarefa tarefaExistente) {
        tarefa = tarefaExistente == null
                ? new Tarefa(0, "", "")
                : tarefaExistente;

        preencherCampos();
        configurarJanela();
    }

    public boolean show() {
        stage.showAndWait();
        return salvo;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    private void preencherCampos() {
        campoTitulo.setText(tarefa.getTitulo());
        campoDescricao.setText(tarefa.getDescricao());
        checkConcluida.setSelected(tarefa.isConcluida());
        campoDescricao.setPrefRowCount(5);
        campoDescricao.setWrapText(true);
    }

    private void configurarJanela() {
        Label titulo = new Label("Título:");
        Label descricao = new Label("Descrição:");
        GridPane campos = new GridPane();
        campos.setHgap(10);
        campos.setVgap(10);
        campos.add(titulo, 0, 0);
        campos.add(campoTitulo, 1, 0);
        campos.add(descricao, 0, 1);
        campos.add(campoDescricao, 1, 1);
        campos.add(checkConcluida, 1, 2);

        Button salvar = new Button("Salvar");
        Button cancelar = new Button("Cancelar");
        salvar.setOnAction(event -> salvar());
        cancelar.setOnAction(event -> stage.close());

        HBox botoes = new HBox(10, salvar, cancelar);
        VBox conteudo = new VBox(
                15,
                new Label("Cadastro de Tarefa"),
                campos,
                botoes
        );
        conteudo.setPadding(new Insets(20));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(tarefa.getId() == 0
                ? "Nova tarefa"
                : "Editar tarefa");
        stage.setScene(new Scene(conteudo, 520, 300));
    }

    private void salvar() {
        String titulo = campoTitulo.getText().trim();

        try {
            Tarefa.validarTitulo(titulo);
        } catch (EntradaInvalidaException exception) {
            new Alert(
                    Alert.AlertType.WARNING,
                    exception.getMessage()
            ).showAndWait();
            return;
        }

        tarefa.editar(titulo, campoDescricao.getText().trim());
        tarefa.setConcluida(checkConcluida.isSelected());
        salvo = true;
        stage.close();
    }
}

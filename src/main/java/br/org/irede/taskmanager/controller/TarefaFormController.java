package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.exception.EntradaInvalidaException;
import br.org.irede.taskmanager.model.Tarefa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class TarefaFormController {

    @FXML
    private TextField campoTitulo;

    @FXML
    private TextArea campoDescricao;

    @FXML
    private CheckBox checkConcluida;

    private Tarefa tarefa;
    private boolean salvo;

    public void configurar(Tarefa tarefa) {
        this.tarefa = tarefa;
        campoTitulo.setText(tarefa.getTitulo());
        campoDescricao.setText(tarefa.getDescricao());
        checkConcluida.setSelected(tarefa.isConcluida());
    }

    public boolean isSalvo() {
        return salvo;
    }

    @FXML
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
        fechar();
    }

    @FXML
    private void cancelar() {
        fechar();
    }

    private void fechar() {
        Window janela = campoTitulo.getScene().getWindow();
        janela.hide();
    }
}
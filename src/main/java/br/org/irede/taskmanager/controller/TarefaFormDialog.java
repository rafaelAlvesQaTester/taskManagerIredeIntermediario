package br.org.irede.taskmanager.controller;

import br.org.irede.taskmanager.model.Tarefa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TarefaFormDialog {

    private final Stage stage = new Stage();
    private final Tarefa tarefa;
    private final TarefaFormController controller;

    public TarefaFormDialog(Tarefa tarefaExistente) {
        tarefa = copiar(tarefaExistente);
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/tarefa-form.fxml")
        );

        try {
            Parent conteudo = loader.load();
            controller = loader.getController();
            controller.configurar(tarefa);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(tarefa.getId() == 0
                    ? "Nova tarefa"
                    : "Editar tarefa");
            stage.setScene(new Scene(conteudo));
            stage.setResizable(false);
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Não foi possível carregar o formulário de tarefa.",
                    exception
            );
        }
    }

    public boolean show() {
        stage.showAndWait();
        return controller.isSalvo();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    private Tarefa copiar(Tarefa original) {
        if (original == null) {
            return new Tarefa(0, "", "");
        }

        Tarefa copia = new Tarefa(
                original.getId(),
                original.getTitulo(),
                original.getDescricao()
        );
        copia.setConcluida(original.isConcluida());
        return copia;
    }
}

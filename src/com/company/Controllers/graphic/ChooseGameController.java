package com.company.Controllers.graphic;

import com.company.Views.Graphic;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseGameController implements Initializable {
    public ImageView back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
            event.consume();
        });
    }
}

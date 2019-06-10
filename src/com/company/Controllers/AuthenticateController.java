package com.company.Controllers;

import com.company.Views.Graphic;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthenticateController {
    public JFXTabPane tabPane;
    public Tab loginTab;
    public Tab signupTab;
    public TextField loginUsername;

    public void login(ActionEvent actionEvent) {
        Graphic.stage.getScene().setRoot(Graphic.mainMenu);
    }

    public void signUp(ActionEvent actionEvent) {
    }
}

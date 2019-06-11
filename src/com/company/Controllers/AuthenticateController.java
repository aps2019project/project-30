package com.company.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class AuthenticateController {
    public JFXTabPane tabPane;
    public Tab loginTab;
    public Tab signupTab;
    public TextField loginUsername;
    public TextField loginPassword;
    public TextField signupUsername;
    public TextField signupPassword;
    public TextField signupPasswordMatch;
    public static ErrorType loginErrorType = null;
    public static ErrorType signEroorType = null;
    public Label error;
    public VBox loginErrorBox;
    public VBox loginBox;

    public void login(ActionEvent actionEvent) {
        AccountController.loginAccount(loginUsername.getText(),loginPassword.getText());
        if(loginErrorType==null){
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
            ((MainMenuController) Graphic.mainMenuLoader.getController()).initValues();
        }
        else{
            new FadeOut(loginBox).playOnFinished(new FadeIn(loginErrorBox)).play();
            new FadeOut(loginErrorBox).playOnFinished(new FadeIn(loginBox)).setDelay(new Duration(2500)).play();
            error.setText(loginErrorType.getMessage());
        }
    }

    public static void loginError(ErrorType longinErrorType){
        loginErrorType=longinErrorType;
    }

    public void signUp(ActionEvent actionEvent) {
        if(!signupPasswordMatch.getText().equals(signupPassword.getText())){
            error.setText("PASSWORDS NOT EQUAL");
        }
        else {
            AccountController.createAccount(signupUsername.getText(),signupPassword.getText());
            if (signEroorType != null) {
                error.setText("USERNAME EXIST");
            }
        }
    }
    public static void signUpError(ErrorType signErrorType){
        signEroorType=signErrorType;
    }
}

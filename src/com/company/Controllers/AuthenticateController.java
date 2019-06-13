package com.company.Controllers;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.company.Controllers.graphic.RootsController;
import com.company.Models.ErrorType;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AuthenticateController {
    public static ErrorType loginErrorType = null;
    public static ErrorType signErrorType = null;
    public JFXTabPane tabPane;
    public Tab loginTab;
    public Tab signupTab;
    public TextField loginUsername;
    public TextField loginPassword;
    public TextField signupUsername;
    public TextField signupPassword;
    public TextField signupPasswordMatch;
    public Label loginError;
    public VBox loginErrorBox;
    public VBox loginBox;
    public VBox signupErrorBox;
    public Label signupError;
    public VBox signupBox;
    public VBox signupSuccussBox;
    public JFXCheckBox rememberMe;

    public static void loginError(ErrorType longinErrorType) {
        loginErrorType = longinErrorType;
    }

    public static void signUpError(ErrorType signErrorType) {
        AuthenticateController.signErrorType = signErrorType;
    }

    public void login(ActionEvent actionEvent) {
        AccountController.loginAccount(loginUsername.getText(), loginPassword.getText());
        if (loginErrorType == null) {
            RootsController.openMainMenu();
        } else {
            new FadeOut(loginBox).playOnFinished(new FadeIn(loginErrorBox)).play();
            new FadeIn(loginBox).playOnFinished(new FadeOut(loginErrorBox)).setDelay(new Duration(2500)).play();
            loginError.setText(loginErrorType.getMessage());
        }
    }

    public void signUp(ActionEvent actionEvent) {
        if (!signupPasswordMatch.getText().equals(signupPassword.getText())) {
            signupError.setText("PASSWORDS NOT EQUAL");
            new FadeOut(signupBox).playOnFinished(new FadeIn(signupErrorBox)).play();
            new FadeIn(signupBox).playOnFinished(new FadeOut(signupErrorBox)).setDelay(new Duration(2500)).play();
        } else {
            AccountController.createAccount(signupUsername.getText(), signupPassword.getText());
            if (signErrorType != null) {
                new FadeOut(signupBox).playOnFinished(new FadeIn(signupErrorBox)).play();
                new FadeIn(signupBox).playOnFinished(new FadeOut(signupErrorBox)).setDelay(new Duration(2500)).play();
                signupError.setText(signErrorType.getMessage());
            } else {
                signupErrorBox.setVisible(false);
                new FadeOut(signupBox).playOnFinished(new FadeIn(signupSuccussBox)).play();
                AnimationFX animationFX = new FadeOut(signupSuccussBox).setDelay(new Duration(1500));
                animationFX.setOnFinished(event -> {
                    tabPane.getSelectionModel().select(loginTab);
                    FadeIn fadeIn = new FadeIn(signupBox);
                    fadeIn.setOnFinished(event1 -> signupErrorBox.setVisible(true));
                    fadeIn.play();
                });
                animationFX.play();
            }
        }
    }

    public void callChangeIsRemembered(ActionEvent actionEvent) {
        MainMenuController.changeIsRememberMe();
    }
}

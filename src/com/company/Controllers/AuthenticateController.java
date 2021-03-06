package com.company.Controllers;

import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.company.Models.Client.Client;
import com.company.Models.ErrorType;
import com.company.Models.Property;
import com.company.Models.Request;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.awt.event.InvocationEvent;
import java.lang.reflect.InvocationTargetException;

public class AuthenticateController {
    public static ErrorType loginErrorType = null;
    public static ErrorType signErrorType = null;
    public JFXTabPane loginAndSignUpTabPane;
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
    public VBox connectingToServerVBox;
    public VBox loginAndSignUpVBox;

    public TextField serverIP;
    public TextField serverPort;
    public VBox connectBox;
    public VBox connectErrorBox;
    public Label connectingError;


    public static void signUpError(ErrorType signErrorType) {
        AuthenticateController.signErrorType = signErrorType;
    }

    public void login(ActionEvent actionEvent) {
        Client.getRequestController().sendRequest(new Request(
                Request.Type.LOGIN,
                new Property(Property.USERNAME_PROPERTY, loginUsername.getText()),
                new Property(Property.PASSWORD_PROPERTY, loginPassword.getText())
        ));
    }

    public void setLoginError(String errorMessage) {
        new FadeOut(loginBox).playOnFinished(new FadeIn(loginErrorBox)).play();
        new FadeIn(loginBox).playOnFinished(new FadeOut(loginErrorBox)).setDelay(new Duration(2500)).play();
        loginError.setText(errorMessage);
    }

    public void setSignUpError(String errorMessage) {
        new FadeOut(signupBox).playOnFinished(new FadeIn(signupErrorBox)).play();
        new FadeIn(signupBox).playOnFinished(new FadeOut(signupErrorBox)).setDelay(new Duration(2500)).play();
        signupError.setText(signErrorType.getMessage());
    }

    public void connectionError() {
        new FadeOut(connectBox).playOnFinished(new FadeIn(connectErrorBox)).play();
        new FadeIn(connectBox).playOnFinished(new FadeOut(connectErrorBox)).setDelay(new Duration(2500)).play();
        connectingError.setText(ErrorType.CONNECTING_SERVER_FAILD.getMessage());
    }

    public void successfulLogin() {
        signupErrorBox.setVisible(false);
        new FadeOut(signupBox).playOnFinished(new FadeIn(signupSuccussBox)).play();
        AnimationFX animationFX = new FadeOut(signupSuccussBox).setDelay(new Duration(1500));
        animationFX.setOnFinished(event -> {
            loginAndSignUpTabPane.getSelectionModel().select(loginTab);
            FadeIn fadeIn = new FadeIn(signupBox);
            fadeIn.setOnFinished(event1 -> signupErrorBox.setVisible(true));
            fadeIn.play();
        });
        animationFX.play();
    }


    public void signUp(ActionEvent actionEvent) {
        Client.getRequestController().sendRequest(new Request(
                Request.Type.SIGN_UP,
                new Property(Property.USERNAME_PROPERTY, signupUsername.getText()),
                new Property(Property.PASSWORD_PROPERTY, signupPassword.getText())
        ));
    }

    private void setSignUpTextBoxesNull() {
        signupUsername.setText(null);
        signupPassword.setText(null);
        signupPasswordMatch.setText(null);
    }

    public void connect(ActionEvent actionEvent) {
        try {
            Client.setPortNumber(Integer.parseInt(serverPort.getText()));
            Client.setSererIP(serverIP.getText());
        } catch (Exception e){

        }
        if (Client.setClientUp()) {
            connectingToServerVBox.setVisible(false);
            loginAndSignUpVBox.setVisible(true);
        } else {
            connectionError();
        }
    }
}

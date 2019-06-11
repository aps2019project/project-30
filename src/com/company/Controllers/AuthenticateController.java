package com.company.Controllers;

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

    public void login(ActionEvent actionEvent) {
        AccountController.loginAccount(loginUsername.getText(),loginPassword.getText());
        if(loginErrorType==null){
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
        }
        else{
            if(loginErrorType.equals(ErrorType.PASSWORD_INVALID))
                error.setText("INVALID PASSORD");
            if(loginErrorType.equals(ErrorType.USERNAME_NOTFOUND))
                error.setText("USERNAME_NOTFOUND");
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
            if (signEroorType == null) {
                Graphic.stage.getScene().setRoot(Graphic.mainMenu);
            } else {
                error.setText("USERNAME EXIST");
            }
        }
    }
    public static void signUpError(ErrorType signErrorType){
        signEroorType=signErrorType;
    }
}

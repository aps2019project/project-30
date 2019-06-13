package com.company.Controllers;

import animatefx.animation.FadeOut;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.company.Views.graphic.Fog;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public ImageView friends;
    public ImageView shop;
    public Label play;
    public Label collection;
    public Label gold;
    public Label exit;
    public StackPane cloudsContainer;
    public ImageView pillars;
    public ImageView foreGround;
    public AnchorPane root;
    private static boolean rememberMe = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        foreGround.fitWidthProperty().bind(root.widthProperty().divide(2));
//        foreGround.fitHeightProperty().bind(foreGround.fitWidthProperty().divide(20 / 17));
//        pillars.fitWidthProperty().bind(root.widthProperty().divide(3/2));
//        pillars.fitHeightProperty().bind(pillars.fitWidthProperty().divide(10 / 3));


        Fog fog = new Fog(600, 200);
        cloudsContainer.getChildren().add(fog.getView());


        friends.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            event.consume();
        });

        shop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.shop);
            event.consume();
        });

        play.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.chooseGame);
            event.consume();
        });

        collection.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.collection);
            event.consume();
        });

        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (rememberMe) {
                AccountController.writeObjectToFile(Account.getLoggedInAccount(), Account.getLoggedInAccountsFolderAddress());
                System.out.println("logged in account saved!");
            }
            System.out.println("Exit");
        });

//        Media media = new Media(new File("com/company/Views/graphic/sounds/mainmenu.mp3").toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
    }

    public void initValues() {
        gold.setText(String.valueOf(Account.getLoggedInAccount().getDrake()));
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Simorgh");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Taj Danayi");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Kamandar Fars");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Neyzedar Arab");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorzdar Arab");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Empower");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorzdar Arab");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Ghool Tak Cheshm");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Mar Sami");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Mar Ghool Peykar");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorge Sefid");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Jadougar Azam");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Siavash");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Nane Sarma");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Arzhang Div");
        //Spells
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Total Disarm");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Lighting Bolt");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "All Disarm");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Dispel");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Sacrifice");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Shock");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Pahlavan Fars");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Fireball");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Hell Fire");
        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Palang");


        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck("test");

        for (int i = 0; i < 24; i++) {
            Account.getLoggedInAccount().getCollection().getCollectionController().addCard(String.valueOf(i + 1), "test");
        }

        Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck("test");
    }


    public static void setRememberMe(boolean rememberMe) {
        MainMenuController.rememberMe = rememberMe;
    }

}

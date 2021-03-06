package com.company.Controllers;

import com.company.Controllers.Server.ServerAccountController;
import com.company.Controllers.graphic.RootsController;
import com.company.Models.Client.Client;
import com.company.Models.Request;
import com.company.Models.Sound;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.company.Views.graphic.Fog;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public ImageView friends;
    public ImageView shop;
    public ImageView scoreboard;
    public Label play;
    public Label collection;
    public Label gold;
    public StackPane cloudsContainer;
    public ImageView pillars;
    public ImageView foreGround;
    public ImageView exit;
    public AnchorPane root;
    public Label save;
    private static boolean rememberMe = false;
    public static long timeTurn;
    public static long timenow=-1;
    public Label chats;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        foreGround.fitWidthProperty().bind(root.widthProperty().divide(2));
//        foreGround.fitHeightProperty().bind(foreGround.fitWidthProperty().divide(20 / 17));
//        pillars.fitWidthProperty().bind(root.widthProperty().divide(3/2));
//        pillars.fitHeightProperty().bind(pillars.fitWidthProperty().divide(10 / 3));

        Sound.muteAndUnmute(Graphic.stage.getScene(),Sound.MAIN_MENU_SOUND_ADDRESS);

        Fog fog = new Fog(1000, 300);
        cloudsContainer.getChildren().add(fog.getView());


        friends.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            event.consume();
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
        });

        shop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            Graphic.stage.getScene().setRoot(Graphic.shop);

            Client.getRequestController().sendRequest(new Request(
                    Request.Type.SHOPBUY
            ));

            RootsController.openShop();
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            event.consume();
        });

        chats.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            RootsController.openChats();
        });

        play.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Graphic.stage.getScene().setRoot(Graphic.chooseGame);
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            event.consume();
        });

        collection.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            RootsController.openGameCollection();
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            event.consume();
        });

        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(rememberMe){
                JsonController.writeLoggedInAccountOnFile();
            }
            Client.getRequestController().sendRequest(
                    new Request(Request.Type.DISCONNECT)
            );
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            System.exit(0);
        });

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            ServerAccountController.saveAccounts();
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
        });

        scoreboard.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Client.getRequestController().sendRequest(
                    new Request(Request.Type.SCOREBOARD)
            );
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
        });
    }

    public void initValues() {
//        gold.setText(String.valueOf(Account.getLoggedInAccount().getDrake()));
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Simorgh");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Taj Danayi");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Kamandar Fars");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Neyzedar Arab");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorzdar Arab");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Empower");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorzdar Arab");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Ghool Tak Cheshm");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Mar Sami");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Mar Ghool Peykar");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Gorge Sefid");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Jadougar Azam");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Siavash");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Nane Sarma");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Arzhang Div");
//        //Spells
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Total Disarm");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Lighting Bolt");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "All Disarm");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Dispel");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Sacrifice");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Shock");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Pahlavan Fars");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Fireball");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Hell Fire");
//        com.company.Controllers.ShopController.buy(Account.getLoggedInAccount(), "Palang");


//        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck("test");
//
//        for (int i = 0; i < 24; i++) {
//            Account.getLoggedInAccount().getCollection().getCollectionController().addCard(String.valueOf(i + 1), "test");
//        }
//
//        Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck("test");
    }

    public static void changeIsRememberMe() {
        rememberMe = true;
    }

}

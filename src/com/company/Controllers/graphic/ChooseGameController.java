package com.company.Controllers.graphic;

import com.company.Controllers.BattleController;
import com.company.Controllers.JsonController;
import com.company.Models.Battle.Battle;
import com.company.Models.Battle.Modes.Mode;
import com.company.Models.User.Account;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class ChooseGameController implements Initializable {
    public AnchorPane anchorPane;
    public StackPane loadSavedGameMenu;

    public ImageView back;
//    public Button single;
    public String numberOfPlayers;
    public String storyorcustom;
    public static Mode mode;
    public int storyLevel=1;
//    public Button multi;
    public VBox singlePlayer;
    public VBox multiPlayer;
    public VBox secondPageSingle;
    public VBox multiorsingle;
    public VBox story;
    public VBox custom;
    public VBox modeBox;
    public VBox selectAcount;
    public VBox killingGenerall;
    public VBox captureTheFlag;
    public VBox captureMostFlag;
    public TextField search;
    public JFXMasonryPane acountContaner;
    public VBox levels;
    public VBox level1;
    public VBox level2;
    public VBox level3;
    public TextField flagsinstory;
    public TextField flagsincustom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("hoyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
                RootsController.backToMainMenu();
            }
        });
        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                numberOfPlayers="singlePlayer";
                multiorsingle.setVisible(false);
                secondPageSingle.setVisible(true);
            }
        });
        multiPlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                numberOfPlayers="multiPlayer";
                selectAcount.setVisible(true);
                multiorsingle.setVisible(false);
            }
        });
        story.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                secondPageSingle.setVisible(false);
                storyorcustom="story";
                levels.setVisible(true);
            }
        });
        custom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                secondPageSingle.setVisible(false);
                storyorcustom="custom";
                modeBox.setVisible(true);
            }
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            List<Account> list=new ArrayList<>();
            if(newValue.isEmpty()){
                list=Account.getAccounts();
            }
            else{
                for(Account account:Account.getAccounts()){
                    if(account.getUsername().contains(newValue))
                        list.add(account);
                }
            }
            for(Account account:list){
                AnchorPane anchorPane=new AnchorPane();
                Label label=new Label(account.getUsername());
                anchorPane.getChildren().add(label);
                acountContaner.getChildren().add(anchorPane);
            }
        }));

        killingGenerall.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mode=Mode.KILLING_GENERAL;
            }
        });
        captureMostFlag.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mode=Mode.COLLECTING_FLAGS;
            }
        });
        captureTheFlag.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mode=Mode.CAPTURE_THE_FLAG;
            }
        });
        flagsincustom.textProperty().addListener(((observable, oldValue, newValue) -> {
            new Battle(storyLevel,Integer.parseInt(newValue));
            RootsController.game();
        }));

        level1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                storyLevel=1;
                mode=Mode.KILLING_GENERAL;
            }
        });
        level2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                storyLevel=2;
                mode=Mode.CAPTURE_THE_FLAG;
            }
        });
        level3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                storyLevel=3;
                mode=Mode.COLLECTING_FLAGS;
            }
        });

        flagsinstory.textProperty().addListener(((observable, oldValue, newValue) -> {
            new Battle(storyLevel,Integer.parseInt(newValue));
            RootsController.game();
        }));




    }

    public Mode getMode() {
        return mode;
    }

    public void multiplayer(ActionEvent actionEvent) {
    }

//    public void startGame(ActionEvent actionEvent) {
//        new Battle(
//                3,
//                2
//        );
//        try {
//            FXMLLoader loader = new FXMLLoader(Graphic.class.getResource("graphic/xmls/game.fxml"));
//            Parent root = loader.load();
//            ((GameController) loader.getController()).init();
//            Graphic.stage.getScene().setRoot(root);
//            Sound.pause(Sound.MAIN_MENU_SOUND_ADDRESS);
//            Sound.play(Sound.BATTLE_MAIN_MUSIC_ADDRESS);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void singlePlayer(ActionEvent actionEvent) {
    }

    public void openLoadSavedGameMenu() {
        GaussianBlur gaussianBlur = new GaussianBlur();
        anchorPane.setEffect(gaussianBlur);
        loadSavedGameMenu.setVisible(true);
        showSavedGamesButtons();
    }

    private void showSavedGamesButtons() {
        int index = 1;
        for (Battle battle:Battle.getSavedBattles()) {
            loadSavedGameMenu.getChildren().add(createLoadSavedGameButton(index,battle));
        }
    }

    public void closeBuffCreationMenu() {
        loadSavedGameMenu.setVisible(false);
        anchorPane.setEffect(null);
    }

    public void cancelLoadSavedGame() {
        closeBuffCreationMenu();
    }

    public HBox createLoadSavedGameButton(int index, Battle battle) {
        HBox singleCard = new HBox();
        singleCard.getStyleClass().add("hbox_card");
        Label number = new Label();
        Label cardName = new Label();
        cardName.setTextFill(WHITE);
        number.setTextFill(BLACK);
        number.setText(String.valueOf(index));
        cardName.setText(battle.getPlayers()[0] + " VS " + battle.getPlayers()[1]);
        singleCard.getStyleClass().add("collection-deck-card");
        cardName.getStyleClass().add("collection-deck-card");
        singleCard.getChildren().add(number);
        singleCard.getChildren().add(cardName);
        return singleCard;
    }
}


package com.company.Controllers.graphic;

import com.company.Controllers.JsonController;
import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.Card.Groups.Deck;
import com.company.Models.Card.Spell.Spell;
import com.company.Models.ErrorType;
import com.company.Models.Sound;
import com.company.Models.User.Account;
import com.company.Views.BattleView;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.List;

import static javafx.scene.paint.Color.*;

public class CollectionController implements Initializable {
    public static ErrorType creatDeckErrorType;
    public AnchorPane mainAnchor;
    private final ToggleGroup mainDeckToggleGroup = new ToggleGroup();
    public JFXTextField deckName;
    public AnchorPane deck_bar;
    public AnchorPane deck_content_bar;
    public JFXButton backtodecks;
    public VBox deck_contaner_content_bar;
    public HBox backbar;
    public JFXButton createDeck;
    public JFXButton importDeck;
    public VBox deckContainer;
    public HBox creatDeckBar;
    public JFXMasonryPane cardContainer;
    public ImageView back;
    public TextField search;
    public String selected_deck=null;
    public ScrollPane firstScroll;
    public ScrollPane secondScroll;
    public static VBox description;


    public void createDeck(ActionEvent actionEvent) {
        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(deckName.getText());
        if (creatDeckErrorType == null) {
            addDeckToDecksContainer(deckName.getText());

        }
    }

    private void addDeckToDecksContainer(String deckname){
        JFXRadioButton mainDeckRadioButton = new JFXRadioButton();
        mainDeckRadioButton.setUserData(deckname);
        mainDeckRadioButton.setPadding(new Insets(10));
        mainDeckRadioButton.setToggleGroup(mainDeckToggleGroup);
        HBox hBox = new HBox();
//        Image image1=new Image("com/company/Views/graphic/images/crest_f1_deck_select.png");
//        ImageView neshan=new ImageView(image1);
//        neshan.setFitWidth(50);
//        neshan.setFitHeight(50);
//        hBox.getChildren().add(neshan);
        hBox.getChildren().add(mainDeckRadioButton);
        hBox.getStyleClass().add("deckBox_collection");
        Label decklabel = new Label(deckname);
        decklabel.getStyleClass().add("collection-deck");
        hBox.getChildren().add(decklabel);
        Image image = new Image("com/company/Views/graphic/images/waste-bin.png");
        ImageView imageView = new ImageView(image);
        Image saveicon = new Image("com/company/Views/graphic/images/save-icon.png");
        ImageView save = new ImageView(saveicon);
        imageView.setId(deckname);
        imageView.getStyleClass().add("deck_image_delete");
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        save.setFitHeight(20);
        save.setFitWidth(20);

        save.setId(deckname);

        hBox.getChildren().add(imageView);
        hBox.getChildren().add(save);
        deckContainer.getChildren().add(hBox);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(((ImageView) event.getSource()).getId());
                deckContainer.getChildren().remove(hBox);
            }
        });

        save.setOnMouseClicked(event -> {
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Choose Directory To Save Deck");
            File defaultDirectory = new File("c:/");
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog(Graphic.stage);
            JsonController.exportDeck(
                    Collection.getDeckByName(((ImageView) event.getSource()).getId()),
                    selectedDirectory.getAbsolutePath()
            );
        });

        decklabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
                selected_deck=decklabel.getText();
                firstScroll.setVisible(false);
                deckContainer.setVisible(false);
                creatDeckBar.setVisible(false);
                showDekContentBar();

            }
        });

    }


    private void showDekContentBar() {
        deck_contaner_content_bar.setVisible(true);
        secondScroll.setVisible(true);
        backbar.setVisible(true);
        deck_contaner_content_bar.getChildren().clear();
        HBox deckN = new HBox();
        deckN.getStyleClass().add("deckBox_collection");
        Label decklabel = new Label(selected_deck);
        decklabel.getStyleClass().add("collection-deck");
        deckN.getChildren().add(decklabel);
        deck_contaner_content_bar.getChildren().add(deckN);
        for (Card card : Collection.getDeckByName(selected_deck).getDeckCards()) {
            HBox singleCard = new HBox();
            singleCard.getStyleClass().add("hbox_card");
            Label number = new Label();
            Label cardName = new Label();
            cardName.setTextFill(WHITE);
            number.setTextFill(BLACK);
            number.setText(String.valueOf(card.getManaPoint()));
            cardName.setText(card.getName());
            singleCard.getStyleClass().add("collection-deck-card");
            cardName.getStyleClass().add("collection-deck-card");
            singleCard.getChildren().add(number);
            singleCard.getChildren().add(cardName);
            deck_contaner_content_bar.getChildren().add(singleCard);
            singleCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Account.getLoggedInAccount().getCollection().getCollectionController().remove(card.getId(), selected_deck);
                    deck_contaner_content_bar.getChildren().remove(singleCard);
                }
            });
            singleCard.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    description= BattleView.cardDesciption(card,"collection");
                    mainAnchor.setRightAnchor(description,410.0);
                    mainAnchor.setTopAnchor(description,singleCard.getLayoutY());
                    mainAnchor.getChildren().add(description);
                }
            });
            singleCard.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mainAnchor.getChildren().remove(description);
                }
            });
        }
        backtodecks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deck_contaner_content_bar.setVisible(false);
                secondScroll.setVisible(false);
                backbar.setVisible(false);
                deckContainer.setVisible(true);
                firstScroll.setVisible(true);
                creatDeckBar.setVisible(true);
                selected_deck = null;
            }
        });

    }



    public static void assigncreatDeckErrorType(ErrorType errorType){
        creatDeckErrorType=errorType;
    }

    public void updateDecks(){
        deckContainer.getChildren().clear();
        updateCollection("");
        for(Deck deck:Account.getLoggedInAccount().getDecks()){
            addDeckToDecksContainer(deck.getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back.setOnMouseClicked(event -> {
            RootsController.backToMainMenu();
        });

        mainDeckToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck((String) newValue.getUserData());
            }
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            updateCollection(newValue);
        }));
    }

    private void updateCollection(String newValue) {
        List<Card> cards;
        if(newValue.isEmpty())
            cards = Account.getLoggedInAccount().getCollection().getCards();
        else
            cards=com.company.Controllers.CollectionController.searchCardsByName(newValue);
        cardContainer.getChildren().clear();
        for (Card card : cards) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(200, 262);
            anchorPane.getStyleClass().add("card-shop-container");
            Label label = new Label(card.getName().toUpperCase());
            label.getStyleClass().add("card-shop-name");
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(200);
            anchorPane.getChildren().add(label);
            AnchorPane.setTopAnchor(label, 220.0);
            cardContainer.getChildren().add(anchorPane);

            anchorPane.setOnMouseClicked(event -> {
                if (selected_deck != null) {
                    Account.getLoggedInAccount().getCollection().getCollectionController().addCard(card.getId(), selected_deck);
                    showDekContentBar();
                }
            });
        }
    }

    public void importDeckFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Deck To Add");
        File defaultDirectory = new File("c:/");
        fileChooser.setInitialDirectory(defaultDirectory);
        File selectedDeck = fileChooser.showOpenDialog(Graphic.stage);
        Deck importedDeck = JsonController.importDeck(selectedDeck.getAbsolutePath());
        Account.getLoggedInAccount().getDecks().add(importedDeck);
        updateDecks();
    }


}

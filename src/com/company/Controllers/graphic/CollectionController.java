package com.company.Controllers.graphic;

import com.company.Models.Card.Card;
import com.company.Models.Card.Groups.Collection;
import com.company.Models.ErrorType;
import com.company.Models.User.Account;
import com.company.Views.Graphic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

import java.net.URL;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    public static ErrorType creatDeckErrorType;
    private final ToggleGroup mainDeckToggleGroup = new ToggleGroup();
    public JFXTextField deckName;
    public AnchorPane deck_bar;
    public AnchorPane deck_content_bar;
    public JFXButton backtodecks;
    public VBox deck_contaner_content_bar;
    public HBox backbar;
    public JFXButton createDeck;
    public VBox deckContainer;
    public HBox creatDeckBar;
    public JFXMasonryPane cardContainer;
    public ImageView back;
    public TextField search;
    public String selected_deck=null;


    public void createDeck(ActionEvent actionEvent) {
        Account.getLoggedInAccount().getCollection().getCollectionController().createDeck(deckName.getText());
        if (creatDeckErrorType == null) {
            JFXRadioButton mainDeckRadioButton = new JFXRadioButton();
            mainDeckRadioButton.setUserData(deckName.getText());
            mainDeckRadioButton.setPadding(new Insets(10));
            mainDeckRadioButton.setToggleGroup(mainDeckToggleGroup);
            HBox hBox = new HBox();
            hBox.getChildren().add(mainDeckRadioButton);
            hBox.getStyleClass().add("deckBox_collection");
            Label decklabel = new Label(deckName.getText());
            decklabel.getStyleClass().add("collection-deck");
            hBox.getChildren().add(decklabel);
            Image image = new Image("com/company/Views/graphic/images/waste-bin.png");
            ImageView imageView = new ImageView(image);
            imageView.setId(deckName.getText());
            imageView.getStyleClass().add("deck_image_delete");
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            hBox.getChildren().add(imageView);
            deckContainer.getChildren().add(hBox);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Account.getLoggedInAccount().getCollection().getCollectionController().deleteDeck(((ImageView) event.getSource()).getId());
                    deckContainer.getChildren().remove(hBox);
                }
            });


            decklabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    selected_deck=decklabel.getText();
                    deckContainer.setVisible(false);
                    creatDeckBar.setVisible(false);
                    showDekContentBar();

                }
            });

        }
    }


    private void showDekContentBar(){
        deck_contaner_content_bar.setVisible(true);
        backbar.setVisible(true);
        deck_contaner_content_bar.getChildren().clear();
        int counter=0;
        for(Card card: Collection.getDeckByName(selected_deck).getDeckCards()){
            counter++;
            HBox singleCard=new HBox();
            singleCard.getStyleClass().add("hbox_card");

            Label number=new Label();
            Label cardName=new Label();

            number.setText(String.valueOf(counter));
            cardName.setText(card.getName());
 //           singleCard.getStyleClass().add("collection-deck");
//            cardName.getStyleClass().add("collection-deck");
            singleCard.getChildren().add(number);
            singleCard.getChildren().add(cardName);
//            singleCard.getOnMouseClicked(new EventHandler<>() {
//                @Override
//                public void handle(Event event) {
//
//                }
//            });
            deck_contaner_content_bar.getChildren().add(singleCard);
        }
        backtodecks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                deck_contaner_content_bar.setVisible(false);
                backbar.setVisible(false);
                deckContainer.setVisible(true);
                creatDeckBar.setVisible(true);
                selected_deck=null;
            }
        });
    }



    public static void assigncreatDeckErrorType(ErrorType errorType){
        creatDeckErrorType=errorType;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            Graphic.stage.getScene().setRoot(Graphic.mainMenu);
        });

        mainDeckToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                Account.getLoggedInAccount().getCollection().getCollectionController().selectDeck((String) newValue.getUserData());
            }
        });

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
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
        }));
    }
}

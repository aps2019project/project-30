package com.company.Controllers.graphic;

import com.company.Controllers.JsonController;
import com.company.Models.Buff.*;
import com.company.Models.Card.AttackType;
import com.company.Models.Client.Client;
import com.company.Models.Request;
import com.company.Models.Shop;
import com.company.Models.Property;
import com.company.Models.Sound;
import com.company.Views.Graphic;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXComboBox;
import com.company.Models.Card.Hero.Hero;
import com.company.Models.Card.Minion.Minion;
import com.company.Models.Card.Soldier;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class CustomCardController implements Initializable {
    final private static String GIFS_FOLDER_PATH = "src/com/company/Views/graphic/images/gifs/";

    public ImageView back;

    public enum state {ATTACK, IDLE, BREATHING, DEATH, RUN}

    public StackPane stackPane;
    public StackPane buffCreationMenu;
    public StackPane minionMovementsGifSettingMenu;
    public StackPane heroMovementsGifSettingMenu;


    public JFXComboBox<AttackType> heroAttackType;
    public JFXComboBox<AttackType> minionAttackType;
    public JFXComboBox<Buff.Name> minionSpecialPower;
    public JFXComboBox<Buff.Name> heroSpecialPower;


    public TextField heroName;
    public TextField heroNeededDrake;
    public TextField heroNeededMana;
    public TextField heroDescription;
    public TextField heroFullHealth;
    public TextField heroAttackPower;
    public TextField heroAreaOfEffect;
    public TextField heroCoolDown;

    public TextField minionName;
    public TextField minionNeededDrake;
    public TextField minionNeededMana;
    public TextField minionDescription;
    public TextField minionFullHealth;
    public TextField minionAttackPower;
    public TextField minionAreaOfEffect;

    public TextField buffValue;
    public TextField castTime;
    public Label buffName;

    private Buff specialPower;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnMouseClicked(event -> {
            Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
            RootsController.openShop();
        });
        heroSpecialPower.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            GaussianBlur gaussianBlur = new GaussianBlur();
            stackPane.setEffect(gaussianBlur);
            buffCreationMenu.setVisible(true);
        });
        minionSpecialPower.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            GaussianBlur gaussianBlur = new GaussianBlur();
            stackPane.setEffect(gaussianBlur);
            buffCreationMenu.setVisible(true);
        });

        heroAttackType.getItems().addAll(AttackType.values());
        minionAttackType.getItems().addAll(AttackType.values());
        heroSpecialPower.getItems().addAll(Buff.Name.values());
        minionSpecialPower.getItems().addAll(Buff.Name.values());
    }

    public void createNewCustomHero(ActionEvent actionEvent) {
        Hero newHero = createNewCustomHeroCard(
                heroName.getText(),
                Integer.parseInt(heroNeededDrake.getText()),
                Integer.parseInt(heroNeededMana.getText()),
                Integer.parseInt(heroFullHealth.getText()),
                Integer.parseInt(heroAttackPower.getText()),
                Integer.parseInt(heroAreaOfEffect.getText()),
                heroAttackType.getSelectionModel().getSelectedItem(),
                specialPower,
                Integer.parseInt(heroCoolDown.getText())
        );
        setSpecialPower(null);

        Request request = new Request(Request.Type.NEW_HERO);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Property.NEW_HERO,JsonController.getGson().toJsonTree(newHero,Hero.class));
        Client.getRequestController().sendRequest(request);

        addNewHeroToHeroesJsonFile(newHero);
        addNewCustomCardToShopCollection(newHero);
        Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
    }

    public void createNewCustomMinion(ActionEvent actionEvent) {
        Minion newMinion = (Minion) createNewCustomMinionCard(
                minionName.getText(),
                Integer.parseInt(minionNeededDrake.getText()),
                Integer.parseInt(minionNeededMana.getText()),
                Integer.parseInt(minionFullHealth.getText()),
                Integer.parseInt(minionAttackPower.getText()),
                Integer.parseInt(minionAreaOfEffect.getText()),
                minionAttackType.getSelectionModel().getSelectedItem(),
                specialPower
        );
        setSpecialPower(null);

        Request request = new Request(Request.Type.NEW_MINION);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(Property.NEW_MINION,JsonController.getGson().toJsonTree(newMinion,Minion.class));
        Client.getRequestController().sendRequest(request);

        addNewMinionToMinionsJsonFile(newMinion);
        addNewCustomCardToShopCollection(newMinion);
        Sound.play(Sound.SELECT_SOUND_EFFECT_ADDRESS,false);
    }

    private void addNewHeroToHeroesJsonFile(Hero newHero) {
        List<Hero> heroes = JsonController.getHeroes();
        heroes.add(newHero);
        JsonController.writeHeroesOnFile(heroes);
    }

    private void addNewCustomCardToShopCollection(Soldier newSoldier) {
        Shop.getShopCollection().getCards().add(newSoldier);
    }


    private void addNewMinionToMinionsJsonFile(Minion newMinion) {
        List<Minion> minions = JsonController.getMinions();
        minions.add(newMinion);
        JsonController.writeMinionsOnFile(minions);
    }

    private Minion createNewCustomMinionCard(
            String name,
            int priceInDrake,
            int manaPoint,
            int fullHealth,
            int attackPower,
            int areaOfEffect,
            AttackType attackType,
            Buff specialPower) {
        Minion newMinion = new Minion();
        newMinion.setName(name);
        newMinion.setPriceInDrake(priceInDrake);
        newMinion.setManaPoint(manaPoint);
        newMinion.setDescription(heroDescription.getText());
        newMinion.setFullHealth(fullHealth);
        newMinion.setAttackPower(attackPower);
        newMinion.setAreaOfEffect(areaOfEffect);
        newMinion.setAttackType(attackType);
        newMinion.getBuffsToCast().add(specialPower);
        return newMinion;
    }

    private Hero createNewCustomHeroCard(
            String name,
            int priceInDrake,
            int manaPoint,
            int fullHealth,
            int attackPower,
            int areaOfEffect,
            AttackType attackType,
            Buff specialPower,
            int coolDown) {
        Hero newHero = new Hero();
        newHero.setName(name);
        newHero.setPriceInDrake(priceInDrake);
        newHero.setManaPoint(manaPoint);
        newHero.setDescription(heroDescription.getText());
        newHero.setFullHealth(fullHealth);
        newHero.setAttackPower(attackPower);
        newHero.setAreaOfEffect(areaOfEffect);
        newHero.setAttackType(attackType);
        newHero.getBuffsToCast().add(specialPower);
        newHero.setCoolDown(coolDown);
        return newHero;
    }

    public Buff constructNewCustomBuff(Buff.Name name, int castTime, int value) {
        Buff newBuff = null;
        switch (name) {
            case ANTI:
                newBuff = new AntiBuff(null, castTime, 0, value);
                break;
            case ATTACK_POWER:
                newBuff = new AttackPowerBuff(null, castTime, 0, value);
                break;
            case ATTACK_WEAKNESS:
                newBuff = new AttackWeaknessBuff(null, castTime, 0, value);
                break;
            case DISARM:
                newBuff = new DisarmBuff(null, castTime, 0, value);
                break;
            case DISPELL:
                newBuff = new DispelBuff(null, castTime, 0, value);
                break;
            case HEALTH_POWER:
                newBuff = new HealthPowerBuff(null, castTime, 0, value);
                break;
            case HEALTH_WEAKNESS:
                newBuff = new HealthWeaknessBuff(null, castTime, 0, value);
                break;
            case HOLY:
                newBuff = new HolyBuff(null, castTime, 0, value);
                break;
            case MANA:
                newBuff = new ManaBuff(null, castTime, 0, value);
                break;
            case POSION:
                newBuff = new PosionBuff(null, castTime, 0, value);
                break;
            case STUN:
                newBuff = new StunBuff(null, castTime, 0, value);
                break;
        }
        return newBuff;
    }

    public void createNewCustomBuff(ActionEvent actionEvent) {
        setSpecialPower(constructNewCustomBuff(heroSpecialPower.getSelectionModel().getSelectedItem(),
                Integer.parseInt(castTime.getText()),
                Integer.parseInt(buffValue.getText())
        ));
        clearNewSpecialPowerFields();
        closeBuffCreationMenu();
    }

    private void clearNewSpecialPowerFields() {
        buffValue.clear();
        castTime.clear();
    }

    public void closeBuffCreationMenu() {
        buffCreationMenu.setVisible(false);
        stackPane.setEffect(null);
    }

    public void cancelImportGifsMenu() {
        heroMovementsGifSettingMenu.setVisible(false);
        minionMovementsGifSettingMenu.setVisible(false);
        stackPane.setEffect(null);
    }

    public void cancelBuffCreationMenu() {
        heroSpecialPower.getSelectionModel().clearSelection();
        minionSpecialPower.getSelectionModel().clearSelection();
        clearNewSpecialPowerFields();
        closeBuffCreationMenu();
    }

    public void setSpecialPower(Buff specialPower) {
        this.specialPower = specialPower;
    }

    public void openHeroImportGifMenu() {
        stackPane.setEffect(new GaussianBlur());
        heroMovementsGifSettingMenu.setVisible(true);
    }

    public void openMinionImportGifMenu() {
        stackPane.setEffect(new GaussianBlur());
        minionMovementsGifSettingMenu.setVisible(true);
    }

    public void importHeroMoveGifFromFile() {
        File selectedGif = importGifFile();
        copyHeroSelectedGifToProjectResources(selectedGif, state.RUN.toString().toLowerCase());
    }

    public void importHeroAttackGifFromFile() {
        File selectedGif = importGifFile();
        copyHeroSelectedGifToProjectResources(selectedGif, state.ATTACK.toString().toLowerCase());
    }

    public void importHeroIdleGifFromFile() {
        File selectedGif = importGifFile();
        copyHeroSelectedGifToProjectResources(selectedGif, state.IDLE.toString().toLowerCase());
    }

    public void importHeroBreathingGifFromFile() {
        File selectedGif = importGifFile();
        copyHeroSelectedGifToProjectResources(selectedGif, state.BREATHING.toString().toLowerCase());
    }

    public void importHeroDeathGifFromFile() {
        File selectedGif = importGifFile();
        copyHeroSelectedGifToProjectResources(selectedGif, state.DEATH.toString().toLowerCase());
    }


    public void importMinionMoveGifFromFile() {
        File selectedGif = importGifFile();
        copyMinionSelectedGifToProjectResources(selectedGif, state.RUN.toString().toLowerCase());
    }

    public void importMinionAttackGifFromFile() {
        File selectedGif = importGifFile();
        copyMinionSelectedGifToProjectResources(selectedGif, state.ATTACK.toString().toLowerCase());
    }

    public void importMinionIdleGifFromFile() {
        File selectedGif = importGifFile();
        copyMinionSelectedGifToProjectResources(selectedGif, state.IDLE.toString().toLowerCase());
    }

    public void importMinionBreathingGifFromFile() {
        File selectedGif = importGifFile();
        copyMinionSelectedGifToProjectResources(selectedGif, state.BREATHING.toString().toLowerCase());
    }

    public void importMinionDeathGifFromFile() {
        File selectedGif = importGifFile();
        copyMinionSelectedGifToProjectResources(selectedGif, state.DEATH.toString().toLowerCase());
    }

    private void copyHeroSelectedGifToProjectResources(File selectedGif, String state) {
        if (selectedGif != null)
            copyFileUsingChannel(selectedGif.getPath(), GIFS_FOLDER_PATH, heroName.getText() + "_" + state + ".gif");
    }

    private void copyMinionSelectedGifToProjectResources(File selectedGif, String state) {
        if (selectedGif != null)
            copyFileUsingChannel(selectedGif.getPath(), GIFS_FOLDER_PATH, minionAreaOfEffect.getText() + "_" + state + ".gif");
    }

    private File importGifFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a gif for move");
        File defaultDirectory = new File("d:/");
        fileChooser.setInitialDirectory(defaultDirectory);
        return fileChooser.showOpenDialog(Graphic.stage);
    }

    private static void copyFileUsingChannel(String sourceFilePath, String destinationFolderPath, String copiedFileName) {
        File source = new File(sourceFilePath);
        File dest = new File(destinationFolderPath + copiedFileName);
        try {
            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

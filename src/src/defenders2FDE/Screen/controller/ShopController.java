package defenders2FDE.Screen.controller;

import defenders2FDE.Manager.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopController {

    @FXML
    Button menuButton;
    @FXML
    private Button buyButton1;
    @FXML
    private Button buyButton2;
    @FXML
    private Button buyButton3;
    @FXML
    private Button buyButton4;
    @FXML
    private Button buyButton5;
    @FXML
    private Button buyButton6;
    @FXML
    private Label priceLabel1;
    @FXML
    private Label priceLabel2;
    @FXML
    private Label priceLabel3;
    @FXML
    private Label priceLabel4;
    @FXML
    private Label priceLabel5;
    @FXML
    private Label priceLabel6;
    @FXML
    private Label coinAmount;
    @FXML
    private Label priceTag1;
    @FXML
    private Label priceTag2;
    @FXML
    private Label priceTag3;
    @FXML
    private Label priceTag4;
    @FXML
    private Label priceTag5;
    @FXML
    private Label priceTag6;

    private Button[] labels;

    private String strPrice1 = "";
    private String strPrice2 = "";
    private String strPrice3 = "";
    private String strPrice4 = "";
    private String strPrice5 = "";
    private String strPrice6 = "";
    private String strCoin = "";
    private int intPrice1 = 0;
    private int intPrice2 = 0;
    private int intPrice3 = 0;
    private int intPrice4 = 0;
    private int intPrice5 = 0;
    private int intPrice6 = 0;
    private int coin = 0;
    private int[] items = new int[]{};
    private DataManager dataManager;

    public void initializeShopController(){
        if ( buyButton1 == null){
            System.out.println("NULL");
        }
        labels = new Button[]{buyButton1, buyButton2, buyButton3, buyButton4, buyButton5, buyButton6};
        System.out.println("Initializing shop controller...");
        dataManager = new DataManager();
        try {
            coin = dataManager.getCoin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        items = dataManager.getItems();
        setLabels();
    }

    public void buyButtonClicked(Button buyButton, Label priceLabel, Label coinLabel){
        if(buyButton.getText().equals("Buy")){

        }
    }

    private void setLabels(){

        for (int i = 1; i < items.length; i++ ){
            if ( items[i] == 1){
                if ( items[0] == i - 1){
                    labels[i - 1].setText("Equipped");
                }else{
                    labels[i - 1].setText("Equip");
                }
            }else{
                labels[i - 1].setText("Buy");
            }
        }
        coinAmount.setText(String.valueOf(coin));
    }

    public void buyButton1Clicked(){
        if ( items[0] != 0){
            items[0] = 0;
            try {
                dataManager.saveShopData();
                setLabels();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void buyButton2Clicked(){
        if ( items[2] == 0){
            if ( coin >= 2000){
                items[2] = 1;
                items[0] = 1;
                try {
                    dataManager.saveShopData();
                    dataManager.setCoin(-2000);
                    coin -= 2000;
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ( items[0] != 1){
                items[0] = 1;
                try {
                    dataManager.saveShopData();
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void buyButton3Clicked(){
        if ( items[3] == 0){
            if ( coin >= 3000){
                items[3] = 1;
                items[0] = 2;
                try {
                    dataManager.saveShopData();
                    dataManager.setCoin(-3000);
                    coin -= 3000;
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ( items[0] != 1){
                items[0] = 2;
                try {
                    dataManager.saveShopData();
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void buyButton4Clicked(){
        if ( items[4] == 0){
            if ( coin >= 4000){
                items[4] = 1;
                items[0] = 3;
                try {
                    dataManager.saveShopData();
                    dataManager.setCoin(-4000);
                    coin -= 4000;
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ( items[0] != 3){
                items[0] = 3;
                try {
                    dataManager.saveShopData();
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void buyButton5Clicked(){
        if ( items[5] == 0){
            if ( coin >= 5000){
                items[5] = 1;
                items[0] = 4;
                try {
                    dataManager.saveShopData();
                    dataManager.setCoin(-5000);
                    coin -= 5000;
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ( items[0] != 4){
                items[0] = 4;
                try {
                    dataManager.saveShopData();
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void buyButton6Clicked(){
        if ( items[6] == 0){
            if ( coin >= 6000){
                items[6] = 1;
                items[0] = 5;
                try {
                    dataManager.saveShopData();
                    dataManager.setCoin(-6000);
                    coin -= 6000;
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ( items[6] != 5){
                items[0] = 5;
                try {
                    dataManager.saveShopData();
                    setLabels();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void menuButtonClicked() throws Exception{
        Stage window;
        window = (Stage) menuButton.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("../fxml/MainScreen.fxml"));
        window.setScene(new Scene(root));
    }
}

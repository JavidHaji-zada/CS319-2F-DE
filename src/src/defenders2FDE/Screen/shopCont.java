package defenders2FDE.Screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//import org.jetbrains.annotations.NotNull;

public class shopCont {
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
    private int intCoin = 0;

    public void buyButtonClicked(Button buyButton, Label priceLabel, Label coinLabel){
        if(buyButton.getText().equals("Buy")){

        }
    }

    public void buyButton1Clicked(){

        strPrice1 = priceLabel1.getText();
        intPrice1 = Integer.parseInt(strPrice1);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton1.getText().equals("Buy") && intCoin >= intPrice1){
            intCoin = intCoin - intPrice1;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel1.setVisible(false);
            priceTag1.setVisible(false);
            buyButton1.setText("Equip");
        }

        if(buyButton1.getText().equals("Equip")){

        }

    }
    public void buyButton2Clicked(){
        strPrice2 = priceLabel2.getText();
        intPrice2 = Integer.parseInt(strPrice2);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton2.getText().equals("Buy") && intCoin >= intPrice2){
            intCoin = intCoin - intPrice2;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel2.setVisible(false);
            priceTag2.setVisible(false);
            buyButton2.setText("Equip");
        }

        if(buyButton2.getText().equals("Equip")){

        }
    }
    public void buyButton3Clicked(){
        strPrice3 = priceLabel3.getText();
        intPrice3 = Integer.parseInt(strPrice1);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton3.getText().equals("Buy") && intCoin >= intPrice3){
            intCoin = intCoin - intPrice3;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel3.setVisible(false);
            priceTag3.setVisible(false);
            buyButton3.setText("Equip");
        }

        if(buyButton3.getText().equals("Equip")){

        }
    }
    public void buyButton4Clicked(){
        strPrice4 = priceLabel4.getText();
        intPrice4 = Integer.parseInt(strPrice4);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton4.getText().equals("Buy") && intCoin >= intPrice4){
            intCoin = intCoin - intPrice4;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel4.setVisible(false);
            priceTag4.setVisible(false);
            buyButton4.setText("Equip");
        }

        if(buyButton4.getText().equals("Equip")){

        }
    }

    public void buyButton5Clicked(){
        strPrice5 = priceLabel5.getText();
        intPrice5 = Integer.parseInt(strPrice5);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton5.getText().equals("Buy") && intCoin >= intPrice5){
            intCoin = intCoin - intPrice5;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel5.setVisible(false);
            priceTag5.setVisible(false);
            buyButton5.setText("Equip");
        }

        if(buyButton5.getText().equals("Equip")){

        }
    }

    public void buyButton6Clicked(){
        strPrice6 = priceLabel6.getText();
        intPrice6 = Integer.parseInt(strPrice6);
        strCoin = coinAmount.getText();
        intCoin = Integer.parseInt(strCoin);

        if(buyButton6.getText().equals("Buy") && intCoin >= intPrice6){
            intCoin = intCoin - intPrice6;
            strCoin = "" + intCoin;
            coinAmount.setText(strCoin);
            priceLabel6.setVisible(false);
            priceTag6.setVisible(false);
            buyButton6.setText("Equip");
        }

        if(buyButton6.getText().equals("Equip")){

        }
    }
    public void menuButtonClicked() throws Exception{
        Stage window;
        window = (Stage) menuButton.getScene().getWindow();
        Pane root = FXMLLoader.load(getClass().getResource("fxml/MainScreen.fxml"));
        window.setScene(new Scene(root));
    }
}

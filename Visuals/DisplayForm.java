package Visuals;
import Transaction.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class DisplayForm {
    private TextField txf1 = new TextField();
    private Text t1 = new Text();
    private Text t2 = new Text();
    private Text t3 = new Text();
    private double fValue;
    private Button input;
    private ChoiceBox cbxType, cbxAccount;
    public DisplayForm(Text t1, Text t2, Text t3, int h1, int w1, int x1, int y1, int distance){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        t1.setFill(Color.GREY);
        t2.setFill(Color.GREY);
        t3.setFill(Color.GREY);
        t3.setX(x1-120);
        t3.setY(y1 +15);
        t2.setX(x1-120);
        t2.setY(y1+55);
        t1.setX(x1-120);
        t1.setY(y1+95);
        t1.setDisable(true);
        t2.setDisable(true);
        t3.setDisable(true);

        cbxType = new ChoiceBox();
        cbxType.getItems().addAll("Food","School","Recreation","Brittany","Bills");
        cbxType.setValue("Food");
        cbxType.setStyle("-fx-font: 16px \"Quicksand\";");

        cbxAccount = new ChoiceBox();
        cbxAccount.getItems().addAll("Chequing","Savings","Credit");
        cbxAccount.setValue("Chequing");
        cbxAccount.setStyle("-fx-font: 16px \"Quicksand\";");
        txf1.setPrefSize(90,10);

        cbxType.setLayoutX(x1);
        cbxType.setLayoutY(y1 + distance - 7);
        cbxAccount.setLayoutX(x1);
        cbxAccount.setLayoutY(y1 - 7 + distance * 2);
        txf1.setLayoutX(x1);
        txf1.setLayoutY(y1-7);
        cbxAccount.setLayoutX(x1);
        cbxAccount.setLayoutY(y1 + 2*distance - 7);
        input = new Button("Record");
        input.setLayoutX(x1 - 45);
        input.setLayoutY(y1 + 113);
        FadeChoiceBox(1.0,0.0,1,cbxType);
        FadeChoiceBox(1.0,0.0,1,cbxAccount);
        FadeText(1.0,0.0,1,t1);
        FadeText(1.0,0.0,1,t2);
        FadeText(1.0,0.0,1,t3);
        FadeTextField(1.0,0.0,1,txf1);
        FadeButton(1.0,0.0,1,input);
        disableControls(true);

        fValue = 0;
    }
    public String[] register(){
        String[] ret;
        ret = new String[4];
        ret[0] = txf1.getText();
        ret[1] = cbxType.getValue().toString();
        ret[2] = cbxAccount.getValue().toString();
        return ret;
    }
    public void disableControls(boolean f){
        txf1.setDisable(f);
        cbxType.setDisable(f);
        cbxAccount.setDisable(f);
        //cbxType.setVisible(!f);
        //cbxAccount.setVisible(!f);
        //txf1.setVisible(!f);
        input.setDisable(f);

    }
    public void displayText(Text t1, Text t2, Text t3,double from, double to){
        FadeText(from,to,500,t1);
        FadeText(from,to,500,t2);
        FadeText(from,to,500,t3);
        FadeTextField(from,to,300,getTxf1());
        //fade cbx
        FadeChoiceBox(from,to,300,cbxType);
        FadeChoiceBox(from,to,300,cbxAccount);
        FadeButton(from, to, 300,getInput());
    }
    public static void FadeChoiceBox(double f, double g, double duration,ChoiceBox p) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public static void FadeButton(double f, double g, double duration,Button p){
        FadeTransition ft = new FadeTransition(Duration.millis(duration),p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public static void FadeText(double f, double g, double duration,Text p){
        FadeTransition ft = new FadeTransition(Duration.millis(duration),p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public static void FadeTextField(double f, double g, double duration,TextField p){
        FadeTransition ft = new FadeTransition(Duration.millis(duration),p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public TextField getTxf1() {
        return txf1;
    }

    public void setTxf1(TextField txf1) {
        this.txf1 = txf1;
    }

    public Text getT1() {
        return t1;
    }

    public void setT1(Text t1) {
        this.t1 = t1;
    }

    public Text getT2() {
        return t2;
    }

    public void setT2(Text t2) {
        this.t2 = t2;
    }

    public Text getT3() {
        return t3;
    }

    public void setT3(Text t3) {
        this.t3 = t3;
    }

    public double getfValue() {
        return fValue;
    }

    public void setfValue(double fValue) {
        this.fValue = fValue;
    }

    public Button getInput() {
        return input;
    }

    public void setInput(Button input) {
        this.input = input;
    }

    public ChoiceBox getCbxType() {
        return cbxType;
    }

    public void setCbxType(ChoiceBox cbxType) {
        this.cbxType = cbxType;
    }

    public ChoiceBox getCbxAccount() {
        return cbxAccount;
    }

    public void setCbxAccount(ChoiceBox cbxAccount) {
        this.cbxAccount = cbxAccount;
    }
}

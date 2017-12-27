package Visuals;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class spendingForm {
    private Text search, spent, last;
    private ChoiceBox<String> cbxSType;
    private double fValue;

    public spendingForm() {
        search = new Text("Search");
        search.setFill(Color.GREY);
        search.setX(35);
        search.setY(371);
        spent = new Text("Amount Spent: ");
        spent.setFill(Color.GREY);
        spent.setX(35);
        spent.setY(421);
        last = new Text("Last purchase: ");
        last.setFill(Color.GREY);
        last.setX(35);
        last.setY(471);
        cbxSType = new ChoiceBox();
        cbxSType.getItems().addAll("Food","School","Recreation","Brittany","Bills");
        cbxSType.setValue("Food");
        cbxSType.setLayoutX(170);
        cbxSType.setLayoutY(348);
        cbxSType.setStyle("-fx-font: 16px \"Quicksand\";");
        FadeText(1,0,700,search);
        FadeText(1,0,700,spent);
        FadeText(1,0,700,last);
        //fade cbx
        FadeChoiceBox(1,0,700,cbxSType);
        disableControls(true);
    }
    public void disableControls(boolean f){
        cbxSType.setDisable(f);
    }
    public void displayText(Text t1, Text t2, Text t3,double from, double to){
        FadeText(from,to,700,t1);
        FadeText(from,to,700,t2);
        FadeText(from,to,700,t3);
        //fade cbx
        FadeChoiceBox(from,to,700,cbxSType);
    }
    public static void FadeChoiceBox(double f, double g, double duration, ChoiceBox p) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public static void FadeButton(double f, double g, double duration, Button p) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public static void FadeText(double f, double g, double duration, Text p) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public static void FadeTextField(double f, double g, double duration, TextField p) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public Text getSearch() {
        return search;
    }

    public void setSearch(Text search) {
        this.search = search;
    }

    public ChoiceBox<String> getCbxSType() {
        return cbxSType;
    }

    public void setCbxSType(ChoiceBox<String> cbxSType) {
        this.cbxSType = cbxSType;
    }

    public Text getSpent() {
        return spent;
    }

    public void setSpent(Text spent) {
        this.spent = spent;
    }

    public Text getLast() {
        return last;
    }

    public void setLast(Text last) {
        this.last = last;
    }

    public double getfValue() {
        return fValue;
    }

    public void setfValue(double fValue) {
        this.fValue = fValue;
    }
}
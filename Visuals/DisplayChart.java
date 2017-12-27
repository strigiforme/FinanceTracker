package Visuals;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

//Displaychart class, contains collection of necessary methods and data to maintain the central piechart in the program

public class DisplayChart {
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    private PieChart pieChart = new PieChart(data);
    private PieChart.Data one;
    private PieChart.Data two;
    private PieChart.Data three;
    private Text xVal,yVal,zVal;
    private double xEnd, yEnd, zEnd;

    public DisplayChart(double x, double y, double z){
        one = new PieChart.Data("one", x);
        two = new PieChart.Data("two", y);
        three = new PieChart.Data("three", z);
        xVal = new Text(x+"");
        yVal = new Text(y+"");
        zVal = new Text(z+"");
        xVal.setFill(Color.GREY);
        yVal.setFill(Color.GREY);
        zVal.setFill(Color.GREY);

        data.addAll(one, two, three);
        pieChart.setLayoutX(-105);
        pieChart.setLayoutY(-50);
        one.getNode().setScaleX(0.53);
        one.getNode().setScaleY(0.53);
        two.getNode().setScaleX(0.53);
        two.getNode().setScaleY(0.53);
        three.getNode().setScaleX(0.53);
        three.getNode().setScaleY(0.53);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(false);

        // make sure our x y z are always adding to 360(because it's a circle)
        double ratio = 360/(x + y + z);
        //set values for starts and ends
        xEnd = -(ratio) * x;
        yEnd = (-(ratio) * y);
        zEnd = (-(ratio) * z);

        double locationx = 290/2;
        double locationy = 150;


        zVal.setDisable(true);
        yVal.setDisable(true);
        xVal.setDisable(true);

        //place text in proper area
        xVal.setX(locationx - 13 + (Math.cos(Math.toRadians(-xEnd/2)) * 75));
        xVal.setY(locationy + 4+ (Math.sin(Math.toRadians(-xEnd/2)) * 75));
        //FOR YVALUE TEXT
        yVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
        yVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
        //FOR ZVALUE TEXT
        zVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
        zVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
    }

    //animation methods
    public static void magnifyX(double scale, double ratio, double x, PieChart.Data o, Text t1,boolean back){
        ratio = -1 * (Math.abs(ratio / 6)) + 0.78;
        if(back) {
            Scale(0.53, o);
            TranslateText(0, 0, t1, false);
        }
        else {
            Scale(-1 * (Math.abs(scale / 6)) + 0.78, o);
            TranslateText(Math.sin(Math.toRadians(-x / 2)) * (ratio * 26), Math.cos(Math.toRadians(-x / 2)) * (ratio * 26), t1, false);
        }
    }
    public static void magnifyY(double scale, double ratio, double x, double y, PieChart.Data o, Text t1,boolean back){
        ratio = -1 * (Math.abs(ratio / 6)) + 0.78;
        if ( ratio > 1)
            ratio = ratio - 1;
        if(back) {
            Scale(0.53, o);
            TranslateText(0,0,t1,false);
        }
        else {
            Scale(-1 * (Math.abs(scale / 6)) + 0.78, o);
            TranslateText(Math.sin(Math.toRadians( -y/2 + -x)) * (Math.pow(ratio,2) * 30),Math.cos(Math.toRadians((-y/2 - x))) * (Math.pow(ratio,2) * 30),t1,false);
        }
    }
    public static void magnifyZ(double scale, double ratio, double x, double y,double z, PieChart.Data o, Text t1,boolean back){
        if(back) {
            Scale(0.53, o);
            TranslateText(Math.sin(Math.toRadians(0)), Math.cos(Math.toRadians(0)), t1, false);
        }
        else {
            Scale(-1 * (Math.abs(scale / 6)) + 0.78, o);
            TranslateText(Math.sin(Math.toRadians((-z / 2) + -y + -x))  * (60/ratio), Math.cos(Math.toRadians((-z / 2) + -y + -x)) * (60/ratio), t1, false);
        }
    }
    public static void TranslateText(double f, double g, Text p, boolean appear) {
        TranslateTransition ts = new TranslateTransition(Duration.millis(700), p);
        ts.setToY(f);
        ts.setToX(g);
        ts.setCycleCount(1);
        ts.setAutoReverse(false);
        FadeTransition ft = new FadeTransition(Duration.millis(200),p);
        ft.setFromValue(1.0);
        if(appear)
            ft.setToValue(0);
        else
            ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(ft,ts);
        pt.play();
    }
    public static void Scale(double f, PieChart.Data p) {
        ScaleTransition st = new ScaleTransition(Duration.millis(700),p.getNode());
        setScale(st, f,f);
        st.play();
    }
    public static void setScale(ScaleTransition st, double h,double g) {
        st.setToX(h);
        st.setToY(g);
        st.setCycleCount(1);
        st.setAutoReverse(false);
    }

    //getters and setters
    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    public PieChart.Data getOne() {
        return one;
    }

    public void setOne(PieChart.Data one) {
        this.one = one;
    }

    public PieChart.Data getTwo() {
        return two;
    }

    public void setTwo(PieChart.Data two) {
        this.two = two;
    }

    public PieChart.Data getThree() {
        return three;
    }

    public void setThree(PieChart.Data three) {
        this.three = three;
    }

    public ObservableList<PieChart.Data> getData() {
        return data;
    }

    public void setData(ObservableList<PieChart.Data> data) {
        this.data = data;
    }

    public Text getxVal() {
        return xVal;
    }

    public void setxVal(Text xVal) {
        this.xVal = xVal;
    }

    public Text getyVal() {
        return yVal;
    }

    public void setyVal(Text yVal) {
        this.yVal = yVal;
    }

    public Text getzVal() {
        return zVal;
    }

    public void setzVal(Text zVal) {
        this.zVal = zVal;
    }

    public double getxEnd() {
        return xEnd;
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public double getyEnd() {
        return yEnd;
    }

    public void setyEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    public double getzEnd() {
        return zEnd;
    }

    public void setzEnd(double zEnd) {
        this.zEnd = zEnd;
    }
}

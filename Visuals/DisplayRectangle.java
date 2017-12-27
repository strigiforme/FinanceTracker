package Visuals;
//DisplayRectangle class contains a lot of information, in each rectangle is nested a Label, rectangle, methods to animate/manipulate
//and a displayForm, which contains even more data

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DisplayRectangle {
    private Rectangle r;
    private double width,height;
    private Text label;
    private DisplayForm d;
    private boolean open = false;

    //Constructor
    public DisplayRectangle(Color c, double x, double y,double width,double height,String label, double tx, double ty){
        r = new Rectangle(x,y,width,height);
        r.setFill(c);
        this.width = width;
        this.height = height;
        this.label = new Text(label);
        StyleText(this.label,tx,ty);
    }

    //Method for setting multiple values of label text. Kinda uneeded
    public void StyleText(Text t,double x, double y){
        t.setFont(Font.font("Quicksand",25));
        t.setX(x);
        t.setY(y);
        t.setFill(Color.GREY);
        t.setDisable(true);
    }
    //this is a method where I can toss all of the parameters used in an animation to move a rectangle
    //using it from memory is incredibly difficult, even is when looking directly at it, but it conserves a TON of space
    public void RectAnimation(DisplayRectangle focus, DisplayRectangle m, DisplayRectangle n, double xScale,
                                     double yScale, double ryMove, double tr1, double tr2, double tt1, double tt2,
                                     double tt3, boolean fade1, boolean fade2, boolean back, double duration) {
        m.getR().setDisable(!back);
        n.getR().setDisable(!back);
        ScaleRect(xScale,yScale,ryMove,focus.getR(),duration);
        TranslateRect(tr1,m.getR(),duration);
        TranslateRect(tr2,n.getR(),duration);
        TranslateText(tt1,0,focus.getLabel(),fade2,duration);
        TranslateText(tt2,0,n.getLabel(),fade1,duration);
        TranslateText(tt3,0,m.getLabel(),fade1,duration);
    }
    //method to save space, was repeating this in many scale methods. Should be able to get rid of it when
    //I figure out how to use generics in a method
    //Animation methods
    public static void displayText(Text t1, Text t2, Text t3,double from, double to){
        FadeText(from,to,1000,t1);
        FadeText(from,to,1000,t2);
        FadeText(from,to,1000,t3);

    }
    //this method moves and fades text along with the rectangle
    public static void TranslateText(double f, double g, Text p, boolean appear,double duration) {
        TranslateTransition ts = new TranslateTransition(Duration.millis(duration), p);
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
    //this method only fades text
    public static void FadeText(double f, double g, double duration,Text p){
        FadeTransition ft = new FadeTransition(Duration.millis(duration),p);
        ft.setFromValue(f);
        ft.setToValue(g);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    //this method moves a rectangle across the screen, used for visuals
    public static void TranslateRect(double f, Rectangle p, double duration) {
        TranslateTransition ts = new TranslateTransition(Duration.millis(duration), p);
        ts.setToY(f);
        ts.setCycleCount(1);
        ts.setAutoReverse(false);
        ts.play();
    }
    public static void ScaleRect(double f,double g,double h, Rectangle p,double duration) {
        ScaleTransition st = new ScaleTransition(Duration.millis(duration),p);
        setScale(st,f,g);
        TranslateTransition ts = new TranslateTransition(Duration.millis(duration),p);
        ts.setToY(h);
        ts.setCycleCount(1);
        ts.setAutoReverse(false);
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(st,ts);
        pt.play();
    }
    public static void ScaleRect(double f,double g,double h, Rectangle p) {
        ScaleTransition st = new ScaleTransition(Duration.millis(700),p);
        setScale(st,f,g);
        TranslateTransition ts = new TranslateTransition(Duration.millis(700),p);
        ts.setToY(h);
        ts.setCycleCount(1);
        ts.setAutoReverse(false);
        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(st,ts);
        pt.play();
    }
    public static void setScale(ScaleTransition st, double h,double g) {
        st.setToX(h);
        st.setToY(g);
        st.setCycleCount(1);
        st.setAutoReverse(false);
    }


    //Getters and setters
    public Text getLabel() {
        return label;
    }

    public void setLabel(Text label) {
        this.label = label;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public DisplayForm getD() {
        return d;
    }

    public void setD(DisplayForm d) {
        this.d = d;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Arc; 
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.animation.ScaleTransition;
import javafx.scene.chart.*;

public class testingAnimation extends Application{
   @Override
   public void start(Stage stage) {
	  //Declaration of major variables in project
	  //Line for decoration
	  Line l1 = new Line(20,20,100,20);
	  l1.setStroke(Color.rgb(27,27,29,1.0));
	  //rectangles to make program look good
	  Rectangle r1 = new Rectangle(0,290,290,60);
	  r1.setFill(Color.rgb(27,27,29,1.0));
	  Rectangle r2 = new Rectangle(0,360,290,60);
	  r2.setFill(Color.rgb(27,27,29,1.0));
	  Rectangle r3 = new Rectangle(0,430,290,60);
	  r3.setFill(Color.rgb(27,27,29,1.0));
      //Variables referring to different values(bank acc values) 
      double x = 780;
      double y = 70;
      double z = 120;
      //update location of circle and text values with one variable
      double locationx = 290/2;
      double locationy = 150;
      //total variable
      double total = z + y + x;
      //variables for where each arc begins and ends
      double xStart;
      double xEnd;
      double yStart;
      double yEnd;
      double zStart;
      double zEnd;
      //text variables for display
      Text xVal,yVal,zVal,spending,history,budget;

      
      //initialize our variables
      //make sure our x y z are always adding to 360(because it's a circle)
      double ratio = 360/total;
      //set values for starts and ends
      xStart = 0;
      xEnd = -(ratio) * x;
      yStart = xEnd;
      yEnd = (-(ratio) * y);
      zStart = yEnd + xEnd;
      zEnd = (-(ratio) * z);
      //create text to show values of the sections
      budget = new Text("budget");
      budget.setFont(Font.font("Quicksand",25));
      budget.setX(20);
      budget.setY(467);
      budget.setFill(Color.GREY);
      history = new Text("history");
      history.setFont(Font.font("Quicksand", 25));
      history.setX(20);
      history.setY(397);
      history.setFill(Color.GREY);
      spending = new Text("spending");
      spending.setFont(Font.font("Quicksand", 25));
      spending.setX(20);
      spending.setY(327);
      spending.setFill(Color.GREY);
      xVal = new Text(x + " ");
      xVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      yVal = new Text(y + " ");
      yVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      zVal = new Text(z + " ");
      zVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      zVal.setFill(Color.GREY);
      yVal.setFill(Color.GREY);
      xVal.setFill(Color.GREY);
      
      //place text in proper area
      xVal.setX(locationx - 13 + (Math.cos(Math.toRadians(-xEnd/2)) * 75));
      xVal.setY(locationy + 4+ (Math.sin(Math.toRadians(-xEnd/2)) * 75));

      
      yVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
      yVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-yEnd/2) + -xEnd)) * 75));

   
      zVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      zVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));

      
    //  Color granite = new Color(99,101,100,255);
    //  Color blu = new Color(39,70,144,255);
    //  Color charcoal = new Color(54,65,86,255);
    //  Color blck = new Color(23,33,33,255);
    //  Color smoke = new Color(125,126,117,255);
      
      ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
      PieChart pieChart = new PieChart(data);
      PieChart.Data one = new PieChart.Data("one", x);
      PieChart.Data two = new PieChart.Data("two", y);
      PieChart.Data three = new PieChart.Data("three", z);
      data.addAll(one, two, three);
      //styling the piechart to fit well on page
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
      
      //Circle to highlight pi chart
	  Circle c1 = new Circle(locationx,locationy,105);
	  c1.setFill(Color.rgb(39,40,39,1.0));
	  
	  //MouseEvents for everything
	  //mouseEvents for first pie slice
	  one.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(700),one.getNode());
          st.setByX(0.1);
          st.setByY(0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
	  one.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(700),one.getNode());
          st.setByX(-0.1);
          st.setByY(-0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
	  //set mouseEvents for second Pie slice
	  two.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(700),two.getNode());
          st.setByX(0.1);
          st.setByY(0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
	  two.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(1000),two.getNode());
          st.setByX(-0.1);
          st.setByY(-0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
	  //Set mouseEvents for third pie slice
	  three.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(1000),three.getNode());
          st.setByX(0.1);
          st.setByY(0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
	  three.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
          ScaleTransition st = new ScaleTransition(Duration.millis(1000),three.getNode());
          st.setByX(-0.1);
          st.setByY(-0.1);
          st.setCycleCount(1);
          st.setAutoReverse(false);
          st.play();
      });
      
      //set up everything else
      Group root = new Group();
      root.getChildren().addAll(c1,xVal,yVal,zVal,r1,r2,r3,l1,spending,history,budget,pieChart);
      Scene scene = new Scene(root, 290,500);
      scene.getStylesheets().add("style.css");
      scene.setFill(Color.rgb(34,34,36,1.0));
      stage.setTitle("Testing Visual elements");
      stage.setScene(scene);
      stage.show();
   }   
   public static void main(String[] args){
      launch(args);
   }
}

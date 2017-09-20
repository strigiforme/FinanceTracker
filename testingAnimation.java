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
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
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
      double x = 580;
      double y = 200;
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
      xEnd = -(ratio) * x;
      yEnd = (-(ratio) * y);
      zEnd = (-(ratio) * z);
      //create text to show values of the sections
      //text for Spending menu
      Text amount = new Text("amount");
      amount.setFont(Font.font("Qucksand",20));
      amount.setX(20);
      amount.setY(360);
      amount.setFill(Color.GREY);
      amount.setDisable(true);
      Text type = new Text("type");
      type.setFont(Font.font("Qucksand",20));
      type.setX(20);
      type.setY(385);
      type.setFill(Color.GREY);
      type.setDisable(true);
      Text account = new Text("account");
      account.setFont(Font.font("Qucksand",20));
      account.setX(20);
      account.setY(415);
      account.setFill(Color.GREY);
      account.setDisable(true);
      //Text created for visual labels in program
      //text for budget label
      budget = new Text("budget");
      budget.setFont(Font.font("Quicksand",25));
      budget.setX(20);
      budget.setY(467);
      budget.setFill(Color.GREY);
      budget.setDisable(true);
      //text for history label
      history = new Text("history");
      history.setFont(Font.font("Quicksand", 25));
      history.setX(20);
      history.setY(397);
      history.setFill(Color.GREY);
      history.setDisable(true);
      //text for spending label
      spending = new Text("spending");
      spending.setFont(Font.font("Quicksand", 25));
      spending.setX(20);
      spending.setY(327);
      spending.setFill(Color.GREY);
      spending.setDisable(true);
      //text to hold data values for piechart
      xVal = new Text(x + " ");
      xVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      yVal = new Text(y + " ");
      yVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      zVal = new Text(z + " ");
      zVal.setFont(Font.font("Quicksand", FontWeight.BOLD, 12));
      zVal.setDisable(true);
      yVal.setDisable(true);
      xVal.setDisable(true);
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
	  
	  //MouseEvent for rectangle1
	  	  r1.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
	  		  RectAnimation(r1,r2,r3,1,4,90,150,150,0,150,150,true,false,spending,history,budget,false,700);
	  	  });
	  	r1.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	  		 RectAnimation(r1,r2,r3,1,1,0,0,0,0,0,0,false,false,spending,history,budget,true,700);
	  	  });
	  //MouseEvents for rectangle2
	  	r2.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
	  		  RectAnimation(r2,r1,r3,1,3.3,0,150,150,-70,220,150,true,false,history,spending,budget,false,500);
	  	  });
	  	r2.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	  		RectAnimation(r2,r1,r3,1,1,0,0,0,0,0,0,false,false,history,spending,budget,true,700);
	  	  });
	  //MouseEvents for rectangle3
	  	r3.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	  		RectAnimation(r3,r2,r1,1,4,-51,240,170,-140,220,170,true,false,budget,history,spending,false,500);
	  	});
	  	r3.addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent ->{
	  		RectAnimation(r3,r1,r2,1,1,0,0,0,0,0,0,false,false,budget,spending,history,true,700);
	  	});
	  //mouseEvents for first pie slice
		  one.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
			  if( x < (y + z + x) / 8){
				  Scale(0.78,one);
				  TranslateText(Math.sin(Math.toRadians(-xEnd/2)) * 48,Math.cos(Math.toRadians( -xEnd/2)) * 48,xVal,false); 
				  ScaleText(1.3,xVal);
			  }
			  else if ( x < (y + z + x) / 6) {
				  Scale(0.75,one);
				  TranslateText(Math.sin(Math.toRadians(-xEnd/2)) * 38,Math.cos(Math.toRadians( -xEnd/2)) * 38,xVal,false);
				  ScaleText(1.3,xVal);
			  }
			  else if ( x < (y + z + x) / 4) {
				  Scale(0.7,one);
				  TranslateText(Math.sin(Math.toRadians(-xEnd/2)) * 30,Math.cos(Math.toRadians( -xEnd/2)) * 30,xVal,false);
				  ScaleText(1.3,xVal);
			  }
			  else {
				  Scale(0.63,one);
				  TranslateText(Math.sin(Math.toRadians(-xEnd/2)) * 13,Math.cos(Math.toRadians( -xEnd/2)) * 25,xVal,false);
				  ScaleText(1.3,xVal);
			  }
			  
	      });
		  one.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	          Scale(0.53,one);
	          TranslateText(0,0,xVal,false);
	          ScaleText(1,xVal);
	      });
		  
		  //set mouseEvents for second Pie slice
		  two.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
			  if( y < (y + z + x) / 8) {
				  Scale(0.78,two);
				  TranslateText(Math.sin(Math.toRadians( -yEnd/2 + -xEnd)) * 48,Math.cos(Math.toRadians((-yEnd/2 - xEnd))) * 48,yVal,false);
				  ScaleText(1.3,yVal);
			  }  
			  else if ( y < (y + z + x) / 6) {
				  Scale(0.75,two);
				  TranslateText(Math.sin(Math.toRadians( -yEnd/2 + -xEnd)) * 38,Math.cos(Math.toRadians((-yEnd/2 - xEnd)) * 38),yVal,false);
				  ScaleText(1.3,yVal);
			  }
			  else if ( y < (y + z + x) / 4) {
				  Scale(0.7,two);
				  TranslateText(Math.sin(Math.toRadians( -yEnd/2 + -xEnd)) * 30,Math.cos(Math.toRadians((-yEnd/2 - xEnd)) * 30),yVal,false);
				  ScaleText(1.3,yVal);
			  }
			  else {
				  Scale(0.63,two);
				  TranslateText(Math.sin(Math.toRadians( -yEnd/2 + -xEnd)) * 25,Math.cos(Math.toRadians((-yEnd/2 - xEnd)) * 25),yVal,false);
				  ScaleText(1.3,yVal);
			  }
	      });
		  two.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	          Scale(0.53,two);
	          TranslateText(0,0,yVal,false);
	          ScaleText(1,yVal);
	      });
		  //Set mouseEvents for third pie slice
		  three.getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
			  if( x < (z + z + x) / 8) {
				  Scale(0.78,three);
			  	  TranslateText(Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 46 ,Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 46,zVal,false);
			  	  ScaleText(1.3,zVal);
			  }
			  else if ( z < (y + z + x) / 6) {
				  Scale(0.75,three);
				  TranslateText(Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 38,Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 38,zVal,false);
				  ScaleText(1.3,zVal);
			  }
			  else if ( z < (y + z + x) / 4) {
				  Scale(0.7,three);
				  TranslateText(Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 30,Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 30,zVal,false);
				  ScaleText(1.3,zVal);
			  }
			  else {
				  Scale(0.63,three);
				  TranslateText(Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 25,Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 30,zVal,false);
				  ScaleText(1.3,zVal);
			  }
	      });
		  three.getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	          Scale(0.53,three);
	          TranslateText(0,0,zVal,false);
	          ScaleText(1,zVal);
	      });

      //set up everything else
      Group root = new Group();
      root.getChildren().addAll(c1,pieChart,xVal,yVal,zVal,r1,r2,r3,l1,spending,history,budget); //amount,type,account
      Scene scene = new Scene(root, 290,500);
      scene.getStylesheets().add("darkStyle.css");
      scene.setFill(Color.rgb(34,34,36,1.0));
      stage.setTitle("Testing Visual elements");
      stage.setScene(scene);
      stage.show();
   }   
   public static void main(String[] args){
      launch(args);
   }
   //this is a method where I can toss all of the parameters used in an animation to move a rectangle
   //using it from memory is incredibly difficult, even is when looking directly at it, but it conserves a TON of space
   public static void RectAnimation(Rectangle focus,Rectangle m, Rectangle n,double xScale,
		  double yScale,double ryMove,double tr1,double tr2,double tt1,double tt2,
		  double tt3, boolean fade1, boolean fade2, Text t1, Text t2, Text t3, boolean back,double duration) {
	      m.setDisable(!back);
		  n.setDisable(!back);
		  //r1.setFill(Color.rgb(27,27,29,1.0));
		  ScaleRect(xScale,yScale,ryMove,focus,duration);
		  TranslateRect(tr1,m,duration);
		  TranslateRect(tr2,n,duration);
		  TranslateText(tt1,0,t1,fade2,duration);
		  TranslateText(tt2,0,t2,fade1,duration);
		  TranslateText(tt3,0,t3,fade1,duration);
   }
   //method to save space, was repeating this in many scale methods. Should be able to get rid of it when
   //I figure out how to use generics in a method
   public static void setScale(ScaleTransition st, double h,double g) {
	     st.setToX(h);
	     st.setToY(g);
	     st.setCycleCount(1);
	     st.setAutoReverse(false);
   }
   public static void Scale(double f, PieChart.Data p) {
	     ScaleTransition st = new ScaleTransition(Duration.millis(700),p.getNode());
	     setScale(st, f,f);
	     st.play();
   }
   public static void ScaleText(double f, Text t) {
	   ScaleTransition st = new ScaleTransition(Duration.millis(700),t);
	   setScale(st, f,f);
	   st.play();
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
   public static void TranslateRect(double f, Rectangle p, double duration) {
	   TranslateTransition ts = new TranslateTransition(Duration.millis(duration), p);
	   ts.setToY(f);
	   ts.setCycleCount(1);
	   ts.setAutoReverse(false);
	   ts.play();
   }
   public static void TranslateRect(double f, Rectangle p) {
	   TranslateTransition ts = new TranslateTransition(Duration.millis(700), p);
	   ts.setToY(f);
	   ts.setCycleCount(1);
	   ts.setAutoReverse(false);
	   ts.play();
   }
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
}

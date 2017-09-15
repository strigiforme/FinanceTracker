import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Arc; 
import javafx.scene.shape.Rectangle;
import javafx.application.Application; 
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class testingAnimation extends Application{
   @Override
   public void start(Stage stage){
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
      Text xVal,yVal,zVal,spending,history,data;

      
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
      data = new Text("data");
      data.setFont(Font.font("Quicksand",25));
      data.setX(20);
      data.setY(467);
      data.setFill(Color.GREY);
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
      xVal.setFont(Font.font("Kollectif", FontWeight.BOLD, 12));
      yVal = new Text(y + " ");
      yVal.setFont(Font.font("Kollectif", FontWeight.BOLD, 12));
      zVal = new Text(z + " ");
      zVal.setFont(Font.font("Kollectif", FontWeight.BOLD, 12));
      zVal.setFill(Color.GREY);
      yVal.setFill(Color.GREY);
      xVal.setFill(Color.GREY);
      //place text in proper area
      xVal.setX(locationx - 13 + (Math.cos(Math.toRadians(-xEnd/2)) * 75));
      xVal.setY(locationy + 4+ (Math.sin(Math.toRadians(-xEnd/2)) * 75));
      System.out.println("XVAL " + xVal);      
      System.out.println(xEnd/2);
      
      yVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
      yVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
      System.out.println("YVAL " + yVal);
      System.out.println(xEnd + (yEnd/2));
   
      zVal.setX(locationx - 13 + (Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      zVal.setY(locationy + 4 + (Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      System.out.println("ZVAL " + zVal);
      System.out.println(xEnd + yEnd + (zEnd/2));
      
    //  Color granite = new Color(99,101,100,255);
    //  Color blu = new Color(39,70,144,255);
    //  Color charcoal = new Color(54,65,86,255);
    //  Color blck = new Color(23,33,33,255);
    //  Color smoke = new Color(125,126,117,255);
      
      //Set up the arcs to create a full circle
      Arc arc1 = new Arc();
      Arc arc2 = new Arc();
      Arc arc3 = new Arc();
      arc1.setFill(Color.rgb(35,25,43,1.0));
      arc1.setCenterX(locationx);
      arc1.setCenterY(locationy);
      arc1.setRadiusX(100.0f);
      arc1.setRadiusY(100.0f);
      arc1.setStartAngle(xStart);
      arc1.setLength(xEnd);
      arc1.setType(ArcType.ROUND);
      //arc2
      arc2.setFill(Color.rgb(39,70,144,1.0));
      arc2.setCenterX(locationx);
      arc2.setCenterY(locationy);
      arc2.setRadiusX(100.0f);
      arc2.setRadiusY(100.0f);
      arc2.setStartAngle(yStart);
      arc2.setLength(yEnd);
      arc2.setType(ArcType.ROUND);
      //arc3
      arc3.setFill(Color.rgb(54,65,86,1.0));
      arc3.setCenterX(locationx);
      arc3.setCenterY(locationy);
      arc3.setRadiusX(100.0f);
      arc3.setRadiusY(100.0f);
      arc3.setStartAngle(zStart);
      arc3.setLength(zEnd);
      arc3.setType(ArcType.ROUND);
      
    //Circle to highlight pi chart
	  Circle c1 = new Circle(locationx,locationy,105);
	  c1.setFill(Color.rgb(39,40,39,1.0));
      
      //set up everything else
      Group root = new Group();
      root.getChildren().addAll(c1,arc1,arc2,arc3,xVal,yVal,zVal,r1,r2,r3,l1,spending,history,data);
      Scene scene = new Scene(root, 290,500);
      scene.setFill(Color.rgb(34,34,36,1.0));
      stage.setTitle("Testing Visual elements");
      stage.setScene(scene);
      stage.show();
      
      for(int a = 50; x < 200; x++) {
    	  arc1.setRadiusX(a);
      }
      
   }
   public static void main(String[] args){
      launch(args);
   }
}


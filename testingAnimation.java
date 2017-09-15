import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Arc; 
import javafx.application.Application; 
import javafx.scene.shape.*;
import javafx.scene.text.Text;

public class testingAnimation extends Application{
   @Override
   public void start(Stage stage){
      //Variables referring to different values(bank acc values) 
      double x = 780;
      double y = 70;
      double z = 120;
      
      double location = 250;
      
      //total variable
      double total = z + y + x;
      //variables for where each arc begins and ends
      double xStart;
      double xEnd;
      double yStart;
      double yEnd;
      double zStart;
      double zEnd;
      //make sure our x y z are always adding to 360(because it's a circle
      double ratio = 360/total;
      //set values for starts and ends
      xStart = 0;
      xEnd = -(ratio) * x;
      yStart = xEnd;
      yEnd = (-(ratio) * y);
      zStart = yEnd + xEnd;
      zEnd = (-(ratio) * z);
      //create text to show values of the sections
      Text xVal = new Text(x + " ");
      Text yVal = new Text(y + " ");
      Text zVal = new Text(z + " ");
      //place text in proper area
      xVal.setX(location - 13 + (Math.cos(Math.toRadians(-xEnd/2)) * 75));
      xVal.setY(location + 4+ (Math.sin(Math.toRadians(-xEnd/2)) * 75));
      System.out.println("XVAL " + xVal);      
      System.out.println(xEnd/2);
      
      yVal.setX(location - 13 + (Math.cos(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
      yVal.setY(location + 4 + (Math.sin(Math.toRadians((-yEnd/2) + -xEnd)) * 75));
      System.out.println("YVAL " + yVal);
      System.out.println(xEnd + (yEnd/2));
   
      zVal.setX(location - 13 + (Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      zVal.setY(location + 4 + (Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      System.out.println("ZVAL " + zVal);
      System.out.println(xEnd + yEnd + (zEnd/2));
      
    //  Color granite = new Color(99,101,100,255);
    //  Color blu = new Color(39,70,144,255);
    //  Color charcoal = new Color(54,65,86,255);
    //  Color blck = new Color(23,33,33,255);
    //  Color smoke = new Color(125,126,117,255);
      Arc arc1 = new Arc();
      Arc arc2 = new Arc();
      Arc arc3 = new Arc();
      arc1.setFill(Color.rgb(99,101,100,1.0));
      arc1.setCenterX(location);
      arc1.setCenterY(location);
      arc1.setRadiusX(100.0f);
      arc1.setRadiusY(100.0f);
      arc1.setStartAngle(xStart);
      arc1.setLength(xEnd);
      arc1.setType(ArcType.ROUND);
      //arc2
      arc2.setFill(Color.rgb(39,70,144,1.0));
      arc2.setCenterX(location);
      arc2.setCenterY(location);
      arc2.setRadiusX(100.0f);
      arc2.setRadiusY(100.0f);
      arc2.setStartAngle(yStart);
      arc2.setLength(yEnd);
      arc2.setType(ArcType.ROUND);
      //arc3
      arc3.setFill(Color.rgb(54,65,86,1.0));
      arc3.setCenterX(location);
      arc3.setCenterY(location);
      arc3.setRadiusX(100.0f);
      arc3.setRadiusY(100.0f);
      arc3.setStartAngle(zStart);
      arc3.setLength(zEnd);
      arc3.setType(ArcType.ROUND);
      //set up everything else
      Group root = new Group();
      root.getChildren().addAll(arc1,arc2,arc3,xVal,yVal,zVal);
      Scene scene = new Scene(root, 500,500);
      stage.setTitle("Testing Visual elements");
      stage.setScene(scene);
      stage.show();
      
   }
   public static void main(String[] args){
      launch(args);
   }
}

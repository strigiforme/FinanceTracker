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
      //set up arc   
      double x = 180;
      double y = 60;
      double z = 120;
      
      double total = z + y + x;
      
      double xStart;
      double xEnd;
      double yStart;
      double yEnd;
      double zStart;
      double zEnd;
      
      double ratio = 360/total;
      
      xStart = 0;
      xEnd = -(ratio) * x;
      yStart = xEnd;
      yEnd = (-(ratio) * y);
      zStart = yEnd + xEnd;
      zEnd = (-(ratio) * z);
      
      Text xVal = new Text(x + " ");
      Text yVal = new Text(y + " ");
      Text zVal = new Text(z + " ");
      
      xVal.setX(250 + (Math.cos(Math.toRadians(-xEnd/2)) * 75));
      System.out.println(xEnd/2);
      xVal.setY(250 + (Math.sin(Math.toRadians(-xEnd/2)) * 75));
      System.out.println("XVAL " + xVal);      
      System.out.println(xEnd);
      
      yVal.setX(250 + (Math.cos(Math.toRadians((-yEnd)/2 + -xEnd)) * 75));
      yVal.setY(250 + (Math.sin(Math.toRadians((-yEnd)/2 + -xEnd)) * 75));
      System.out.println("YVAL " + yVal);
      System.out.println(xEnd + (yEnd)/2);
      
      zVal.setX(250 + (Math.cos(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      zVal.setY(250 + (Math.sin(Math.toRadians((-zEnd/2) + -yEnd + -xEnd)) * 75));
      System.out.println("ZVAL " + zVal);
      System.out.println(xEnd + yEnd + (zEnd)/2);
    //  Color granite = new Color(99,101,100,255);
    //  Color blu = new Color(39,70,144,255);
    //  Color charcoal = new Color(54,65,86,255);
    //  Color blck = new Color(23,33,33,255);
    //  Color smoke = new Color(125,126,117,255);
      Arc arc1 = new Arc();
      Arc arc2 = new Arc();
      Arc arc3 = new Arc();
      arc1.setFill(Color.rgb(99,101,100,1.0));
      arc1.setCenterX(250.0f);
      arc1.setCenterY(250.0f);
      arc1.setRadiusX(100.0f);
      arc1.setRadiusY(100.0f);
      arc1.setStartAngle(xStart);
      arc1.setLength(xEnd);
      arc1.setType(ArcType.ROUND);
      //arc2
      arc2.setFill(Color.rgb(39,70,144,1.0));
      arc2.setCenterX(250.0f);
      arc2.setCenterY(250.0f);
      arc2.setRadiusX(100.0f);
      arc2.setRadiusY(100.0f);
      arc2.setStartAngle(yStart);
      arc2.setLength(yEnd);
      arc2.setType(ArcType.ROUND);
      //arc3
      arc3.setFill(Color.rgb(54,65,86,1.0));
      arc3.setCenterX(250.0f);
      arc3.setCenterY(250.0f);
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
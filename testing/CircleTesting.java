//This was originally supposed to go on stack overflow because I couldn't solve the original problem
//but it was made very clear once I got a bigger picture
//imports
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Arc; 
import javafx.application.Application; 
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;

public class CircleTesting extends Application{
   @Override
   public void start(Stage stage){
	  //create text objects that hold values to display their location on circle
	  Text t1 = new Text("PI / 4");
	  Text t2 = new Text("PI / 2");
	  Text t3 = new Text("3PI / 4");
	  Text t4 = new Text("PI");
	  Text t5 = new Text("5PI / 4");
	  Text t6 = new Text("3PI / 2");
	  Text t7 = new Text("7PI / 4");
	  Text t8 = new Text("2PI");
	  
	  //Set text to easily readable color against circle
	  t1.setFill(Color.WHITE);
	  t2.setFill(Color.WHITE);
	  t3.setFill(Color.WHITE);
	  t4.setFill(Color.WHITE);
	  t5.setFill(Color.WHITE);
	  t6.setFill(Color.WHITE);
	  t7.setFill(Color.WHITE);
	  t8.setFill(Color.WHITE);
	  
	  //Set coordinates of text based on degree
	  setCoords(t1,45);//pi/4
	  setCoords(t2,90);//pi/2
	  setCoords(t3,135);//3pi/4
	  setCoords(t4,180);//pi
	  setCoords(t5,225);//5pi/4
	  setCoords(t6,270);//3pi/2
	  setCoords(t7,315);//7pi/4
	  setCoords(t8,360);//2pi
	  
	  //create circle for text to display against
      Circle c1 = new Circle(263.0,245.0,200);
      //make circle black(ish)
      c1.setFill(Color.rgb(99,101,100,1.0));
      //create root group
      Group root = new Group();
      //set up window
      //add nodes to root
      root.getChildren().addAll(c1,t1,t2,t3,t4,t5,t6,t7,t8);
      Scene scene = new Scene(root, 500,500);
      stage.setTitle("Testing Circles and Cos functions");
      stage.setScene(scene);
      stage.show();
      
   }
   //main method
   public static void main(String[] args){
      launch(args);
   }
   //method to set coordinates
   public void setCoords(Text t, double degrees) {
	   t.setX(250 + Math.cos(Math.toRadians(degrees)) * 180);
	   t.setY(250 + Math.sin(Math.toRadians(degrees)) * 180);
   }
}

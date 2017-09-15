import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.application.Application; 
import javafx.scene.shape.*;
import javafx.scene.text.Text;

public class AnimatingDemo extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) {
		  //create arcs
		Arc arc1 = new Arc();
	      Arc arc2 = new Arc();
	      Arc arc3 = new Arc();
	      arc1.setFill(Color.rgb(35,25,43,1.0));
	      arc1.setCenterX(250);
	      arc1.setCenterY(250);
	      arc1.setRadiusX(100.0f);
	      arc1.setRadiusY(100.0f);
	      arc1.setStartAngle(315);
	      arc1.setLength(-90);
	      arc1.setType(ArcType.ROUND);
	      //arc2
	      arc2.setFill(Color.rgb(39,70,144,1.0));
	      arc2.setCenterX(250);
	      arc2.setCenterY(250);
	      arc2.setRadiusX(100.0f);
	      arc2.setRadiusY(100.0f);
	      arc2.setStartAngle(90);
	      arc2.setLength(-135);
	      arc2.setType(ArcType.ROUND);
	      //arc3
	      arc3.setFill(Color.rgb(54,65,86,1.0));
	      arc3.setCenterX(250);
	      arc3.setCenterY(250);
	      arc3.setRadiusX(100.0f);
	      arc3.setRadiusY(100.0f);
	      arc3.setStartAngle(225);
	      arc3.setLength(-135);
	      arc3.setType(ArcType.ROUND);
	      
	      //create root group
	      Group root = new Group();
	      //set up window
	      //add nodes to root
	      root.getChildren().addAll(arc1,arc2,arc3);
	      Scene scene = new Scene(root, 500,500);
	      stage.setTitle("Testing arc animation");
	      stage.setScene(scene);
	      stage.show();
	}
}

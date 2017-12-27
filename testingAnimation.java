import Visuals.*;
import Transaction.*;
import java.lang.StringBuilder;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.*;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testingAnimation extends Application{
    Account a;
    DisplayForm d1;
    DateFormat dform;
    Date today;
    List<Transaction> tList;
    spendingForm s;

   @Override
   public void start(Stage stage) throws IOException {
       //Miscellaneous declaration of objects such as date, dateformat, scanners, etc.
       today = new Date();
       dform = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       Color rColor = (Color.rgb(27,27,29,1.0));

       double x = 580;
       double y = 200;
       double z = 120;

       File purchaseRecords = new File("src/purchaseRecords.txt");
       File accountInfo = new File("src/accountInfo.txt");
       Scanner accreader = new Scanner(accountInfo);
       StringBuilder sb = new StringBuilder();
       if(accreader.hasNext()) {
           while (accreader.hasNext()) {
               sb.append(accreader.next());
           }
           String temp = sb.toString();
           String[] accData = temp.split("~");
           String[] accName = accData[1].split(":");
           String[] accCheq = accData[2].split(":");
           String[] accSav = accData[3].split(":");
           String[] accCred = accData[4].split(":");

           x = Double.parseDouble(accCheq[1]);
           y = Double.parseDouble(accSav[1]);
           z = Double.parseDouble(accCred[1]);
       }
       else{
           x = 0;
           y = 0;
           z = 0;
       }
        /*
       //Read from saved file of purchases to upload to current program
       File purchaseRecords = new File("C:/Users/Howard Pearce/workspace/FinanceTracker/src/purchaseRecords.txt");
       Scanner flReader = new Scanner(purchaseRecords);
       tList = new List<>();

       if(!flReader.hasNext()){
           x = 0;
           y = 0;
           z = 0;
       }
       else{
           while(flReader.hasNext()){
               String temp = flReader.nextLine();
               String[] arrTemp = temp.split(" ");
               tList.add(new Transaction(arrTemp[0],Integer.parseInt(arrTemp[1]),arrTemp[2],arrTemp[3],arrTemp[4]));
           }
       }
        */

	  //Initialize our displayrectangles to show information and options
	  DisplayRectangle r1 = new DisplayRectangle(rColor,0,290,290,60,"spending",20,327);
	  DisplayRectangle r2 = new DisplayRectangle(rColor,0,360,290,60, "history",20,397);
	  DisplayRectangle r3 = new DisplayRectangle(rColor, 0, 430,290,60,"budget",20,467);

      //DisplayChart initialization to show userData
      DisplayChart pchart = new DisplayChart(x,y,z);

      Text account = new Text("account");
      Text amount = new Text("amount");
      Text type = new Text("type");
      d1 = new DisplayForm(account,type,amount,30,10,155,346,40);
      s = new spendingForm();

      d1.getInput().setOnAction(this::processButtonPress);

      //Circle to highlight pi chart
	  Circle c1 = new Circle(290/2,150,105);
	  c1.setFill(Color.rgb(39,40,39,1.0));
	  //MouseEvent for rectangle1
       r1.getR().addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           if(!r1.isOpen()) {
               r1.RectAnimation(r1,r2,r3,1,4,90,150,150,0,150,150,true,false,false,700);
               if (d1.getfValue() == 0) {
                   d1.displayText(type, account, amount, 0, 1);
                   d1.setfValue(1);
                   d1.disableControls(false);
                   r1.setOpen(true);
               }
           }
           else if(r1.isOpen()){
               r1.RectAnimation(r1, r2, r3, 1, 1, 0, 0, 0, 0, 0, 0, false, false, true, 700);
               if(d1.getfValue() == 1){
                   d1.displayText(type,account,amount,1,0);
                   d1.setfValue(0);
                   d1.disableControls(true);

                   r1.setOpen(false);
               }
           }
       });
	  	r2.getR().addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
	  	    if(!r2.isOpen()) {
                r2.RectAnimation(r2, r1, r3, 1, 3.3, 0, 150, 150, -70, 150, 220, true, false, false, 500);
                if (d1.getfValue() == 0) {
                    s.displayText(s.getLast(), s.getSpent(), s.getSearch(), 0, 1);
                    s.setfValue(1);
                    s.disableControls(false);
                    r2.setOpen(true);
                }
	  	    }
            else if (r2.isOpen()){
                r2.RectAnimation(r2,r1,r3,1,1,0,0,0,0,0,0,false,false,true,700);
                if (d1.getfValue() == 0) {
                    s.displayText(s.getLast(), s.getSpent(), s.getSearch(), 1, 0);
                    s.setfValue(0);
                    s.disableControls(true);
                    r2.setOpen(false);
                }
            }
	  	  });
	   //MouseEvents for rectangle3
	  	r3.getR().addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	  	    if(!r3.isOpen()) {
                r3.RectAnimation(r3, r2, r1, 1, 4, -51, 240, 170, -140, 220, 170, true, false, false, 500);
                r3.setOpen(true);
	  	    }
            else if(r3.isOpen()){
                r3.RectAnimation(r3,r1,r2,1,1,0,0,0,0,0,0,false,false,true,700);
                r3.setOpen(false);
            }
	  	});

	  	double xEnd = pchart.getxEnd();
	  	double yEnd = pchart.getyEnd();
	  	double zEnd = pchart.getzEnd();

	  	double xScale = x / ((x + y + z));
	  	double yScale =  y / (x + y + z);
	  	double zScale = z / (x + y + z);

	   //mouseEvents for first pie slice
		  pchart.getOne().getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
			  pchart.magnifyX(xScale,xScale,xEnd,pchart.getOne(),pchart.getxVal(),false);
	      });
		  pchart.getOne().getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
	          pchart.magnifyX(0.53,1,0,pchart.getOne(),pchart.getxVal(),true);
	      });

		  //set mouseEvents for second Pie slice
		  pchart.getTwo().getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
		  	pchart.magnifyY(yScale, 16*yScale,xEnd,yEnd,pchart.getTwo(),pchart.getyVal(),false);
	      });
		  pchart.getTwo().getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
			  pchart.magnifyY(0.53,1,0,0,pchart.getTwo(),pchart.getyVal(),true);
	      });
		  //Set mouseEvents for third pie slice
		  pchart.getThree().getNode().addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
			  pchart.magnifyZ(zScale, 16*zScale,xEnd,yEnd,zEnd,pchart.getThree(),pchart.getzVal(),false);
	      });
		  pchart.getThree().getNode().addEventFilter(MouseEvent.MOUSE_EXITED, mouseEvent -> {
			  pchart.magnifyZ(0.53,1,0,0,0,pchart.getThree(),pchart.getzVal(),true);
	      });

      //set up everything else
	   Pane p = new Pane();
	   p.getChildren().addAll(c1,pchart.getPieChart(),pchart.getxVal(),pchart.getyVal(),pchart.getzVal(),r1.getR(),r2.getR(),r3.getR(),r1.getLabel(),r2.getLabel(),r3.getLabel(),d1.getT1(),d1.getT2(),d1.getT3(),d1.getTxf1(),d1.getCbxType(),d1.getCbxAccount(),d1.getInput(),s.getSearch(),s.getCbxSType(),s.getSpent(),s.getLast());
       Group root = new Group();


       Scene scene = new Scene(p, 290,500);
       scene.getStylesheets().add("style.css");
       scene.setFill(Color.rgb(34,34,36,1.0));
       stage.setTitle("Testing Visual elements");
       stage.setScene(scene);
       stage.show();
   }
   public void processButtonPress(ActionEvent event){
       if(event.getSource() == d1.getInput()){
           System.out.println("Test");
       }
   }
   public static void main(String[] args){
      launch(args);
   }
}
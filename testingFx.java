/*
Howard Pearce
2017/08/26
This is a program to track my finances using javafx
Personal Project
*/

//To do in program:
//Create functionality in menubars
//sleek down and make the program look nice, have proper interface
//add more functions and abilities (graph especially)


//Javafx imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;

//Utility imports
import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.text.DecimalFormat;
//Data structure imports
import java.util.ArrayList;
//Date imports
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//File manipulation imports
import java.io.File;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;

public class testingFx extends Application{
   //Create controls
   //Using notation that involves naming each variable with a prefix indicating datatype
   private Button btnTransaction, btnSearch, btnExit, btnWithdraw, btnDeposit, btnPay;
   private Scene scnMain, scnPopWindow; 
   private File fleRecords, fleAccountInfo;
   private FileWriter fwrRecords,fwrAccountInfo;
   private Stage popStage;
   private Text txtType,txtItem,txtAmount,txtTotalBalance,txtBalanceValue,txtSeperator,txtChequing,txtSavings,txtCredit,txtChequingValue,txtSavingsValue,txtCreditValue,txtSearchValue;
   private Text txtTransaction1,txtTransaction2,txtTransaction3,txtTransaction4,txtTransaction5,txtTransaction6,txtTransaction7,txtSearch;
   private ChoiceBox cbxAccounts, cbxPurchaseType, cbxSearchBox;
   private TextField txfInput, txfPurchase, txfMiscData;
   private Account accUser;
   private ArrayList<Transaction> arrylstReceipt;
   private Date dteDate;
   private DateFormat dateFormat;
   private Rectangle rctBackdrop;
   private VBox vbxAccountTotals, vbxTransactionList;
   private FileReader flrRecordReader,flrAccountReader;
   private String[] strArrRecordedData, strArrTemp;
   private NumberFormat nbfFormatter;
   private MenuBar mbrMain;
   private Menu mnuView,mnuSpacer;
   private MenuItem mniPay;
   
   //Main method
   public static void main(String[] args){
      launch(args);
   }
   @Override
   public void start(Stage stage) throws IOException{ 
   
      //Create another stage for popup
      popStage = new Stage();
         
      /*This section of code is dedicated to reading and parsing strings from files to
      upload information for accurate record-keeping and persistency. If this part is 
      going to be edited please create a backup of the current record files so they aren't lost
      on accident.*/     
      //Create files
      fleRecords = new File("records.txt");
      fleAccountInfo = new File("accountInfo.txt");
      //create data Structure to hold receipt list during runtime
      arrylstReceipt = new ArrayList();
      //fleRecords = new File("C:/Users/Howard Pearce/Documents/FinanceTracker/records.txt");
      //fleAccountInfo = new File("C:/Users/Howard Pearce/Documents/FinanceTracker/accountInfo.txt");
      //Create scanners to read from files
      Scanner inRec = new Scanner(fleRecords);
      Scanner inAcc = new Scanner(fleAccountInfo);
      String fileString = "";
      //read in from accountinfo file
      StringBuilder sb = new StringBuilder();
      while(inAcc.hasNext()) {
        sb.append(inAcc.next());
      }
      inAcc.close();
      fileString = sb.toString();
      //split string into components
      String[] accData = fileString.split("~");
      //Load the values from the parsed string into an account object
      String[] accName = accData[1].split(":");
      String[] accCheq = accData[2].split(":");
      String[] accSav = accData[3].split(":");
      String[] accCred = accData[4].split(":");
      //create new account
      accUser = new Account(accName[1],Double.parseDouble(accCheq[1]),Double.parseDouble(accSav[1]),Double.parseDouble(accCred[1]));
      fileString = "";
      //read in from the records file
      StringBuilder sbr = new StringBuilder();
      while(inRec.hasNext()){
         sbr.append(inRec.next());
      }
      inRec.close();
      fileString = sbr.toString();
      strArrRecordedData = fileString.split("~");
      //add all the purchases to a receipt arraylist for internal organization
      for(int x = 1; x <= strArrRecordedData.length - 1; x++){
         strArrTemp = strArrRecordedData[x].split(":");
         Transaction tempTran = new Transaction(strArrTemp[0],Double.parseDouble(strArrTemp[2]),strArrTemp[1],strArrTemp[3],strArrTemp[4]);
         arrylstReceipt.add(tempTran);
      }
      //End of filewriting operations     
      
      
      //this section conists of initializing and styling controls
      //Miscellaneous declaration of objects and variables
      //Set up date and date object
      dteDate = new Date();
      dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
      //set up decimal formatter
      nbfFormatter = new DecimalFormat("#0.00");
      //Create FileWriters and readers
      fwrAccountInfo = new FileWriter(fleAccountInfo);
      fwrRecords = new FileWriter(fleRecords);
      flrAccountReader = new FileReader(fleAccountInfo);  
      flrRecordReader = new FileReader(fleRecords);
      
      //Javafx controls and visual display intialization
      //initialize menubar and menu objects
      mbrMain = new MenuBar();
      mnuSpacer = new Menu("                                                                                      ");
      mnuSpacer.setDisable(true);
      mnuView = new Menu("View");
      mniPay = new MenuItem("Pay View");
      mnuView.getItems().add(mniPay);
      mbrMain.getMenus().addAll(mnuView,mnuSpacer);
      //Create actions for the menuItems
      mniPay.setOnAction(new EventHandler<ActionEvent>() {
          @Override public void handle(ActionEvent e) {
              System.out.println("Opening Database Connection...");
          }
      });
      //Initialize textfields          
      txfInput = new TextField();
      txfPurchase = new TextField();  
      //Initialize choiceboxes
      cbxSearchBox = new ChoiceBox();
      cbxSearchBox.getItems().addAll("School","Food","Tech","Building","btnPay","Misc");
      cbxSearchBox.setValue("Food");
      cbxPurchaseType = new ChoiceBox();
      cbxPurchaseType.getItems().addAll("School","Food","Tech","Building","btnPay","Misc");
      cbxPurchaseType.setValue("Food");
      cbxAccounts = new ChoiceBox();
      cbxAccounts.getItems().addAll("Chequing","Savings","Credit");
      cbxAccounts.setValue("Chequing");
      //Format and create rectangle for spicy visuals
      rctBackdrop = new Rectangle();
      rctBackdrop.setWidth(130);
      rctBackdrop.setHeight(170);
      //Fill in rectangle
      rctBackdrop.setFill(Color.LIGHTGRAY);
      rctBackdrop.setStroke(Color.LIGHTGRAY);  
      //Initialize and style text
      txtTransaction1 = new Text();
      txtTransaction2 = new Text();
      txtTransaction3 = new Text();
      txtTransaction4 = new Text();
      txtTransaction5 = new Text();
      txtTransaction6 = new Text();
      txtTransaction7 = new Text();
      txtSearchValue = new Text("0.0");
      txtSearch = new Text("Search for:");
      txtChequingValue = new Text(Double.toString(accUser.getChequing()));
      txtSavingsValue = new Text(Double.toString(accUser.getSavings()));
      txtCreditValue = new Text(Double.toString(accUser.getCredit()));
      txtChequing = new Text("Chequing:");
      txtSavings = new Text("Savings:");
      txtCredit = new Text("Credit:");
      txtSeperator = new Text("Your Accounts:  -------------");
      txtBalanceValue = new Text(Double.toString(accUser.getChequing() + accUser.getSavings() + accUser.getCredit()));
      txtTotalBalance = new Text("Total Bal:");
      txtAmount = new Text("Amt:");
      txtType = new Text("  Type of purchase:");
      txtItem = new Text("     Item purchased:");
      txtSearchValue.setFill(Color.BLUE);
      txtAmount.setFill(Color.BLUE);
      txtType.setFill(Color.BLUE);
      txtItem.setFill(Color.BLUE);
      txtChequingValue.setFill(Color.BLUE);
      txtSavingsValue.setFill(Color.BLUE);
      txtCreditValue.setFill(Color.BLUE);
      txtTotalBalance.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
      txtBalanceValue.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
      txtBalanceValue.setFill(Color.BLUE);
      txtSeperator.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 12));   
      //Initialize buttons
      btnSearch = new Button();
      btnSearch.setText("Search");
      btnSearch.setOnAction(this::processButtonPress);
      btnWithdraw = new Button();
      btnDeposit = new Button();
      btnExit = new Button();
      btnTransaction = new Button();
      btnExit.setText("Exit");
      btnExit.setOnAction(this::processButtonPress);
      btnWithdraw.setText("Withdraw");
      btnWithdraw.setOnAction(this::processButtonPress);
      btnDeposit.setText(" Deposit ");
      btnDeposit.setOnAction(this::processButtonPress);
      btnTransaction.setText("Transaction");
      btnTransaction.setOnAction(this::processButtonPress);
      
      
      //Create all root nodes and format them
      Group root = new Group();
      Group PopRoot = new Group();
      GridPane grid = new GridPane();
      GridPane popPane = new GridPane();
      GridPane grdMenu = new GridPane();
      vbxAccountTotals = new VBox(22);
      vbxTransactionList = new VBox(8);
      
      //Adding rows and cols to gridpanes
      for (int x = 0; x <= 35; x++){
         grid.getColumnConstraints().add(new ColumnConstraints(10));
      }
      for (int x = 0; x <= 50; x++){
         grid.getRowConstraints().add(new RowConstraints(10));
      }
      for (int x = 0; x <= 26; x++){
         popPane.getColumnConstraints().add(new ColumnConstraints(10));
      }
      for (int x = 0; x <= 18; x++){
         popPane.getRowConstraints().add(new RowConstraints(10));
      }

      //set constraints for popup stage
      popPane.setConstraints(txfPurchase,15,5,8,1);
      popPane.setConstraints(txtItem,1,5,5,1);
      popPane.setConstraints(txtType,1,2,4,1);
      popPane.setConstraints(txtAmount,3,8,2,1);
      popPane.setConstraints(cbxAccounts,6,8,8,1);
      popPane.setConstraints(cbxPurchaseType,15,2,8,1);
      popPane.setConstraints(txfInput,15,8,8,1);
      popPane.setConstraints(btnWithdraw,4,13,8,1);
      popPane.setConstraints(btnDeposit,17,13,8,1);
      //add them to root of the popup stage
      popPane.getChildren().addAll(btnWithdraw,btnDeposit,txfInput,cbxPurchaseType,cbxAccounts,txtAmount,txtType,txtItem,txfPurchase);
      
      //set child nodes for VBox
      vbxAccountTotals.getChildren().addAll(txtChequingValue,txtSavingsValue,txtCreditValue);
      vbxTransactionList.getChildren().addAll(txtTransaction1,txtTransaction2,txtTransaction3,txtTransaction4,txtTransaction5,txtTransaction6,txtTransaction7);
      
      //Set constraints for main stage
      grid.setConstraints(btnSearch,10,22,6,1);
      grid.setConstraints(txtSearchValue,12,19,3,1);
      grid.setConstraints(cbxSearchBox,2,22,7,1);
      grid.setConstraints(txtSearch,2,19,3,1);
      grid.setConstraints(rctBackdrop,19,12,7,7);
      grid.setConstraints(vbxTransactionList,19,15,3,1);
      grid.setConstraints(txtChequing,2,8,3,1);
      grid.setConstraints(txtSavings,2,12,3,1);
      grid.setConstraints(txtCredit,2,16,3,1);
      grid.setConstraints(vbxAccountTotals,12,12,3,1);
      grid.setConstraints(txtSeperator,2,6,6,1);
      grid.setConstraints(txtBalanceValue,12,4,3,1);
      grid.setConstraints(txtTotalBalance,2,4,8,1);
      grid.setConstraints(btnExit,29,45,12,1);
      grid.setConstraints(btnTransaction,21,4,10,1);
      grdMenu.setConstraints(mbrMain,0,0);
      //add all child nodes
      grdMenu.getChildren().addAll(mbrMain);
      grid.getChildren().addAll(btnExit,btnTransaction,txtTotalBalance,txtBalanceValue,txtSeperator,vbxAccountTotals,txtChequing,txtSavings,txtCredit,rctBackdrop,vbxTransactionList,txtSearch,cbxSearchBox,txtSearchValue,btnSearch);
      updateGUI();
      
      //Nest nodes
      root.getChildren().add(grid);
      root.getChildren().add(grdMenu);
      PopRoot.getChildren().add(popPane);
      scnMain = new Scene(root,350,500);
      scnPopWindow = new Scene(PopRoot,260,180);
      popStage.setScene(scnPopWindow);
      popStage.setTitle("Pay manager");
      stage.setTitle("Finance Tracker");
      stage.setScene(scnMain);
      stage.show();
   }
   public void processButtonPress(ActionEvent event){
     if (event.getSource() == btnSearch){
         double tempTot = 0;
         for(int x = 0; x < arrylstReceipt.size();x++){
            if(arrylstReceipt.get(x).getType().equals(cbxSearchBox.getValue())){
               tempTot += arrylstReceipt.get(x).getAmt();
            }
         }
         txtSearchValue.setText(Double.toString(tempTot));
     }
     if (event.getSource() == btnExit){
       try{
         fwrAccountInfo.write("~name:" + accUser.getName() + "\r\n~chequing:" + accUser.getChequing() + "\r\n~savings:" + accUser.getSavings() + "\r\n~credit:" + accUser.getCredit());
         for (int x = 0; x < arrylstReceipt.size(); x++){
            fwrRecords.write(arrylstReceipt.get(x).record() + " \r\n");
         }
         flrRecordReader.close();
         fwrAccountInfo.close();
         fwrRecords.close();
         System.exit(0);
       }
       catch(IOException e){
         e.printStackTrace();
       }
     }
     if (event.getSource() == btnTransaction){
         popStage.showAndWait();
     }
     if (event.getSource() == btnWithdraw){
         String strDate = dateFormat.format(dteDate);
         double amount = (Double.parseDouble(txfInput.getCharacters().toString()));
         Transaction temp = new Transaction("Withdrawal",amount,cbxPurchaseType.getValue().toString(),strDate,txfPurchase.getCharacters().toString());
         arrylstReceipt.add(temp);
         
         //Change money values for accounts
         if (cbxAccounts.getValue().equals("Chequing")){
            accUser.setChequing(Double.parseDouble(nbfFormatter.format(accUser.getChequing() - amount)));
         }
         else if(cbxAccounts.getValue().equals("Savings")){
            accUser.setSavings(Double.parseDouble(nbfFormatter.format(accUser.getSavings() - amount)));
         }
         else if(cbxAccounts.getValue().equals("Credit")){
            accUser.setCredit(Double.parseDouble(nbfFormatter.format(accUser.getCredit() - amount)));
         }
         updateGUI();
         updateTranList();
     }
     if (event.getSource() == btnDeposit){
         String strDate = dateFormat.format(dteDate);
         double amount = (Double.parseDouble(txfInput.getCharacters().toString()));
         Transaction temp = new Transaction("Deposit",amount,cbxPurchaseType.getValue().toString(),strDate,txfPurchase.getCharacters().toString());
         arrylstReceipt.add(temp);
         
         //Change money values for accounts
         if (cbxAccounts.getValue().equals("Chequing")){
            accUser.setChequing(Double.parseDouble(nbfFormatter.format(accUser.getChequing() + amount)));
         }
         else if(cbxAccounts.getValue().equals("Savings")){
            accUser.setSavings(Double.parseDouble(nbfFormatter.format(accUser.getSavings() + amount)));
         }
         else if(cbxAccounts.getValue().equals("Credit")){
            accUser.setCredit(Double.parseDouble(nbfFormatter.format(accUser.getCredit() + amount)));
         }
         updateGUI();
         updateTranList();
     }
   }
   public void updateGUI(){
      txtChequingValue.setText(nbfFormatter.format(accUser.getChequing()));
      txtSavingsValue.setText(nbfFormatter.format(accUser.getSavings()));
      txtCreditValue.setText(nbfFormatter.format(accUser.getCredit()));
      txtBalanceValue.setText(nbfFormatter.format(accUser.getChequing() + accUser.getSavings() + accUser.getCredit()));
      updateTranList();
   }
   public void updateTranList(){
      if (arrylstReceipt.size() >= 7){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
         txtTransaction3.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 3)));
         txtTransaction4.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 4)));
         txtTransaction5.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 5)));
         txtTransaction6.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 6)));
         txtTransaction7.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 7)));
      }
      else if (arrylstReceipt.size() == 6){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
         txtTransaction3.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 3)));
         txtTransaction4.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 4)));
         txtTransaction5.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 5)));
         txtTransaction6.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 6)));
      }
      else if (arrylstReceipt.size() == 5){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
         txtTransaction3.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 3)));
         txtTransaction4.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 4)));
         txtTransaction5.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 5)));
      }
      else if (arrylstReceipt.size() == 4){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
         txtTransaction3.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 3)));
         txtTransaction4.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 4)));  
      }
      else if (arrylstReceipt.size() == 3){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
         txtTransaction3.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 3)));
      }
      else if (arrylstReceipt.size() == 2){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
         txtTransaction2.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 2)));
      }
      else if (arrylstReceipt.size() == 1){
         txtTransaction1.setText(alignFix(arrylstReceipt.get(arrylstReceipt.size() - 1)));
      }
   }
   public String alignFix(Transaction r){
      //this method fixes the alignment(somewhat) of a string so it looks centered
      //have a temporary variable hold original length of string
      String s = r.writeHistory();
      int temp = s.length();
      if (s.length() < 24){
         if (s.length()%2 != 0){
            for (int x = 0; x < ((-1)*(temp - 24))/2; x++){
               s = "  " + s;
            }
            for (int x = 0; x < ((-1)*(temp - 24))/2; x++){
               s = s + " ";
            }
         }
         else{
            for (int x = 0; x < ((-1)*(temp - 24))/2; x++){
               s = "  " + s;
            }
            for (int x = 0; x < ((-1)*(temp - 24))/2; x++){
               s = s + " ";
            }
         }
      }
      else if (s.length() == 23){
      
      }
      else if(s.length() > 24){
         Transaction x = new Transaction(r);
         x.setData(x.getData().substring(0,4) + "...");
         s = x.writeHistory();
      }
      System.out.println(s);
      return s;
   } 
}
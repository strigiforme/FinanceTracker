import Transaction.*;
import java.io.*;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.StringBuilder;

public class filereader {
    public filereader(){}

    public double[] getBal() throws IOException{
        double x;
        double y;
        double z;

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

        double[] retval = new double[3];
        retval[0] = x;
        retval[1] = y;
        retval[2] = z;

        return retval;
    }

    public TransactionList getreceipt() throws IOException{
        TransactionList retval = new TransactionList();

        File purchaseRecords = new File("src/records.txt");
        Scanner recreader = new Scanner(purchaseRecords);
        StringBuilder sb = new StringBuilder();
        if(recreader.hasNext()) {
            while (recreader.hasNext()) {
                sb.append(recreader.next());
            }
            String temp = sb.toString();
            String[] accData = temp.split("~");

            for(int x = 0; x < accData.length; x++){
                if(!accData[x].equals("") && accData[x] != null) {
                    String[] tranData = accData[x].split(":");
                    retval.add(new Transaction(tranData[0], Double.parseDouble(tranData[2]), tranData[1], tranData[3], ""));
                }
            }
        }
        return retval;
    }
}

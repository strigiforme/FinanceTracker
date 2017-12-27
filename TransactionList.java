import Transaction.*;

public class TransactionList {
    List<Transaction> tlist;

    public TransactionList(){
        tlist = new List<>();
    }

    public TransactionList(Transaction t){
        tlist = new List<>();
        tlist.add(t);
    }

    public void add(Transaction t){
        tlist.add(t);
    }

    public String toString(int i){
        String ret = new String();
        if(i > tlist.size()){
            i = tlist.size();
        }
        for(int x = 0; x < i; x++){
            ret += tlist.get(x).toString() + "\r\n";
        }
        return ret;
    }
}

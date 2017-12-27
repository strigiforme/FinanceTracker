public class Account {
   	private double balance;
      private double Chequing;
      private double Savings;
      private double Credit;
   	private String owner;
      public Account(String owner, double Chequing, double Savings, double Credit){
         this.owner = owner;
         this.Chequing = Chequing;
         this.Savings = Savings;
         this.Credit = Credit;
         balance = Credit + Chequing + Savings;
      }
      public String toString(){
         return owner + " " + Chequing + " " + Savings + " " + Credit;
      }
      public double getBalance(){
         return balance;
      }
      public double getChequing(){
         return Chequing;
      }
      public double getSavings(){
         return Savings;
      }
      public double getCredit(){
         return Credit;
      }
      public void setBalance(double d){
         balance = d;
      }
      public void setChequing(double c){
         Chequing = c;
      }
      public void setSavings(double s){
         Savings = s;
      }
      public void setCredit(double c){
         Credit = c;
      }
      public String getName(){
         return owner;
      }
      public void setName(String n){
         owner = n;
      }
}

public class Transaction {
  	private double amt;
   private String type;
   private String date;
   private String data;
   private String details;
   private String origin;
   private String tranType;
   
   public Transaction(String tranType,double amt, String type, String date, String data){
      this.tranType = tranType;
      this.amt = amt;
      this.type = type;
      this.date = date;
      this.data = data;
     // this.details = details;
     // this.origin = origin;
   }
   public Transaction(Transaction another){
      this.amt = another.getAmt();
      this.type = another.getType();
      this.date = another.getDate();
      this.data = another.getData();
      this.details = another.getDetails();
      this.origin = another.getOrigin();
      this.tranType = another.getTranType();
   }
   public String toString(){
      return type + " transaction of $" + amt + " on " + date.substring(0,10);
   }
   public String record(){
         return "~" + tranType + ":" + type + ":" + amt + ":" + date.substring(0,10) + ":" + data;
   }
   public String writeHistory(){
      if(!date.equals("")){
         return data + " - $" + amt + " - " + date.substring(5,7) + "/" + date.substring(8,10);
      }
      else
         return "";
   }
   public double getAmt() {
	   return amt;
   }  
   public void setAmt(double amt) {
   	this.amt = amt;
   }
   
   public String getType() {
   	return type;
   }
   
   public void setType(String type) {
   	this.type = type;
   }
   
   public String getDate() {
   	return date;
   }
   
   public void setDate(String date) {
   	this.date = date;
   }
   
   public String getData() {
   	return data;
   }
   
   public void setData(String data) {
   	this.data = data;
   }
   
   public String getDetails() {
   	return details;
   }
   
   public void setDetails(String details) {
   	this.details = details;
   }
   
   public String getOrigin(){
      return origin;
   }
   
   public String getTranType(){
      return tranType;
   }
}
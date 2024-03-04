
import java.time.LocalDate;
public class Trade {
    private double entry_price;
    private double amount;
    private LocalDate entry_date;
    private double tp_price;
    private double sl_price;
    private double exit_price;
    private LocalDate exit_date;
    private double profit;
    private String type;
    public Trade(double entry_price,double amount, LocalDate entry_date,double tp_price,double sl_price,String type){
        this.entry_price = entry_price;
        this.amount = amount;
        this.entry_date = entry_date;
        this.tp_price = tp_price;
        this.sl_price  = sl_price;
        exit_price = 0;
        this.type = type;
    }
    public double Getentry_price(){
        return  entry_price;
    }
    public double getAmount() {
        return amount;
    }
    public double getTp_price() {
        return tp_price;
    }
    public double getSl_price() {
        return sl_price;
    }
    public LocalDate getEntry_date() {
        return entry_date;
    }
    public double GetProfit(){
        return profit;
    }
    public double getExit_price() {
        return exit_price;
    }
    public LocalDate getExit_date() {
        return exit_date;
    }
    public String getType() {
        return type;
    }
    public void setExit_date(LocalDate exit_date) {
        this.exit_date = exit_date;
    }
    public void setEntry_price(double entry_price) {
        this.entry_price = entry_price;
    }
    public void setEntry_date(LocalDate entry_date) {
        this.entry_date = entry_date;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setTp_price(double tp_price) {
        this.tp_price = tp_price;
    }
    public void setSl_price(double sl_price) {
        this.sl_price = sl_price;
    }
    public void setExit_price(double exit_price) {
        this.exit_price = exit_price;
    }
    public void SetProfit(double profit){
        this.profit = profit;
    }
    public void setType(String type) {
        this.type = type;
    }
}

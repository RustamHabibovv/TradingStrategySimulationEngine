
public class Stock {
    private double open_price;
    private double high_price;
    private double low_price;
    private double close_price;
    public  Stock(double open_price,double high_price, double low_price, double close_price){
        this.open_price = open_price;
        this.high_price = high_price;
        this.low_price = low_price;
        this.close_price = close_price;
    }
    public double getOpen_price() {
        return open_price;
    }
    public  double getHigh_price(){
        return high_price;
    }
    public double getLow_price() {
        return low_price;
    }
    public double getClose_price() {
        return close_price;
    }
    public void setOpen_price(double open_price) {
        this.open_price = open_price;
    }
    public void setHigh_price(double high_price) {
        this.high_price = high_price;
    }
    public void setLow_price(double low_price) {
        this.low_price = low_price;
    }
    public void setClose_price(double close_price) {
        this.close_price = close_price;
    }
}

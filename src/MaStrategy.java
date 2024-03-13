
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MaStrategy {
    public static double CalculateMA(StockData stockData, LocalDate startdate,int day){
        LocalDate date = startdate;
        double MA = 0;

        for(int i=0;i<day;i++ ){
            if(stockData.stockdata.containsKey(date)){
                MA+=stockData.stockdata.get(date).getClose_price();
            }
            else {
                i--;
            }
            date = date.plusDays(1);
        }

        MA = MA/day;
        return MA;
    }
    public static boolean BuySignal(StockData stockData,LocalDate startdate){
        if(CalculateMA(stockData,startdate.minusDays(1),20)<CalculateMA(stockData,startdate.minusDays(1),50)&&
        CalculateMA(stockData,startdate,20)>CalculateMA(stockData,startdate,50)
        ){
            return true;
        }
        return false;
    }
    public static boolean SellSignal(StockData stockData,LocalDate startdate){
        if(CalculateMA(stockData,startdate.minusDays(1),20)>CalculateMA(stockData,startdate.minusDays(1),50)&&
                CalculateMA(stockData,startdate,20)<CalculateMA(stockData,startdate,50)
        ){
            return true;
        }
        return false;
    }
}

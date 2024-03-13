
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
public class RsiStrategy {
    public static double CalculateRsi(StockData stockData, LocalDate startDate,int period){
        if(startDate.isBefore(LocalDate.of(2019,1,25))){
            startDate = LocalDate.of(2019,1,28);
        }
        LocalDate date = startDate;
        double RSI  = 0;
        double RS = 0;
        double average_gain = 0;
        double average_lose = 0;

        for( int i=0; i<period;i++){
            if(stockData.stockdata.containsKey(date)){
                boolean flag = true;
                double pricebefore = stockData.stockdata.get(LocalDate.of(2019,1,7)).getClose_price();
                int k =1;
                while(flag) {
                    if (stockData.stockdata.containsKey(date.minusDays(k))) {
                        pricebefore = stockData.stockdata.get(date.minusDays(k)).getClose_price();
                        flag = false;
                    } else {
                        k++;
                    }
                }
                double price = stockData.stockdata.get(date).getClose_price();
                if(price>pricebefore){
                    average_gain+=price-pricebefore;
                }
                else {
                    average_lose-= pricebefore-price;
                }
            }
            else {
                i--;
            }
            date = date.minusDays(1);
        }
        average_gain = average_gain/period;
        average_lose = average_lose/period;

        RS = average_gain/average_lose;
        RSI = 100 - (100/(1+RS));

        return RSI;
    }
    public static boolean BuySignal(StockData stockData, LocalDate startdate){
        if(CalculateRsi(stockData,startdate,14)<=30){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean SellSignal (StockData stockData, LocalDate startdate){
        if(CalculateRsi(stockData,startdate,14)>=70){
            return true;
        }
        else{
            return false;
        }
    }
}

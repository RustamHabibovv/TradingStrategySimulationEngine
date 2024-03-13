
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BollingerBandsStrategy {
    public static double Calculate20DaySD(StockData stockData, LocalDate startDate){
        LocalDate date = startDate;
        double sum = 0;
        List<Double> prices = new ArrayList<>();

        for(int i=0;i<20;i++ ){

            if(stockData.stockdata.containsKey(date)){
                sum+=stockData.stockdata.get(date).getClose_price();
                prices.add(stockData.stockdata.get(date).getClose_price());
            }
            else {
                i--;
            }
            date = date.plusDays(1);
        }

        double squared_deviation = 0;
        double mean = sum/20;

        for (double price: prices
        ) {
            squared_deviation+= Math.pow(price-mean,2);
        }
        squared_deviation = squared_deviation/20;
        squared_deviation = Math.sqrt(squared_deviation);
        return squared_deviation;

    }

    public static double CalculateUpperBand(StockData stockData, LocalDate startDate){

        double MA20 = MaStrategy.CalculateMA(stockData,startDate,20);
        double SD20 = Calculate20DaySD(stockData,startDate);
        double UpperBand = MA20 + (SD20*2);
        return  UpperBand;
    }

    public static double CalculateLowerBand(StockData stockData, LocalDate startDate){

        double MA20 = MaStrategy.CalculateMA(stockData,startDate,20);
        double SD20 = Calculate20DaySD(stockData,startDate);
        double  LowerBand = MA20 - (SD20*2);
        return  LowerBand;

    }

    public static boolean BuySignal(StockData stockData,LocalDate startDate){

        if(Math.abs(stockData.stockdata.get(startDate).getClose_price()-CalculateLowerBand(stockData,startDate))<stockData.stockdata.get(startDate).getClose_price()/100){
            return true;
        }
        return false;
    }

    public static boolean SellSignal(StockData stockData,LocalDate startDate){
        if(Math.abs(stockData.stockdata.get(startDate).getClose_price()-CalculateUpperBand(stockData,startDate))<stockData.stockdata.get(startDate).getClose_price()/100){
            return true;
        }
        return false;
    }
}

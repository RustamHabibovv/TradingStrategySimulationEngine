
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MACDStrategy {
    public static double CalculateEma(StockData stockData, LocalDate startDate, int period){
        LocalDate date = startDate;
        int k =1;
        boolean flag = true;
        while(flag) {
            if (stockData.stockdata.containsKey(date.plusDays(k))) {
               date = date.plusDays(k);
               flag = false;
            } else {
                k++;
            }
        }
        double Ema = MaStrategy.CalculateMA(stockData,date,period);
        double smoothing_constant = 2.00/(period+1);

        for(int i=1; i<period;i++){
            if(stockData.stockdata.containsKey(date)){
                Ema = (stockData.stockdata.get(date).getClose_price()-Ema)*smoothing_constant+Ema;
            }
            else{
                i--;
            }
            date = date.plusDays(1);
        }
        return Ema;
    }
    public static double CalculateMACD(StockData stockData,LocalDate startDate){
        double MACD = CalculateEma(stockData,startDate,12) - CalculateEma(stockData,startDate,26);
        return MACD;
    }
    public static double CalculateSignalLine(StockData stockData, LocalDate startDate){
        LocalDate date = startDate;
        int k =1;
        boolean flag = true;
        while(flag) {
            if (stockData.stockdata.containsKey(date.plusDays(k))) {
                date = date.plusDays(k);
                flag = false;
            } else {
                k++;
            }
        }
            double Ema = CalculateMACD(stockData,date);
            double smoothing_constant = 2.00/(9+1);

            for(int i=1; i<9;i++){
                if(stockData.stockdata.containsKey(date)){
                    Ema = (CalculateMACD(stockData,date)-Ema)*smoothing_constant+Ema;
                }
                else{
                    i--;
                }
                date = date.plusDays(1);
            }
            return Ema;
    }


    public static boolean BuySignal(StockData stockData,LocalDate startDate){
        if(CalculateMACD(stockData,startDate.minusDays(1))<CalculateSignalLine(stockData,startDate.minusDays(1))&& CalculateMACD(stockData,startDate)>CalculateSignalLine(stockData,startDate)){
         return true;
        }
        return  false;
    }

    public static boolean SellSignal(StockData stockData,LocalDate startDate){
        if(CalculateMACD(stockData,startDate.minusDays(1))>CalculateSignalLine(stockData,startDate.minusDays(1))&& CalculateMACD(stockData,startDate)<CalculateSignalLine(stockData,startDate)){
            return true;
        }
        return  false;
    }
}

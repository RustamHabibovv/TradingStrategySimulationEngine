
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class DualMovingAveragesRSIStrategy {
    public static boolean BuySignal(StockData stockData, LocalDate startDate){
    if(MaStrategy.BuySignal(stockData,startDate)&&RsiStrategy.BuySignal(stockData,startDate)){
        return true;
    }
    else{
        return false;
    }
    }
    public static boolean SellSignal(StockData stockData,LocalDate startDate){
        if(MaStrategy.SellSignal(stockData,startDate)&&RsiStrategy.SellSignal(stockData,startDate)){
            return true;
        }
        else{
            return false;
        }
    }
}

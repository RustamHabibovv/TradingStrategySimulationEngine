
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
    public static void SimulateStrategy(StockData stockData,LocalDate startdate,LocalDate enddate, double budget, int leverage,double transaction_cost,double risk_trade,double take_profit,double stop_loss ){
        Trades openTrades = new Trades();
        Trades closedTrades  = new Trades();
        int win = 0;
        int total_trades = 0;
        int lose = 0;
        double initial_budget = budget;
        double amount = budget*(risk_trade/100)*leverage;

        for (LocalDate date = startdate; date.isBefore(enddate);date = date.plusDays(1)){
            if(date.isBefore(LocalDate.of(2019,1,25))){
                date = LocalDate.of(2019,1,28);
            }

            if(stockData.stockdata.containsKey(date)){
                double price = stockData.stockdata.get(date).getClose_price();

                if(BuySignal(stockData,date)){

                    Trade trade = new Trade(price,amount,date,price*(1+(take_profit/100)),price*(1-(stop_loss/100)),"long");
                    openTrades.addtrade(trade);
                    total_trades++;
                    budget = budget-(amount/leverage)-transaction_cost;





                }
                else if(SellSignal(stockData,date)) {
                    Trade trade = new Trade(price,amount,date,price*(1-(take_profit/100)),price*(1+(stop_loss/100)),"short");
                    openTrades.addtrade(trade);
                    total_trades++;
                    budget = budget-(amount/leverage)-transaction_cost;




                }
                for (Iterator<Trade> iterator = openTrades.opentrades.iterator(); iterator.hasNext();
                ) {
                    Trade t = iterator.next();

                    if (t.getTp_price()<price&&"long".equals(t.getType())){
                        t.setExit_date(date);
                        t.setExit_price(price);
                        t.SetProfit(t.getAmount()*(take_profit/100));
                        budget+=(t.GetProfit()+(amount/leverage)-transaction_cost);
                        win++;
                        closedTrades.addtrade(t);
                        iterator.remove();


                    }
                    else if (t.getSl_price()>price&&"long".equals(t.getType())){
                        t.setExit_date(date);
                        t.setExit_price(price);
                        t.SetProfit(t.getAmount()*(stop_loss/100));
                        budget+=t.GetProfit()-transaction_cost;

                        lose++;


                        closedTrades.addtrade(t);
                        iterator.remove();

                    }
                    else {
                        if (t.getTp_price() > price && "short".equals(t.getType())) {
                            t.setExit_date(date);
                            t.setExit_price(price);
                            t.SetProfit(t.getAmount() * (take_profit / 100));
                            budget += (t.GetProfit() + (amount / leverage) - transaction_cost);

                            win++;
                            closedTrades.addtrade(t);


                            iterator.remove();


                        } else if (t.getSl_price() < price && "short".equals(t.getType())) {
                            t.setExit_date(date);
                            t.setExit_price(price);
                            t.SetProfit(t.getAmount() * (stop_loss / 100));
                            lose++;
                            budget += t.GetProfit() - transaction_cost;


                            closedTrades.addtrade(t);
                            iterator.remove();

                        }
                    }

                }

            }
        }

        openTrades.opentrades.removeAll(closedTrades.opentrades);
        List<String> tradesinfo = new ArrayList<>();

        for (Trade t: closedTrades.opentrades
        ) {
            String s = (("Type of trade:"+t.getType()));
            String l = ("Entry date:"+t.getEntry_date());
            String k  = ("Exit date:"+t.getExit_date());
            String z = ("Entry price:"+t.Getentry_price());
            String c = ("Exit price:"+ t.getExit_price());
            String b= ("---------------------------------");
            String info = s+"\n" + l + "\n" + k+ "\n" + z+"\n"+c+"\n"+b;

            tradesinfo.add(info);

        }

        ResultPage resultPage = new ResultPage(initial_budget,budget,total_trades,win,lose,tradesinfo);


    }


}

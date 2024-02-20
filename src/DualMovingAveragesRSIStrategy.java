

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

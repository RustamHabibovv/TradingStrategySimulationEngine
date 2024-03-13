
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    String aaplepath = "stockdatafiles\\AAPL.csv";
     String nvdapath = "stockdatafiles\\NVDA.csv";
     String amznpath = "stockdatafiles\\AMZN.csv";
     String googlpath = "stockdatafiles\\GOOGL.csv";
     String metapath = "stockdatafiles\\META .csv";
     String tslapath = "stockdatafiles\\TSLA.csv";
     String jpmath = "stockdatafiles\\JPM.csv";
     String kopath = "stockdatafiles\\KO.csv";
     String pgpath = "stockdatafiles\\PG.csv";
     String intcpath = "stockdatafiles\\INTC.csv";
     public StockData stockdata=  new StockData();

    public static void SimulateStrategy(String strategy_name,StockData stockData,LocalDate startdate,LocalDate enddate, double budget, int leverage,double transaction_cost,double risk_trade,double take_profit,double stop_loss ){
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

                if(strategy_name=="Moving Average Strategy") {

                    if (MaStrategy.BuySignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 + (take_profit / 100)), price * (1 - (stop_loss / 100)), "long");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    } else if (MaStrategy.SellSignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 - (take_profit / 100)), price * (1 + (stop_loss / 100)), "short");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    }
                }
                if(strategy_name=="RSI Strategy") {

                    if (RsiStrategy.BuySignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 + (take_profit / 100)), price * (1 - (stop_loss / 100)), "long");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    } else if (RsiStrategy.SellSignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 - (take_profit / 100)), price * (1 + (stop_loss / 100)), "short");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    }
                }
                if(strategy_name=="MACD Strategy") {

                    if (MACDStrategy.BuySignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 + (take_profit / 100)), price * (1 - (stop_loss / 100)), "long");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    } else if (MACDStrategy.SellSignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 - (take_profit / 100)), price * (1 + (stop_loss / 100)), "short");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    }
                }
                if(strategy_name=="Bollinger Bands Strategy") {

                    if (BollingerBandsStrategy.BuySignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 + (take_profit / 100)), price * (1 - (stop_loss / 100)), "long");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    } else if (BollingerBandsStrategy.SellSignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 - (take_profit / 100)), price * (1 + (stop_loss / 100)), "short");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    }
                }
                if(strategy_name=="Dual Moving Averages + RSI Strategy") {

                    if (DualMovingAveragesRSIStrategy.BuySignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 + (take_profit / 100)), price * (1 - (stop_loss / 100)), "long");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    } else if (DualMovingAveragesRSIStrategy.SellSignal(stockData, date)) {
                        Trade trade = new Trade(price, amount, date, price * (1 - (take_profit / 100)), price * (1 + (stop_loss / 100)), "short");
                        openTrades.addtrade(trade);
                        total_trades++;
                        budget = budget - (amount / leverage) - transaction_cost;
                    }
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



    public Simulation(String stockname, String strategy_name,LocalDate start_date,LocalDate end_date,double budget, int leverage,double transaction_cost,double risk_trade,double take_profit,double stop_loss){
         switch(stockname){
             case "AAPL":
                 stockdata.ReadData(aaplepath,stockdata.stockdata);
                 break;

             case "NVDA":
                 stockdata.ReadData(nvdapath,stockdata.stockdata);
                 break;

             case "AMZN":
                 stockdata.ReadData(amznpath,stockdata.stockdata);
                 break;
             case "GOOGL":
                 stockdata.ReadData(googlpath,stockdata.stockdata);
                 break;
             case "META":
                 stockdata.ReadData(metapath,stockdata.stockdata);
                 break;
             case "TSLA":
                 stockdata.ReadData(tslapath,stockdata.stockdata);
                 break;
             case "JPM":
                 stockdata.ReadData(jpmath,stockdata.stockdata);
                 break;
             case "KO":
                 stockdata.ReadData(kopath,stockdata.stockdata);
                 break;
             case "PG":
                 stockdata.ReadData(pgpath,stockdata.stockdata);
                 break;
             case "INTC":
                 stockdata.ReadData(intcpath,stockdata.stockdata);
                 break;

         }
         switch (strategy_name){
             case "Moving Average Strategy":
                 SimulateStrategy(strategy_name,stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "RSI Strategy":
                 SimulateStrategy(strategy_name,stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "MACD Strategy":
                 SimulateStrategy(strategy_name,stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "Bollinger Bands Strategy":
                 SimulateStrategy(strategy_name,stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "Dual Moving Averages + RSI Strategy":
                 SimulateStrategy(strategy_name,stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;
         }
     }
}

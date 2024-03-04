
import java.time.LocalDate;
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
                 MaStrategy.SimulateStrategy(stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "RSI Strategy":
                 RsiStrategy.SimulateStrategy(stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "MACD Strategy":
                 MACDStrategy.SimulateStrategy(stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "Bollinger Bands Strategy":
                 BollingerBandsStrategy.SimulateStrategy(stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;

             case "Dual Moving Averages + RSI Strategy":
                 DualMovingAveragesRSIStrategy.SimulateStrategy(stockdata,start_date,end_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
                 break;
         }
     }
}

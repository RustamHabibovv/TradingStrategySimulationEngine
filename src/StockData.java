

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StockData {

    HashMap<LocalDate, Stock> stockdata = new HashMap<LocalDate, Stock>();

    public StockData(){

    }

    public StockData(String path){

        ReadData(path, stockdata);




    }

    public void ReadData(String path,HashMap<LocalDate,Stock> sdata) {
        try{
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(path));

            line = reader.readLine();

            while((line = reader.readLine())!= null){
                String[ ] parts = line.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                double open_price = Double.parseDouble(parts[1]);
                double high_price = Double.parseDouble(parts[2]);
                double low_price = Double.parseDouble(parts[3]);
                double close_price = Double.parseDouble(parts[4]);

                Stock s = new Stock(open_price,high_price,low_price,close_price);


                sdata.put(date,s);


            }


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public double GetOpenprice(LocalDate date){
        return stockdata.get(date).getOpen_price();
    }
}

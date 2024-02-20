

import java.time.LocalDate;
import java.util.ArrayList;

public class Trades {
     ArrayList<Trade> opentrades = new ArrayList<>();

     public Trades(){

     }

     public void addtrade(Trade trade){
         opentrades.add(trade);
     }

     public Trade get_trade(LocalDate entry_date, double entry_price){
         for (Trade t:opentrades
              ) {
             if (t.getEntry_date()==entry_date&&t.Getentry_price()==entry_price){
                 return t;
             }

         }
         return null;

     }
     public boolean hittp(double price){
         for (Trade t:opentrades) {
             if(t.getTp_price()==price){
                 return true;
             }

         }
         return false;
     }
    public boolean hitsl(double price){
        for (Trade t:opentrades) {
            if(t.getSl_price()==price){
                return true;
            }

        }
        return false;
    }


}

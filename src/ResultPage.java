

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ResultPage extends JFrame {
    JFrame frame = new JFrame();

    ResultPage(double initial_budget, double budget, int number_trades, int win, int lose, List<String> tradesinfo){
        this.setSize(700,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Results Page");
        this.setResizable(false);

        JPanel resultspanel = new JPanel(null);
        resultspanel.setBounds(0,0,350,650);

        resultspanel.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(resultspanel);

        JPanel tradespanel = new JPanel(null);
        tradespanel.setBounds(350,0,350,650);

        this.add(tradespanel);


        Font labelfont = new Font("Calibri",Font.BOLD,18);


        JLabel titlelabel = new JLabel();

        titlelabel.setText("Results");
        titlelabel.setFont(new Font("Calibri",Font.BOLD,28));
        titlelabel.setBounds(100,0,100,100);
        resultspanel.add(titlelabel);

        JLabel initial_budgetlabel  = new JLabel();
        initial_budgetlabel.setText("Starting Budget: "+initial_budget);
        initial_budgetlabel.setFont(labelfont);
        initial_budgetlabel.setBounds(20,70,200,50);
        resultspanel.add(initial_budgetlabel);

        JLabel budgetlabel  = new JLabel();
        budgetlabel.setText(String.format("Budget after trades: %.2f",budget));
        budgetlabel.setFont(labelfont);
        budgetlabel.setBounds(20,120,300,50);
        resultspanel.add(budgetlabel);

        JLabel totallabel  = new JLabel();
        totallabel.setText("Total Trades: "+number_trades);
        totallabel.setFont(labelfont);
        totallabel.setBounds(20,170,200,50);
        resultspanel.add(totallabel);

        JLabel winlabel  = new JLabel();
        winlabel.setText("Wins: "+win);
        winlabel.setFont(labelfont);
        winlabel.setBounds(20,210,200,50);
        resultspanel.add(winlabel);

        JLabel loselabel  = new JLabel();
        loselabel.setText("Loses: "+lose);
        loselabel.setFont(labelfont);
        loselabel.setBounds(20,260,200,50);
        resultspanel.add(loselabel);

        JLabel openlabel  = new JLabel();
        openlabel.setText("Open trades: "+(number_trades-win-lose));
        openlabel.setFont(labelfont);
        openlabel.setBounds(20,310,200,50);
        resultspanel.add(openlabel);

        JLabel roilabel  = new JLabel();
        roilabel.setText(String.format("ROI%%(Return on investment): %.2f%%",(((budget/initial_budget)*100)-100)));
        roilabel.setFont(labelfont);
        roilabel.setBounds(20,360,350,50);
        resultspanel.add(roilabel);

        JLabel winratelabel  = new JLabel();
        winratelabel.setText(String.format("Winrate%% : %.2f%%", Double.valueOf(win)/Double.valueOf(number_trades)*100));
        winratelabel.setFont(labelfont);
        winratelabel.setBounds(20,410,300,50);
        resultspanel.add(winratelabel);


        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : tradesinfo) {

            item = "<html>" + item.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";

            listModel.addElement(item);


        }


        JList<String> jList = new JList<>(listModel);

        jList.setFont( new Font("Calibri",Font.BOLD,17));




        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setBounds(0,0,335,650);

        tradespanel.add(scrollPane);









    }
}

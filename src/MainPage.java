
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import org.jdatepicker.impl.JDatePanelImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
public class MainPage extends JFrame implements  ActionListener {
    JFrame frame = new JFrame();
    String [] stocklist = new String[] {"AAPL","NVDA","AMZN","GOOGL","META","TSLA","JPM",
            "KO","PG","INTC"};
    String[] strategieslist = new String[]{"Moving Average Strategy", "RSI Strategy","MACD Strategy","Bollinger Bands Strategy","Dual Moving Averages + RSI Strategy"};
    JComboBox StocksList = new JComboBox(stocklist);
    JComboBox Strategies = new JComboBox(strategieslist);
    String selectedStock =   StocksList.getSelectedItem().toString();
    String selectedStrategy = Strategies.getSelectedItem().toString();
    DatePickerSettings datesettings1 = new DatePickerSettings();
    DatePickerSettings datesettings2 = new DatePickerSettings();
    LocalDate start_date = LocalDate.of(2019,1,7);
    LocalDate end_date = LocalDate.of(2023,10,24);
    DatePicker startdatePicker = new DatePicker(datesettings1);
    DatePicker enddatePicker  = new DatePicker(datesettings2);
    LocalDate Start_date = startdatePicker.getDate();
    LocalDate End_date = enddatePicker.getDate();
    JTextField budgettextfield = new JTextField(7);
    String[] leverages = new String[] {"10","50","100","200","400","500"};
    JTextField transaction_field = new JTextField(4);
    JComboBox leveragebox = new JComboBox(leverages);
    JTextField risk_field = new JTextField(8);
    JTextField take_profit_textfield = new JTextField(8);
    JTextField stop_loss_textfield = new JTextField(8);

        MainPage(){
            this.setSize(800,800);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            this.setResizable(false);
            this.setTitle("Trading Strategy Simulator");

            budgettextfield.setText("10000");
            transaction_field.setText("0.1");
            risk_field.setText("5");
            take_profit_textfield.setText("10");
            stop_loss_textfield.setText("10");
            startdatePicker.setDate(LocalDate.of(2019,1,7));
            enddatePicker.setDate(LocalDate.of(2023,10,24));

            EmptyBorder border1 = new EmptyBorder(0,0,0,200);
            EmptyBorder border2 = new EmptyBorder(0,0,70,0);
            Strategies.setBorder(border1);
            Strategies.setBackground(Color.GRAY);

            StocksList.setEditable(true);
            StocksList.setBorder(border1);
            StocksList.setBackground(Color.GRAY);
            Strategies.setEditable(true);

            JLabel strategylabel = new JLabel();
            strategylabel.setText("Strategy:");
            JLabel stocklabel = new JLabel();
            stocklabel.setText("Stock:");
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel startlabel = new JLabel();
            startlabel.setText("Start Date:");
            JLabel endlabel = new JLabel();
            endlabel.setText("End Date:");
            startdatePicker.setBorder(border1);
            startdatePicker.setBackground(Color.GRAY);
            datesettings1.setDateRangeLimits(start_date,end_date);
            datesettings2.setDateRangeLimits(start_date,end_date);

            panel.add(strategylabel);
            panel.add(Strategies);
            panel.add(stocklabel);
            panel.add(StocksList) ;
            panel.add(startlabel);
            panel.add(startdatePicker);
            panel.add(endlabel);
            panel.add(enddatePicker);

            JLabel settingstext = new JLabel();
            settingstext.setText("Initial Settings");
            settingstext.setBorder(border2);
            settingstext.setFont(new Font("Calibri",Font.BOLD,28));
            JPanel settingspanel = new JPanel();
            settingspanel.setBounds(0,100,300,700);
            settingspanel.setBackground(Color.lightGray);
            settingspanel.setLayout(null);
            settingstext.setBounds(20,20,200,100);
            settingspanel.add(settingstext);
            JLabel budgetlabel = new JLabel();
            budgetlabel.setText("Initial Budget:");
            Font inputfont = new Font("Calibri",Font.BOLD,15);
            Font labelfont = new Font("Calibri",Font.BOLD,17);
            budgettextfield.setFont(inputfont);
            budgetlabel.setFont(labelfont);
            JLabel leveragelabel = new JLabel();
            leveragelabel.setText("Leverage:");
            leveragelabel.setFont(labelfont);
            JLabel transactionlabel  = new JLabel();
            transactionlabel.setText("Transaction Cost:");
            transactionlabel.setFont(labelfont);
            transaction_field.setFont(inputfont);
            JLabel risklabel = new JLabel();
            risklabel.setText("Risk Per Trade(%):");
            risklabel.setFont(labelfont);
            risk_field.setFont(inputfont);
            JLabel takeprofit_label = new JLabel();
            takeprofit_label.setText("Take Profit(%):");
            takeprofit_label.setFont(labelfont);
            take_profit_textfield.setFont(inputfont);
            JLabel stop_loss_label = new JLabel();
            stop_loss_label.setText("Stop loss(%):");
            stop_loss_label.setFont(labelfont);
            stop_loss_textfield.setFont(inputfont);
            JButton settings_apply_button = new JButton("Simulate");
            settings_apply_button.setFont(labelfont);
            settings_apply_button.addActionListener((ActionListener) this);
            budgetlabel.setBounds(10,70,200,100);
            budgettextfield.setBounds(130,107,160,25);
            leveragelabel.setBounds(10,180,200,50);
            leveragebox.setBounds(110,190,180,25);
            transactionlabel.setBounds(0,250,200,70);
            transaction_field.setBounds(120,270,170,25);
            risklabel.setBounds(0,350,150,30);
            risk_field.setBounds(135, 350,155,30);
            takeprofit_label.setBounds(0,400,120,70);
            take_profit_textfield.setBounds(130,420,160,30);
            stop_loss_label.setBounds(0,480,120,70);
            stop_loss_textfield.setBounds(120,500,170,30);
            settings_apply_button.setBounds(65,620,150,50);

            JPanel info_text_panel = new JPanel();
            info_text_panel.setLayout(null);
            JLabel info_text = new JLabel();
            String information = "<html><!--docxjs library predefined styles--><style>\n" +
                    ".docx-wrapper { background: null; padding: 30px; padding-bottom: 0px; display: flex; flex-flow: column; align-items: center; } \n" +
                    ".docx-wrapper>section.docx { background: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); margin-bottom: 30px; }\n" +
                    ".docx { color: black; }\n" +
                    "section.docx { box-sizing: border-box; display: flex; flex-flow: column nowrap; position: relative; overflow: hidden; }\n" +
                    "section.docx>article { margin-bottom: auto; }\n" +
                    ".docx table { border-collapse: collapse; }\n" +
                    ".docx table td, .docx table th { vertical-align: top; }\n" +
                    ".docx p { margin: 0pt; min-height: 1em; }\n" +
                    ".docx span { white-space: pre-wrap; overflow-wrap: break-word; }\n" +
                    ".docx a { color: inherit; text-decoration: inherit; }\n" +
                    "</style><!--docxjs document theme values--><style>.docx {\n" +
                    "  --docx-majorHAnsi-font: Calibri Light;\n" +
                    "  --docx-minorHAnsi-font: Calibri;\n" +
                    "  --docx-dk1-color: #000000;\n" +
                    "  --docx-lt1-color: #FFFFFF;\n" +
                    "  --docx-dk2-color: #44546A;\n" +
                    "  --docx-lt2-color: #E7E6E6;\n" +
                    "  --docx-accent1-color: #4472C4;\n" +
                    "  --docx-accent2-color: #ED7D31;\n" +
                    "  --docx-accent3-color: #A5A5A5;\n" +
                    "  --docx-accent4-color: #FFC000;\n" +
                    "  --docx-accent5-color: #5B9BD5;\n" +
                    "  --docx-accent6-color: #70AD47;\n" +
                    "  --docx-hlink-color: #0563C1;\n" +
                    "  --docx-folHlink-color: #954F72;\n" +
                    "}\n" +
                    "</style><!--docxjs document styles--><style>.docx span {\n" +
                    "  font-family: var(--docx-minorHAnsi-font);\n" +
                    "  min-height: 16.00pt;\n" +
                    "  font-size: 16.00pt;\n" +
                    "}\n" +
                    ".docx p {\n" +
                    "  margin-bottom: 8.00pt;\n" +
                    "  line-height: 1.08;\n" +
                    "}\n" +
                    ".docx table, table.docx_tablenormal td {\n" +
                    "  padding-top: 0.00pt;\n" +
                    "  padding-left: 5.40pt;\n" +
                    "  padding-bottom: 0.00pt;\n" +
                    "  padding-right: 5.40pt;\n" +
                    "}\n" +
                    "p.docx_listparagraph {\n" +
                    "  margin-left: 36.00pt;\n" +
                    "}\n" +
                    "</style><!--docxjs document numbering styles--><style>p.docx-num-1-0:before {\n" +
                    "  content: \"\"counter(docx-num-1-0, decimal)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-0;\n" +
                    "}\n" +
                    "p.docx-num-1-0 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 36.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-0 {\n" +
                    "  counter-reset: docx-num-1-1;\n" +
                    "}\n" +
                    "p.docx-num-1-1:before {\n" +
                    "  content: \"\"counter(docx-num-1-1, lower-alpha)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-1;\n" +
                    "}\n" +
                    "p.docx-num-1-1 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 72.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-1 {\n" +
                    "  counter-reset: docx-num-1-2;\n" +
                    "}\n" +
                    "p.docx-num-1-2:before {\n" +
                    "  content: \"\"counter(docx-num-1-2, lower-roman)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-2;\n" +
                    "}\n" +
                    "p.docx-num-1-2 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -9.00pt;\n" +
                    "  margin-left: 108.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-2 {\n" +
                    "  counter-reset: docx-num-1-3;\n" +
                    "}\n" +
                    "p.docx-num-1-3:before {\n" +
                    "  content: \"\"counter(docx-num-1-3, decimal)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-3;\n" +
                    "}\n" +
                    "p.docx-num-1-3 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 144.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-3 {\n" +
                    "  counter-reset: docx-num-1-4;\n" +
                    "}\n" +
                    "p.docx-num-1-4:before {\n" +
                    "  content: \"\"counter(docx-num-1-4, lower-alpha)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-4;\n" +
                    "}\n" +
                    "p.docx-num-1-4 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 180.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-4 {\n" +
                    "  counter-reset: docx-num-1-5;\n" +
                    "}\n" +
                    "p.docx-num-1-5:before {\n" +
                    "  content: \"\"counter(docx-num-1-5, lower-roman)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-5;\n" +
                    "}\n" +
                    "p.docx-num-1-5 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -9.00pt;\n" +
                    "  margin-left: 216.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-5 {\n" +
                    "  counter-reset: docx-num-1-6;\n" +
                    "}\n" +
                    "p.docx-num-1-6:before {\n" +
                    "  content: \"\"counter(docx-num-1-6, decimal)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-6;\n" +
                    "}\n" +
                    "p.docx-num-1-6 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 252.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-6 {\n" +
                    "  counter-reset: docx-num-1-7;\n" +
                    "}\n" +
                    "p.docx-num-1-7:before {\n" +
                    "  content: \"\"counter(docx-num-1-7, lower-alpha)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-7;\n" +
                    "}\n" +
                    "p.docx-num-1-7 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -18.00pt;\n" +
                    "  margin-left: 288.00pt;\n" +
                    "}\n" +
                    "p.docx-num-1-7 {\n" +
                    "  counter-reset: docx-num-1-8;\n" +
                    "}\n" +
                    "p.docx-num-1-8:before {\n" +
                    "  content: \"\"counter(docx-num-1-8, lower-roman)\".\\9\";\n" +
                    "  counter-increment: docx-num-1-8;\n" +
                    "}\n" +
                    "p.docx-num-1-8 {\n" +
                    "  display: list-item;\n" +
                    "  list-style-position: inside;\n" +
                    "  list-style-type: none;\n" +
                    "  text-indent: -9.00pt;\n" +
                    "  margin-left: 324.00pt;\n" +
                    "}\n" +
                    ".docx-wrapper {\n" +
                    "  counter-reset: docx-num-1-0;\n" +
                    "}\n" +
                    "</style><div class=\"docx-wrapper\"><section class=\"docx\" style=\"padding: 72pt; width: 612pt; min-height: 792pt;\"><article><p><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 22pt; font-size: 22pt;\">       Trading Strategy Simulator Instructions</span></p><p><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">This is Trading Strategy Simulator engine program, provides users to </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">backtest</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">in </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">5 year</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> period historical </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">5</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> different famous trading strategy based on different indicators such as RSI( relative strength indicator), MACD</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">(moving average convergence/divergence)</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">, Bollinger Bands, Moving Averages</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">.</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> User can choose among </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">10 </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">different most famous stocks available. Information about Strategies:</span></p><p class=\"docx_listparagraph docx-num-1-0\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Moving Averages Strategy</span></p><p class=\"docx_listparagraph\"><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">Strategy consist of 50-day and 200-day moving averages crossover, when short-term MA crossovers long-term MA, it is </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">buy signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">, when short-term MA cross below long-term MA, it is </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">sell signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">.</span></p><p class=\"docx_listparagraph docx-num-1-0\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">RSI Strategy</span></p><p class=\"docx_listparagraph\"><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">When RSI falls below certain threshold such as 35, it means stock is underpriced and it is </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">buy signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">, when RSI cross over 65 threshold it means stock is overpriced and it is time for </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">sell signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">.</span></p><p class=\"docx_listparagraph docx-num-1-0\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">MACD Strategy</span></p><p class=\"docx_listparagraph\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Buy signal </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">when MACD line crossover signal line, </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Sell signal </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">when MACD line cross below signal line.</span></p><p class=\"docx_listparagraph docx-num-1-0\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Bollinger Bands Strategy</span></p><p class=\"docx_listparagraph\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Buy signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> when price touches or falls below lower band of Bollinger Bands, </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Sell signal</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> when price touches or rises above upper Bollinger Bands.</span></p><p class=\"docx_listparagraph docx-num-1-0\"><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Dual </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">Moving Averages + RSI</span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\"> crossover</span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\"> Strategy</span></p><p class=\"docx_listparagraph\"><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">This is combined strategy from first and second strategy, for justifying signals more.</span></p><p class=\"docx_listparagraph\"></p><p><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">In the</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> main page, you first have to select strategy you want to apply, then stocks you want to include in your portfolio, next step is selecting start and end dates of simulation. Then you have to initialize settings of simulation such as initial budget, leverage, Transaction cost per trade, </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">Risk per </span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">trade(how</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> many percent of total budget to spend in single trade), Take- Profit and Stop-Loss percentage( after how many percent up or down, trade should be closed)</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\">. </span><span style=\"font-family: &quot;Times New Roman&quot;; font-weight: bold; min-height: 16pt; font-size: 16pt;\">You have to initialize all spaces!</span><span style=\"font-family: &quot;Times New Roman&quot;; min-height: 16pt; font-size: 16pt;\"> Simulation can take some time, after it is done the results of simulation will be shown in the new frame.</span></p><p></p></article></section></div></html>";
            information.replace("\n","<br>");
            info_text.setText(information);
            info_text.setBounds(0,0,1250,550);
            info_text_panel.add(info_text);
            info_text_panel.setBounds(300,100,1300,550);

            settingspanel.add(budgetlabel);
            settingspanel.add(budgettextfield);
            settingspanel.add(leveragelabel);
            settingspanel.add(leveragebox);
            settingspanel.add(transactionlabel);
            settingspanel.add(transaction_field);
            settingspanel.add(risklabel);
            settingspanel.add(risk_field);
            settingspanel.add(takeprofit_label);
            settingspanel.add(take_profit_textfield);
            settingspanel.add(stop_loss_label);
            settingspanel.add(stop_loss_textfield);
            settingspanel.add(settings_apply_button);
            settingspanel.setBorder(BorderFactory.createLoweredBevelBorder());

            panel.setBounds(0,0,1600,100);
            panel.setBackground(Color.GRAY);
            panel.setBorder(BorderFactory.createLoweredBevelBorder());
            JPanel console = new JPanel(null);
            console.setBackground(Color.gray);
            JLabel console_title = new JLabel();
            console_title.setText("Simulation Console:");
            console_title.setForeground(Color.white);
            console_title.setBounds(0,0,100,100);
            console.add(console_title);
            info_text_panel.setBorder(BorderFactory.createLineBorder(Color.black));
            console.setBounds(301,550,1300,300);
            console.setBorder(BorderFactory.createLoweredBevelBorder());

            this.add(panel);
            this.add(settingspanel);
            this.add(info_text_panel);
            this.add(console);
           this.setVisible(true);
        }

    public void actionPerformed(ActionEvent e) {

            selectedStock =   StocksList.getSelectedItem().toString();
            selectedStrategy = Strategies.getSelectedItem().toString();
            Start_date = startdatePicker.getDate();
            End_date = enddatePicker.getDate();
            double budget = Double.parseDouble(budgettextfield.getText());
            int leverage = Integer.parseInt(((String)leveragebox.getSelectedItem()));
            double transaction_cost = Double.parseDouble(transaction_field.getText());
            double risk_trade = Double.parseDouble(risk_field.getText());
            double take_profit = Double.parseDouble(take_profit_textfield.getText());
            double stop_loss = Double.parseDouble(stop_loss_textfield.getText());
            double initialbudget = budget;

            Simulation simulation  = new Simulation(selectedStock,selectedStrategy,Start_date,End_date,budget,leverage,transaction_cost,risk_trade,take_profit,stop_loss);
    }
}

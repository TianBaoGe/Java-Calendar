import com.sun.org.apache.bcel.internal.generic.LUSHR;
/**
 * Created by tianbao on 17-9-4.
 * @author 20172180143林霭良
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Objects;

/**
 * Created by tianbao on 17-9-4.
 * @author tianbao
 */
public class MyFrame extends JFrame implements ActionListener
{

    private MyCalendar calendar;
    private JLabel showMessage=new JLabel("",JLabel.CENTER);
    private LocalDate date = LocalDate.now();
    private int year = date.getYear();
    private int month = date.getMonthValue();
    private int today = date.getDayOfMonth();
    private JButton nextmonth =  new JButton("下月");
    private JButton premonth  = new JButton("上月");
    private JButton sure =  new JButton("确定");
    private JButton comeToday = new JButton("返回今天");
    private JTextField text = new JTextField(10);
    private MyButton labelDay[] = new MyButton[42];
    private Calendar lunarDate = Calendar.getInstance();
    private Lunar myLunar;
    private JLabel showtop;
    private String[] xinqi = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    private JPanel east2;
    private JLabel east3;
    private JLabel east4;
    private JLabel[] three = new JLabel[3];
    private ChangeButton numberButton = new ChangeButton();
    private JLabel east5;
    private JLabel east6;
    private JLabel[] five = new JLabel[5];
    private JPanel east7;
    private LunarCalendar Festival;
    private TianDi tiandi;
    private int symbol ;

    public MyFrame()
    {
        setSize(1800,900);

        JPanel pCenter = new NewPanel();
        JPanel pTop = new JPanel();
        JPanel ALL = new JPanel();


        pCenter.setLayout(new GridLayout(6,7));
        pTop.setLayout(new GridLayout(1,7));
        pCenter.setBackground(Color.WHITE);

        String[] name = {"周日","周一","周二","周三","周四","周五","周六"};
        for(int i = 0;i<7;i++)
        {
            JLabel a = new JLabel(name[i],JLabel.CENTER);

            a.setPreferredSize(new Dimension(200,60));
            a.setForeground(Color.WHITE);

            a.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,22));


            a.setOpaque(true);
            a.setBackground(new Color(201,201,201));

            pTop.add(a);
        }


        calendar = new MyCalendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setNumber(today);



        String day[]=calendar.getCalendar();
        String temp = new Integer(today).toString();

        for(int i = 0;i<42;i++)
        {
            /*String text = "<html><font size = \"15\",face = \"微软雅黑\"> 10 <br/><br/> <font size = \"4\">  国庆节  <br/><html>";*/
            /*JLabel B = new JLabel(day[i],JLabel.CENTER);*/
            labelDay[i] = new MyButton(day[i]);
            labelDay[i].setNumber(i);
            labelDay[i].setFont(new java.awt.Font("文泉驿等宽微米黑",1,37));

            labelDay[i].setOpaque(true);
            /*a.setBackground(new Color(253,245,230));*/
            labelDay[i].setBackground(Color.white);

            labelDay[i].setPreferredSize(new Dimension(200,120));
            labelDay[i].setContentAreaFilled(false);

            pCenter.add(labelDay[i]);

            if(Objects.equals(day[i],temp)) symbol = i;
        }
        labelDay[symbol].setFont(new java.awt.Font("文泉驿等宽微米黑",1,50));
        labelDay[symbol].setForeground(Color.RED);

        showMessage.setText(calendar.getYear()+"年"+ calendar.getMonth()+"月"+ calendar.getNumber() +"日" );
        showMessage.setPreferredSize(new Dimension(1400,50));
        showMessage.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,25));




        JPanel control = new JPanel();
        control.setLayout(new GridLayout(1,5,5,5));



        nextmonth.setPreferredSize(new Dimension(200,45));
        premonth.setPreferredSize(new Dimension(200,45));
        sure.setPreferredSize(new Dimension(200,45));
        text.setPreferredSize(new Dimension(200,45));
        comeToday.setPreferredSize(new Dimension(200,45));
        control.add(premonth);
        control.add(text);
        control.add(sure);
        control.add(nextmonth);
        control.add(comeToday);
        /*text.setText("请输入查询的年份");
        text.setFont(new java.awt.Font(
        "微软雅黑",1,15));
        text.setForeground(new Color(201,201,201));
        */



        ALL.add(showMessage);
        ALL.add(pTop);
        ALL.add(pCenter);
        ALL.add(control);

        JPanel Lunar = new JPanel();
        Lunar.setPreferredSize(new Dimension(400,900));
        ALL.setPreferredSize(new Dimension(1400,850));
        Lunar.setBackground(Color.WHITE);

        add(ALL,BorderLayout.WEST);
        add(Lunar,BorderLayout.EAST);

        nextmonth.addActionListener(this);
        premonth.addActionListener(this);
        sure.addActionListener(this);
        nextmonth.setBackground(Color.white);
        premonth.setBackground(Color.white);
        sure.setBackground(Color.white);
        comeToday.setBackground(Color.white);
        text.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.PLAIN,18));
        nextmonth.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.PLAIN,19));
        premonth.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.PLAIN,19));

        sure.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.PLAIN,19));
        comeToday.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.PLAIN,19));
        comeToday.addActionListener(new comeBack());



        //右侧显示板块
        JPanel east1 = new JPanel();
        east1.setPreferredSize(new Dimension(400,900));
        east1.setBackground(Color.white);
        Lunar.add(east1);
        showtop = new JLabel("",JLabel.CENTER);
        showtop.setPreferredSize(new Dimension(400 ,60));
        showtop.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,24));
        showtop.setText(calendar.getYear()+ "-" + calendar.getMonth() + "-" + calendar.getNumber() + "  " + xinqi[calendar.getWeek()]);
        east1.add(showtop);

        east2 = new JPanel();
        east2.setBackground(Color.white);
        east2.setPreferredSize(new Dimension(400,133));
        east2.setLayout(new GridLayout(1,3,2,2));
        for(int i = 0;i<3;i++)
        {
            three[i] = new JLabel("",JLabel.CENTER);
            east2.add(three[i]);
        }
        three[1].setText("" + calendar.getNumber());
        three[1].setForeground(Color.WHITE);
        three[1].setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,70));
        three[1].setOpaque(true);
        three[1].setBackground(new Color(201,201,201));
        east1.add(east2);

        east3 = new JLabel("",JLabel.CENTER);
        east3.setPreferredSize(new Dimension(400,40));
        east3.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,22));
        lunarDate.set(calendar.getYear(),calendar.getMonth()-1,calendar.getNumber());
        myLunar = new Lunar(lunarDate);
        east3.setText(myLunar.toString());
        east1.add(east3);

        east4 = new JLabel("",JLabel.CENTER);
        east4.setPreferredSize(new Dimension(400,40));
        east4.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,22));
        east4.setText(myLunar.cyclical() + "年  [" + myLunar.animalsYear() + "年]");
        east1.add(east4);

        east5 = new JLabel("",JLabel.CENTER);
        east5.setPreferredSize(new Dimension(400,40));
        east5.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,22));
        tiandi = new TianDi(year,month,today);
        east5.setText(tiandi.toString());
        east1.add(east5);

        east6 = new JLabel("",JLabel.CENTER);
        east6.setPreferredSize(new Dimension(400,40));
        east6.setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,24));
        east6.setForeground(Color.RED);
        myLunar.CalculateLunarCalendar(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
        east6.setText(myLunar.getSolarFestival()+myLunar.getLunarFestival());
        east1.add(east6);

        JPanel line = new LineJPanel();
        line.setPreferredSize(new Dimension(400,60));
        line.setBackground(Color.WHITE);
        east1.add(line);

        east7 = new JPanel();
        east7.setBackground(Color.WHITE);
        east7.setPreferredSize(new Dimension(400,80));
        east7.setLayout(new GridLayout(1,5));
        for(int i = 0;i<5;i++)
        {
            five[i] = new JLabel("",JLabel.CENTER);
            east7.add(five[i]);
        }
        five[1].setText("宜");
        five[3].setText("忌");
        five[1].setForeground(Color.WHITE);
        five[1].setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,65));
        five[1].setOpaque(true);
        five[1].setBackground(new Color(201,201,201));
        five[3].setForeground(Color.WHITE);
        five[3].setFont(new java.awt.Font("文泉驿等宽微米黑",Font.BOLD,65));
        five[3].setOpaque(true);
        five[3].setBackground(new Color(201,201,201));
        east1.add(east7);


        for(int i = 0;i<42;i++) labelDay[i].addActionListener(numberButton);



    }


    public void actionPerformed(ActionEvent e)
    {
        int tempNumber = 0;

        if(e.getSource()==nextmonth)
        {
            month=month+1;
            if(month>12)
            {
                year+=1;
                month = 1;
            }
            calendar.setYear(year);
            calendar.setMonth(month);
            String day[]=calendar.getCalendar();

            for(int i=0;i<42;i++)
            {
                labelDay[i].setText(day[i]);
                if(Objects.equals(day[i],"1")) tempNumber = i;
            }
        }
        else if(e.getSource()==premonth)
        {
            month=month-1;
            if(month<1)
            {
                month = 12;
                year -= 1;
            }
            calendar.setYear(year);
            calendar.setMonth(month);
            String day[]=calendar.getCalendar();

            for(int i=0;i<42;i++)
            {
                labelDay[i].setText(day[i]);
                if(Objects.equals(day[i],"1")) tempNumber = i;
            }
        }
        else if(e.getSource()== sure)
        {
            month = 1;
            calendar.setMonth(month);
            calendar.setYear(Integer.parseInt(text.getText()));
            String day[]=calendar.getCalendar();
            for(int i=0;i<42;i++)
            {
                labelDay[i].setText(day[i]);
                if(Objects.equals(day[i],"1")) tempNumber = i;
            }
        }
        calendar.setNumber(1);
        showMessage.setText(calendar.getYear()+"年"+ calendar.getMonth()+"月"+ calendar.getNumber() +"日" );
        showtop.setText(calendar.getYear()+ "-" + calendar.getMonth() + "-" + calendar.getNumber() + "  " + xinqi[calendar.getWeek()]);
        three[1].setText("" + calendar.getNumber());
        lunarDate.set(calendar.getYear(),calendar.getMonth()-1,calendar.getNumber());
        myLunar = new Lunar(lunarDate);
        east3.setText(myLunar.toString());
        east4.setText(myLunar.cyclical() + "年  [" + myLunar.animalsYear() + "年]");
        myLunar.CalculateLunarCalendar(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
        east6.setText(myLunar.getSolarFestival()+myLunar.getLunarFestival());
        tiandi = new TianDi(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
        east5.setText(tiandi.toString());

        labelDay[symbol].setForeground(Color.BLACK);
        labelDay[symbol].setFont(new java.awt.Font("文泉驿等宽微米黑",1,37));
        labelDay[tempNumber].setForeground(Color.RED);
        labelDay[tempNumber].setFont(new java.awt.Font("文泉驿等宽微米黑",1,50));
        symbol = tempNumber;
    }

    private class ChangeButton implements ActionListener
    {
        private int number;

        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand() == "") return;
            String haha = e.getActionCommand();
            number = Integer.parseInt(haha);

            MyButton bang = (MyButton) e.getSource();

            labelDay[symbol].setForeground(Color.BLACK);
            labelDay[symbol].setFont(new java.awt.Font("文泉驿等宽微米黑",1,37));
            bang.setForeground(Color.RED);
            bang.setFont(new java.awt.Font("文泉驿等宽微米黑",1,50));
            symbol = bang.number;

            calendar.setNumber(number);
            showMessage.setText(calendar.getYear()+"年"+ calendar.getMonth()+"月"+ calendar.getNumber() +"日" );
            showtop.setText(calendar.getYear()+ "-" + calendar.getMonth() + "-" + calendar.getNumber() + "  " + xinqi[calendar.getWeek()]);
            three[1].setText("" + calendar.getNumber());
            lunarDate.set(calendar.getYear(),calendar.getMonth()-1,calendar.getNumber());
            myLunar = new Lunar(lunarDate);
            east3.setText(myLunar.toString());
            east4.setText(myLunar.cyclical() + "年  [" + myLunar.animalsYear() + "年]");
            myLunar.CalculateLunarCalendar(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
            east6.setText(myLunar.getSolarFestival()+myLunar.getLunarFestival());
            tiandi = new TianDi(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
            east5.setText(tiandi.toString());
        }
    }


    private class comeBack implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            LocalDate date = LocalDate.now();
            String Day = new Integer(date.getDayOfMonth()).toString();
            int tempNumber = 0;


            calendar.setMonth(date.getMonthValue());
            calendar.setYear(date.getYear());
            calendar.setNumber(date.getDayOfMonth());
            String day[]=calendar.getCalendar();
            for(int i=0;i<42;i++)
            {
                labelDay[i].setText(day[i]);
                if(Objects.equals(day[i],Day)) tempNumber = i;

            }

            labelDay[symbol].setForeground(Color.BLACK);
            labelDay[symbol].setFont(new java.awt.Font("文泉驿等宽微米黑",1,37));
            labelDay[tempNumber].setForeground(Color.RED);
            labelDay[tempNumber].setFont(new java.awt.Font("文泉驿等宽微米黑",1,50));
            symbol = tempNumber;


            showMessage.setText(calendar.getYear()+"年"+ calendar.getMonth()+"月"+ calendar.getNumber() +"日" );
            showtop.setText(calendar.getYear()+ "-" + calendar.getMonth() + "-" + calendar.getNumber() + "  " + xinqi[calendar.getWeek()]);
            three[1].setText("" + calendar.getNumber());
            lunarDate.set(calendar.getYear(),calendar.getMonth()-1,calendar.getNumber());
            myLunar = new Lunar(lunarDate);
            east3.setText(myLunar.toString());
            east4.setText(myLunar.cyclical() + "年  [" + myLunar.animalsYear() + "年]");
            myLunar.CalculateLunarCalendar(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
            east6.setText(myLunar.getSolarFestival()+myLunar.getLunarFestival());
            tiandi = new TianDi(calendar.getYear(),calendar.getMonth(),calendar.getNumber());
            east5.setText(tiandi.toString());
        }
    }


    private class NewPanel extends JPanel
    {
        public NewPanel()
        {

        }


        public void paintComponent(Graphics g)
        {
            int x = 0, y = 0;
            ImageIcon icon = new ImageIcon("haha.jpg");
            g.drawImage(icon.getImage(), x, y, getSize().width,
                    getSize().height, this);
            g.drawImage(icon.getImage(), x, y, this);

        }

    }

    private static class LineJPanel extends  JPanel
    {
        public void paint(Graphics g)
        {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.draw(new Line2D.Double(30,10,370,10));
        }
    }

    private class MyButton extends JButton
    {
        public int number;
        public MyButton(String name)
        {
            super(name);
        }
        public void setNumber(int number)
        {
            this.number = number;
        }
    }
}



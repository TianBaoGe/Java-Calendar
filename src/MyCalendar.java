/**
 * Created by tianbao on 17-9-4.
 * @author 20172180143林霭良
 */
import java.util.Calendar;

public class MyCalendar
{
    String day[];
    int year=2017   ,month=0;
    int number  =1;
    Calendar date=Calendar.getInstance();
    int week;

    public void setYear(int year)
    {
        this.year=year;
    }

    public int getYear()
    {
        return year;
    }

    public void setMonth(int month)
    {
        this.month=month;
    }

    public int getMonth()
    {
        return month;
    }

    public int getNumber(){return number;}

    public void setNumber(int number) {this.number = number;}

    public int getWeek()
    {
        date.set(year,month-1,number);
        return date.get(Calendar.DAY_OF_WEEK)-1;
    }


    public String[] getCalendar()
    {
        String a[]=new String[42];
        Calendar date=Calendar.getInstance();
        date.set(year,month-1,1);
        int week=date.get(Calendar.DAY_OF_WEEK)-1;
        int day=0;


        //判断大月份
        if(month==1||month==3||month==5||month==7
                ||month==8||month==10||month==12)
        {
            day=31;
        }

        //判断小月
        if(month==4||month==6||month==9||month==11)
        {
            day=30;
        }

        //判断平年与闰年
        if(month==2)
        {
            if(((year%4==0)&&(year%100!=0))||(year%400==0))
            {
                day=29;
            }

            else
            {
                day=28;
            }
        }

        for(int i=week,n=1;i<week+day;i++)
        {
            a[i]=String.valueOf(n) ;
            n++;
        }
        return a;
    }
}
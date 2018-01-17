/**
 * Created by tianbao on 17-9-4.
 * @author 20172180143林霭良
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Lunar {
    private int year;
    private int month;
    private int day;
    private boolean leap;
    private static String solarTerms = "";
    private static Calendar offDate = Calendar.getInstance();
    private String solarFestival = "";
    private String lunarFestival = "";
    private Calendar temp = Calendar.getInstance();


    public String getSolarFestival() {return solarFestival;}
    public String getLunarFestival(){return lunarFestival;}

    private final String[] Animals = new String[]
            { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };

    private final String[] Gan = new String[]
            { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };

    private final String[] Zhi = new String[]
            { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };

    final static String chineseNumber[] =
            { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };

    final static String Big_Or_Small[] =
            { "大", "小", "大", "小", "大", "小", "大", "大", "小", "大", "小", "大" };

    private static String[] SolarTerm =
            { "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
                    "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪",
                    "冬至" };


    static SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
            " yyyy年MM月dd日 ");

    final static long[] lunarInfo = new long[]
            { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0,
                    0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255,
                    0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0,
                    0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2,
                    0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60,
                    0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550,
                    0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
                    0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0,
                    0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4,
                    0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0,
                    0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540,
                    0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a,
                    0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970,
                    0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5,
                    0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0,
                    0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4,
                    0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
                    0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0,
                    0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7,
                    0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0,
                    0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255,
                    0x06d20, 0x0ada0 };


    final static long[] STermInfo = new long[] { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
            218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532,
            504758 };

    // ====== 传回农历 y年的总天数
    final private static int yearDays(int y)
    {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1)
        {
            if ((lunarInfo[y - 1900] & i) != 0)
                sum += 1;
        }
        return (sum + leapDays(y));
    }


    // ====== 传回农历 y年闰月的天数
    final private static int leapDays(int y)
    {
        if (leapMonth(y) != 0)
        {
            if ((lunarInfo[y - 1900] & 0x10000) != 0)
                return 30;
            else
                return 29;
        }
        else
            return 0;
    }


    // ====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
    final private static int leapMonth(int y)
    {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }


    // ====== 传回农历 y年m月的总天数
    final private static int monthDays(int y, int m)
    {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }


    // ====== 传回农历 y年的生肖
    final public String animalsYear()
    {

        return Animals[(year - 4) % 12];
    }


    // ====== 传入 月日的offset 传回干支, 0=甲子
    final private  String cyclicalm(int num)
    {

        return (Gan[num % 10] + Zhi[num % 12]);
    }


    // ====== 传入 offset 传回干支, 0=甲子
    final public String cyclical()
    {
        int num = year - 1900 + 36;
        return (cyclicalm(num));
    }


    // ===== 某年的第n个节气为几日(从0小寒起算)
    final private static int sTerm(int y, int n) {


        offDate.set(1900, 0, 6, 2, 5, 0);
        long temp = offDate.getTime().getTime();
        offDate.setTime(new Date((long) ((31556925974.7 * (y - 1900) + STermInfo[n] * 60000L) + temp)));


        return offDate.get(Calendar.DAY_OF_MONTH);
    }

    /** */
    /**
     * 传出y年m月d日对应的农历. yearCyl3:农历年与1864的相差数 ? monCyl4:从1900年1月31日以来,闰月数
     * dayCyl5:与1900年1月31日相差的天数,再加40 ?
     *
     * @param cal
     * @return
     */
    public Lunar(Calendar cal)
    {
        // cal.add(cal.get(Calendar.DAY_OF_MONTH),1);
        @SuppressWarnings(" unused ")
        int yearCyl, monCyl, dayCyl;
        int leapMonth = 0;
        Date baseDate = null;
        try
        {
            baseDate = chineseDateFormat.parse(" 1900年1月31日 ");
        }
        catch (ParseException e)
        {
            e.printStackTrace(); // To change body of catch statement use
            // Options | File Templates.
        }


        // 求出和1900年1月31日相差的天数
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        dayCyl = offset + 40;
        monCyl = 14;


        // 用offset减去每农历年的天数
        // 计算当天是农历第几天
        // i最终结果是农历的年份
        // offset是当年的第几天
        int iYear, daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++)
        {
            daysOfYear = yearDays(iYear);
            offset -= daysOfYear;
            monCyl += 12;
        }
        if (offset < 0)
        {
            offset += daysOfYear;
            iYear--;
            monCyl -= 12;
        }
        // 农历年份
        year = iYear;


        yearCyl = iYear - 1864;
        leapMonth = leapMonth(iYear); // 闰哪个月,1-12
        leap = false;


        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        int iMonth, daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++)
        {
            // 闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap)
            {
                --iMonth;
                leap = true;
                daysOfMonth = leapDays(year);
            }
            else
                daysOfMonth = monthDays(year, iMonth);


            offset -= daysOfMonth;
            // 解除闰月
            if (leap && iMonth == (leapMonth + 1))
                leap = false;
            if (!leap)
                monCyl++;
        }
        // offset为0时，并且刚才计算的月份是闰月，要校正
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1)
        {
            if (leap)
            {
                leap = false;
            }
            else
            {
                leap = true;
                --iMonth;
                --monCyl;
            }
        }
        // offset小于0时，也要校正
        if (offset < 0)
        {
            offset += daysOfMonth;
            --iMonth;
            --monCyl;
        }
        month = iMonth;
        day = offset + 1;
    }

    // ******************计算节气**********//
    public static String getSolarTerms(int y,int m,int d){
        if (d == sTerm(y, (m - 1) * 2))
            solarTerms = SolarTerm[(m - 1) * 2];
        else if (d == sTerm(y, (m - 1) * 2 + 1))
            solarTerms = SolarTerm[(m - 1) * 2 + 1];
        else
            solarTerms = "";
        return solarTerms;
    }

    public static String getChinaDayString(int day)
    {
        String chineseTen[] =
                { "初", "十", "廿", "卅" };
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30)
            return "";
        if (day == 10)
            return "初十";
        else
            return chineseTen[day / 10] + chineseNumber[n];
    }


    public String toString()
    {
        return /* cyclical() + "年" + */(leap ? "闰" : "")
                + chineseNumber[month - 1] + "月" + getChinaDayString(day);
    }


    public String numeric_md()
    {// 返回阿拉伯数字的阴历日期
        String temp_day;
        String temp_mon;
        temp_mon = month < 10 ? "0" + month : "" + month;
        temp_day = day < 10 ? "0" + day : "" + day;


        return temp_mon + temp_day;
    }


    public String get_month()
    {// 返回阴历的月份
        return chineseNumber[month - 1];
    }


    public String get_date()
    {// 返回阴历的天
        return getChinaDayString(day);
    }


    public String get_Big_Or_Small()
    {// 返回的月份的大或小
        return Big_Or_Small[month - 1];
    }

    final static String[] sFtv = new String[]{"0101*元旦", "0214 情人节",
            "0308 妇女节", "0312 植树节", "0314 国际警察日", "0315 消费者权益日", "0323 世界气象日",
            "0401 愚人节", "0407 世界卫生日", "0501*劳动节", "0504 青年节", "0508 红十字日",
            "0512 护士节", "0515 国际家庭日", "0517 世界电信日", "0519 全国助残日", "0531 世界无烟日",
            "0601 儿童节", "0605 世界环境日", "0606 全国爱眼日", "0623 奥林匹克日", "0625 全国土地日",
            "0626 反毒品日", "0701 建党节", "0707 抗战纪念日", "0711 世界人口日", "0801 建军节",
            "0908 国际扫盲日", "0909 毛泽东逝世纪念", "0910 教师节", "0917 国际和平日",
            "0920 国际爱牙日", "0922 国际聋人节", "0927 世界旅游日", "0928 孔子诞辰", "1001*国庆节",
            "1004 世界动物日", "1006 老人节", "1007 国际住房日", "1009 世界邮政日", "1015 国际盲人节",
            "1016 世界粮食日", "1024 联合国日", "1031 万圣节", "1108 中国记者日", "1109 消防宣传日",
            "1112 孙中山诞辰", "1114 世界糖尿病日", "1117 国际大学生节", "1128 感恩节",
            "1201 世界艾滋病日", "1203 世界残疾人日", "1209 世界足球日", "1220 澳门回归",
            "1225 圣诞节", "1226 毛xx诞辰"};

    final static String[] lFtv = {"0101*  春节", "0115   元宵", "0505   端午",
            "0707   七夕", "0815   中秋", "0909   重阳", "1208   腊八", "1223   小年",
            "0100*  除夕"};

    final static String[] wFtv = {"0521 母亲节", "0631 父亲节"};//每年6月第3个星期日是父亲节,5月的第2个星期日是母亲节


    public void CalculateLunarCalendar(int y, int m, int d)
    {
        temp.set(y,m-1,d);
        //计算公历节日
        this.solarFestival = "";
        for (int i = 0; i < sFtv.length; i++)
        {
            if (Integer.parseInt(sFtv[i].substring(0, 2)) == m && Integer.parseInt(sFtv[i].substring(2, 4)) == d)
            {
                solarFestival = sFtv[i].substring(5);
            }
        }
        //计算农历节日
        this.lunarFestival = "";
        for (int i = 0; i < lFtv.length; i++)
        {
            if (Integer.parseInt(lFtv[i].substring(0, 2)) == month-1 && Integer.parseInt(lFtv[i].substring(2, 4)) == day)
            {
                lunarFestival = lFtv[i].substring(5);
            }
        }
        //计算月周节日

        // System.out.println(baseDate.get(Calendar.WEEK_OF_MONTH) + ""
        //       + baseDate.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < wFtv.length; i++)
        {
            if (Integer.parseInt(wFtv[i].substring(0, 2)) == m && Integer.parseInt(wFtv[i].substring(2, 3)) == temp.get(Calendar.WEEK_OF_MONTH)-1 && Integer.parseInt(wFtv[i].substring(3, 4)) == temp.get(Calendar.DAY_OF_WEEK))
            {
                solarFestival += wFtv[i].substring(5);
            }
        }
    }

}



import java.text.DateFormat;

import java.util.Date;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import  java.util.*;
/**
 * Created by tianbao on 17-9-4.
 * @author 20172180143林霭良
 */
class TianDi

{
    String result;
    TianGanTable a = new TianGanTable();

    public TianDi(int y,int m,int d)
    {
        a.tianDiDay(y,m,d);
        result = a.getResult();
    }

    public String toString()
    {
        return result;
    }

}

class TianGanTable

{

    private static final String[] arrTian = {"甲","乙","丙","丁",	"戊","己","庚","辛","壬","癸"};

    private static final String[] arrDi = {"子","丑","寅","卯","辰","巳","午",	"未","申","酉","戌","亥"};

    private static Map<Integer,String> tian = new HashMap<Integer,String>();

    private static Map<Integer,String> di = new HashMap<Integer,String>();

    private String result;

    public String getResult() {return result;}

    TianGanTable()

    {

        tianDiPut(tian,arrTian);

        tianDiPut(di,arrDi);

    }

//计算日

    public  void tianDiDay(int Y,int M,int D)

    {

//获取日期



        Calendar ca = Calendar.getInstance();

        ca.set(Y,M-1,D);

        int days = ca.get(Calendar.DAY_OF_MONTH);

        int month = ca.get(Calendar.MONTH)+1;

        int year = ca.get(Calendar.YEAR);

        int last_year = Y % 100;//获取年的后2位

        int first_year = (Y - Y%100)/100 ;//获取年的前2位

//计算年的天干地支

        int indexTian = (year-3)%10; //天

        int indexDi = (year-3)%12;	//地

        String yearOfTianDi = tian.get(indexTian)+di.get(indexDi); //得到年的天干地支

//计算日的天干地支

//日的天

        int g=(int)(4*first_year+Math.floor(first_year/4)+5*last_year+Math.floor(last_year/4)+Math.floor(3*(month+1)/5)+days-3);

        g = g%10;

//日的地

        int i=0;

        if (month%2==0)

        {

            i=6;

        }

        int z=(int)(8*first_year+Math.floor(first_year/4)+5*last_year+Math.floor(last_year/4)+Math.floor(3*(month+1)/5)+days+7+i);

        z = z%12;

        String c = tian.get(g)+di.get(z);	//得到日的天干地支

//计算月的天干地支

        String strMonth = month+"月";

        String tianGanOfYear = tian.get(indexTian); //获取年的天干

//判断输入日期年的天干对应月地支的位置

        Map monthTable = monthTable();

        int indexOfmonth = indexOfmonthTable(tianGanOfYear);

        if (monthTable.containsKey(strMonth))

        {

            Map stmpMonth = (Map)monthTable.get(strMonth);



            result = String.format(stmpMonth.get(indexOfmonth)+" "+c+"日");

        }else{

            System.out.println("输入的月份不存在");

        }

    }

//将天干、地支数组添加到Map集合

    public static void tianDiPut(Map<Integer,String> tiandi,String[] arr)

    {

        for (int i=0; i<arr.length ;i++ )

        {

            if (i!=arr.length-1)

            {

                tiandi.put(i+1,arr[i]);

            }else

            {

                tiandi.put(0,arr[arr.length-1]);

            }

        }

    }

//天干地支对照表，返回一个Map集合

    public static Map tianDiTable()

    {

        Map<Integer,String> indexTable = new HashMap<Integer,String>();

        int x = 0;

        while (x<60)

        {

            int i = x%10;

            int j = x%12;

            int key = x+1;

            String values = arrTian[i]+arrDi[j];

            indexTable.put(key,values);

            x++;

        }

        return indexTable;

    }

//月地支表

    public static Map monthTable ()

    {

        String[][] arr = {{"丙寅月","戊寅月","庚寅月","壬寅月","甲寅月"},

                {"丁卯月","己卯月","辛卯月","癸卯月","乙卯月"},

                {"戊辰月","庚辰月","壬辰月","甲辰月","丙辰月"},

                {"己巳月","辛巳月","癸巳月","乙巳月","丁巳月"},

                {"庚午月","壬午月","甲午月","丙午月","戊午月"},

                {"辛未月","癸未月","乙未月","丁未月","己未月"},

                {"壬申月","甲申月","丙申月","戊申月","庚申月"},

                {"癸酉月","乙酉月","丁酉月","己酉月","辛酉月"},

                {"甲戌月","丙戌月","戊戌月","庚戌月","壬戌月"},

                {"乙亥月","丁亥月","己亥月","辛亥月","癸亥月"},

                {"丙子月","戊子月","庚子月","壬子月","甲子月"},

                {"丁丑月","己丑月","辛丑月","癸丑月","乙丑月"}};

        String[] monthNum = {"2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月","1月"};

        Map<String,Map<Integer,String>> map = new HashMap<String,Map<Integer,String>>();

        for (int i=0;i<arr.length;i++)

        {

            Map<Integer,String> map1 = new HashMap<Integer,String>();

            for (int j=0;j<arr[i].length ;j++ )

            {

                map1.put(j+1,arr[i][j]);

            }

            map.put(monthNum[i],map1);

        }

        return map;

    }

//年的天干对应月地支的位置

    public static int indexOfmonthTable(String tianGanOfYear )

    {

        String[] tmp = {"","甲己","乙庚","丙辛","丁壬","戊癸"};

        int num=0 ;

        for (int i=0;i<tmp.length ;i++ )

        {

            if (tmp[i].contains(tianGanOfYear))

            {

                num = i;

            }

        }

        return num;

    }

}


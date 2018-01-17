import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by tianbao on 17-9-4.
 * @author 20172180143林霭良
 */
public class MainFrame
{
    public static void main(String[] args)
    {


        JFrame frame = new MyFrame();
        frame.setTitle("天保的日历");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Toolkit tool=frame.getToolkit(); //得到一个Toolkit对象
        Image myimage=tool.getImage("timg.gif");
        frame.setIconImage(myimage);
        frame.setVisible(true);


        /*
        String[] word = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String name:word)
        {
            System.out.println(name);
        }
        */
    }
}

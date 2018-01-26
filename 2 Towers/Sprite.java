import java.awt.*;
import javax.swing.*;
public class Sprite extends JPanel
{
    protected int x;
    protected int y;
    public Sprite(int myX, int myY)
    {
        x = myX;
        y = myY;

    }

    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
     public void setX(int myX)
    {
        x = myX;
    }
     public void setY(int myY)
    {
        y = myY;
    }
}





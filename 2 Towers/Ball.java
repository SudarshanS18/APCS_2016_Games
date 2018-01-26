import java.awt.*;
import javax.swing.*;
public class Ball extends Sprite
{
    protected int width;
    protected int height;
    public Ball(int x, int y, int w, int h)
    {
        super(x,y);
        width = w;
        height = h;
    }

    public void move(Line l, boolean lineCheck, boolean lineFall)
    {
        if(l.getLineDone() && !lineFall)
        {
            if(x <= l.getWidth()  + l.getX()  )
            {
                x += 3;
            }

        }
        if(!lineCheck)
        {
            if(x > l.getWidth() + l.getX())
            {
                y += 6; 

            }
        }
    }
      public void setHeight(int h)
    {
        height = h;
    }
      public void setWidth(int w)
    {
        width = w;
    }
}



import java.awt.geom.*;
import java.awt.event.*;
import java.awt.Color.*;
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class Line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line extends Sprite
{

    protected int width;
    protected int height;
    protected boolean lineDone;
    protected boolean lineFall;

    public Line(int x, int y)
    {
        super(x,y);
        width = 0;
        height = 0;
        lineDone = false;
        lineFall = false;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public boolean getLineDone()
    {
        return lineDone; 
    }

    public void fall()
    {
        if(y == 300)
        {
            lineFall = false;

        }
        else
        {
            y = y + 5;
        }
    }

    public void keyPressed(KeyEvent e, int mode) 
    {

        int key = e.getKeyCode();
        lineFall = false;

        if(!lineDone)
        {
            if(mode == 1)
            {
                if (key == KeyEvent.VK_SPACE)
                {

                    height = 4;
                    width += 8;
                }
            }
            else if( mode == 2)
            {
                if (key == KeyEvent.VK_SPACE)
                {
                    height = 4;
                    width += (int)(Math.random() * 25);
                }
            }
            else if (mode == 3)
            {
                if (key == KeyEvent.VK_SPACE)
                {
                    y = 150;
                    height = 4;
                    width+= (int) ((Math.random() * 15) +3);
                }
            }
        }

    }

    public void keyReleased(KeyEvent e, int mode)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE)
        {

            lineDone = true;
            lineFall = true;
        }
    }

    public boolean lineDone()
    {
        return lineDone;
    }

    public boolean lineFall()
    {
        return lineFall;
    }

    public void setHeight(int h)
    {
        height = h;
    }

    public void setWidth(int w)
    {
        width = w;
    }

    public void setLineDone(boolean boo)
    {
        lineDone = boo;
    }

    
}


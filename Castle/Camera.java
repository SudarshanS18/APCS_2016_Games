import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
/**
 * Write a description of class Camera here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camera
{
    public int dx;
    public int dy;
    public int x;
    public int y;
    public int ticks;
    public int jumpCtr;
    public boolean xMotionRight;
    public boolean xMotionLeft;
    public Camera(int x, int y)
    {
        this.x=x;
        this.y=y;
        ticks=0;
        jumpCtr=0;
        xMotionRight=true;
        xMotionLeft=true;
    }

    public int getDX()
    {
        return dx;
    }

    public int getDY()
    {
        return dy;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void move()
    {
         //x += dx;
    }
    
    public void keyPressed(KeyEvent e) 
    {

        int keyBoard = e.getKeyCode();

        if (keyBoard == KeyEvent.VK_LEFT&&xMotionLeft) 
        {
            dx = -5;
        }
        if (keyBoard == KeyEvent.VK_RIGHT&&xMotionRight) 
        {
            dx = 5;
        }
         if (keyBoard == KeyEvent.VK_UP)
        {
            if(jumpCtr<1)
            {
                jump();
            }
            jumpCtr++;
        }
    }
     private void jump()
    {
        if (dy > 0)
        {
            dy=0;
        }

        dy-=10;
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int keyBoard = e.getKeyCode();

        if (keyBoard == KeyEvent.VK_LEFT) 
        {
            dx = 0;
        }
        if (keyBoard == KeyEvent.VK_UP)
        {
            //do nothing
        }
        if (keyBoard == KeyEvent.VK_RIGHT) 
        {
            dx = 0;
        }
    }
    
    public void xLeft(boolean b)
    {
        xMotionLeft=b;
    }
    public void xRight(boolean b)
    {
        xMotionRight=b;
    }
    public void gravity(boolean gr)
    {
        if (gr)
        {
            ticks++;
            if (ticks%3 == 0 && dy <15)
            {
                dy+=1;
            }
        }
    }
}

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
public class Hero extends GameObject
{
    private boolean key;
    
    public boolean gravity;
    public Hero(int x, int y)
    {
        super(x, y);
        key = false;
        gravity=true;
    }

    public void makeHero()
    {
        loadImage("HEROTEST.png");
        getImageDimensions();
    }


    public boolean hasKey()
    {
        return key;
    }

    

    //     public void keyTyped(KeyEvent e)
    //     {
    //         int keyBoard = e.getKeyCode();
    //         if (keyBoard == KeyEvent.VK_UP)
    //         {
    //             jump();
    //         }
    //     }
    
    

   
    
    public void gravityOn(Camera c)
    {
        gravity=true;
    }
    public void gravityOff(Camera c)
    {
        gravity = false;
        c.dy=0;
        c.jumpCtr=0;
    }
    
}
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class GameObject
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image img;
    private int ticks;
    public GameObject(int ax, int ay)
    {
        x=ax;
        y=ay;
        visible=true;
        ticks=0;
    }
    
    protected void getImageDimensions()
    {
        width=img.getWidth(null);
        height = img.getHeight(null);
    }

    protected void loadImage(String imageName)
    {
        ImageIcon ii=new ImageIcon (imageName);
        img=ii.getImage();
    }
    
    public void togVisible()
    {
        visible=!visible;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x,y, width, height);
    }

    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public Image getImage()
    {
        return img;
    }
    
    public void setVisible(boolean b)
    {
        visible=b;
    }
    
    public void move(Camera c)
    {
        x-=c.getDX();
        y-=c.getDY();
    }
}

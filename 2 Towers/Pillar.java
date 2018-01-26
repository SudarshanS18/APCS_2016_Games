/**
 * Write a description of class Pillar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pillar extends Sprite
{
    protected int width;
    protected int height;
    // protected int 
    public Pillar(int x, int y, int myWidth, int myHeight)
    {
        super(x,y);
        width = myWidth;
        height = myHeight;
    }
      public void setHeight(int h)
    {
        height = h;
    }
      public void setWidth(int w)
    {
        width = w;
    }
    
    
    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }
}



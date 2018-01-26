
/**
 * Write a description of class MovingGround here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingGround extends Ground
{
    private int dist;
    private int Xo;
    private int Yo;
    private int movement;
    public MovingGround(int x, int y, String name, int moveDist)
    {
        super(x,y,name);
        Xo=x;
        Yo=y;
        movement=2;
        dist=moveDist;
    }
    
    public void moveGround(Camera c)
    {
        Xo-=c.dx;
        Yo-=c.dy;
        if (this.x<Xo+dist&&this.x>=Xo)
        {
            x+=movement;
        }
        else if (this.x>=Xo+dist||this.x<=Xo)
        {
            movement=movement*-1;
            x+=movement;
        }
        else
        {
            System.out.println("Out of Bounds");
        }
    }
}

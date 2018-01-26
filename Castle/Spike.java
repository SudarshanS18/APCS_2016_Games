
/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spike extends GameObject
{
    public Spike (int x, int y)
    {
        super(x,y);
    }
    
    public void makeSpike()
    {
        loadImage("spikes.png");
        getImageDimensions();
    }
}


/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VertSpikeLeft extends GameObject
{
    public VertSpikeLeft (int x, int y)
    {
        super(x,y);
    }
    
    public void makeVertSpikeLeft()
    {
        loadImage("vertSpikes.png");
        getImageDimensions();
    }
}

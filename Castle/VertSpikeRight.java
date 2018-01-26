
/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VertSpikeRight extends GameObject
{
    public VertSpikeRight (int x, int y)
    {
        super(x,y);
    }

    public void makeVertSpikeRight()
    {
        loadImage("vertSpikesRight.png");
        getImageDimensions();
    }
}


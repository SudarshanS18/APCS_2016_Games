
/**
 * Write a description of class WinZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinZone extends GameObject
{
    public WinZone(int x, int y)
    {
        super (x, y);
    }
    
    public void makeWinZone()
    {
        loadImage("winZone.png");
        getImageDimensions();
    }
}

import java.awt.*;
public class Key extends GameObject
{
	public Key(int x, int y, Image m)
	{
		super(x, y);
	}
	
	public void makeKey()
	{
		loadImage("... .png");
		getImageDimensions();
	}
}
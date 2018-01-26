import java.awt.*;
public class Door  extends GameObject
{
	public Door(int x, int y, Image m)
	{
		super(x, y);
	}
	
	public void makeDoor()
	{
		loadImage("... .png");
		getImageDimensions();
	}
}
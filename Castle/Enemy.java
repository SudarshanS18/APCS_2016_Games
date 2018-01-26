import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
public class Enemy extends GameObject
{
	private final int INITIAL_X;
	private boolean direction;
	
	public Enemy(int x, int y)
	{
		super(x, y);
		INITIAL_X=0;
		direction = false; //false is left, true is right
	}
	
	public void changeDirection()
	{
		direction = !direction;
	}
	
	public void makeEnemy()
	{
		loadImage("heart.png");
		getImageDimensions();
	}
	public void move() 
	{

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }
}
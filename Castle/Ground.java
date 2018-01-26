
public class Ground extends GameObject
{
    private String filename;
    public Ground(int x, int y, String thefilename)
    {
        super (x, y);
        filename=thefilename;
    }

    public void makeGround()
    {
        loadImage(filename);
        getImageDimensions();
    }

}

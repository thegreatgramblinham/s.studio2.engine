import java.awt.*;

public class GameObject
{
    //Variables
    private Rectangle _size;

    //Constructors
    public GameObject()
    {

    }

    //Get Methods
    public Rectangle GetSize()
    {
        return _size;
    }

    //Set Methods
    public void SetSize(Rectangle rect)
    {
        _size = rect;
    }
}
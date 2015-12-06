package GameObjectBase;

import java.awt.*;

public class GameObject
{
    //Variables
    private Rectangle _size;
    private int _x;
    private int _y;

    //Constructors
    public GameObject()
    {

    }

    //Get Methods
    public Rectangle GetSize()
    {
        return _size;
    }

    public int GetX()
    {
        return _x;
    }

    public int GetY()
    {
        return _y;
    }

    //Set Methods
    public void SetSize(Rectangle rect)
    {
        _size = rect;
    }

    public void SetX(int x)
    {
        _x = x;
    }

    public void SetY(int y)
    {
        _y = y;
    }
}
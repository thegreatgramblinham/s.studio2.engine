package GameObjectBase;

import java.awt.*;

public class GameObject
{
    //Properties
    private Rectangle Size;
    private int X;
    private int Y;

    //Constructors
    public GameObject()
    {

    }

    //Get Methods
    public Rectangle GetSize()
    {
        return Size;
    }

    public int GetX()
    {
        return X;
    }

    public int GetY()
    {
        return Y;
    }

    //Set Methods
    public void SetSize(Rectangle rect)
    {
        Size = rect;
    }

    public void SetX(int x)
    {
        X = x;
    }

    public void SetY(int y)
    {
        Y = y;
    }
}
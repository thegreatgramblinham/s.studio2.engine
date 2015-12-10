package GameObjectBase;

import java.awt.*;
import java.util.UUID;

public abstract class GameObject
{
    //Properties
    private Rectangle _size;
    private int _x;
    private int _y;
    private UUID _id;

    //Constructors
    public GameObject(Rectangle size)
    {
        _size = size;
        _x = _y = 0;
        _id = UUID.randomUUID();
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

    public UUID GetID()
    {
        return _id;
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

    //Public Methods
    public void SetLocation(Point p)
    {
        if(p == null) return;
        SetX(p.x);
        SetY(p.y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof GameObject)) return false;
        if(!(obj.hashCode() == hashCode())) return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        return _id.hashCode();
    }
}
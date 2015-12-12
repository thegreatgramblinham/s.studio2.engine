package GameObjectBase;

import java.awt.*;
import java.util.UUID;

public abstract class GameObject extends BoundedObject
{
    //Properties
    private UUID _id;

    //Constructors
    public GameObject(Rectangle size)
    {
        super(size);
    }

    public GameObject(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    public GameObject(int width, int height)
    {
        super(width, height);
    }

    //Get Methods
    public UUID GetID()
    {
        return _id;
    }

    //Public Methods
    public void SetLocation(Point p)
    {
        if(p == null) return;
        x = p.x;
        y =p.y;
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

    //Private Methods
    private void Init()
    {
        _id = UUID.randomUUID();
    }
}
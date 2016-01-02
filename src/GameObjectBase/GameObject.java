package GameObjectBase;

import java.awt.*;
import java.util.UUID;

abstract class GameObject extends BoundedObject
{
    //Properties
    private UUID _id;

    //Constructors
    public GameObject(Rectangle size)
    {
        super(size);
        Init();
    }

    public GameObject(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        Init();
    }

    public GameObject(int width, int height)
    {
        super(width, height);
        Init();
    }

    //Get Methods
    public UUID GetID()
    {
        return _id;
    }

    //Public Methods
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
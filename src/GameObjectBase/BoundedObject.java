package GameObjectBase;

import java.awt.*;

public abstract class BoundedObject extends Rectangle
{
    //Private Variables

    //Properties

    //Constructor
    public BoundedObject(Rectangle rect)
    {
        super(rect);
    }

    public BoundedObject(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    public BoundedObject(int width, int height)
    {
        super(width, height);
    }

    //GetMethods

    //SetMethods

    //Public Methods

    //Private Methods
}

package GameObjectBase;

import GameObjectBase.BoundedObject;

import java.awt.*;

public class HitBox extends BoundedObject
{
    //Private Variables

    //Properties

    //Constructor
    public HitBox(Point point, int width, int height)
    {
        super(point.x, point.y, width, height);
    }

    public HitBox(Rectangle rect)
    {
        super(rect);
    }

    //GetMethods

    //SetMethods

    //Public Methods

    //Private Methods
}

package GameObjectBase;

import GameObjectBase.BoundedObject;

import java.awt.*;

public class HitBox extends BoundedObject
{
    //Private Variables
    private Point _hitBoxOffset;

    //Properties

    //Constructor
    public HitBox(Rectangle rect, Point objectLocation)
    {
        super(rect);
        _hitBoxOffset = new Point(
                objectLocation.x - rect.x, objectLocation.y - rect.y);
    }

    //GetMethods

    //SetMethods

    //Public Methods
    @Override
    public void DSetLocation(double x, double y)
    {
        super.DSetLocation(x - _hitBoxOffset.x,
                y - _hitBoxOffset.y);
    }

    @Override
    public void NSetLocation(Point p)
    {
        super.NSetLocation(new Point(x - _hitBoxOffset.x,
                y - _hitBoxOffset.y));
    }

    //Private Methods

    //Override Handling
    @Override
    public void setLocation(Point p)
    {
        this.NSetLocation(p);
    }

    @Override
    public void setLocation(int x, int y)
    {
        this.NSetLocation(new Point(x,y));
    }
}

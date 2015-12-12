package PhysicsBase;

import java.awt.*;

public class HitBox extends Rectangle
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
    public int GetTop()
    {
        return (int)this.getCenterY() + this.GetHalfHeight();
    }

    public int GetRight()
    {
        return (int)this.getCenterX() + this.GetHalfWidth();
    }

    public int GetBottom()
    {
        return (int)this.getCenterY() -  this.GetHalfHeight();
    }

    public int GetLeft()
    {
        return (int)this.getCenterX() - this.GetHalfWidth();
    }

    public int GetHalfWidth()
    {
        return (int)Math.ceil(this.width/2.0);
    }

    public int GetHalfHeight()
    {
        return (int)Math.ceil(this.height/2.0);
    }

    //SetMethods

    //Public Methods

    //Private Methods
}

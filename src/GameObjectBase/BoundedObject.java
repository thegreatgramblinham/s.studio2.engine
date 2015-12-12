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

    //Get Methods
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

    //Public Methods
    public Point GetCenterPoint()
    {
        return new Point((int)this.getCenterX(), (int)this.getCenterY());
    }

    //Private Methods
}

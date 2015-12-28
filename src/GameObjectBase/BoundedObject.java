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
        return y + height;
    }

    public int GetRight()
    {
        return x + width;
    }

    public int GetBottom()
    {
        return y;
    }

    public int GetLeft()
    {
        return x;
    }

    public double GetHalfWidth()
    {
        return (this.width/2.0);
    }

    public double GetHalfHeight()
    {
        return (this.height/2.0);
    }

    //Public Methods
    public Point GetCenterPoint()
    {
        return new Point((int)this.getCenterX(), (int)this.getCenterY());
    }

    //Private Methods
}

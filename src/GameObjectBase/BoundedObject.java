package GameObjectBase;

import java.awt.*;

public abstract class BoundedObject extends Rectangle
{
    //Private Variables
    protected double _x;
    protected double _y;

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

    public int GetX()
    {
        return (int)Math.ceil(_x);
    }

    public int GetY()
    {
        return (int)Math.ceil(_y);
    }

    //Set Methods
    public void SetX(double x)
    {
        _x = x;
        this.x = (int)Math.ceil(_x);
    }

    public void SetY(double y)
    {
        _y = y;
        this.y = (int)Math.ceil(y);
    }

    //Public Methods
    public Point GetCenterPoint()
    {
        return new Point((int)this.getCenterX(), (int)this.getCenterY());
    }

    public void DSetLocation(double x, double y)
    {
        this.x = (int)Math.ceil(x);
        _x = x;

        this.y = (int)Math.ceil(y);
        _y = y;
    }

    public void NSetLocation(Point p)
    {
        if(p == null) return;
        x = p.x;
        _x = p.x;

        y = p.y;
        _y = p.y;
    }

    public Point NGetLocation()
    {
        return new Point(x,y);
    }

    //Private Methods
}

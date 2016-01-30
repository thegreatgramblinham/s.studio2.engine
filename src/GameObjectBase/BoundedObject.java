package GameObjectBase;

import Global.DPoint;

import java.awt.*;

public abstract class BoundedObject extends Rectangle
{
    //Private Variables
    protected double _dx;
    protected double _dy;

    //Properties

    //Constructor
    public BoundedObject(Rectangle rect)
    {
        super(rect);
        Init();
    }

    public BoundedObject(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        Init();
    }

    public BoundedObject(int width, int height)
    {
        super(width, height);
        Init();
    }

    //Get Methods
    public int GetTop()
    {
        return (int)getY();
    }

    public int GetBottom()
    {
        return (int)(getY() + height);
    }

    public int GetLeft()
    {
        return (int)getX();
    }

    public int GetRight()
    {
        return (int)(getX() + width);
    }

    public double GetHalfWidth()
    {
        return (this.width/2.0);
    }

    public double GetHalfHeight()
    {
        return (this.height/2.0);
    }

    public double GetDX()
    {
        return _dx;
    }

    public double GetDY()
    {
        return _dy;
    }

    public DPoint DGetLocation()
    {
        return new DPoint(GetDX(), GetDY());
    }

    public Point NGetLocation()
    {
        return new Point(x,y);
    }

    public Point GetUpperLeft()
    {
        return new Point(this.GetLeft(), this.GetTop());
    }

    public Point GetUpperRight()
    {
        return new Point(this.GetRight(), this.GetTop());
    }

    public Point GetLowerLeft()
    {
        return new Point(this.GetLeft(), this.GetBottom());
    }

    public Point GetLowerRight()
    {
        return new Point(this.GetRight(), this.GetBottom());
    }

    @Override
    public double getX()
    {
        return Math.round(_dx);
    }

    @Override
    public double getY()
    {
        return Math.round(_dy);
    }

    //Set Methods
    public void SetDX(double x)
    {
        _dx = x;
        this.x = (int)Math.round(_dx);
    }

    public void SetDY(double y)
    {
        _dy = y;
        this.y = (int)Math.round(y);
    }

    public void DSetLocation(double x, double y)
    {
        this.x = (int)Math.round(x);
        SetDX(x);

        this.y = (int)Math.round(y);
        SetDY(y);
    }

    public void NSetLocation(Point p)
    {
        if(p == null) return;

        SetDX(p.x);
        SetDY(p.y);
    }

    //Public Methods
    public Point GetCenterPoint()
    {
        return new Point((int)Math.round(getCenterX()),
                (int)Math.round(getCenterY()));
    }

    public boolean BoundsEquals(Rectangle other)
    {
        return this.x == other.x &&
                this.y == other.y &&
                this.width == other.width &&
                this.height == other.height;
    }

    public boolean IsLeftAlignedTo(double x, double tolerance)
    {
        return Math.abs(this.GetLeft() - x) <= tolerance;
    }

    public boolean IsRightAlignedTo(double x, double tolerance)
    {
        return Math.abs(this.GetRight() - x) <= tolerance;
    }

    public boolean IsTopAlignedTo(double y, double tolerance)
    {
        return Math.abs(this.GetTop() - y) <= tolerance;
    }

    public boolean IsBottomAlignedTo(double y, double tolerance)
    {
        return Math.abs(this.GetBottom() - y) <= tolerance;
    }

    //Private Methods
    private void Init()
    {
        _dx = x;
        _dy = y;
    }

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

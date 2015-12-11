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

    //GetMethods
    public int GetTop()
    {
        return (int)this.getCenterY() + (this.height/2);
    }

    public int GetRight()
    {
        return (int)this.getCenterX() + (this.width/2);
    }

    public int GetBottom()
    {
        return (int)this.getCenterY() - (this.height/2);
    }

    public int GetLeft()
    {
        return (int)this.getCenterX() - (this.width/2);
    }

    //SetMethods

    //Public Methods

    //Private Methods
}

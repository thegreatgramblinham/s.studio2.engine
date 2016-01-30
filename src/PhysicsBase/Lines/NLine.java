package PhysicsBase.Lines;

import GeneralHelpers.PointHelper;

import java.awt.*;

public class NLine
{
    //Public Variables
    public Point point1;
    public Point point2;


    //Constructor
    public NLine(Point p1, Point p2)
    {
        point1 = p1;
        point2 = p2;
    }

    //Public Methods
    public double GetLength()
    {
        return PointHelper.DistanceTo(point1, point2);
    }

    //Private Methods
}

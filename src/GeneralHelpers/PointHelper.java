package GeneralHelpers;

import PhysicsBase.Vectors.DistanceVector;

import java.awt.*;

public final class PointHelper
{
    //Constructor
    private PointHelper(){}

    //Public Methods
    public static double DistanceTo(Point p1, Point p2)
    {
        int a = p2.x - p1.x;
        int b = p2.x - p1.x;

        double dist = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        return dist;
    }

    public static double SlopeOf(Point p1, Point p2)
    {
        int a = p2.x - p1.x;
        int b = p2.y - p2.y;

        double slp = (double)b / (double)a;

        return slp;
    }

    public static double HorizontalDistanceBetween(Point p1, Point p2)
    {
        return Math.abs(p2.x - p1.x);
    }

    public static double VerticalDistanceBetween(Point p1, Point p2)
    {
        return Math.abs(p2.y - p1.y);
    }

    public static Point NTranslateNewPoint(Point p, DistanceVector v)
    {
        double x2 = TranslateX(p.x, v);

        double y2 = TranslateY(p.y, v);

        return new Point((int)Math.ceil(x2),(int)Math.ceil(y2));
    }

    public static double TranslateX(double x, DistanceVector v)
    {
        double xScale = Math.cos(v.GetRadianRotation());
        double xDis = xScale * v.GetLength();
        double x2 = x + xDis;

        return x2;
    }

    public static double TranslateY(double y, DistanceVector v)
    {
        double yScale = Math.sin(v.GetRadianRotation());
        double yDis = yScale * v.GetLength();
        double y2 = y + yDis;


        return y2;
    }

}

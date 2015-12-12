package GeneralHelpers;

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
        return Math.abs(p2.y - p2.y);
    }
}

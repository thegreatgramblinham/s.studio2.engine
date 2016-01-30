package PhysicsBase.Lines;

import java.awt.*;

public final class LineHelper
{
    //Private Variables 

    //Constructors
    private LineHelper(){}

    //Public Methods
    public static boolean Intersect(NLine l1, NLine l2)
    {
        //todo need check for colinear
        return (CheckSegmentTriangle(l1.point1, l2.point1, l2.point2) !=
                    CheckSegmentTriangle(l1.point2, l2.point1, l2.point2)) &&
               (CheckSegmentTriangle(l1.point1, l1.point2, l2.point1) !=
                    CheckSegmentTriangle(l1.point1, l1.point2, l2.point2));
    }

    //Private Methods
    private static boolean CheckSegmentTriangle(Point p1, Point p2, Point q1)
    {
        return ((q1.getY() - p1.getY()) * (p2.getX() - p1.getX()))
                > ((p2.getY() - p1.getY()) * (q1.getX()-p1.getX()));
    }
}

package GeneralHelpers;

import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public final class VectorHelper
{
    //Constructor
    private VectorHelper(){}

    //Public Methods
    public static VelocityVector AddVectors(VelocityVector v1, VelocityVector v2)
    {
        if(v1 == null) return v2;
        if(v2 == null) return v1;

        double v1x = v1.GetSpeed()*Math.cos(v1.GetRadianRotation());
        double v1y = v1.GetSpeed()*Math.sin(v1.GetRadianRotation());
        double v2x = v2.GetSpeed()*Math.cos(v2.GetRadianRotation());
        double v2y = v2.GetSpeed()*Math.sin(v2.GetRadianRotation());

        Point p1 = new Point((int)v1x,(int)v1y);
        Point p2 = new Point((int)v2x,(int)v2y);

        double dist = PointHelper.DistanceTo(p1,p2);

        return new VelocityVector(Math.atan(PointHelper.SlopeOf(p1,p2)), dist);
    }

    //Private Methods
}

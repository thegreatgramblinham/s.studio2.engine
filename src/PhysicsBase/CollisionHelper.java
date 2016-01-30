package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GameObjectBase.HitBox;
import GeneralHelpers.PointHelper;
import PhysicsBase.Lines.LineHelper;
import PhysicsBase.Lines.NLine;
import PhysicsBase.Vectors.DistanceVector;
import PhysicsBase.Vectors.VelocityVector;

import java.util.HashSet;

final class CollisionHelper
{
    //Properties
    private CollisionHelper(){}

    //Public Methods
    public static boolean Collision(GameWorldObject o1, GameWorldObject o2)
    {
        return CheckHorizontalOverlap(o1.GetHitBox(), o2.GetHitBox())
                && CheckVerticalOverlap(o1.GetHitBox(), o2.GetHitBox());

    }

    public static boolean WillCollide(GameWorldObject o1, GameWorldObject o2)
    {
        VelocityVector v1 = o1.GetVelocity();
        VelocityVector v2 = o2.GetVelocity();

        HashSet<NLine> o1Lines = new HashSet<>();
        HashSet<NLine> o2Lines = new HashSet<>();

        //Upper Left
        o1Lines.add(new NLine(o1.GetUpperLeft(),
                PointHelper.NTranslateNewPoint(o1.GetUpperLeft(),
                        new DistanceVector(v1.GetRadianRotation(), v1.GetSpeed()))));
        o2Lines.add(new NLine(o2.GetUpperLeft(),
                PointHelper.NTranslateNewPoint(o2.GetUpperLeft(),
                        new DistanceVector(v2.GetRadianRotation(), v2.GetSpeed()))));

        //Upper Right
        o1Lines.add(new NLine(o1.GetUpperRight(),
                PointHelper.NTranslateNewPoint(o1.GetUpperRight(),
                        new DistanceVector(v1.GetRadianRotation(), v1.GetSpeed()))));
        o2Lines.add(new NLine(o2.GetUpperRight(),
                PointHelper.NTranslateNewPoint(o2.GetUpperRight(),
                        new DistanceVector(v2.GetRadianRotation(), v2.GetSpeed()))));

        //Lower Left
        o1Lines.add(new NLine(o1.GetLowerLeft(),
                PointHelper.NTranslateNewPoint(o1.GetLowerLeft(),
                        new DistanceVector(v1.GetRadianRotation(), v1.GetSpeed()))));
        o2Lines.add(new NLine(o2.GetLowerLeft(),
                PointHelper.NTranslateNewPoint(o2.GetLowerLeft(),
                        new DistanceVector(v2.GetRadianRotation(), v2.GetSpeed()))));

        //Lower Right
        o1Lines.add(new NLine(o1.GetLowerRight(),
                PointHelper.NTranslateNewPoint(o1.GetLowerRight(),
                        new DistanceVector(v1.GetRadianRotation(), v1.GetSpeed()))));
        o2Lines.add(new NLine(o2.GetLowerRight(),
                PointHelper.NTranslateNewPoint(o2.GetLowerRight(),
                        new DistanceVector(v2.GetRadianRotation(), v2.GetSpeed()))));


        boolean willCollide = false;
        for (NLine line1 : o1Lines)
        {
            for (NLine line2 : o2Lines)
            {
                if(LineHelper.Intersect(line1, line2))
                {
                    willCollide = true;
                    break;
                }
            }
            if(willCollide) break;
        }

        if(!willCollide) return false;

        //todo creating event part for handling in another module?

        return true;
    }

    //Private Methods
    private static boolean CheckVerticalOverlap(HitBox o1, HitBox o2)
    {
        double threshold = o1.GetHalfHeight() + o2.GetHalfHeight();
        double actual = PointHelper.VerticalDistanceBetween(o1.GetCenterPoint(),o2.GetCenterPoint());

        return actual <= threshold;
    }

    private static boolean CheckHorizontalOverlap(HitBox o1, HitBox o2)
    {
        double threshold = o1.GetHalfWidth() + o2.GetHalfWidth();
        double actual = PointHelper.HorizontalDistanceBetween(o1.GetCenterPoint(), o2.GetCenterPoint());

        return actual <= threshold;
    }


}

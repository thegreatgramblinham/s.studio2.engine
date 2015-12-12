package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GeneralHelpers.PointHelper;

public final class CollisionHelper
{
    //Properties
    private CollisionHelper(){}

    //Public Methods
    public static boolean Collision(GameWorldObject o1, GameWorldObject o2)
    {
        return CheckHorizontalOverlap(o1.GetHitBox(), o2.GetHitBox())
                && CheckVerticalOverlap(o1.GetHitBox(), o2.GetHitBox());

    }

    //Private Methods
    private static boolean CheckVerticalOverlap(HitBox o1, HitBox o2)
    {
        if(o1.GetHalfHeight() + o2.GetHalfHeight() <=
                PointHelper.VerticalDistanceBetween(o1.GetCenterPoint(),o2.GetCenterPoint()))
            return true;
        return false;
    }

    private static boolean CheckHorizontalOverlap(HitBox o1, HitBox o2)
    {
        if(o1.GetHalfWidth() + o2.GetHalfWidth() <=
                PointHelper.HorizontalDistanceBetween(o1.GetCenterPoint(), o2.GetCenterPoint()))
            return true;
        return false;
    }
}

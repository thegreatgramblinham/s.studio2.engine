package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GeneralHelpers.PointHelper;

import java.awt.*;

public final class CollisionHelper
{
    //Properties
    private CollisionHelper(){}

    //Public Methods
    public boolean Collision(GameWorldObject o1, GameWorldObject o2)
    {
        return CheckHorizontalOverlap(o1.GetHitBox(), o2.GetHitBox())
                && CheckVerticalOverlap(o1.GetHitBox(), o2.GetHitBox());

    }

    //Private Methods
    private boolean CheckVerticalOverlap(HitBox o1, HitBox o2)
    {
        if(o1.GetHalfHeight() + o2.GetHalfHeight() <=
                Math.cos(PointHelper.DistanceTo(o1.GetCenterPoint(), o2.GetCenterPoint())))
            return true;
        return false;
    }

    private boolean CheckHorizontalOverlap(HitBox o1, HitBox o2)
    {
        if(o1.GetHalfWidth() + o2.GetHalfWidth() <=
                Math.sin(PointHelper.DistanceTo(o1.GetCenterPoint(), o2.GetCenterPoint())))
            return true;
        return false;
    }
}

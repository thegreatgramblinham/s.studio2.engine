package PhysicsBase;

import GameObjectBase.GameWorldObject;

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
        return false;
    }

    private boolean CheckHorizontalOverlap(HitBox o1, HitBox o2)
    {
        return false;
    }
}

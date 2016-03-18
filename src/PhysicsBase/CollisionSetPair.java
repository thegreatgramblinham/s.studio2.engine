package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;

import java.util.UUID;

public class CollisionSetPair
{
    //Public Variables
    public GameWorldObject object1;
    public GameWorldObject object2;

    public Side object1CollisionSide;
    public Side object2CollisionSide;

    public boolean activelyColliding;

    //Constructor
    public CollisionSetPair(GameWorldObject obj1, GameWorldObject obj2,
                            Side obj1CollisionSide, Side obj2CollisionSide)
    {
        object1 = obj1;
        object2 = obj2;
        object1CollisionSide = obj1CollisionSide;
        object2CollisionSide = obj2CollisionSide;
        activelyColliding = true;
    }

    //Public Methods
    public boolean ContainsId(UUID id)
    {
        return object1.GetID() == id || object2.GetID() == id;
    }

    public boolean AreBothObjectsAtRest()
    {
        return (object1.GetVelocity() == null || object1.GetVelocity().GetSpeed() == 0.0D) &&
                (object2.GetVelocity() == null || object2.GetVelocity().GetSpeed() == 0.0D);
    }

    public boolean IsOneObjectAtRest()
    {
        if(object1 == null || object2 == null) return false;
        if(object1.GetVelocity() == null && object2.GetVelocity() == null) return false;


        if((object1.GetVelocity() == null || object1.GetVelocity().GetSpeed() == 0.0D)
            && (object2.GetVelocity() != null && object2.GetVelocity().GetSpeed() > 0.0D))
            return true;
        if((object2.GetVelocity() == null || object2.GetVelocity().GetSpeed() == 0.0D)
                && (object1.GetVelocity() != null && object1.GetVelocity().GetSpeed() > 0.0D))
            return true;

        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollisionSetPair that = (CollisionSetPair) o;

        if(this.object1 == that.object1 && this.object2 == that.object2) return true;
        if(this.object2 == that.object1 && this.object1 == that.object2) return true;
        if(this.object1 == that.object2 && this.object2 == that.object1) return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        int result = object1 != null ? object1.hashCode() : 0;
        result = result + (object2 != null ? object2.hashCode() : 0);
        return result;
    }
}

package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;

import java.util.UUID;

class CollisionSetPair
{
    //Public Variables
    public GameWorldObject object1;
    public GameWorldObject object2;

    public Side object1CollisionSide;
    public Side object2CollisionSide;

    //Constructor
    public CollisionSetPair(GameWorldObject obj1, GameWorldObject obj2,
                            Side obj1CollisionSide, Side obj2CollisionSide)
    {
        object1 = obj1;
        object2 = obj2;
        object1CollisionSide = obj1CollisionSide;
        object2CollisionSide = obj2CollisionSide;
    }

    //Public Methods
    public boolean ContainsId(UUID id)
    {
        return object1.GetID() == id || object2.GetID() == id;
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

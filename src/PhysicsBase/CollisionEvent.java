package PhysicsBase;

import GameObjectBase.GameWorldObject;
import SectorBase.enums.Direction;

import java.util.HashMap;

public class CollisionEvent
{
    //Public Variables
    public GameWorldObject collider;
    public HashMap<GameWorldObject, Direction> collidesWith;

    //Properties
    public CollisionEvent(GameWorldObject obj,
                          HashMap<GameWorldObject, Direction> collidesWith)
    {
        collider = obj;
        this.collidesWith = collidesWith;
    }
}

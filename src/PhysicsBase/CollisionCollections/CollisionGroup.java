package PhysicsBase.CollisionCollections;

import GameObjectBase.GameWorldObject;

import java.util.HashSet;
import java.util.UUID;

public class CollisionGroup
{
    //Private Fields
    private HashSet<GameWorldObject> _group;
    private UUID _id;

    //Constructor
    public CollisionGroup()
    {
        Init();
    }

    //Get Methods
    public UUID GetID()
    {
        return _id;
    }

    //Private Methods
    private void Init()
    {
        _group = new HashSet<>();
        _id = UUID.randomUUID();
    }
}

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

    //Public Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollisionGroup that = (CollisionGroup) o;

        return _id.equals(that._id);

    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    //Private Methods
    private void Init()
    {
        _group = new HashSet<>();
        _id = UUID.randomUUID();
    }
}

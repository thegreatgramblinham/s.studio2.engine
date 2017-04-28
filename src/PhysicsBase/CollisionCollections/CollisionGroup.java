package PhysicsBase.CollisionCollections;

import GameObjectBase.GameWorldObject;
import Interfaces.IGameWorldObject;

import java.util.HashSet;
import java.util.UUID;

public class CollisionGroup
{
    //Private Fields
    private HashSet<IGameWorldObject> _group;
    private UUID _id;
    private String _name;

    //Constructor
    public CollisionGroup(String name)
    {
        Init();
        _name = name;
    }

    //Get Methods
    public UUID GetID()
    {
        return _id;
    }

    public String GetName(){ return _name; }

    //Public Methods
    public void Add(IGameWorldObject gObj)
    {
        _group.add(gObj);
    }

    public void Remove(IGameWorldObject gObj)
    {
        _group.remove(gObj);
    }

    public boolean Contains(IGameWorldObject gObj)
    {
        return _group.contains(gObj);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollisionGroup that = (CollisionGroup) o;

        return _id.equals(that._id) && _name.equals(that._name);
    }

    @Override
    public int hashCode()
    {
        return _id.hashCode();
    }

    //Private Methods
    private void Init()
    {
        _group = new HashSet<>();
        _id = UUID.randomUUID();
    }
}

package SectorBase;

import GameObjectBase.BoundedObject;
import PhysicsBase.CollisionManager;

public class Sector extends BoundedObject
{
    //Private Fields
    private CollisionManager _collisionManager;
    private SectorMap _map;

    //Constructor
    public Sector(int width, int height, int gridUnitSize)
    {
        super(width, height);
        Init(gridUnitSize);
    }

    //GetMethods

    //SetMethods

    //Private Methods
    private void Init(int gridUnitSize)
    {
        _map = new SectorMap(width, height, gridUnitSize);
        _collisionManager = new CollisionManager(_map);
    }
}

package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionManager;
import SectorBase.enums.Direction;

public class Sector extends BoundedObject
{
    //Private Fields
    private CollisionManager _collisionManager;
    private SectorMap _map;

    protected Sector _up;
    protected Sector _down;
    protected Sector _left;
    protected Sector _right;

    //Constructor
    public Sector(int width, int height, int gridUnitSize)
    {
        super(width, height);
        Init(gridUnitSize);
    }

    //GetMethods
    public CollisionManager GetCollisionManager()
    {
        return _collisionManager;
    }

    //SetMethods

    //Public Methods
    public void AddObject(GameWorldObject obj)
    {
        _map.InsertObject(obj);
    }

    public void RemoveObj(GameWorldObject obj)
    {
        _map.RemoveObject(obj);
    }

    public void LinkToSide(Sector other, Direction d)
    {
        switch (d)
        {
            case Up:
                _up = other;
                other._down = this;
                break;
            case Down:
                _down = other;
                other._up = this;
                break;
            case Left:
                _left = other;
                other._right = this;
                break;
            case Right:
                _right = other;
                other._left = this;
                break;
        }
    }

    //Private Methods
    private void Init(int gridUnitSize)
    {
        _map = new SectorMap(width, height, gridUnitSize);
        _collisionManager = new CollisionManager(_map);
    }
}

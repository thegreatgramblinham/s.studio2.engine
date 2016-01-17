package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionEvent;
import PhysicsBase.CollisionManager;
import PhysicsBase.LocationManager;
import SectorBase.enums.Direction;
import SectorBase.enums.GravityApplication;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class Sector extends BoundedObject
{
    //Private Fields
    private CollisionManager _collisionManager;
    private LocationManager _vectorManager;
    private SectorMap _map;
    private float _gravity;
    private GravityApplication _gravityApp;

    protected Sector _up;
    protected Sector _down;
    protected Sector _left;
    protected Sector _right;

    //Constructor
    public Sector(int width, int height, int gridUnitSize,
                  float gravity, GravityApplication gravityApp)
    {
        super(width, height);
        Init(gridUnitSize, gravity, gravityApp);
    }

    //GetMethods
    public Iterator<GameWorldObject> GetObjectsInSector()
    {
        return _map.GetAllObjectIterator();
    }

    //SetMethods

    //Public Methods
    public void HandleCollisions()
    {
        HashSet<CollisionEvent> collisons
                = _collisionManager.CheckCollisions();

        if(collisons.isEmpty()) return;

        Iterator<CollisionEvent> objs = collisons.iterator();

        CollisionEvent currEvent;

        while(objs.hasNext())
        {
            currEvent = objs.next();
            _collisionManager.HandleCollision(currEvent);
        }
    }

    public void UpdateVectors()
    {
        _vectorManager.AdvancePositions();
    }

    public void AddObject(GameWorldObject obj)
    {
        _map.InsertObject(obj);
    }

    public void RemoveObj(GameWorldObject obj)
    {
        _map.RemoveObject(obj);
    }

    public Iterator<GameWorldObject> GetObjectsAtPoint(Point p)
    {
        return _map.GetObjectsAtSubSector(p);
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
    private void Init(int gridUnitSize,
                      float gravity,
                      GravityApplication gravityApp)
    {
        _map = new SectorMap(width, height, gridUnitSize);
        _collisionManager = new CollisionManager(_map);
        _gravity = gravity;
        _gravityApp = gravityApp;
        _vectorManager = new LocationManager(_map,_gravity,_gravityApp);
    }
}

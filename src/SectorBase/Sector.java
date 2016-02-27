package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionEvent;
import PhysicsBase.CollisionManager;
import PhysicsBase.CollisionSetPair;
import PhysicsBase.LocationManager;
import SectorBase.enums.Direction;
import SectorBase.enums.GravityApplication;

import java.awt.*;
import java.util.ArrayList;
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

    private ArrayList<HashSet<GameWorldObject>> _renderGroups;

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

    public HashSet<GameWorldObject> GetRenderGroup(int group)
    {
        if(group >= _renderGroups.size()
                || _renderGroups.get(group) == null)
        {
            return null;
        }
        else
        {
            return _renderGroups.get(group);
        }
    }

    public int GetRenderGroupCount()
    {
        return _renderGroups.size();
    }

    //SetMethods

    //Public Methods
    public void HandleCollisions()
    {
        HashSet<CollisionSetPair> collisons
                = _collisionManager.CheckCollisions();

        if(collisons.isEmpty()) return;

        for (CollisionSetPair currEvent : collisons )
        {
            _collisionManager.HandleCollision(currEvent);
        }
    }

    public void UpdateVectors()
    {
        _vectorManager.AdvancePositions();
    }

    public void AddObject(GameWorldObject obj, int renderGroup)
    {
        _map.InsertObject(obj);

        if(_renderGroups.size() <= renderGroup
                || _renderGroups.get(renderGroup) == null)
        {
            //Add null padding until our next group index
            for(int i = _renderGroups.size();
                i <= renderGroup;
                i++)
            {
                _renderGroups.add(i, null);
            }
            _renderGroups.add(renderGroup, new HashSet<GameWorldObject>());
            _renderGroups.get(renderGroup).add(obj);
        }
        else
        {
            _renderGroups.get(renderGroup).add(obj);
        }
    }

    public void RemoveObject(GameWorldObject obj)
    {
        _map.RemoveObject(obj);

        for (HashSet<GameWorldObject> renderGroup : _renderGroups)
        {
            if(renderGroup == null) continue;

            if(renderGroup.contains(obj))
                renderGroup.remove(obj);
        }
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
        _renderGroups = new ArrayList<>();
    }
}

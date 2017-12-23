package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;
import Interfaces.IGameWorldObject;
import PhysicsBase.CollisionManager;
import PhysicsBase.CollisionCollections.ObjectCollisionPair;
import PhysicsBase.CollisionRules.CollisionGroupNamePair;
import PhysicsBase.CollisionRules.CollisionRuleManager;
import PhysicsBase.CollisionRules.enums.CollisionRule;
import PhysicsBase.LocationManager;
import SectorBase.enums.Direction;
import SectorBase.enums.GravityApplication;

import java.awt.*;
import java.util.*;


public class Sector extends BoundedObject
{
    //Private Fields
    private UUID _id;
    private CollisionManager _collisionManager;
    private LocationManager _vectorManager;
    private CollisionRuleManager _collisionRuleManager;
    private SectorMap _map;
    private float _gravity;
    private GravityApplication _gravityApp;

    private ArrayList<HashSet<IGameWorldObject>> _renderGroups;

    //Constructor
    public Sector(int width, int height, int gridUnitSize,
                  float gravity, GravityApplication gravityApp,
                  HashMap<CollisionGroupNamePair, CollisionRule> collisionRules)
    {
        super(width, height);
        Init(gridUnitSize, gravity, gravityApp, collisionRules);
    }

    //GetMethods
    public Iterator<IGameWorldObject> GetObjectsInSector()
    {
        return _map.GetAllObjectIterator();
    }

    public HashSet<IGameWorldObject> GetRenderGroup(int group)
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
        HashSet<ObjectCollisionPair> collisons
                = _collisionManager.CheckCollisions();

        if(collisons.isEmpty()) return;

        for (ObjectCollisionPair currEvent : collisons )
        {
            _collisionManager.HandleCollision(currEvent);
        }
    }

    public void UpdateVectors()
    {
        _vectorManager.AdvancePositions();
    }

    public void UpdateObjectLocations()
    {
        Iterator<IGameWorldObject> iter = _map.GetAllObjectIterator();

        if(iter == null) return;

        while (iter.hasNext())
        {
            IGameWorldObject gObj = iter.next();
            _map.UpdateObjectLocation(gObj);
        }
    }

    public void AddObject(IGameWorldObject obj, int renderGroup,
                          String collisionGroupName)
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
            _renderGroups.add(renderGroup, new HashSet<>());
            _renderGroups.get(renderGroup).add(obj);
        }
        else
        {
            _renderGroups.get(renderGroup).add(obj);
        }

        _collisionRuleManager.AddObject(obj, collisionGroupName);
    }

    public void RemoveObject(IGameWorldObject obj)
    {
        _map.RemoveObject(obj);

        for (HashSet<IGameWorldObject> renderGroup : _renderGroups)
        {
            if(renderGroup == null) continue;

            if(renderGroup.contains(obj))
                renderGroup.remove(obj);
        }

        _collisionRuleManager.RemoveObject(obj);
    }

    public Iterator<IGameWorldObject> GetObjectsAtPoint(Point p)
    {
        return _map.GetObjectsAtSubSector(p);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Sector sector = (Sector) o;

        return _id.equals(sector._id);

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + _id.hashCode();
        return result;
    }

    //Private Methods
    private void Init(int gridUnitSize,
                      float gravity,
                      GravityApplication gravityApp,
                      HashMap<CollisionGroupNamePair, CollisionRule> collisionRules)
    {
        _id = UUID.randomUUID();
        _map = new SectorMap(width, height, gridUnitSize);
        _collisionRuleManager = new CollisionRuleManager(collisionRules);
        _collisionManager = new CollisionManager(_map, _collisionRuleManager);
        _gravity = gravity;
        _gravityApp = gravityApp;
        _vectorManager = new LocationManager(_map,_gravity,_gravityApp);
        _renderGroups = new ArrayList<>();
    }
}

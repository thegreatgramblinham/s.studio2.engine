package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class SectorMap extends BoundedObject
{
    //Private Variables
    private int _gridUnitSize;
    private int _totalObjectCount;
    private HashSet<GameWorldObject>[][] _map;
    private HashMap<GameWorldObject, HashSet<GameWorldObject>> _objectToSubSector;

    //Properties

    //Constructor
    public SectorMap(int width, int height, int gridUnitSize)
    {
        super(width,height);
        _gridUnitSize = gridUnitSize;
        _totalObjectCount = 0;
        InitMap();
    }

    //GetMethods
    public int GetObjectCount()
    {
        return _totalObjectCount;
    }

    public Object[] GetObjectCollection()
    {
        return _objectToSubSector.keySet().toArray();
    }

    //SetMethods

    //Public Methods
    public void InsertObject(GameWorldObject obj)
    {
        if(obj == null) return;

        Point p = SectorMapHelper.CoordinateToGridPosition(obj.x, obj.y, _gridUnitSize);

        HashSet subSector = _map[p.x][p.y];

        if(subSector == null)
        {
            subSector = new HashSet();
            subSector.add(obj);
            _map[p.x][p.y] = subSector;
        }
        else
        {
            subSector.add(obj);
        }

        _objectToSubSector.put(obj, subSector);
        _totalObjectCount++;
    }

    public void UpdateObjectLocation(GameWorldObject obj)
    {
        if(obj == null) return;
        if(!_objectToSubSector.containsKey(obj)) return;

        Point p = SectorMapHelper.CoordinateToGridPosition(obj.x, obj.y, _gridUnitSize);

        HashSet oldSector = _objectToSubSector.get(obj);
        HashSet newSector = _map[p.x][p.y];

        if(newSector == null)
        {
            newSector = new HashSet();
            newSector.add(obj);
            oldSector.remove(obj);
            _map[p.x][p.y] = newSector;
        }
        else
        {
            newSector.add(obj);
            oldSector.remove(obj);
        }

        _objectToSubSector.put(obj, newSector);
    }

    public void RemoveObject(GameWorldObject obj)
    {
        if(obj == null) return;
        if(!_objectToSubSector.containsKey(obj)) return;

        HashSet subSector = _objectToSubSector.get(obj);

        subSector.remove(obj);
        _objectToSubSector.remove(obj);

        _totalObjectCount--;
    }

    public Object[] GetObjectsAtSubSector(Point point)
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(point.x, point.y, _gridUnitSize);
        HashSet objSet = _map[p.x][p.y];
        return objSet.toArray();
    }

    public Object[] GetObjectsAtSubSector(int x, int y)
    {
        return GetObjectsAtSubSector(new Point(x,y));
    }

    //Private Methods
    private void InitMap()
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(width, height, _gridUnitSize);

        _map = new HashSet[p.x][p.y];
        _objectToSubSector = new HashMap<>();
    }
}

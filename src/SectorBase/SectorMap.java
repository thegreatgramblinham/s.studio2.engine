package SectorBase;

import GameObjectBase.BoundedObject;
import GameObjectBase.GameWorldObject;
import Global.IdHashSet;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class SectorMap extends BoundedObject
{
    //Private Variables
    private int _xUnits;
    private int _yUnits;

    private int _gridUnitSize;
    private int _totalObjectCount;
    private IdHashSet<GameWorldObject>[][] _map;
    private HashMap<GameWorldObject, HashSet<IdHashSet<GameWorldObject>>> _objectToSubSector;

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

    public Iterator<GameWorldObject> GetAllObjectIterator()
    {
        return _objectToSubSector.keySet().iterator();
    }

    //SetMethods

    //Public Methods
    public void InsertObject(GameWorldObject obj)
    {
        if(obj == null) return;

        HashSet<Point> points = SectorMapHelper.RectToGridPositions(
                obj.GetHitBox().getBounds(),
                _gridUnitSize);

        Iterator<Point> pIter = points.iterator();

        HashSet<IdHashSet<GameWorldObject>> subSectors = new HashSet<>();

        while(pIter.hasNext())
        {
            Point p = pIter.next();

            IdHashSet subSector = _map[p.x][p.y];

            if (subSector == null)
            {
                subSector = new IdHashSet();
                subSector.add(obj);
                _map[p.x][p.y] = subSector;
            } else
            {
                subSector.add(obj);
            }

            subSectors.add(subSector);
        }

        _objectToSubSector.put(obj, subSectors);
        _totalObjectCount++;
    }

    public void UpdateObjectLocation(GameWorldObject obj)
    {
        if(obj == null) return;
        if(!_objectToSubSector.containsKey(obj)) return;

        HashSet<Point> points = SectorMapHelper.RectToGridPositions(
                obj.GetHitBox().getBounds(),
                _gridUnitSize);

        //remove from old location
        HashSet oldSectors = _objectToSubSector.get(obj);

        Iterator<HashSet<GameWorldObject>> oldIter = oldSectors.iterator();

        while(oldIter.hasNext())
        {
            HashSet oldSet = oldIter.next();
            oldSet.remove(obj);
        }

        Iterator<Point> pIter = points.iterator();
        HashSet<IdHashSet<GameWorldObject>> subSectors = new HashSet<>();

        while(pIter.hasNext())
        {
            Point p = pIter.next();

            //add to new location
            IdHashSet newSector = _map[p.x][p.y];

            if (newSector == null)
            {
                newSector = new IdHashSet();
                newSector.add(obj);
                _map[p.x][p.y] = newSector;
            } else
            {
                newSector.add(obj);
            }

            subSectors.add(newSector);
        }

        _objectToSubSector.put(obj, subSectors);
    }

    public void RemoveObject(GameWorldObject obj)
    {
        if(obj == null) return;
        if(!_objectToSubSector.containsKey(obj)) return;

        HashSet oldSectors = _objectToSubSector.get(obj);

        Iterator<HashSet<GameWorldObject>> oldIter = oldSectors.iterator();

        while(oldIter.hasNext())
        {
            HashSet oldSet = oldIter.next();
            oldSet.remove(obj);
        }

        _totalObjectCount--;
    }

    public Iterator<GameWorldObject> GetObjectsAtSubSector(Point point)
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(point.x,
                point.y, _gridUnitSize);

        HashSet objSet = _map[p.x][p.y];

        if(objSet == null)
        {
            return null;
        }
        else
        {
            return objSet.iterator();
        }
    }

    public Iterator<GameWorldObject> GetObjectsAtSubSector(int x, int y)
    {
        return GetObjectsAtSubSector(new Point(x,y));
    }

    public Iterator<GameWorldObject> GetObjectsAtSubSectors(Rectangle rect)
    {
        HashSet<Point> gPoints
                = SectorMapHelper.RectToGridPositions(rect, _gridUnitSize);

        HashSet<GameWorldObject> totalObjSet = new HashSet<>();

        Iterator<Point> pIter = gPoints.iterator();

        while(pIter.hasNext())
        {
            Point p = pIter.next();

            HashSet objSet = _map[p.x][p.y];

            if(objSet == null || objSet.isEmpty()) continue;

            totalObjSet.addAll(objSet);
        }

        return totalObjSet.iterator();
    }

    //Private Methods
    private void InitMap()
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(width, height, _gridUnitSize);

        _xUnits = p.x+1;
        _yUnits = p.y+1;

        _map = new IdHashSet[_xUnits][_yUnits];
        _objectToSubSector = new HashMap<>();
    }
}

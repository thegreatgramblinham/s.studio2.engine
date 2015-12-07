package SectorBase;

import GameObjectBase.GameWorldObject;

import java.awt.*;
import java.util.ArrayList;

public class SectorMap extends Rectangle
{
    //Private Variables
    private int _gridUnitSize;
    private ArrayList<GameWorldObject>[][] _map;

    //Properties

    //Constructor
    public SectorMap(int width, int height, int gridUnitSize)
    {
        super(width,height);
        _gridUnitSize = gridUnitSize;
    }

    //GetMethods

    //SetMethods

    //Public Methods
    public void InsertObject(GameWorldObject obj)
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(obj.GetX(), obj.GetY(), _gridUnitSize);
        _map[p.x][p.y].add(obj);
    }

    public GameWorldObject[] GetObjectsAtSubSector(Point point)
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(point.x, point.y, _gridUnitSize);
        return (GameWorldObject[])_map[p.x][p.y].toArray();
    }

    //Private Methods
    private void InitMap()
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(width, height, _gridUnitSize);

        _map = new ArrayList[p.x][p.y];
    }
}

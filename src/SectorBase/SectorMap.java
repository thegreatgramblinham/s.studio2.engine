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
    public void InsertObject(int x, int y, GameWorldObject obj)
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(x, y, _gridUnitSize);
        _map[p.x][p.y].add(obj);
    }

    //Private Methods
    private void InitMap()
    {
        Point p = SectorMapHelper.CoordinateToGridPosition(width, height, _gridUnitSize);

        _map = new ArrayList[p.x][p.y];
    }
}

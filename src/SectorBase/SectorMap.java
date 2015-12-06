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

    //Private Methods
    private void InitMap()
    {
        //need a conversion from pixels to grid coordinate units - helper?


    }
}

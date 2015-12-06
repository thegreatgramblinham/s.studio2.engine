package SectorBase;

import GameObjectBase.GameWorldObject;

import java.awt.*;
import java.util.ArrayList;

public class SectorMap extends Rectangle
{
    //Private Variables
    private int _heightUnit;
    private int _widthUnit;
    private ArrayList<GameWorldObject>[][] _map;

    //Properties

    //Constructor
    public SectorMap(int width, int height)
    {
        super(width,height);

    }

    //GetMethods

    //SetMethods

    //Private Methods
    private void InitMap()
    {
        //need a conversion from pixels to grid coordinate units - helper?


    }
}

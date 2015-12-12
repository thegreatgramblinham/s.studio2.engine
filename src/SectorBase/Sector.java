package SectorBase;

import GameObjectBase.BoundedObject;

public class Sector extends BoundedObject
{
    //Properties
    private SectorMap SectorMap;

    //Constructor
    public Sector(int width, int height)
    {
        super(width, height);
    }

    //GetMethods
    private SectorMap GetSectorMap()
    {
        return SectorMap;
    }

    //SetMethods
}

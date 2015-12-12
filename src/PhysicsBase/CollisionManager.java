package PhysicsBase;

import GameObjectBase.GameWorldObject;
import SectorBase.SectorMap;

public class CollisionManager
{
    //Private Variables
    private SectorMap _map;

    //Properties

    //Constructor
    public CollisionManager(SectorMap map)
    {
        _map = map;
    }

    //GetMethods

    //SetMethods

    //Public Methods
    public void CheckCollisions()
    {
        Object[] objArr = _map.GetObjectCollection();

        if(objArr == null || objArr.length == 0) return;

        for(int i = 0; i< objArr.length; i++)
        {
            GameWorldObject gameObj = (GameWorldObject)objArr[i];

            //check for collisions within each object in the same subsector
            Object[] sectorObjs =
                    _map.GetObjectsAtSubSector(gameObj.x, gameObj.y);
        }
    }

    //Private Methods
}

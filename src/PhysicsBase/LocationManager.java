package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GeneralHelpers.PointHelper;
import PhysicsBase.Vectors.DistanceVector;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.SectorMap;

import java.awt.*;
import java.util.Iterator;

public class LocationManager
{
    //Private Variables
    private SectorMap _map;

    //Properties

    //Constructor
    public LocationManager(SectorMap map)
    {
        _map = map;
    }

    //GetMethods

    //SetMethods

    //Public Methods
    public void AdvancePositions()
    {
        Iterator<GameWorldObject> objIter = _map.GetAllObjectIterator();

        while(objIter.hasNext())
        {
            GameWorldObject gObj = objIter.next();

            VelocityVector v = gObj.GetVelocity();

            if(v == null || v.GetSpeed() == 0.0) continue;

            //todo apply gravity

            Point p1 = gObj.NGetLocation();

            Point p2 = PointHelper.NTranslatePoint(p1,
                    new DistanceVector(v.GetRadianRotation(), v.GetSpeed()));

            gObj.NSetLocation(p2);
        }
    }

    //Private Methods
}

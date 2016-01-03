package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GeneralHelpers.PointHelper;
import PhysicsBase.Vectors.DistanceVector;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.SectorMap;

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

            double x1 = gObj.GetDX();
            double y1 = gObj.GetDY();

            double x2 = PointHelper.TranslateX(x1,
                    new DistanceVector(v.GetRadianRotation(), v.GetSpeed()));
            double y2 = PointHelper.TranslateY(y1,
                    new DistanceVector(v.GetRadianRotation(), v.GetSpeed()));

            gObj.DSetLocation(x2,y2);

            //todo apply gravity
            gObj.SetVelocity(new VelocityVector(v.GetRadianRotation(), v.GetSpeed()*.99));
        }
    }

    //Private Methods
}

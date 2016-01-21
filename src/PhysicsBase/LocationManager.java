package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GeneralHelpers.PointHelper;
import Global.DPoint;
import PhysicsBase.Vectors.DistanceVector;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.SectorMap;
import SectorBase.enums.GravityApplication;

import java.util.Iterator;

public class LocationManager
{
    //Private Variables
    private SectorMap _map;
    private float _gravity;
    private GravityApplication _gravityApp;

    //Properties

    //Constructor
    public LocationManager(SectorMap map, float gravity,
                           GravityApplication gravityApp)
    {
        _map = map;
        _gravity = gravity;
        _gravityApp = gravityApp;
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

            //If we don't have a location to update, just move on.
            if(v == null || gObj.GetIsImmobile()) continue;
            if(v.GetSpeed() == 0.0)
            {
                gObj.SetVelocity(new VelocityVector(0.0, 0.0));
                continue;
            }

            DPoint p = gObj.DGetLocation();

            double x2 = PointHelper.TranslateX(p.GetX(),
                    new DistanceVector(v.GetRadianRotation(), v.GetSpeed()*(1-(_gravity * gObj.GetMass()))));
            double y2 = PointHelper.TranslateY(p.GetY(),
                    new DistanceVector(v.GetRadianRotation(), v.GetSpeed()*(1-(_gravity * gObj.GetMass()))));

            gObj.DSetLocation(x2,y2);
            _map.UpdateObjectLocation(gObj);

            //todo apply gravity
            //gObj.SetVelocity(new VelocityVector(v.GetRadianRotation(), v.GetSpeed()*.991));
            ApplyGravityToObject(gObj);
        }
    }

    //Private Methods
    public void ApplyGravityToObject(GameWorldObject obj)
    {
        VelocityVector v = obj.GetVelocity();
        switch (_gravityApp)
        {
            case Area:
                obj.SetVelocity(new VelocityVector(v.GetRadianRotation(), v.GetSpeed()*(1-(_gravity * obj.GetMass()))));
                //obj.SetVelocity(new VelocityVector(v.GetRadianRotation(), v.GetSpeed()*(1 - (obj.GetMass()+_gravity)/2)));
                break;
            case Up:
                break;
            case Down:
                break;
            case Left:
                break;
            case Right:
                break;
        }

    }
}

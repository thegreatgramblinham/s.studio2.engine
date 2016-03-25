package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GeneralHelpers.PointHelper;
import PhysicsBase.CollisionCollections.ObjectCollisionPair;
import PhysicsBase.CollisionRules.CollisionRuleManager;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.SectorMap;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class CollisionManager
{
    //Private Variables
    private SectorMap _map;
    private CollisionRuleManager _ruleManager;

    //Properties

    //Constructor
    public CollisionManager(SectorMap map, CollisionRuleManager ruleManager)
    {
        _map = map;
        _ruleManager = ruleManager;
    }

    //GetMethods

    //SetMethods

    //Public Methods
    public HashSet<ObjectCollisionPair> CheckCollisions()
    {
        HashSet<ObjectCollisionPair> collisions = new HashSet<>();

        Iterator<GameWorldObject> allObjIter = _map.GetAllObjectIterator();

        if(allObjIter == null || !allObjIter.hasNext()) return collisions;

        while(allObjIter.hasNext())
        {
            GameWorldObject gameObj = allObjIter.next();

            if(gameObj.GetIsImmobile()) continue;
            if(gameObj.GetCanCollide() == false) continue;

            //check for collisions within each object in the same subsector(s)
            Iterator<GameWorldObject> sectorObjs
                    = _map.GetObjectsAtSubSectors(gameObj.GetHitBox());

            ObjectCollisionPair e = null;

            while (sectorObjs.hasNext())
            {
                GameWorldObject sectorGameObj = sectorObjs.next();

                //No need to collide with self
                if(sectorGameObj == gameObj) continue;
                if(sectorGameObj.GetCanCollide() == false) continue;

                boolean collisionEvent = CollisionHelper.Collision(gameObj, sectorGameObj);

                if(!collisionEvent) continue;

                e = new ObjectCollisionPair(gameObj, sectorGameObj, null, null);
                DetermineCollisionDirection(e);

                if(!collisions.contains(e))
                    collisions.add(e);
            }

        }

        return collisions;
    }

    public void HandleCollision(ObjectCollisionPair e)
    {
        StringBuilder sb = new StringBuilder();

        //logging the collision
        sb.append("[");
        sb.append(e.object1.GetAlias()+" from "+e.object1CollisionSide+" & ");
        sb.append(e.object2.GetAlias()+" from "+e.object2CollisionSide);
        sb.append("]");

        if(e.object1CollisionSide == null && e.object2CollisionSide == null) return;

        if(e.object2CollisionSide == Side.Top)
        {
            e.object1.NSetLocation(new Point(e.object1.x, e.object1.y-1));
            e.object2.NSetLocation(new Point(e.object2.x, e.object2.y+1));
        }
        else if (e.object2CollisionSide == Side.Left )
        {
            e.object1.NSetLocation(new Point(e.object1.x-1, e.object1.y));
            e.object2.NSetLocation(new Point(e.object2.x+1, e.object2.y));
        }
        else if(e.object2CollisionSide == Side.Bottom)
        {
            e.object1.NSetLocation(new Point(e.object1.x, e.object1.y+1));
            e.object2.NSetLocation(new Point(e.object2.x, e.object2.y-1));
        }
        else if(e.object2CollisionSide == Side.Right)
        {
            e.object1.NSetLocation(new Point(e.object1.x+1, e.object1.y));
            e.object2.NSetLocation(new Point(e.object2.x-1, e.object2.y));
        }


        if(e.activelyColliding)
        {
//            VelocityVector v1 = e.object1.GetVelocity();
//            VelocityVector v2 = e.object2.GetVelocity();
//
//            //currently using object 2 as the relative object.
//            //todo transfer of motion with two moving objects
//            e.object1.StopAcceleration();
//            e.object2.StopAcceleration();
//            if(v1 != null && v2 != null)
//            {
//                VelocityVector test = VectorHelper.AddVectors(v1,v2);
//                test.SetRadianRotation(test.GetRadianRotation() + Math.PI);
//
//                e.object1.SetVelocity(test);
//                e.object2.SetVelocity(VectorHelper.AddVectors(v1,v2));
//
//                _map.UpdateObjectLocation(e.object1);
//                _map.UpdateObjectLocation(e.object2);
//            }
        }

        if(e.IsOneObjectAtRest())
        {
            VelocityVector v1 = e.object1.GetVelocity();
            VelocityVector v2 = e.object2.GetVelocity();

            e.object1.SetVelocity(v2);
            e.object2.SetVelocity(v1);
        }

        //Call for any special collision effects from overriding objects.
        e.object1.OnCollide(e.object2);
        e.object2.OnCollide(e.object1);
        System.out.println("COLLISION! - <"+sb.toString()+">");
        //todo collision with multiple objects
    }

    //Private Methods
    private void DetermineCollisionDirection(ObjectCollisionPair pair)
    {
        //With respect to object 2's center point
        int rise = PointHelper.SlopeRiseOf(pair.object1.GetCenterPoint(),
                pair.object2.GetCenterPoint());
        int run = PointHelper.SlopeRunOf(pair.object1.GetCenterPoint(),
                pair.object2.GetCenterPoint());

        if(pair.object1.GetCenterPoint().x > pair.object2.GetLeft()
                && pair.object1.GetCenterPoint().x < pair.object2.GetRight())
        {
            CollisionOnYAxis(pair, rise);
        }
        else if(pair.object1.GetCenterPoint().y < pair.object2.GetBottom()
                && pair.object1.GetCenterPoint().y > pair.object2.GetTop())
        {
            CollisionOnXAxis(pair, run);
        }
        else
        {
            double adjustedRun = (Math.abs(run) - pair.object1.GetHalfWidth()
                    - pair.object2.GetHalfWidth());
            double adjustedRise = (Math.abs(rise) - pair.object1.GetHalfHeight()
                    - pair.object2.GetHalfHeight());

            if (Math.abs(adjustedRun) < Math.abs(adjustedRise))
                CollisionOnXAxis(pair, run);
            else //rise > run
                CollisionOnYAxis(pair, rise);
        }

        if(pair.object1CollisionSide == null || pair.object2CollisionSide == null)
            pair.activelyColliding = false;
    }

    private void CollisionOnXAxis(ObjectCollisionPair pair, double run)
    {
        //collision on the x axis
        if (run > 0)
        {
            pair.object1CollisionSide = Side.Right;
            pair.object2CollisionSide = Side.Left;
        } else
        {
            pair.object1CollisionSide = Side.Left;
            pair.object2CollisionSide = Side.Right;
        }
    }

    private void CollisionOnYAxis(ObjectCollisionPair pair, double rise)
    {
        //collision on the y axis
        if (rise > 0)
        {
            pair.object1CollisionSide = Side.Bottom;
            pair.object2CollisionSide = Side.Top;
        } else
        {
            pair.object1CollisionSide = Side.Top;
            pair.object2CollisionSide = Side.Bottom;
        }
    }
}

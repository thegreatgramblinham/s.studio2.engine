package PhysicsBase;

import GameObjectBase.GameWorldObject;
import GameObjectBase.enums.Side;
import GeneralHelpers.ConversionHelper;
import PhysicsBase.Vectors.VelocityVector;
import SectorBase.SectorMap;
import SectorBase.enums.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

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
    public HashSet<CollisionSetPair> CheckCollisions()
    {
        HashSet<CollisionSetPair> collisions = new HashSet<>();

        Iterator<GameWorldObject> allObjIter = _map.GetAllObjectIterator();

        if(allObjIter == null || !allObjIter.hasNext()) return collisions;

        //TODO IMPLEMENT WILL COLLIDE - THIS IS THE NEXT BIG FEATURE AND IS CRITICAL

        while(allObjIter.hasNext())
        {
            GameWorldObject gameObj = allObjIter.next();

            if(gameObj.GetIsImmobile()) continue;
            if(gameObj.GetCanCollide() == false) continue;

            //check for collisions within each object in the same subsector(s)
            Iterator<GameWorldObject> sectorObjs
                    = _map.GetObjectsAtSubSectors(gameObj.GetHitBox());

            CollisionSetPair e = null;

            while (sectorObjs.hasNext())
            {
                GameWorldObject sectorGameObj = sectorObjs.next();

                //No need to collide with self
                if(sectorGameObj == gameObj) continue;
                if(sectorGameObj.GetCanCollide() == false) continue;

                boolean collisionEvent = CollisionHelper.Collision(gameObj, sectorGameObj);

                if(!collisionEvent) continue;

                e = new CollisionSetPair(gameObj, sectorGameObj, null, null);
                DetermineCollisionDirection(e);

                if(!collisions.contains(e))
                    collisions.add(e);
            }

        }

        return collisions;
    }

    public void HandleCollision(CollisionSetPair e)
    {
        StringBuilder sb = new StringBuilder();

        //logging the collision
        sb.append("[");
        sb.append(e.object1.GetAlias()+" from "+e.object1CollisionSide+" &");
        sb.append(e.object2.GetAlias()+" from "+e.object2CollisionSide);
        sb.append("]");

        if(e.object1CollisionSide == null && e.object2CollisionSide == null) return;

        if(e.activelyColliding)
        {
            //currently using object 2 as the relative object.
            switch (e.object1CollisionSide)
            {
                case Top:
                    e.object1.NSetLocation(new Point(e.object1.x, e.object2.GetBottom() + 1));
                    //e.object2.NSetLocation(new Point(e.object2.x, e.object1.GetTop() - (int)e.object1.getHeight() - 1));
                    break;
                case Bottom:
                    e.object1.NSetLocation(new Point(e.object1.x, e.object2.GetTop() - (int)e.object1.getHeight() - 1));
                    //e.object2.NSetLocation(new Point(e.object2.x, e.object1.GetBottom() + 1));
                    break;
                case Left:
                    e.object1.NSetLocation(new Point(e.object2.GetRight() + 1, e.object1.y));
                    //e.object2.NSetLocation(new Point(e.object1.GetLeft() - (int)e.object1.getWidth()- 1, e.object2.y));
                    break;
                case Right:
                    e.object1.NSetLocation(new Point(e.object2.GetLeft() - (int)e.object1.getWidth() - 1, e.object1.y));
                    //e.object2.NSetLocation(new Point(e.object1.GetRight() + 1, e.object2.y));
                    break;
            }

            e.object1.StopAcceleration();
            e.object2.StopAcceleration();

            _map.UpdateObjectLocation(e.object1);
            _map.UpdateObjectLocation(e.object2);
        }

        if(e.IsOneObjectAtRest())
        {
            VelocityVector v1 = e.object1.GetVelocity();
            VelocityVector v2 = e.object2.GetVelocity();

            e.object1.SetVelocity(v2);
            e.object2.SetVelocity(v1);
        }

        System.out.println("COLLISION! - <"+sb.toString()+">");
        //todo collision with multiple objects
    }

    //Private Methods
    private void DetermineCollisionDirection(CollisionSetPair pair)
    {
        if(pair.AreBothObjectsAtRest())
            return;

        if(pair.IsOneObjectAtRest())
        {
            DetermineCollisionWithRestingObject(pair);
            return;
        }

        if(pair.object1.IsRightAlignedTo(pair.object2.GetLeft(), 10))
        {
            pair.object1CollisionSide = Side.Right;
            pair.object2CollisionSide = Side.Left;
        }
        else if(pair.object1.IsLeftAlignedTo(pair.object2.GetRight(), 10))
        {
            pair.object1CollisionSide = Side.Left;
            pair.object2CollisionSide = Side.Right;
        }
        else if(pair.object1.IsTopAlignedTo(pair.object2.GetBottom(), 10))
        {
            pair.object1CollisionSide = Side.Top;
            pair.object2CollisionSide = Side.Bottom;
        }
        else if(pair.object1.IsBottomAlignedTo(pair.object2.GetTop(), 10))
        {
            pair.object1CollisionSide = Side.Bottom;
            pair.object2CollisionSide = Side.Top;
        }

        if(pair.object1CollisionSide == null || pair.object2CollisionSide == null)
            pair.activelyColliding = false;
    }

    private void DetermineCollisionWithRestingObject(CollisionSetPair pair)
    {
        VelocityVector v1 = pair.object1.GetVelocity();
        VelocityVector v2 = pair.object2.GetVelocity();

        if(v1 == null || v1.GetSpeed() == 0.0D)
        {
            pair.object2CollisionSide = ConversionHelper.GetRadianToCollisionSide(
                    v2.GetRadianRotation());
            pair.object1CollisionSide = ConversionHelper.GetOppositeSide(
                    pair.object2CollisionSide);
        }
        else
        {
            pair.object1CollisionSide = ConversionHelper.GetRadianToCollisionSide(
                    v1.GetRadianRotation());
            pair.object2CollisionSide = ConversionHelper.GetOppositeSide(
                    pair.object1CollisionSide);
        }
    }
}

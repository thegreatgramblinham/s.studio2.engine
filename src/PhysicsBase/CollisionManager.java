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
    public HashSet<CollisionEvent> CheckCollisions()
    {
        HashSet<CollisionEvent> collisions = new HashSet<>();

        Iterator<GameWorldObject> allObjIter = _map.GetAllObjectIterator();

        if(allObjIter == null || !allObjIter.hasNext()) return collisions;

        //TODO IMPLEMENT WILL COLLIDE - THIS IS THE NEXT BIG FEATURE AND IS CRITICAL

        while(allObjIter.hasNext())
        {
            GameWorldObject gameObj = allObjIter.next();

            if(gameObj.GetIsImmobile()) continue;

            //check for collisions within each object in the same subsector(s)
            Iterator<GameWorldObject> sectorObjs
                    = _map.GetObjectsAtSubSectors(gameObj.GetHitBox());

            CollisionEvent e = null;

            while (sectorObjs.hasNext())
            {
                GameWorldObject sectorGameObj = sectorObjs.next();

                //No need to collide with self
                if(sectorGameObj == gameObj) continue;

                boolean collisionEvent = CollisionHelper.Collision(gameObj,
                        sectorGameObj);

                if(!collisionEvent) continue;

                if(e == null)
                {
                    HashMap<GameWorldObject, Direction> collideWith
                            = new HashMap<>();
                    collideWith.put(sectorGameObj,
                            DetermineCollisionDirection(gameObj,sectorGameObj));

                    e = new CollisionEvent(gameObj, collideWith);
                }
                else
                {
                    e.collidesWith.put(sectorGameObj,
                            DetermineCollisionDirection(gameObj,sectorGameObj));
                }
            }

            if(e != null) collisions.add(e);
        }

        return collisions;
    }

    public void HandleCollision(CollisionEvent e)
    {
        StringBuilder sb = new StringBuilder();

        Iterator<GameWorldObject> objIter = e.collidesWith.keySet().iterator();

        //logging the collision
        sb.append("[");
        while (objIter.hasNext())
        {
            GameWorldObject collObj = objIter.next();

            sb.append(collObj.GetAlias()+" from "+e.collidesWith.get(collObj)+" &");
        }
        sb.append("]");

        //simple case, start with one object
        GameWorldObject firstCollideWith = e.collidesWith.keySet().iterator().next();

        //todo if collidesWith is not immobile, then it's location needs to be updated as well.
        switch(e.collidesWith.get(firstCollideWith))
        {
            case Up:
                e.collider.NSetLocation(
                        new Point(
                                e.collider.x,
                                (int)(firstCollideWith.getY()
                                        + firstCollideWith.height + 1)));
                break;
            case Down:
                e.collider.NSetLocation(
                        new Point(
                                e.collider.x,
                                (int)(firstCollideWith.getY() - e.collider.height - 1)));
                break;
            case Left:
                e.collider.NSetLocation(
                        new Point(firstCollideWith.x + firstCollideWith.width + 1,
                                e.collider.y));
                break;
            case Right:
                e.collider.NSetLocation(
                        new Point(firstCollideWith.x - e.collider.width - 1,
                                e.collider.y));
                break;
            default:
                break;
        }

        VelocityVector prevSpeed = e.collider.GetVelocity();
        e.collider.SetVelocity(new VelocityVector(0,0));

        if(!firstCollideWith.GetIsImmobile())
        {
            firstCollideWith.AccelerateBy(prevSpeed);
        }

        System.out.println("COLLISION! - <"+e.collider.GetAlias()+" : "+sb.toString()+">");
        //todo collision with multiple objects
    }

    //Private Methods
    private Direction DetermineCollisionDirection(GameWorldObject collider,
                                             GameWorldObject collidesWith)
    {
        if(collider.GetVelocity() == null) return null;

        return ConversionHelper.GetRadianToCollisionDirection(
                  collider.GetVelocity().GetRadianRotation());
    }

    private void DetermineCollisionDirection(CollisionSetPair pair)
    {
        if(pair.object1.IsRightAlignedTo(pair.object2.GetLeft(), 2))
        {
            pair.object1CollisionSide = Side.Right;
            pair.object2CollisionSide = Side.Left;
        }
        else if(pair.object1.IsLeftAlignedTo(pair.object2.GetRight(), 2))
        {
            pair.object1CollisionSide = Side.Left;
            pair.object2CollisionSide = Side.Right;
        }
        else if(pair.object1.IsTopAlignedTo(pair.object2.GetBottom(), 2))
        {
            pair.object1CollisionSide = Side.Top;
            pair.object2CollisionSide = Side.Bottom;
        }
        else if(pair.object1.IsBottomAlignedTo(pair.object2.GetTop(), 2))
        {
            pair.object1CollisionSide = Side.Bottom;
            pair.object2CollisionSide = Side.Top;
        }
    }
}

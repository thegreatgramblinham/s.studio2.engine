package PhysicsBase;

import GameObjectBase.GameWorldObject;
import SectorBase.SectorMap;
import SectorBase.enums.Direction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

//todo support for varying speeds - vector collision
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
        //HashMap<GameWorldObject, HashSet<GameWorldObject>> collisions
        //        = new HashMap<>();

        HashSet<CollisionEvent> collisions = new HashSet<>();

        Iterator<GameWorldObject> allObjIter = _map.GetAllObjectIterator();

        if(allObjIter == null || !allObjIter.hasNext()) return collisions;

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
                    collideWith.put(sectorGameObj, Direction.Up);

                    e = new CollisionEvent(gameObj, collideWith);
                }
                else
                {
                    e.collidesWith.put(sectorGameObj, Direction.Up);
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

        sb.append("[");
        while (objIter.hasNext())
        {
            GameWorldObject collObj = objIter.next();

            sb.append(collObj.GetAlias()+"&");
        }
        sb.append("]");

        //simple case, start with one object
        System.out.println("COLLISION! - <"+e.collider.GetAlias()+" : "+sb.toString()+">");
        //todo collision with multiple objects
    }

    //Private Methods
}

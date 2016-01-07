package PhysicsBase;

import GameObjectBase.GameWorldObject;
import SectorBase.SectorMap;
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
    public HashMap<GameWorldObject, HashSet<GameWorldObject>> CheckCollisions()
    {
        HashMap<GameWorldObject, HashSet<GameWorldObject>> collisions
                = new HashMap<>();

        Iterator<GameWorldObject> allObjIter = _map.GetAllObjectIterator();

        if(allObjIter == null || !allObjIter.hasNext()) return collisions;

        while(allObjIter.hasNext())
        {
            GameWorldObject gameObj = allObjIter.next();

            if(gameObj.GetIsImmobile()) continue;

            //check for collisions within each object in the same subsector(s)
            Iterator<GameWorldObject> sectorObjs
                    = _map.GetObjectsAtSubSectors(gameObj.GetHitBox());

            while (sectorObjs.hasNext())
            {
                GameWorldObject sectorGameObj = sectorObjs.next();

                //No need to collide with self
                if(sectorGameObj == gameObj) continue;

                boolean collisionEvent = CollisionHelper.Collision(gameObj,
                        sectorGameObj);

                if(!collisionEvent) continue;

                if(!collisions.containsKey(gameObj))
                {
                    collisions.put(gameObj,
                            new HashSet<GameWorldObject>(){{add(sectorGameObj);}});
                }
                else
                {
                    //This object is colliding with multiple objects.
                    collisions.get(gameObj).add(sectorGameObj);
                }
            }
        }

        return collisions;
    }

    public void HandleCollision(GameWorldObject obj,
                                HashSet<GameWorldObject> collidingObjs)
    {
        StringBuilder sb = new StringBuilder();

        Iterator<GameWorldObject> objIter = collidingObjs.iterator();

        sb.append("[");
        while (objIter.hasNext())
        {
            GameWorldObject collObj = objIter.next();

            sb.append(collObj.GetAlias()+"&");
        }
        sb.append("]");

        //simple case, start with one object
        System.out.println("COLLISION! - <"+obj.GetAlias()+" : "+sb.toString()+">");
        //todo collision with multiple objects
    }

    //Private Methods
}

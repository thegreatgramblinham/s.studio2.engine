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

        Object[] objArr = _map.GetObjectCollection();

        if(objArr == null || objArr.length == 0) return collisions;

        for(int i = 0; i< objArr.length; i++)
        {
            GameWorldObject gameObj = (GameWorldObject)objArr[i];

            //check for collisions within each object in the same subsector
            // todo this needs to be for entire area.
            //Object[] sectorObjArr =
            //        _map.GetObjectsAtSubSector(gameObj.x, gameObj.y);

            Iterator<GameWorldObject> sectorObjs
                    = _map.GetObjectsAtSubSectors(gameObj.GetHitBox());
            while (sectorObjs.hasNext())
            {
                GameWorldObject sectorGameObj = sectorObjs.next();

            //for(int j = 0; j< sectorObjArr.length; j++)
            //{
            //    GameWorldObject sectorGameObj = (GameWorldObject)sectorObjArr[j];

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
        //simple case, start with one object
        System.out.println("COLLISION");
        //todo collision with multiple objects
    }

    //Private Methods
}

package PhysicsBase;

import GameObjectBase.DummyGameObject;
import PhysicsBase.CollisionManager;
import PhysicsBase.CollisionCollections.ObjectCollisionPair;
import PhysicsBase.CollisionRules.CollisionRuleManager;
import SectorBase.SectorMap;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class CollisionManagerTest
{
    private CollisionManager _manager;
    private DummyGameObject obj1;
    private DummyGameObject obj2;
    private SectorMap _map;

    @org.junit.Before
    public void setUp() throws Exception
    {
        _map = new SectorMap(500, 500, 20);
        _manager = new CollisionManager(_map, new CollisionRuleManager());

        obj1 = new DummyGameObject(new Rectangle(5, 5, 3, 3), false, 1);
        obj2 = new DummyGameObject(new Rectangle(25, 25, 3, 3), false, 1);

        _map.InsertObject(obj1);
        _map.InsertObject(obj2);
    }

    @Test
    public void testCheckCollisionsInit() throws Exception
    {
        Assert.assertTrue(_manager.CheckCollisions().isEmpty());
    }

    @Test
    public void testCheckCollisionsFirstMove() throws Exception
    {
        obj1.NSetLocation(new Point(10, 10));
        _map.UpdateObjectLocation(obj1);

        obj2.NSetLocation(new Point(15, 15));
        _map.UpdateObjectLocation(obj2);

        Assert.assertTrue(_manager.CheckCollisions().isEmpty());
    }

    @Test
    public void testCheckCollisionsSecondMove() throws Exception
    {
        //Objects are bounds touching.
        obj1.NSetLocation(new Point(11, 11));
        _map.UpdateObjectLocation(obj1);

        obj2.NSetLocation(new Point(14, 14));
        _map.UpdateObjectLocation(obj2);

        HashSet<ObjectCollisionPair> collisions = _manager.CheckCollisions();

        Assert.assertTrue(!collisions.isEmpty());
        Assert.assertTrue(collisions.size() == 1);

        Iterator<ObjectCollisionPair> collIter = collisions.iterator();

        while(collIter.hasNext())
        {
            ObjectCollisionPair e = collIter.next();

            if(e.object1 == obj1)
            {
                Assert.assertTrue(e.object2 == obj2);
                Assert.assertTrue(e.activelyColliding = true);
            }
            else if(e.object1 == obj2)
            {
                Assert.assertTrue(e.object2 == obj1);
                Assert.assertTrue(e.activelyColliding = true);
            }
            else
            {
                throw new Exception("Unexpected Object");
            }
        }
    }

    @Test
    public void testCheckInitCollisionsThirdMove() throws Exception
    {
        //Objects are inside each other.
        obj1.NSetLocation(new Point(12, 12));
        _map.UpdateObjectLocation(obj1);

        obj2.NSetLocation(new Point(13, 13));
        _map.UpdateObjectLocation(obj2);

        HashSet<ObjectCollisionPair> collisions = _manager.CheckCollisions();

        Assert.assertTrue(!collisions.isEmpty());
        Assert.assertTrue(collisions.size() == 1);

        Iterator<ObjectCollisionPair> collIter = collisions.iterator();

        while(collIter.hasNext())
        {
            ObjectCollisionPair e = collIter.next();

            if(e.object1 == obj1)
            {
                Assert.assertTrue(e.object2 == obj2);
                Assert.assertTrue(e.activelyColliding = true);
            }
            else if(e.object1 == obj2)
            {
                Assert.assertTrue(e.object2 == obj1);
                Assert.assertTrue(e.activelyColliding = true);
            }
            else
            {
                throw new Exception("Unexpected Object");
            }
        }
    }

    //todo this is going to be a tough one to design,
    //multiple cases - perhaps even own test class
    @Test
    public void testHandleCollision() throws Exception
    {
        throw new Exception("UNIMPLEMENTED");
    }
}
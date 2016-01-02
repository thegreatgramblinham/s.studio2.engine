package EngineTesting.PhysicsBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionManager;
import SectorBase.SectorMap;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

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
        _manager = new CollisionManager(_map);

        obj1 = new DummyGameObject(new Rectangle(5, 5, 3, 3));
        obj2 = new DummyGameObject(new Rectangle(25, 25, 3, 3));

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

        HashMap<GameWorldObject, HashSet<GameWorldObject>> collisions
                = _manager.CheckCollisions();

        Assert.assertTrue(!collisions.isEmpty());
        Assert.assertTrue(collisions.size() == 2);

        Assert.assertTrue(collisions.containsKey(obj1));
        Assert.assertTrue(collisions.containsKey(obj2));

        Assert.assertTrue(collisions.get(obj1).size() == 1);
        Assert.assertTrue(collisions.get(obj1).contains(obj2));

        Assert.assertTrue(collisions.get(obj2).size() == 1);
        Assert.assertTrue(collisions.get(obj2).contains(obj1));
    }

    @Test
    public void testCheckInitCollisionsThirdMove() throws Exception
    {
        //Objects are inside each other.
        obj1.NSetLocation(new Point(12, 12));
        _map.UpdateObjectLocation(obj1);

        obj2.NSetLocation(new Point(13, 13));
        _map.UpdateObjectLocation(obj2);

        HashMap<GameWorldObject, HashSet<GameWorldObject>> collisions
                = _manager.CheckCollisions();

        Assert.assertTrue(!collisions.isEmpty());
        Assert.assertTrue(collisions.size() == 2);

        Assert.assertTrue(collisions.containsKey(obj1));
        Assert.assertTrue(collisions.containsKey(obj2));

        Assert.assertTrue(collisions.get(obj1).size() == 1);
        Assert.assertTrue(collisions.get(obj1).contains(obj2));

        Assert.assertTrue(collisions.get(obj2).size() == 1);
        Assert.assertTrue(collisions.get(obj2).contains(obj1));
    }

    //todo this is going to be a tough one to design,
    //multiple cases - perhaps even own test class
    @Test
    public void testHandleCollision() throws Exception
    {
        throw new Exception("UNIMPLEMENTED");
    }
}
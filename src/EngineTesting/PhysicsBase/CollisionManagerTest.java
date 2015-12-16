package EngineTesting.PhysicsBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import PhysicsBase.CollisionManager;
import SectorBase.SectorMap;
import org.junit.Assert;
import org.junit.Test;
import java.awt.*;

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
    public void testCheckCollisions() throws Exception
    {
        Assert.assertTrue(_manager.CheckCollisions().isEmpty());
    }
}
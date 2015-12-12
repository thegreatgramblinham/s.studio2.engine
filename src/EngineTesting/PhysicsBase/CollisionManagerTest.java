package EngineTesting.PhysicsBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import PhysicsBase.CollisionManager;
import SectorBase.Sector;
import SectorBase.SectorMap;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

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

        //obj1 = new DummyGameObject
    }

    @Test
    public void testCheckCollisions() throws Exception
    {

    }
}
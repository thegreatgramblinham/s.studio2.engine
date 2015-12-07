package EngineTesting.SectorBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import GameObjectBase.GameWorldObject;
import SectorBase.SectorMap;
import org.junit.Assert;

import java.awt.*;

import static org.junit.Assert.*;

public class SectorMapTest
{
    //Private Variables
    private SectorMap _map;
    private Point _testPoint;
    private DummyGameObject _testObject;

    @org.junit.Before
    public void setUp() throws Exception
    {
        _map = new SectorMap(500, 500, 20);
        _testPoint = new Point(180, 180);
        _testObject = new DummyGameObject(
                new Rectangle(_testPoint.x, _testPoint.y, 50, 50));
    }

    @org.junit.Test
    public void testInsertObject() throws Exception
    {
        _map.InsertObject(_testObject);
        Assert.assertTrue(_map.GetObjectsAtSubSector(_testPoint).length == 1);
    }

    @org.junit.Test
    public void testGetObjectsAtSubSector() throws Exception
    {

    }
}
package EngineTesting.SectorBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import SectorBase.SectorMap;
import org.junit.Assert;
import java.awt.*;

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
        _testPoint = new Point(90, 90);
        _testObject = new DummyGameObject(new Rectangle(200, 200, 50, 50));
        _testObject.SetLocation(_testPoint);
    }

    @org.junit.Test
    public void testInsertObject() throws Exception
    {
        _map.InsertObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 1);
    }

    @org.junit.Test
    public void testGetObjectsAtSubSector() throws Exception
    {
        _map.InsertObject(_testObject);
        Object[] objArr = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(objArr.length == 1);
        Assert.assertTrue(objArr[0] == _testObject);
    }

    @org.junit.Test
    public void testRemoveObject() throws Exception
    {
        _map.InsertObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 1);
        Object[] objArr = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(objArr.length == 1);

        _map.RemoveObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 0);
        objArr = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(objArr.length == 0);
    }

    @org.junit.Test
    public void testUpdateObjectLocation() throws Exception
    {
        _map.InsertObject(_testObject);
        Object[] objArr = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(objArr.length == 1);

        Point newLoc = new Point(50,25);
        _testObject.SetLocation(newLoc);

        _map.UpdateObjectLocation(_testObject);

        //Check old location
        objArr = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(objArr.length == 0);

        //Check new location
        objArr = _map.GetObjectsAtSubSector(newLoc);
        Assert.assertTrue(objArr.length == 1);
        Assert.assertTrue(objArr[0] == _testObject);
    }


}
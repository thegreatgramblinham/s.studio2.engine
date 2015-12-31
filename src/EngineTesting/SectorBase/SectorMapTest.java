package EngineTesting.SectorBase;

import EngineTesting.GameObjectBase.DummyGameObject;
import GameObjectBase.GameWorldObject;
import SectorBase.SectorMap;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.Iterator;

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

    @Test
    public void testGetAllObjectIterator() throws Exception
    {

    }

    @org.junit.Test
    public void testInsertObject() throws Exception
    {
        _map.InsertObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 1);
    }

    @org.junit.Test
    public void testUpdateObjectLocation() throws Exception
    {
        //Check origin location
        _map.InsertObject(_testObject);
        Iterator<GameWorldObject> objIter
                = _map.GetObjectsAtSubSector(_testPoint);

        Assert.assertTrue(objIter.next() == _testObject);
        Assert.assertTrue(!objIter.hasNext());

        //Change location
        Point newLoc = new Point(50,25);
        _testObject.SetLocation(newLoc);

        _map.UpdateObjectLocation(_testObject);

        //Check old location
        objIter = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(!objIter.hasNext());

        //Check new location
        objIter = _map.GetObjectsAtSubSector(newLoc);
        Assert.assertTrue(objIter.next() == _testObject);
        Assert.assertTrue(!objIter.hasNext());
    }

    @org.junit.Test
    public void testRemoveObject() throws Exception
    {
        //Insertion
        _map.InsertObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 1);
        Iterator<GameWorldObject> objIter = _map.GetObjectsAtSubSector(_testPoint);

        Assert.assertTrue(objIter.next() == _testObject);
        Assert.assertTrue(!objIter.hasNext());

        //Removal
        _map.RemoveObject(_testObject);
        Assert.assertTrue(_map.GetObjectCount() == 0);

        objIter = _map.GetObjectsAtSubSector(_testPoint);
        Assert.assertTrue(!objIter.hasNext());
    }

    @org.junit.Test
    public void testGetObjectsAtSubSector() throws Exception
    {
        _map.InsertObject(_testObject);

        Iterator<GameWorldObject> objIter
                = _map.GetObjectsAtSubSector(_testPoint);

        GameWorldObject obj = null;

        int cnt = 0;
        while (objIter.hasNext())
        {
            obj = objIter.next();

            cnt++;
        }
        Assert.assertTrue(cnt == 1);
        Assert.assertTrue(obj == _testObject);
    }

    @Test
    public void testGetObjectsAtSubSectors() throws Exception
    {

    }
}
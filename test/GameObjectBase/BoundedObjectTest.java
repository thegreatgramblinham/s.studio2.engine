package GameObjectBase;

import Global.DPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;


public class BoundedObjectTest
{

    private DummyBoundedObject _dummyObject;

    @Before
    public void setUp() throws Exception
    {
        _dummyObject = new DummyBoundedObject(new Rectangle(0,0,21,17));
    }

    @Test
    public void testGetHalfWidth() throws Exception
    {
        double width = _dummyObject.GetHalfWidth();

        Assert.assertTrue(width == 10.5);

        _dummyObject.setBounds(0,0,10,10);

        width = _dummyObject.GetHalfWidth();

        Assert.assertTrue(width == 5);
    }

    @Test
    public void testGetHalfHeight() throws Exception
    {
        double height = _dummyObject.GetHalfHeight();

        Assert.assertTrue(height == 8.5);

        _dummyObject.setBounds(0,0,10,10);

        height = _dummyObject.GetHalfHeight();

        Assert.assertTrue(height == 5);
    }

    @Test
    public void testGetSetDX() throws Exception
    {
        Assert.assertTrue(_dummyObject.GetDX() == 0.0);
        Assert.assertTrue(_dummyObject.x == 0);

        _dummyObject.SetDX(65.4321D);

        Assert.assertTrue(_dummyObject.GetDX() == 65.4321);
        Assert.assertTrue(_dummyObject.x == 65);

        _dummyObject.SetDX(76.54321D);

        Assert.assertTrue(_dummyObject.GetDX() == 76.54321);
        Assert.assertTrue(_dummyObject.x == 77);
    }

    @Test
    public void testGetSetDY() throws Exception
    {
        Assert.assertTrue(_dummyObject.GetDY() == 0.0);
        Assert.assertTrue(_dummyObject.y == 0);

        _dummyObject.SetDY(65.4321D);

        Assert.assertTrue(_dummyObject.GetDY() == 65.4321);
        Assert.assertTrue(_dummyObject.y == 65);

        _dummyObject.SetDY(76.54321D);

        Assert.assertTrue(_dummyObject.GetDY() == 76.54321);
        Assert.assertTrue(_dummyObject.y == 77);
    }

    @Test
    public void testGetX() throws Exception
    {
        Assert.assertTrue(_dummyObject.getX() == 0);

        _dummyObject.SetDX(65.4321D);
        Assert.assertTrue(_dummyObject.getX() == 65);

        _dummyObject.SetDX(76.54321D);
        Assert.assertTrue(_dummyObject.getX() == 77);
    }

    @Test
    public void testGet() throws Exception
    {
        Assert.assertTrue(_dummyObject.getY() == 0);

        _dummyObject.SetDY(65.4321D);
        Assert.assertTrue(_dummyObject.getY() == 65);

        _dummyObject.SetDY(76.54321D);
        Assert.assertTrue(_dummyObject.getY() == 77);
    }

    @Test
    public void testGetCenterPoint() throws Exception
    {
        Assert.assertTrue(_dummyObject.GetCenterPoint()
                .equals(new Point(11,9)));

        _dummyObject.setBounds(0,0,10,10);

        Assert.assertTrue(_dummyObject.GetCenterPoint()
                .equals(new Point(5,5)));
    }

    @Test
    public void testDGetSetLocation() throws Exception
    {
        DPoint p = _dummyObject.DGetLocation();
        Assert.assertTrue(p.GetX() == 0.0D);
        Assert.assertTrue(p.GetY() == 0.0D);

        _dummyObject.DSetLocation(12.34, 56.78);

        p = _dummyObject.DGetLocation();
        Assert.assertTrue(p.GetX() == 12.34);
        Assert.assertTrue(p.GetY() == 56.78);

        Point p2 = _dummyObject.NGetLocation();
        Assert.assertTrue(p2.x == 12);
        Assert.assertTrue(p2.y == 57);
    }

    @Test
    public void testNGetSetLocation() throws Exception
    {
        Point p = _dummyObject.NGetLocation();
        Assert.assertTrue(p.x == 0);
        Assert.assertTrue(p.y == 0);

        _dummyObject.NSetLocation(new Point(12, 34));

        p = _dummyObject.NGetLocation();
        Assert.assertTrue(p.x == 12);
        Assert.assertTrue(p.y == 34);

        DPoint p2 = _dummyObject.DGetLocation();
        Assert.assertTrue(p2.GetX() == 12.0D);
        Assert.assertTrue(p2.GetY() == 34.0D);
    }

    @Test
    public void testBoundsEquals() throws Exception
    {
        Assert.assertTrue(_dummyObject.BoundsEquals(
                new Rectangle(0,0,21,17)));

        Assert.assertFalse(_dummyObject.BoundsEquals(
                new Rectangle(0,0,22,18)));

        Assert.assertFalse(_dummyObject.BoundsEquals(
                new Rectangle(1,1,22,18)));
    }

    @Test
    public void testSetLocationOverrides() throws Exception
    {
        //Point override
        _dummyObject.setLocation(new Point(12, 34));

        Point p = _dummyObject.getLocation();
        Assert.assertTrue(p.x == 12);
        Assert.assertTrue(p.y == 34);

        DPoint p2 = _dummyObject.DGetLocation();
        Assert.assertTrue(p2.GetX() == 12.0D);
        Assert.assertTrue(p2.GetY() == 34.0D);

        //x,y override
        _dummyObject.setLocation(12, 34);

        p = _dummyObject.getLocation();
        Assert.assertTrue(p.x == 12);
        Assert.assertTrue(p.y == 34);

        p2 = _dummyObject.DGetLocation();
        Assert.assertTrue(p2.GetX() == 12.0D);
        Assert.assertTrue(p2.GetY() == 34.0D);
    }

    private class DummyBoundedObject extends BoundedObject
    {
        public DummyBoundedObject(Rectangle rect)
        {
            super(rect);
        }
    }
}
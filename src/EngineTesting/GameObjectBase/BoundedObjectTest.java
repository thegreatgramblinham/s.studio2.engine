package EngineTesting.GameObjectBase;

import GameObjectBase.BoundedObject;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoundedObjectTest
{

    private DummyBoundedObject _dummyObject;

    @Before
    public void setUp() throws Exception
    {
        _dummyObject = new DummyBoundedObject(new Rectangle(0,0,20,20));
    }

    @Test
    public void testGetHalfWidth() throws Exception
    {

    }

    @Test
    public void testGetHalfHeight() throws Exception
    {

    }

    @Test
    public void testGetDX() throws Exception
    {

    }

    @Test
    public void testGetDY() throws Exception
    {

    }

    @Test
    public void testGetX() throws Exception
    {

    }

    @Test
    public void testGetY() throws Exception
    {

    }

    @Test
    public void testSetDX() throws Exception
    {

    }

    @Test
    public void testSetDY() throws Exception
    {

    }

    @Test
    public void testGetCenterPoint() throws Exception
    {

    }

    @Test
    public void testDSetLocation() throws Exception
    {

    }

    @Test
    public void testNSetLocation() throws Exception
    {

    }

    @Test
    public void testNGetLocation() throws Exception
    {

    }

    @Test
    public void testBoundsEquals() throws Exception
    {

    }

    @Test
    public void testSetLocation() throws Exception
    {

    }

    @Test
    public void testSetLocation1() throws Exception
    {

    }

    private class DummyBoundedObject extends BoundedObject
    {
        public DummyBoundedObject(Rectangle rect)
        {
            super(rect);
        }
    }
}
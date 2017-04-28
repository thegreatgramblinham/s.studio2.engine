package GameObjectBase;

import PhysicsBase.Vectors.VelocityVector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;


public class GameWorldObjectTest
{

    //Private Variables
    private DummyGameObject _dummyObj;

    //Setup
    @Before
    public void setUp() throws Exception
    {
        _dummyObj = new DummyGameObject(new Rectangle(0,0,20,20), false, 1);
    }

    //Tests
    @Test
    public void testGetHitBox() throws Exception
    {
        HitBox hitBox = _dummyObj.GetHitBox();

        Assert.assertTrue(hitBox != null);
        Assert.assertTrue(_dummyObj.BoundsEquals(hitBox));
    }

    @Test
    public void testGetSetVelocity() throws Exception
    {
        Assert.assertTrue(_dummyObj.GetVelocity() == null);

        VelocityVector vector = new VelocityVector(0, 5);
        _dummyObj.SetVelocity(vector);

        Assert.assertTrue(vector.equals(_dummyObj.GetVelocity()));
    }

    @Test
    public void testGetSetAlias() throws Exception
    {
        Assert.assertTrue(_dummyObj.GetAlias() == null);

        String name = "dummyAlias";
        _dummyObj.SetAlias(name);

        Assert.assertTrue(_dummyObj.GetAlias() == name);
    }

    @Test
    public void testGetSetImmobile() throws Exception
    {
        Assert.assertFalse(_dummyObj.GetIsImmobile());

        Point firstLoc = _dummyObj.NGetLocation();

        //Attempt to move through all move methods
        _dummyObj.NSetLocation(new Point(firstLoc.x+1,firstLoc.y+1));
        Point currLoc = _dummyObj.NGetLocation();
        Assert.assertTrue(firstLoc.x != currLoc.x
                && firstLoc.y != currLoc.y);

        _dummyObj.SetVelocity(new VelocityVector(0, 2));
        Assert.assertTrue(_dummyObj.GetVelocity() != null);

        _dummyObj.ClearVelocity();

        _dummyObj.AccelerateBy(new VelocityVector(0, 5));
        Assert.assertTrue(_dummyObj.GetVelocity() != null);

        //Reset and Set Immobility
        _dummyObj.NSetLocation(firstLoc);
        _dummyObj.ClearVelocity();
        _dummyObj.SetIsImmobile(true);

        //Attempt to move through all move methods - no movement should occur
        _dummyObj.NSetLocation(new Point(firstLoc.x+1,firstLoc.y+1));
        currLoc = _dummyObj.NGetLocation();
        Assert.assertTrue(firstLoc.x == currLoc.x
                && firstLoc.y == currLoc.y);

        _dummyObj.SetVelocity(new VelocityVector(0, 2));
        Assert.assertTrue(_dummyObj.GetVelocity() == null);

        _dummyObj.ClearVelocity();

        _dummyObj.AccelerateBy(new VelocityVector(0, 5));
        Assert.assertTrue(_dummyObj.GetVelocity() == null);

        Assert.assertTrue(_dummyObj.GetIsImmobile());
    }

    @Test
    public void testDSetLocation() throws Exception
    {
        Point firstLoc = _dummyObj.NGetLocation();

        //Test 1 - location closer to one
        _dummyObj.DSetLocation(1.2, 1.2);

        //Not at old location
        Assert.assertFalse(firstLoc.equals(_dummyObj.NGetLocation()));

        //At new location with hit box.
        Assert.assertTrue(new Point(1,1).equals(_dummyObj.NGetLocation()));
        Assert.assertTrue(_dummyObj.GetHitBox().NGetLocation()
                .equals(_dummyObj.NGetLocation()));

        _dummyObj.NSetLocation(firstLoc); //Reset location


        //Test 2 - location closer to two
        _dummyObj.DSetLocation(1.8, 1.8);

        //Not at old location
        Assert.assertFalse(firstLoc.equals(_dummyObj.NGetLocation()));

        //At new location with hit box.
        Assert.assertTrue(new Point(2,2).equals(_dummyObj.NGetLocation()));
        Assert.assertTrue(_dummyObj.GetHitBox().NGetLocation()
                .equals(_dummyObj.NGetLocation()));
    }

    @Test
    public void testNSetLocation() throws Exception
    {
        Point firstLoc = _dummyObj.NGetLocation();

        _dummyObj.NSetLocation(new Point(1,1));

        //Not at old location
        Assert.assertFalse(firstLoc.equals(_dummyObj.NGetLocation()));

        //At new location with hit box.
        Assert.assertTrue(new Point(1,1).equals(_dummyObj.NGetLocation()));
        Assert.assertTrue(_dummyObj.GetHitBox().NGetLocation()
                .equals(_dummyObj.NGetLocation()));
    }

    @Test
    public void testAccelerateBy() throws Exception
    {
        Assert.assertTrue(_dummyObj.GetVelocity() == null);

        VelocityVector v1 = new VelocityVector(0, 2);
        _dummyObj.AccelerateBy(v1);

        Assert.assertTrue(_dummyObj.GetVelocity().equals(v1));

        VelocityVector v2 = new VelocityVector(0, 5);
        _dummyObj.AccelerateBy(v2);

        Assert.assertFalse(_dummyObj.GetVelocity().equals(v1));
        Assert.assertFalse(_dummyObj.GetVelocity().equals(v2));

        Assert.assertTrue(_dummyObj.GetVelocity().GetRadianRotation() == 0.0);
        Assert.assertTrue(_dummyObj.GetVelocity().GetSpeed() == 7.0);
    }

    @Test
    public void testClearVelocity() throws Exception
    {
        _dummyObj.SetVelocity(new VelocityVector(0, 2));
        Assert.assertTrue(_dummyObj.GetVelocity() != null);

        _dummyObj.ClearVelocity();

        Assert.assertTrue(_dummyObj.GetVelocity() == null);
    }
}
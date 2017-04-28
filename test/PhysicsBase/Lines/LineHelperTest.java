package PhysicsBase.Lines;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class LineHelperTest
{

    //Private Variables
    private NLine _line1;
    private NLine _line2;

    //Tests
    @Test
    public void testLineSegmentIntersect1() throws Exception
    {
        //perfect vertical and horizontal orientation
        _line1 = new NLine(new Point(1,0), new Point(1,2));
        _line2 = new NLine(new Point(0,1), new Point(2,1));

        Assert.assertTrue(LineHelper.Intersect(_line1, _line2));
    }

    @Test
    public void testLineSegmentIntersect2() throws Exception
    {
        //turned 45 degrees from above
        _line1 = new NLine(new Point(0,0), new Point(2,2));
        _line2 = new NLine(new Point(0,2), new Point(2,0));

        Assert.assertTrue(LineHelper.Intersect(_line1, _line2));
    }

    @Test
    public void testLineSegmentIntersect3() throws Exception
    {
        //vertical parallel
        _line1 = new NLine(new Point(0,0), new Point(0,2));
        _line2 = new NLine(new Point(2,0), new Point(2,2));

        Assert.assertFalse(LineHelper.Intersect(_line1, _line2));
    }

    @Test
    public void testLineSegmentIntersect4() throws Exception
    {
        //horizontal parallel
        _line1 = new NLine(new Point(0,0), new Point(2,0));
        _line2 = new NLine(new Point(0,1), new Point(2,1));

        Assert.assertFalse(LineHelper.Intersect(_line1, _line2));
    }

    @Test
    public void testLineSegmentIntersect5() throws Exception
    {
        //same startpoint, same angle, different distance.
        _line1 = new NLine(new Point(0,0), new Point(0,1));
        _line2 = new NLine(new Point(0,0), new Point(0,2));

        Assert.assertFalse(LineHelper.Intersect(_line1, _line2));
    }
}
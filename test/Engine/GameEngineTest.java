package Engine;

import SectorBase.Sector;
import SectorBase.enums.GravityApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameEngineTest
{

    private GameEngine _engine;

    @Before
    public void setUp() throws Exception
    {
        _engine = new GameEngine();
    }

    @Test
    public void testSetActiveSector() throws Exception
    {
        //engine should not start without an active sector.
        _engine.Start();
        Assert.assertFalse(_engine.IsRunning());

        Sector testSector = new Sector(0,0,0,0.0F,GravityApplication.Area,null);
        _engine.SetActiveSector(testSector);

        Assert.assertTrue(_engine.GetActiveSector() == testSector);

    }

    @Test
    public void testCycleEngine() throws Exception
    {
        throw new Exception("UNIMPLEMENTED");
    }

    @Test
    public void testStart() throws Exception
    {
        //engine should not start without an active sector.
        _engine.Start();
        Assert.assertFalse(_engine.IsRunning());

        _engine.SetActiveSector(new Sector(0,0,0,0.0F,GravityApplication.Area, null));

        _engine.Start();
        Assert.assertTrue(_engine.IsRunning());
    }

    @Test
    public void testStop() throws Exception
    {
        _engine.SetActiveSector(new Sector(0,0,0,0.0F,GravityApplication.Area, null));

        _engine.Start();
        Assert.assertTrue(_engine.IsRunning());

        _engine.Stop();
        Assert.assertFalse(_engine.IsRunning());
    }

    @Test
    public void testCreateSector() throws Exception
    {
        Sector sector = _engine.CreateSector(0,0,0,0.0F,GravityApplication.Area);

        Assert.assertTrue(sector == _engine.GetActiveSector());
    }
}
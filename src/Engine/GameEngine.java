package Engine;

import SectorBase.Sector;
import sun.print.PrintJob2D;

import java.util.HashSet;
import java.util.concurrent.ThreadFactory;

public class GameEngine
{
    //Private Variables
    private double _framerate;
    private HashSet<Sector> _sectorSet;

    private Sector _activeSector;
    private Thread _physicsLoop;

    //Properties

    //Constructor
    public GameEngine()
    {

    }

    //GetMethods

    //SetMethods

    //Public Methods
    public void Start()
    {
        _physicsLoop = new Thread(new PhysicsLoop(_activeSector));
    }

    public Sector CreateSector(int width, int height, int gridUnitSize)
    {
        Sector sec = new Sector(width, height, gridUnitSize);
        _sectorSet.add(sec);

        return sec;
    }

    //Private Methods
    private void Init()
    {
        _sectorSet = new HashSet<>();
    }
}

package Engine;

import SectorBase.Sector;

import java.util.HashSet;


public class GameEngine
{
    //Private Variables
    private double _frameRate;
    private HashSet<Sector> _sectorSet;

    private Sector _activeSector;

    private Thread _physicsThread;
    private PhysicsManager _physicsLoop;


    //Constructor
    public GameEngine(double frameRate)
    {
        Init();
        _frameRate = frameRate;
    }

    //GetMethods

    //SetMethods
    public void SetActiveSector(Sector activeSector)
    {
        if(activeSector == null) return;

        if(!_sectorSet.contains(activeSector))
            _sectorSet.add(activeSector);

        _activeSector = activeSector;
    }

    //Public Methods
    public void CycleEngine()
    {

    }

    public void Start()
    {
        _physicsLoop = new PhysicsManager(_activeSector, _frameRate);
        _physicsThread = new Thread(_physicsLoop);
        _physicsThread.start();
    }

    public void Stop()
    {
        _physicsLoop.RequestCancellation();
        _physicsThread.interrupt();
        _physicsThread = null;
    }

    public boolean IsRunning()
    {
        return _physicsThread == null;
    }

    public Sector CreateSector(int width, int height, int gridUnitSize)
    {
        Sector sec = new Sector(width, height, gridUnitSize);
        _sectorSet.add(sec);

        if(_activeSector == null) _activeSector = sec;

        return sec;
    }

    //Private Methods
    private void Init()
    {
        _sectorSet = new HashSet<>();
    }
}

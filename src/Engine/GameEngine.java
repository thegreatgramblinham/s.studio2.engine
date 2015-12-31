package Engine;

import SectorBase.Sector;

import java.util.HashSet;


public class GameEngine
{
    //Private Variables
    private double _frameRate;
    private HashSet<Sector> _sectorSet;

    private Sector _activeSector;

    private PhysicsManager _physicsManager;

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
        _physicsManager.CyclePhysicsFrame();
    }

    public void Start()
    {
        _physicsManager = new PhysicsManager(_activeSector);
    }

    public void Stop()
    {
        _physicsManager = null;
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

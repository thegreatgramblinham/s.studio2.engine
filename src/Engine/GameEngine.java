package Engine;

import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionRules.CollisionGroupNamePair;
import PhysicsBase.CollisionRules.CollisionGroupPair;
import PhysicsBase.CollisionRules.enums.CollisionRule;
import SectorBase.Sector;
import SectorBase.enums.GravityApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class GameEngine
{
    //Private Variables
    private HashSet<Sector> _sectorSet;

    private HashMap<CollisionGroupNamePair, CollisionRule> _collisionRules;

    private Sector _activeSector;
    private PhysicsManager _physicsManager;

    private boolean _isRunning;

    //Constructor
    public GameEngine()
    {
        Init();
    }

    //GetMethods
    public int GetSectorCount()
    {
        return _sectorSet.size();
    }

    public Sector GetActiveSector()
    {
        return _activeSector;
    }

    //SetMethods
    public void SetActiveSector(Sector activeSector)
    {
        if(activeSector == null) return;

        if(!_sectorSet.contains(activeSector))
            _sectorSet.add(activeSector);

        _activeSector = activeSector;
    }

    //Public Methods
    public boolean IsRunning()
    {
        return _isRunning;
    }

    public void CycleEngine()
    {
        _physicsManager.CyclePhysicsFrame();
        ActiveSectorGarbageCollection();
    }

    public void CycleCollision()
    {
        _physicsManager.CycleCollisionFrame();
    }

    public void Start()
    {
        if(_activeSector == null) return;

        _physicsManager = new PhysicsManager(_activeSector);
        _isRunning = true;
    }

    public void Stop()
    {
        _physicsManager = null;
        _isRunning = false;
    }

    public Sector CreateSector(int width, int height, int gridUnitSize,
                               float gravity, GravityApplication gravityApp)
    {
        Sector sec = new Sector(width, height, gridUnitSize,
                gravity, gravityApp, _collisionRules);
        _sectorSet.add(sec);

        if(_activeSector == null) _activeSector = sec;

        return sec;
    }

    public void AddCollisionRule(CollisionGroupNamePair pair, CollisionRule rule)
    {
        _collisionRules.put(pair, rule);
    }

    //Private Methods
    private void Init()
    {
        _sectorSet = new HashSet<>();
        _collisionRules = new HashMap<>();
    }

    private void ActiveSectorGarbageCollection()
    {
        //todo set a flag from another loop to see if this is needed.
        Iterator<GameWorldObject> iter
                = _activeSector.GetObjectsInSector();

        HashSet<GameWorldObject> needsDeletion = new HashSet<>();

        while(iter.hasNext())
        {
            GameWorldObject gObj = iter.next();

            if(gObj.GetNeedsDeletion())
                needsDeletion.add(gObj);
        }

        for(GameWorldObject gObj : needsDeletion)
        {
            gObj.OnRemoval();
            _activeSector.RemoveObject(gObj);
        }
    }
}

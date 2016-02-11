package Engine;

import SectorBase.Sector;

class PhysicsManager
{
    //Private Variables
    private Sector _sector;

    //Properties
    public PhysicsManager(Sector activeSector)
    {
        _sector = activeSector;
    }

    //Constructor

    //GetMethods

    //SetMethods

    //Public Methods
    public void CyclePhysicsFrame()
    {
        //todo advance location here - according to vectors+gravity.
        _sector.HandleCollisions();
        _sector.UpdateVectors();
    }

    public void CycleCollisionFrame()
    {
        _sector.HandleCollisions();
    }

    //Private Methods
}

package Engine;

import SectorBase.Sector;

import java.util.HashSet;

class PhysicsLoop implements Runnable
{
    //Private Variables
    private Sector _sector;
    private boolean _cancellationRequested;

    //Properties
    public PhysicsLoop(Sector activeSector)
    {
        _sector = activeSector;
        _cancellationRequested = false;
    }

    //Constructor

    //GetMethods

    //SetMethods

    //Public Methods
    @Override
    public void run()
    {
        while(!_cancellationRequested)
        {

        }
    }

    public void RequestCancellation()
    {
        _cancellationRequested = true;
    }

    //Private Methods
}

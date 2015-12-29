package Engine;

import SectorBase.Sector;

class PhysicsLoop implements Runnable
{
    //Private Variables
    private Sector _sector;
    private double _frameRate;
    private boolean _cancellationRequested;

    //Properties
    public PhysicsLoop(Sector activeSector, double frameRate)
    {
        _sector = activeSector;
        _cancellationRequested = false;
        _frameRate = frameRate;
    }

    //Constructor

    //GetMethods

    //SetMethods

    //Public Methods
    @Override
    public void run()
    {
        //todo enforce frame rate.
        while(!_cancellationRequested)
        {
            _sector.HandleCollisions();
            _sector.UpdateVectors();
        }
    }

    public void RequestCancellation()
    {
        _cancellationRequested = true;
    }

    //Private Methods
}

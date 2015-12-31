package Engine;

import SectorBase.Sector;

class PhysicsManager implements Runnable
{
    //Private Variables
    private Sector _sector;
    private double _frameRate;
    private boolean _cancellationRequested;

    //Properties
    public PhysicsManager(Sector activeSector, double fps)
    {
        _sector = activeSector;
        _cancellationRequested = false;
        _frameRate = fps;
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

    @Override
    public void run()
    {
        System.nanoTime();
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

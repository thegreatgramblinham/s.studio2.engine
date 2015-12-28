import SectorBase.Sector;

import java.util.HashSet;

public class Engine
{
    //Private Variables
    private HashSet<Sector> _sectorSet;

    //Properties

    //Constructor
    public Engine()
    {

    }

    //GetMethods

    //SetMethods

    //Public Methods
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

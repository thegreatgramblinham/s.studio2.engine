package SectorBase;

import java.awt.*;

final class SectorMapHelper
{
    //Constructor
    private SectorMapHelper(){}

    //Public Methods
    public static int CoordinateToGridPosition(int coord, int gridUnitSize)
    {
        return (int)Math.ceil((double)coord/(double)gridUnitSize);
    }

    public static Point CoordinateToGridPosition(int x, int y, int gridUnitSize)
    {
        int xCoords = SectorMapHelper.CoordinateToGridPosition(x, gridUnitSize);
        int yCoords = SectorMapHelper.CoordinateToGridPosition(y, gridUnitSize);

        return new Point(xCoords,yCoords);
    }
}

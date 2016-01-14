package GeneralHelpers;

import SectorBase.enums.Direction;

public final class ConversionHelper
{
    //Constructor
    private ConversionHelper(){}

    //Public Methods
    public static Direction GetRadianToCollisionDirection(double radians)
    {
        if(radians == 0.0D)
            return Direction.Right;
        if(radians == Math.PI)
            return Direction.Left;
        if(radians == Math.PI/2)
            return Direction.Down;
        if(radians == (3*Math.PI)/2)
            return Direction.Up;

        return null;
    }

    public static Direction RadianToDirection(double radians)
    {
        if(radians == 0.0D)
            return Direction.Right;
        if(radians == Math.PI)
            return Direction.Left;
        if(radians == Math.PI/2)
            return Direction.Up;
        if(radians == (3*Math.PI)/2)
            return Direction.Down;

        return null;
    }

    public static double GetOppositeDirection(double radians)
    {
        return radians + Math.PI;
    }

    //Private Methods
}

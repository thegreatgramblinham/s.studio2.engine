package GeneralHelpers;

import GameObjectBase.enums.Side;
import SectorBase.enums.Direction;

public final class ConversionHelper
{
    //Constructor
    private ConversionHelper(){}

    //Public Methods
    public static Side GetRadianToCollisionSide(double radians)
    {
        if(radians == 0.0D)
            return Side.Right;
        if(radians == Math.PI)
            return Side.Left;
        if(radians == Math.PI/2)
            return Side.Bottom;
        if(radians == (3*Math.PI)/2)
            return Side.Top;

        return null;
    }

    public static Side GetOppositeSide(Side s)
    {
        switch (s)
        {
            case Top:
                return Side.Bottom;
            case Bottom:
                return Side.Top;
            case Left:
                return Side.Right;
            case Right:
                return Side.Left;
        }
        return null;
    }

    public static Direction GetOppositeDirectionFromSide(Side s)
    {
        switch (s)
        {
            case Top:
                return Direction.Down;
            case Bottom:
                return Direction.Up;
            case Left:
                return Direction.Right;
            case Right:
                return Direction.Left;
        }
        return null;
    }

    public static Direction GetDirectionFromSide(Side s)
    {
        switch (s)
        {
            case Top:
                return Direction.Up;
            case Bottom:
                return Direction.Down;
            case Left:
                return Direction.Left;
            case Right:
                return Direction.Right;
        }
        return null;
    }

    public static Side StringToSide(String side)
    {
        Side enumSide = null;

        switch(side.toUpperCase())
        {
            case "TOP":
                enumSide = Side.Top;
                break;
            case "RIGHT":
                enumSide = Side.Right;
                break;
            case "LEFT":
                enumSide = Side.Left;
                break;
            case "BOTTOM":
                enumSide = Side.Bottom;
                break;
        }

        return enumSide;
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

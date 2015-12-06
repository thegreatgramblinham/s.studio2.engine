package PhysicsBase;

public class Vector
{
    //Properties
    private double RadianRotation;

    //Constructor
    public Vector(double radianRotation)
    {
        RadianRotation = radianRotation;
    }

    //GetMethods
    public double GetRadianRotation()
    {
        return RadianRotation;
    }

    //SetMethods
    public void SetRadianRotation(double radianRotation)
    {
        RadianRotation = radianRotation;
    }
}

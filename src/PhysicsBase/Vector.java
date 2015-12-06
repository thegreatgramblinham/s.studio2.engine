package PhysicsBase;

public class Vector
{
    //Variables
    private double _radianRotation;

    //Constructor
    public Vector(double radianRotation)
    {
        _radianRotation = radianRotation;
    }

    //GetMethods
    public double GetRadianRotation()
    {
        return _radianRotation;
    }

    //SetMethods
    public void SetRadianRotation(double radianRotation)
    {
        _radianRotation = radianRotation;
    }
}

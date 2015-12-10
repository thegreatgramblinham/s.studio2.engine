package PhysicsBase.Vectors;

public class VectorBase
{
    //Properties
    private double RadianRotation;

    //Constructor
    public VectorBase(double radianRotation)
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

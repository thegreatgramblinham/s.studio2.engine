package PhysicsBase.Vectors;

public class DistanceVector extends VectorBase
{
    //Properties
    private int Length;

    //Constructor
    public DistanceVector(double radianRotation, int length)
    {
        super(radianRotation);
        Length = length;
    }

    //GetMethods
    public double GetLength()
    {
        return Length;
    }

    //SetMethods
    public void SetLength(int length)
    {
        Length = length;
    }
}

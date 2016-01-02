package PhysicsBase.Vectors;

public class DistanceVector extends VectorBase
{
    //Properties
    private double _length;

    //Constructor
    public DistanceVector(double radianRotation, double length)
    {
        super(radianRotation);
        _length = length;
    }

    //GetMethods
    public double GetLength()
    {
        return _length;
    }

    //SetMethods
    public void SetLength(int length)
    {
        _length = length;
    }
}

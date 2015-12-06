package PhysicsBase;

public class DistanceVector extends Vector
{
    //Variables
    private int _length;

    //Constructor
    public DistanceVector(double radianRotation, int length)
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

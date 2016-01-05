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

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DistanceVector that = (DistanceVector) o;

        return Double.compare(that._length, _length) == 0;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(_length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

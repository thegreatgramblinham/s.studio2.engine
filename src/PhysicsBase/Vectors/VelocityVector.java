package PhysicsBase.Vectors;

public class VelocityVector extends VectorBase
{
    //Properties
    private double _speed;

    //Constructor
    public VelocityVector(double radianRotation, double speed)
    {
        super(radianRotation);
        _speed = speed;
    }

    //GetMethods
    public double GetSpeed()
    {
        return _speed;
    }

    //SetMethods
    public void SetSpeed(double speed)
    {
        _speed = speed;
    }

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VelocityVector that = (VelocityVector) o;

        return Double.compare(that._speed, _speed) == 0;

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(_speed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

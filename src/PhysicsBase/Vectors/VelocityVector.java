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
    public void SetSpeed(int speed)
    {
        _speed = speed;
    }
}

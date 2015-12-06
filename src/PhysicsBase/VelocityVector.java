package PhysicsBase;

public class VelocityVector extends Vector
{
    //Variables
    private int _speed;

    //Constructor
    public VelocityVector(double radianRotation, int speed)
    {
        super(radianRotation);
        _speed = speed;
    }

    //GetMethods
    public int GetSpeed()
    {
        return _speed;
    }

    //SetMethods
    public void SetSpeed(int speed)
    {
        _speed = speed;
    }
}

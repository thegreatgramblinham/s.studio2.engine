package PhysicsBase;

public class VelocityVector extends Vector
{
    //Properties
    private int Speed;

    //Constructor
    public VelocityVector(double radianRotation, int speed)
    {
        super(radianRotation);
        Speed = speed;
    }

    //GetMethods
    public int GetSpeed()
    {
        return Speed;
    }

    //SetMethods
    public void SetSpeed(int speed)
    {
        Speed = speed;
    }
}

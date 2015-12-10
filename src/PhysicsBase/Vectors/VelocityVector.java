package PhysicsBase.Vectors;

public class VelocityVector extends VectorBase
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

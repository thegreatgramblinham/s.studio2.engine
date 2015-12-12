package GameObjectBase;

import PhysicsBase.HitBox;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public abstract class GameWorldObject extends GameObject
{
    //Private Variables
    private HitBox _hitBox;

    //Properties
    private VelocityVector Velocity;

    //Constructor
    public GameWorldObject(Rectangle size)
    {
        super(size);
        Velocity = new VelocityVector(0, 0);
        _hitBox = new HitBox(size);

    }

    //GetMethods
    public HitBox GetHitBox()
    {
        return _hitBox;
    }

    public VelocityVector GetVelocity()
    {
        return Velocity;
    }

    //SetMethods
    public void SetVelocity(VelocityVector velocity)
    {
        Velocity = velocity;
    }

}

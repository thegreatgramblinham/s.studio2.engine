package GameObjectBase;

import PhysicsBase.VelocityVector;

import java.awt.*;

public class GameWorldObject extends GameObject
{
    //Properties
    private Rectangle HitBox;
    private VelocityVector Velocity;

    //Constructor
    public GameWorldObject()
    {
        super();
        Velocity = new VelocityVector(0, 0);
    }

    //GetMethods
    public Rectangle GetHitBox()
    {
        return HitBox;
    }

    public VelocityVector GetVelocity()
    {
        return Velocity;
    }

    //SetMethods
    public void SetHitBox(Rectangle hitBox)
    {
        //should this even have a setter? it's based of the size.
        //probably should be managed internally
        HitBox = hitBox;
    }

    public void SetVelocity(VelocityVector velocity)
    {
        Velocity = velocity;
    }

}

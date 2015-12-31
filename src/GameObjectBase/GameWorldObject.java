package GameObjectBase;

import PhysicsBase.HitBox;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public abstract class GameWorldObject extends GameObject
{
    //Private Variables
    private HitBox _hitBox;

    //Properties
    private VelocityVector _velocity;
    private String _alias;

    //Constructors
    public GameWorldObject(Rectangle size)
    {
        super(size);
        Init();
        _hitBox = new HitBox(size);
    }

    public GameWorldObject(Rectangle size, String alias)
    {
        super(size);
        Init();
        _alias = alias;
        _hitBox = new HitBox(size);
    }

    //GetMethods
    public HitBox GetHitBox()
    {
        return _hitBox;
    }

    public VelocityVector GetVelocity()
    {
        return _velocity;
    }

    public String GetAlias()
    {
        return _alias;
    }

    //SetMethods
    public void SetVelocity(VelocityVector velocity)
    {
        _velocity = velocity;
    }

    public void SetAlias(String alias)
    {
        _alias = alias;
    }

    public void SetLocation(Point p)
    {
        this.x = p.x;
        this.y = p.y;
        _hitBox.x = p.x;
        _hitBox.y = p.y;
    }

    //Private Methods
    private void Init()
    {
        _velocity = new VelocityVector(0,0);
    }

}

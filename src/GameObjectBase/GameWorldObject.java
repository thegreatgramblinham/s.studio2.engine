package GameObjectBase;

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

    @Override
    public void DSetLocation(double x, double y)
    {
        super.DSetLocation(x,y);
        _hitBox.DSetLocation(x,y);
    }

    @Override
    public void NSetLocation(Point p)
    {
        if(p == null) return;

        super.NSetLocation(p);
        _hitBox.NSetLocation(p);
    }

    //Public Methods
    public void AccelerateBy(VelocityVector v)
    {
        if(_velocity == null
                || _velocity.GetSpeed() <= 0.0)
        {
            _velocity = v;
        }
        else if (_velocity.GetRadianRotation()
                != v.GetRadianRotation())
        {
            _velocity = v;
        }
        else
        {
            _velocity.SetSpeed(_velocity.GetSpeed() + v.GetSpeed());
        }
    }

    //Private Methods
    private void Init()
    {
        _velocity = new VelocityVector(0,0);
    }

}

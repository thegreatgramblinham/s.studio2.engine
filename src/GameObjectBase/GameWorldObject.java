package GameObjectBase;

import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public abstract class GameWorldObject extends GameObject
{
    //Private Variables
    private HitBox _hitBox;

    //Properties
    private VelocityVector _velocity;
    private VelocityVector _acceleration;
    private String _alias;
    private float _mass;
    private boolean _isImmobile;
    private boolean _isAccelerating = false;

    //Constructors
    public GameWorldObject(Rectangle size, boolean isImmobile, float mass)
    {
        super(size);
        Init(mass);
        _hitBox = new HitBox(size);
        _isImmobile = isImmobile;
    }

    public GameWorldObject(Rectangle size, boolean isImmobile, String alias, float mass)
    {
        super(size);
        Init(mass);
        _alias = alias;
        _hitBox = new HitBox(size);
        _isImmobile = isImmobile;
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

    public VelocityVector GetAcceleration(){ return _acceleration; }

    public String GetAlias()
    {
        return _alias;
    }

    public boolean GetIsImmobile()
    {
        return _isImmobile;
    }

    public float GetMass()
    {
        return _mass;
    }

    public boolean GetIsAccelerating()
    {
        return _isAccelerating;
    }

    //SetMethods
    public void SetVelocity(VelocityVector velocity)
    {
        if(_isImmobile) return;

        _velocity = velocity;
    }

    public void SetAlias(String alias)
    {
        _alias = alias;
    }

    public void SetIsImmobile(boolean immobile)
    {
        _isImmobile = immobile;
    }

    public void SetMass(float mass)
    {
        _mass = mass;
    }

    @Override
    public void DSetLocation(double x, double y)
    {
        if(_isImmobile) return;

        super.DSetLocation(x,y);
        _hitBox.DSetLocation(x,y);
    }

    @Override
    public void NSetLocation(Point p)
    {
        if(p == null) return;
        if(_isImmobile) return;

        super.NSetLocation(p);
        _hitBox.NSetLocation(p);
    }

    //Public Methods
    public void AccelerateBy(VelocityVector v)
    {
        if(_isImmobile) return;

        if(_acceleration != v) _acceleration = v;

        if(_velocity == null
                || _velocity.GetSpeed() <= 0.0)
        {
            _velocity = new VelocityVector(v.GetRadianRotation(), v.GetSpeed());
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

        _isAccelerating = true;
    }

    public void StopAcceleration()
    {
        _isAccelerating = false;
    }

    public void ClearVelocity()
    {
        _velocity = null;
    }

    //Private Methods
    private void Init(float mass)
    {
        _velocity = null;
        _isImmobile = false;
        this.SetMass(mass);
    }

    //Override Handling
    @Override
    public void setLocation(Point p)
    {
        this.NSetLocation(p);
    }

    @Override
    public void setLocation(int x, int y)
    {
        this.NSetLocation(new Point(x,y));
    }
}

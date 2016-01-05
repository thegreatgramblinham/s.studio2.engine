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
    private boolean _isImmobile;

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

    public boolean GetIsImmobile()
    {
        return _isImmobile;
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
    }

    public void ClearVelocity()
    {
        _velocity = null;
    }

    //Private Methods
    private void Init()
    {
        _velocity = null;
        _isImmobile = false;
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

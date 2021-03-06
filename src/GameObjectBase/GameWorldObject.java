package GameObjectBase;

import PhysicsBase.Vectors.VelocityVector;
import javafx.scene.image.Image;

import java.awt.*;

public abstract class GameWorldObject extends GameObject
{
    //Private Variables
    private HitBox _hitBox;

    //Properties
    private Image _sprite;
    private VelocityVector _velocity;
    private VelocityVector _acceleration;
    private String _alias;
    private float _mass;
    private boolean _isImmobile;
    private boolean _isAccelerating = false;
    private boolean _canCollide = true;
    private boolean _needsDeletion = false;

    //Constructors
    public GameWorldObject(Rectangle size, Rectangle hitBox,
                           boolean isImmobile,
                           float mass)
    {
        super(size);
        Init(mass);
        _hitBox = new HitBox(hitBox, this.NGetLocation());

        NSetLocation(size.getLocation());
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

    public Image GetSprite()
    {
        return _sprite;
    }

    public boolean GetCanCollide()
    {
        return _canCollide;
    }

    public boolean GetNeedsDeletion()
    {
        return _needsDeletion;
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

    public void SetCanCollide(boolean canCollide)
    {
        _canCollide = canCollide;
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

    public void SetSprite(Image sprite)
    {
        _sprite = sprite;
    }

    public void SetNeedsDeletion(boolean needsDeletion)
    {
        _needsDeletion = needsDeletion;
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

    public void OnCollide(GameWorldObject other){}

    public void OnRemoval()
    {
        System.out.println("DELETION! - <["+GetAlias()+" removed from sector]>");
    }

    public boolean IsMoving()
    {
        return GetVelocity() != null && GetVelocity().GetSpeed() != 0.0D;
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

package GameObjectBase;

import java.awt.*;

public class GameWorldObject extends GameObject
{
    //Variables
    private Rectangle _hitBox;
    private int _velocity;

    //Constructor
    public GameWorldObject()
    {
        super();
    }

    //GetMethods
    public Rectangle GetHitBox()
    {
        return _hitBox;
    }

    public int GetVelocity()
    {
        return _velocity;
    }

    //SetMethods
    public void SetHitBox(Rectangle hitBox)
    {
        //should this even have a setter? it's based of the size.
        //probably should be managed internally
        _hitBox = hitBox;
    }

    public void SetVelocity(int velocity)
    {
        _velocity = velocity;
    }

}

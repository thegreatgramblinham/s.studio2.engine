package GameObjectBase;


import java.awt.*;

public class DummyGameObject extends GameWorldObject
{
    //Properties
    public DummyGameObject(Rectangle size, boolean isImmobile, float mass)
    {
        super(size, size, isImmobile, mass);
    }
}

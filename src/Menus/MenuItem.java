package Menus;

import GameObjectBase.BoundedObject;

import java.awt.*;

public abstract class MenuItem extends BoundedObject
{
    //Private Variables
    private boolean _isVisible;
    private boolean _isDisabled;

    //Constructor
    public MenuItem(Rectangle rect)
    {
        super(rect);
        Init();
    }

    //Get Methods
    public boolean GetIsVisible()
    {
        return _isVisible;
    }
    public boolean SetIsDisabled()
    {
        return _isDisabled;
    }

    //Set Methods
    public void SetIsDisabled(boolean isDisabled)
    {
        this._isDisabled = isDisabled;
    }

    public void SetIsVisible(boolean isVisible)
    {
        this._isVisible = isVisible;
    }

    //Private Methods
    private void Init()
    {
        _isVisible = true;
        _isDisabled = false;
    }
}

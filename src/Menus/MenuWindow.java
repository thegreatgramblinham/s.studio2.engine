package Menus;

import GameObjectBase.BoundedObject;

import java.awt.*;

public abstract class MenuWindow extends BoundedObject
{
    //Private Variables
    private MenuItem _content;

    //Constructor
    public MenuWindow(Rectangle rect, MenuItem content)
    {
        super(rect);
        _content = content;
    }
}

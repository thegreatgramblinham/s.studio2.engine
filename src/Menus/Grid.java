package Menus;

import java.awt.*;

public class Grid extends MenuItem
{
    //Private variables
    private MenuItem[][] _grid;

    //Constructor
    public Grid(Rectangle rect, int rows, int columns)
    {
        super(rect);
        _grid = new MenuItem[rows][columns]; //todo sizing
    }
}

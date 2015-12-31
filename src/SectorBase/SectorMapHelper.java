package SectorBase;

import java.awt.*;
import java.util.HashSet;

final class SectorMapHelper
{
    //Constructor
    private SectorMapHelper(){}

    //Public Methods
    public static int CoordinateToGridPosition(int coord, int gridUnitSize)
    {
        return (int)Math.ceil((double)coord/(double)gridUnitSize);
    }

    public static Point CoordinateToGridPosition(int x, int y, int gridUnitSize)
    {
        int xCoords = SectorMapHelper.CoordinateToGridPosition(x, gridUnitSize);
        int yCoords = SectorMapHelper.CoordinateToGridPosition(y, gridUnitSize);

        return new Point(xCoords-1,yCoords-1);
    }

    public static HashSet<Point> RectToGridPositions(Rectangle rect, int gridUnitSize)
    {
        HashSet<Point> gridPositions = new HashSet<>();

        //upper left point
        int x1 = (int)Math.ceil(rect.getX());
        int y1 = (int)Math.ceil(rect.getY());

        Point p1 = CoordinateToGridPosition(x1, y1, gridUnitSize);

        //lower right point
        int x2 = (int)Math.ceil(rect.getX() + rect.getWidth());
        int y2 = (int)Math.ceil(rect.getY() + rect.getHeight());

        Point p2 = CoordinateToGridPosition(x2, y2, gridUnitSize);

        for (int i = 0; p1.x + i <= p2.x; i++)
        {
            for (int j = 0; p1.y + j <= p2.y; j++)
            {
                gridPositions.add(new Point(p1.x + i, p1.y + j));
            }
        }

        return gridPositions;
    }
}

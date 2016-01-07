package Global;

public class DPoint
{
    //Properties
    private double _x;
    private double _y;

    //Constructor
    public DPoint(double x, double y)
    {
        _x = x;
        _y = y;
    }

    //GetMethods
    public double GetX()
    {
        return _x;
    }

    public double GetY()
    {
        return _y;
    }

    //SetMethods
    public void SetX(double x)
    {
        _x = x;
    }

    public void SetY(double y)
    {
        _y = y;
    }

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DPoint dPoint = (DPoint) o;

        if (Double.compare(dPoint._x, _x) != 0) return false;
        return Double.compare(dPoint._y, _y) == 0;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(_x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(_y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

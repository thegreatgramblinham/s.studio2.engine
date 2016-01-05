package PhysicsBase.Vectors;

class VectorBase
{
    //Properties
    private double RadianRotation;

    //Constructor
    public VectorBase(double radianRotation)
    {
        RadianRotation = radianRotation;
    }

    //GetMethods
    public double GetRadianRotation()
    {
        return RadianRotation;
    }

    //SetMethods
    public void SetRadianRotation(double radianRotation)
    {
        RadianRotation = radianRotation;
    }

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VectorBase that = (VectorBase) o;

        return Double.compare(that.RadianRotation, RadianRotation) == 0;
    }

    @Override
    public int hashCode()
    {
        long temp = Double.doubleToLongBits(RadianRotation);
        return (int) (temp ^ (temp >>> 32));
    }
}

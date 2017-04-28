package Global.DataStructures;

import java.util.HashSet;
import java.util.UUID;

public class IdHashSet<T> extends HashSet<T>
{
    private UUID _id;

    public IdHashSet()
    {
        super();
        _id = UUID.randomUUID();
    }

    //Get Methods
    public UUID GetId()
    {
        return _id;
    }

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IdHashSet idHashSet = (IdHashSet) o;

        return _id.equals(idHashSet._id);

    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + _id.hashCode();
        return result;
    }
}

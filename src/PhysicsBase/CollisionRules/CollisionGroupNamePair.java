package PhysicsBase.CollisionRules;

public class CollisionGroupNamePair
{
    //Variables
    public String groupName1;
    public String groupName2;

    //Constructor
    public CollisionGroupNamePair(String grp1, String grp2)
    {
        groupName1 = grp1;
        groupName2 = grp2;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollisionGroupNamePair that = (CollisionGroupNamePair) o;

        //As long as the object contains the same two groups they're equal.
        //They can be assigned to either variable.
        if(this.groupName1 == that.groupName1 && this.groupName2 == that.groupName2) return true;
        if(this.groupName2 == that.groupName1 && this.groupName1 == that.groupName2) return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        int result = groupName1 != null ? groupName1.hashCode() : 0;
        result = result + (groupName2 != null ? groupName2.hashCode() : 0);
        return result;
    }

}

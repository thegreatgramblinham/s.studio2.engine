package PhysicsBase.CollisionRules;

import PhysicsBase.CollisionCollections.CollisionGroup;
import PhysicsBase.CollisionRules.enums.CollisionRule;

public class CollisionGroupNamePair
{
    //Variables
    public String group1;
    public String group2;

    //Constructor
    public CollisionGroupNamePair(String grp1, String grp2)
    {
        group1 = grp1;
        group2 = grp2;
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
        if(this.group1 == that.group1 && this.group2 == that.group2) return true;
        if(this.group2 == that.group1 && this.group1 == that.group2) return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        int result = group1 != null ? group1.hashCode() : 0;
        result = result + (group2 != null ? group2.hashCode() : 0);
        return result;
    }

}

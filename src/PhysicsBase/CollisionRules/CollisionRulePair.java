package PhysicsBase.CollisionRules;

import PhysicsBase.CollisionCollections.CollisionGroup;
import PhysicsBase.CollisionRules.enums.CollisionRule;

public class CollisionRulePair
{
    //Variables
    public CollisionGroup group1;
    public CollisionGroup group2;
    public CollisionRule rule;

    //Constructor
    public CollisionRulePair(CollisionGroup grp1, CollisionGroup grp2,
                             CollisionRule rule)
    {
        group1 = grp1;
        group2 = grp2;
        this.rule = rule;
    }

    //Get Methods

    //Set Methods

    //Public Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollisionRulePair that = (CollisionRulePair) o;

        if(this.rule != that.rule) return false;

        //As long as the object contains the same two groups they're equal.
        //They can be assigned to either variable.
        if(this.group1 == that.group1 && this.group2 == that.group2) return true;
        if(this.group2 == that.group1 && this.group1 == that.group2) return true;

        return false;
    }

    @Override
    public int hashCode() {
        int result = group1 != null ? group1.hashCode() : 0;
        result = 31 * result + (group2 != null ? group2.hashCode() : 0);
        result = 31 * result + (rule != null ? rule.hashCode() : 0);
        return result;
    }


    //Private Methods

}

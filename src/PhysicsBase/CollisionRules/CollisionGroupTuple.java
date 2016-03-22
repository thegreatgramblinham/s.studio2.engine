package PhysicsBase.CollisionRules;

import PhysicsBase.CollisionCollections.CollisionGroup;
import PhysicsBase.CollisionRules.enums.CollisionRule;

public class CollisionGroupTuple
{
    //Variables
    public CollisionGroup group1;
    public CollisionGroup group2;
    public CollisionRule rule;

    //Constructor
    public CollisionGroupTuple(CollisionGroup grp1, CollisionGroup grp2,
                          CollisionRule rule)
    {
        group1 = grp1;
        group2 = grp2;
        this.rule = rule;
    }

    //Get Methods

    //Set Methods

    //Public Methods

    //Private Methods

}

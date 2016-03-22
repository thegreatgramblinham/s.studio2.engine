package PhysicsBase.CollisionRules;

import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionCollections.CollisionGroup;
import PhysicsBase.CollisionRules.enums.CollisionRule;

import java.util.HashMap;

public class CollisionRuleManager
{
    //Private Fields
    private HashMap<CollisionGroupPair, CollisionRule> _groupToRules;
    private HashMap<GameWorldObject, CollisionGroup> _objectToGroup;

    //Variables

    //Constructor

    //Get Methods

    //Set Methods

    //Public Methods
    public void AddRule(CollisionGroupPair pair, CollisionRule rule)
    {
        _groupToRules.put(pair, rule);
    }

    //Private Methods
    private void Init()
    {
        _groupToRules = new HashMap<>();
        _objectToGroup = new HashMap<>();
    }

}

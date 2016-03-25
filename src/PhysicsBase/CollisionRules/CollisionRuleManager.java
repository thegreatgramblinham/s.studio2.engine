package PhysicsBase.CollisionRules;

import GameObjectBase.GameWorldObject;
import PhysicsBase.CollisionCollections.CollisionGroup;
import PhysicsBase.CollisionRules.enums.CollisionRule;

import java.util.HashMap;
import java.util.HashSet;

public class CollisionRuleManager
{
    //Private Fields
    private HashMap<CollisionGroupPair, CollisionRule> _groupToRules;
    private HashMap<GameWorldObject, CollisionGroup> _objectToGroup;

    //Variables

    //Constructor
    public CollisionRuleManager()
    {
        Init();
    }

    //Get Methods

    //Set Methods

    //Public Methods
    public void AddRule(CollisionGroupPair pair, CollisionRule rule)
    {
        _groupToRules.put(pair, rule);
    }

    public CollisionRule GetRule(CollisionGroupPair pair)
    {
        return _groupToRules.containsKey(pair)
                ? _groupToRules.get(pair)
                : null;
    }

    public void AddObject(GameWorldObject gObj, CollisionGroup group)
    {
        group.Add(gObj);
        if(_objectToGroup.containsKey(gObj))
        {
            _objectToGroup.remove(gObj);
            _objectToGroup.put(gObj, group);
        }
        else
        {
            _objectToGroup.put(gObj, group);
        }
    }

    public void RemoveObject(GameWorldObject gObj)
    {
        if(!_objectToGroup.containsKey(gObj)) return;

        _objectToGroup.get(gObj).Remove(gObj);

        _objectToGroup.remove(gObj);
    }

    //Private Methods
    private void Init()
    {
        _groupToRules = new HashMap<>();
        _objectToGroup = new HashMap<>();
    }

}

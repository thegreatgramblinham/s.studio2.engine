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
    private HashMap<String, CollisionGroup> _groupNameToGroup;

    //Variables

    //Constructor
    public CollisionRuleManager()
    {
        Init();
    }

    public CollisionRuleManager(
            HashMap<CollisionGroupNamePair, CollisionRule> collisionRules)
    {
        Init();

        if(collisionRules != null)
            for(CollisionGroupNamePair pair : collisionRules.keySet())
            {
                if(!_groupNameToGroup.containsKey(pair.groupName1))
                    _groupNameToGroup.put(
                            pair.groupName1, new CollisionGroup(pair.groupName1));
                if(!_groupNameToGroup.containsKey(pair.groupName2))
                    _groupNameToGroup.put(
                            pair.groupName2, new CollisionGroup(pair.groupName2));

                AddRule(new CollisionGroupPair(
                        _groupNameToGroup.get(pair.groupName1),
                        _groupNameToGroup.get(pair.groupName2)
                        ), collisionRules.get(pair));
            }
    }

    //Get Methods
    public CollisionRule GetRule(CollisionGroupPair pair)
    {
        return _groupToRules.containsKey(pair)
                ? _groupToRules.get(pair)
                : null;
    }

    public CollisionGroup GetGroup(GameWorldObject gObj)
    {
        return  _objectToGroup.containsKey(gObj)
                ? _objectToGroup.get(gObj)
                : null;
    }

    //Set Methods

    //Public Methods
    public void AddRule(CollisionGroupPair pair, CollisionRule rule)
    {
        _groupToRules.put(pair, rule);
    }

    public void AddObject(GameWorldObject gObj, String groupName)
    {
        CollisionGroup group;

        if(_groupNameToGroup.containsKey(groupName))
            group = _groupNameToGroup.get(groupName);
        else
            group = new CollisionGroup(groupName);

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
        _groupNameToGroup = new HashMap<>();
    }

}

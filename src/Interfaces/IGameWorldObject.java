package Interfaces;

import GameObjectBase.HitBox;
import Global.DPoint;
import PhysicsBase.Vectors.VelocityVector;

import java.awt.*;

public interface IGameWorldObject extends IGameObject
{
    //Get Methods
    HitBox GetHitBox();
    VelocityVector GetVelocity();
    String GetAlias();
    boolean GetIsImmobile();
    float GetMass();
    boolean GetCanCollide();
    boolean GetNeedsDeletion();
    DPoint DGetLocation();
    Point NGetLocation();

    //Set Methods
    void SetVelocity(VelocityVector velocity);
    void SetAlias(String alias);
    void SetIsImmobile(boolean immobile);
    void SetMass(float mass);
    void DSetLocation(double x, double y);
    void NSetLocation(Point p);
    void SetNeedsDeletion(boolean needsDeletion);

    //Public Methods
    void AccelerateBy(VelocityVector v);
    void ClearVelocity();
    void OnCollide(IGameWorldObject other);
    void OnDeletion();
}

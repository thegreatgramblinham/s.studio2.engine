package Global;

public class Tuple<T, V>
{
    //Variables
    private T _item1;
    private V _item2;

    //Constructor
    public Tuple(T item1, V item2)
    {
        _item1 = item1;
        _item2 = item2;
    }

    //Get Methods
    public T GetItem1()
    {
        return _item1;
    }

    public V GetItem2()
    {
        return _item2;
    }

    //Set Methods
    public void SetItem2(V _item2)
    {
        this._item2 = _item2;
    }

    public void SetItem1(T _item1)
    {
        this._item1 = _item1;
    }

}

package fr.herman.metatype.model;

import fr.herman.metatype.model.method.Getter;
import fr.herman.metatype.model.method.Setter;
import fr.herman.metatype.utils.Getters;

public class MetaPropertyGetterSetterTemplate<ROOT, CURRENT, VALUE> implements MetaPropertyGetterSetter<ROOT, CURRENT, VALUE>
{

    private final MetaClass<ROOT, ?, CURRENT> parent;
    private final Class<VALUE>                type;
    private final String                      name;
    private final Getter<CURRENT, VALUE>      getter;
    private final Setter<CURRENT, VALUE>      setter;

    public MetaPropertyGetterSetterTemplate(MetaClassTemplate<ROOT, ?, CURRENT> parent, Class<VALUE> type, String name, Getter<CURRENT, VALUE> getter, Setter<CURRENT, VALUE> setter)
    {
        this.parent = parent;
        this.type = type;
        this.name = name;
        this.getter = getter;
        this.setter = setter;
        parent.addProperty(this);
    }

    public <GS extends Getter<CURRENT, VALUE> & Setter<CURRENT, VALUE>> MetaPropertyGetterSetterTemplate(MetaClassTemplate<ROOT, ?, CURRENT> parent, Class<VALUE> type, String name, GS getterSetter)
    {
        this(parent, type, name, getterSetter, getterSetter);
    }

    @Override
    public Class<CURRENT> modelType()
    {
        return parent.type();
    }

    @Override
    public Class<VALUE> type()
    {
        return type;
    }

    @Override
    public Class<ROOT> rootType()
    {
        return parent.rootType();
    }

    @Override
    public String name()
    {
        return name;
    }

    @Override
    public Getter<ROOT, VALUE> getter()
    {
        return this;
    }

    @Override
    public VALUE getValue(ROOT o)
    {
        return Getters.get(parent, getter, o);
    }

    @Override
    public Setter<ROOT, VALUE> setter()
    {
        return this;
    }

    @Override
    public void setValue(ROOT object, VALUE value)
    {
        setter.setValue(parent.getValue(object), value);
    }

    @Override
    public boolean hasGetter()
    {
        return true;
    }

    @Override
    public boolean hasSetter()
    {
        return true;
    }

}

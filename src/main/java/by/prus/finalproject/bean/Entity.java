package by.prus.finalproject.bean;

import java.io.Serializable;

/**
 * Main Bean class. The parrent of all others class having just identity field and
 * equals/hashcode methods that can be used by all others entities.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public abstract class Entity implements Serializable {

    private Integer identity;

    public Integer getIdentity() {
        return identity;
    }
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object object) {
        if(object != null) {
            if(object != this) {
                if(object.getClass() == getClass() && identity != null) {
                    return identity.equals(((Entity)object).identity);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return identity != null ? identity.hashCode() : 0;
    }

}

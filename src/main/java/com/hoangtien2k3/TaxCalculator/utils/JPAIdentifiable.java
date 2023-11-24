package com.hoangtien2k3.TaxCalculator.utils;

import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class JPAIdentifiable<I> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1824201581188983295L;

    public abstract I getId();

    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getEffectiveClass(other) != getEffectiveClass(this)) {
            return false;
        }

        try {
            return other instanceof JPAIdentifiable<?> i && Objects.equals(getId(), i.getId());
        } catch (ClassCastException exception) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getEffectiveClass(this).hashCode();
    }

    private static Class<?> getEffectiveClass(Object object) {
        return object instanceof HibernateProxy hibernateProxy
                ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass()
                : object.getClass();
    }

}

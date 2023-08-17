package org.example.core;

import java.io.Serializable;

@FunctionalInterface
public interface Callback<T> extends Serializable {

    static final long serialVersionUID = 1L;
    public void callback(T obj);
}

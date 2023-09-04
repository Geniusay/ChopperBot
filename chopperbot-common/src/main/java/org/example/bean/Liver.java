package org.example.bean;

import java.io.Serializable;

/**
 * @author Genius
 * @date 2023/08/17 22:16
 **/
public abstract class Liver implements Serializable {
    private String liverName;
    private String liverId;

    public Liver(String liverName, String liverId) {
        this.liverName = liverName;
        this.liverId = liverId;
    }
}

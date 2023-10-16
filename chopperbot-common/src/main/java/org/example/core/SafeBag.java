package org.example.core;

/**
 * @author Genius
 * @date 2023/10/17 01:30
 **/
public class SafeBag<T> {
    T data;

    public SafeBag(T data) {
        this.data = data;
    }

    public SafeBag() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data){
        this.data = data;
    }
}

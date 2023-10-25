package org.example.core.auto;

import java.util.Map;

public interface AutoGenerator <T>{

    void preGenerate();
    T generate(Map<String,Object> data);
}

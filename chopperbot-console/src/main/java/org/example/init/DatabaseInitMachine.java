package org.example.init;

import lombok.NoArgsConstructor;
import org.example.sql.SQLInitMachine;
import org.example.sql.annotation.SQLInit;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Genius
 * @date 2023/10/20 01:10
 **/
@Component
@NoArgsConstructor
public class DatabaseInitMachine implements SQLInitMachine{

    @SQLInit(isTable = false)
    public List<?> sqlInit() {
        return null;
    }
}

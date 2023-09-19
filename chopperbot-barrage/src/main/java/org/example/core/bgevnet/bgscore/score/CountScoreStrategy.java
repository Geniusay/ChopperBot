package org.example.core.bgevnet.bgscore.score;

import java.util.List;

/**
 * @author Genius
 * @date 2023/09/13 23:57
 **/
public class CountScoreStrategy extends AbstractScoreStrategy{

    @Override
    public int score(List<String> list) {
        return list.size();
    }


}

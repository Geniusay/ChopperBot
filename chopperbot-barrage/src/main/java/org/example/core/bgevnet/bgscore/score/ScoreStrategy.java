package org.example.core.bgevnet.bgscore.score;

import org.example.pojo.Barrage;

import java.util.List;

public interface ScoreStrategy {
    int score(List<String> list);
}

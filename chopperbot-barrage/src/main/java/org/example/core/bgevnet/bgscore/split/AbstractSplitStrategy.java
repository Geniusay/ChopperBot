package org.example.core.bgevnet.bgscore.split;

import lombok.Data;
import org.example.bean.Barrage;
import org.example.bean.LiverKeyword;
import org.example.core.bgevnet.bgscore.score.ScoreStrategy;

import java.util.List;
import java.util.Map;

/**
 * @author Genius
 * @date 2023/09/13 22:30
 **/
@Data
public abstract class AbstractSplitStrategy implements SplitStrategy {

    protected ScoreStrategy scoreStrategy;

    protected List<Barrage> barrages;

    protected Map<String, LiverKeyword> liverKeywordMap;

    protected long duration;

    public AbstractSplitStrategy(ScoreStrategy scoreStrategy, List<Barrage> barrages, long duration) {
        this.scoreStrategy = scoreStrategy;
        this.barrages = barrages;
        this.duration = duration;
    }

}

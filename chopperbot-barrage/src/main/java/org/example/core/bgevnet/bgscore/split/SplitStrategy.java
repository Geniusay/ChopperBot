package org.example.core.bgevnet.bgscore.split;

import org.example.core.bgevnet.bgscore.BarragePoint;

import java.util.List;

public interface SplitStrategy {

    List<BarragePoint> split();
}

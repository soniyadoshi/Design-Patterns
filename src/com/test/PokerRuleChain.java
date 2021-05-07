package com.test;

import java.util.List;
import java.util.Map;

public interface PokerRuleChain {

    void setNextRule(PokerRuleChain chain);
    void findWinner(Map<Integer, List<Card>> playerDetails);

}

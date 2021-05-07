package com.test;

import java.util.List;
import java.util.Map;

public class PokerRuleDecider {

    private PokerRuleChain rule1;

    public PokerRuleDecider() {

        this.rule1 = new StraightFlushHand();
        PokerRuleChain rule2 = new ThreeOfAKindHand();
        PokerRuleChain rule3 = new StraightHand();
        PokerRuleChain rule4 = new FlushHand();
        PokerRuleChain rule5 = new PairHand();
        PokerRuleChain rule6 = new HighCardHand();

        this.rule1.setNextRule(rule2);
        rule2.setNextRule(rule3);
        rule3.setNextRule(rule4);
        rule4.setNextRule(rule5);
        rule5.setNextRule(rule6);
    }

    public void findWinner (Map<Integer, List<Card>> playerDetails) {
        rule1.findWinner(playerDetails);
    }
}

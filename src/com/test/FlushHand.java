package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlushHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("FlushHand");

        Map<Integer, List<Card>> result = getFlush(playerDetails);

        if (result.size() == 0) {

            this.rule.findWinner(playerDetails);

        } else if (result.size() > 1) {

            new HighCardHand().findWinner(result);

        } else {
            result.forEach((k,v) -> {
                System.out.println(k);
            });
        }

    }

    private Map<Integer, List<Card>> getFlush(Map<Integer, List<Card>> playerDetails) {

        Map<Integer, List<Card>> result = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            if (isFlush(cards)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private boolean isFlush(List<Card> cards) {

        for (int i =0; i<cards.size() -1 ; i++) {
            if (!cards.get(i).getSuit().equalsIgnoreCase(cards.get(i+1).getSuit())) {
                return false;
            }
        }
        return true;
    }
}

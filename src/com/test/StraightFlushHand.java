package com.test;

import java.util.*;

public class StraightFlushHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("StraightFlushHand");

        Map<Integer, List<Card>> result = getStraightFlush(playerDetails);

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

    private Map<Integer, List<Card>> getStraightFlush(Map<Integer, List<Card>> playerDetails) {

        Map<Integer, List<Card>> result = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            if (isStraightFlush(cards)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        if (result.size() > 0) {
            result = new StraightHand().getStraightHand(result);
        }

        return result;
    }

    private boolean isStraightFlush(List<Card> cards) {

        for (int i =0; i<cards.size() -1 ; i++) {
            if (!cards.get(i).getSuit().equalsIgnoreCase(cards.get(i+1).getSuit())) {
                return false;
            }
        }
        return true;
    }
}

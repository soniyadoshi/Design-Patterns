package com.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StraightHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("StraightHand");

        Map<Integer, List<Card>> result = getStraightHand(playerDetails);

        if (result.size() == 0) {

            this.rule.findWinner(playerDetails);

        } else if (result.size() > 1) {

            new HighCardHand().findWinner(result);

        } else {

            result.forEach((k, v) -> {
                System.out.println(k);
            });
        }
    }

    public Map<Integer, List<Card>> getStraightHand (Map<Integer, List<Card>> playerDetails) {

        Map<Integer, List<Card>> result = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            Collections.sort(cards);

            int ace = cards.get(cards.size()-1).getRank();

            if (ace == 14 && isStraightHandForA(cards)) {

                result.put(entry.getKey(), entry.getValue());

            } else if (isStraightHand(cards)) {

                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private boolean isStraightHandForA (List<Card> cards) {

        int diff1 = cards.get(cards.size()-1).getRank() - cards.get(cards.size()-2).getRank();
        int diff2 = cards.get(cards.size()-2).getRank() - cards.get(cards.size()-3).getRank();

        if (diff2 == 1 && diff1 == 11) {
            return true;
        }
        return false;
    }

    private boolean isStraightHand (List<Card> cards) {

        for (int i= 0; i<cards.size() -1 ; i++) {

            if (cards.get(i+1).getRank() - cards.get(i).getRank() != 1) {
                return false;
            }
        }
        return true;
    }
}

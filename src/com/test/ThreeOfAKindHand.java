package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreeOfAKindHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("ThreeOfAKindHand");

        Map<Integer, List<Card>> result = getThreeOfAkind(playerDetails);

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

    private Map<Integer, List<Card>> getThreeOfAkind(Map<Integer, List<Card>> playerDetails) {

        Map<Integer, List<Card>> result = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            if (isThreeOfAKind(cards)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private boolean isThreeOfAKind(List<Card> cards) {

        for (int i =0; i<cards.size() -1 ; i++) {
            if (cards.get(i).getRank() != (cards.get(i+1).getRank())) {
                return false;
            }
        }
        return true;
    }
}

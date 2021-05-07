package com.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("PairHand");

        Map<Integer, List<Card>> result = getPair(playerDetails);

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

    private Map<Integer, List<Card>> getPair(Map<Integer, List<Card>> playerDetails) {

        Map<Integer, List<Card>> result = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            if (isPair(cards)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private boolean isPair(List<Card> cards) {

        Collections.sort(cards);

        int cnt = 0;

        for (int i =0; i<cards.size() -1; i++) {

            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                cnt++;
            }
        }
        return cnt == 1 ? true : false;
    }
}

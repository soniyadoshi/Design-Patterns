package com.test;

import java.util.*;

public class HighCardHand implements PokerRuleChain {

    private PokerRuleChain rule;

    @Override
    public void setNextRule(PokerRuleChain nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void findWinner(Map<Integer, List<Card>> playerDetails) {

        System.out.println("HighCardHand");

        findHighCard(playerDetails);

    }

    private void findHighCard (Map<Integer, List<Card>> playerDetails) {



        int max = getMax(playerDetails, 0);
        Map<Integer, Card> maxMap = getMaxMap(playerDetails, max);

        if (maxMap.size() > 1) {
            max = getMax(playerDetails, 1);
            maxMap = getMaxMap(playerDetails, max);
        }

        if (maxMap.size() > 1) {
            max = getMax(playerDetails, 2);
            maxMap = getMaxMap(playerDetails, max);
        }

        maxMap.forEach((k, v) -> {
            System.out.print(k + " ");
        });

    }

    private Map<Integer, Card> getMaxMap (Map<Integer, List<Card>> playerDetails, int max) {

        Map<Integer, Card> maxMap = new HashMap<>();

        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            for (Card c : cards) {
                if (c.getRank() == max) {
                    maxMap.put(entry.getKey(), c);
                }
            }
        }
        return maxMap;
    }

    private int getMax (Map<Integer, List<Card>> playerDetails, int i) {

        int max = 0;
        for (Map.Entry<Integer, List<Card>> entry : playerDetails.entrySet()) {

            List<Card> cards = entry.getValue();

            Collections.reverse(cards);

            if (cards.get(i).getRank() > max) {
                max = cards.get(i).getRank();
            }

        }

        return max;
    }




}

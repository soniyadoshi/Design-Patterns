package com.test;

import java.util.*;

public class PokerGame {

    static List<Character> suitList = new ArrayList<>();

    static Map<Character, Integer> rankMap = new HashMap<>();

    static {
        suitList.add('h');
        suitList.add('d');
        suitList.add('s');
        suitList.add('c');

        rankMap.put('T', 10);
        rankMap.put('J', 11);
        rankMap.put('Q', 12);
        rankMap.put('K', 13);
        rankMap.put('A', 14);
        rankMap.put('2', 2);
        rankMap.put('3', 3);
        rankMap.put('4', 4);
        rankMap.put('5', 5);
        rankMap.put('6', 6);
        rankMap.put('7', 7);
        rankMap.put('8', 8);
        rankMap.put('9', 9);


    }

    public static void main (String args[]) {

        Map<Integer, List<Card>> playerCards = buildStructure();

        PokerRuleDecider decider = new PokerRuleDecider();
        decider.findWinner(playerCards);

    }

    public static Map<Integer, List<Card>> buildStructure () {

        Scanner scanner = new Scanner(System.in);

        int players = scanner.nextInt();

        Map<Integer, List<Card>> playerCards = new HashMap<>();


        for (int i=1; i<=players; i++) {

            int playerId = scanner.nextInt();

            String c1 = scanner.next();

            String c2 = scanner.next();

            String c3 = scanner.next();

            playerCards.put(playerId, new ArrayList<>());

            Card card1 = new Card(getRank(c1.charAt(0)), getSuits(c1.charAt(1)));
            playerCards.get(playerId).add(card1);

            Card card2 = new Card(getRank(c2.charAt(0)), getSuits(c2.charAt(1)));
            playerCards.get(playerId).add(card2);

            Card card3 = new Card(getRank(c3.charAt(0)), getSuits(c3.charAt(1)));
            playerCards.get(playerId).add(card3);
        }

        playerCards.forEach((k,v) -> {
            System.out.println(k + " " + v.toString());
        });


        return playerCards;
    }

    public static int getRank (char c) {

        if (rankMap.containsKey(c)) {
            return rankMap.get(c);
        }
        return -1;
    }

    public static String getSuits (char c) {
        if (suitList.contains(c)) {
            return String.valueOf(c);
        }
        return "";
    }
}


import com.sun.jdi.IntegerValue;

import java.util.Arrays;
import java.util.*;
import java.util.Collections;
import java.io.*;




public class Game {

    public static boolean gameOver = false;
  public  static int moneyInPot = 0;
  public static List<String> card = new ArrayList<>(List.of("2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD"));




        // shuffle deck
        public static void shuffle(List<String> deck)
        {
            Collections.shuffle(deck);

        }

        // waits for a certain period of time
    public static void waitfr(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception e)
        {
        }
    }


    public static String pick(List<String> c ){
        String temp = c.get(0);
        discard(c.get(0), c);
        return temp;
    }

    public static void discard(String chosenCard, List<String> listOfCards){
            listOfCards.remove(chosenCard);

    }

    public static boolean isTrio(List<String> cards){
            String temp = cards.get(0);
            for(int i = 0; i < cards.size(); i++){
                String t = cards.get(i);
                if(cards.get(i).charAt(0) == temp.charAt(0)){
                    temp = cards.get(i);
                }
                else{
                    return false;
                }
            }
            return true;
    }


    public static boolean sameSuit(List <String> cards){
        String temp = cards.get(0);
        for(int i = 0; i < cards.size(); i++){
            String t = cards.get(i);
            if(cards.get(i).charAt(1) == temp.charAt(1)){
                temp = cards.get(i);
            }
            else{
                return false;
            }
        }
        return true;
    }

    public static boolean pair(List <String> cards) {
        String temp = cards.get(0);
        for (int i = 0; i < cards.size(); i++) {
            String t = cards.get(i);
            if (cards.get(i).charAt(0) == temp.charAt(0)) {
                return true;
            } else {
                temp = cards.get(i);
            }
        }
        return false;
    }

    public static boolean sequence(List <String> cards) {
        String temp = cards.get(0);
        for (int i = 0; i < cards.size(); i++) {
            String t = cards.get(i);
            if (Integer.valueOf(cards.get(i).charAt(0)) - Integer.valueOf(temp.charAt(0)) == 1 || Integer.valueOf(cards.get(i).charAt(0)) - Integer.valueOf(temp.charAt(0)) == -1) {
                return true;
            } else {
                temp = cards.get(i);

            }
        }
        if(sequencePicCard(cards)){
            return sequencePicCard(cards);
        }
        return false;
    }

    public static boolean sequencePicCard(List<String> cards){
        String temp = cards.get(0);
        int x = 0;
        for(int i = 0 ; i < cards.size(); i++){
            if(Character.isDigit((cards.get(i).charAt(0)))) {
                if (Integer.valueOf(card.get(i).charAt(0)) == 10) {
                    x += 0;

                }
            } else if (card.get(i).equals("J") || card.get(i).equals("Q")) {
                    x += 1;
                }

        } if(x==3){
            return true;



        } for(int i = 0 ; i < cards.size(); i++){
            if(card.get(i).substring(0,0).getClass().equals(String.class)) {

                if (card.get(i).substring(0, 0).equals("J") || card.get(i).substring(0, 0).equals("Q") || card.get(i).substring(0, 0).equals("K")) {
                    return true;
                }
            }
        } for (int j = 0; j < cards.size(); j++) {
            if(card.get(j).substring(0,0).getClass().equals(String.class)) {
                if (card.get(j).substring(0, 0).equals("A") || card.get(j).substring(0, 0).equals("Q") || card.get(j).substring(0, 0).equals("K")) {
                    return true;
                }
            }
            }
            return false;
    }

    public static int checkAceCard(List<String> cards, List<String> other){

        String temp = cards.get(0);
        String temp2 = other.get(0);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).charAt(0) == 'A' && other.get(i).charAt(0) != 'A') {
                return 1;
            } else if(cards.get(i).charAt(0) != 'A' && other.get(i).charAt(0) == 'A'){
               return 2;
            }
            temp = cards.get(i);
            temp2 = other.get(i);
        }
        return 0;
    }

    public static int checkKing(List<String> cards, List<String> other){
        String temp = cards.get(0);
        String temp2 = other.get(0);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).charAt(0) == 'K' && other.get(i).charAt(0) != 'K') {
                return 1;
            } else  if((cards.get(i).charAt(0) != 'K' && other.get(i).charAt(0) == 'K')){
                return 2;
            }
            temp = cards.get(i);
            temp2 = other.get(i);
        }
        return 0;
    }

    public static int checkQueen(List<String> cards, List<String> other){
        String temp = cards.get(0);
        String temp2 = other.get(0);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).charAt(0) == 'Q' && other.get(i).charAt(0) != 'Q') {
                return 1;
            } else if(cards.get(i).charAt(0) != 'Q' && other.get(i).charAt(0) == 'Q'){
                return 2;
            }
            temp = cards.get(i);
            temp2 = other.get(i);
        }
        return 0;
    }

    public static int checkJack(List<String> cards, List<String> other){
        String temp = cards.get(0);
        String temp2 = other.get(0);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).charAt(0) == 'J' && other.get(i).charAt(0) != 'J') {
                return 1;
            } else if(cards.get(i).charAt(0) != 'J' && other.get(i).charAt(0) == 'J'){
                return 2;
            }
            temp = cards.get(i);
            temp2 = other.get(i);
        }
        return 0;
    }

    public static int checkH(List<String> cards) {
        int temp = Integer.valueOf(cards.get(0));

        for (int i = 0; i < cards.size()-1; i++) {
            if (temp > Integer.valueOf(cards.get(i + 1))) {
                temp = Integer.valueOf(cards.get(i));
            }
            else{
                temp = Integer.valueOf(cards.get(i+1));
            }
        }
        return temp;
    }

    public static String checkHighCard(List<String> cards, List<String> other){
            if(checkAceCard(cards,other) == 1){
                return "User has Ace! High card wins!";

            } else if (checkAceCard(cards,other) == 2) {
                return "CPU has Ace! High card wins!";

            } else if (checkKing(cards,other) == 1) {
                return "User has King! High card wins!";

            } else if (checkKing(cards,other) == 2) {
                return "CPU has King! High card wins!";

            } else if (checkQueen(cards,other) == 1) {
                return "User has Queen! High card wins!";

            } else if (checkQueen(cards,other) == 2) {
                return "CPU has Queen! High card wins!";

            } else if (checkJack(cards,other) == 1) {
                return "User has Jack! High card wins!";

            } else if (checkJack(cards,other) == 2) {
                return "CPU has Jack! High card wins!";

            } else if (checkH(cards)> checkH(other)) {
                return "High card wins!";

            } else {
                return "High card wins!";
            }
    }




    public static String compare(List<String> usercards, List<String> CPUcards){

            if(isTrio(usercards) && !isTrio(CPUcards)){
                return "User wins";

            } else if(isTrio(CPUcards) && !isTrio(usercards)){
                return "CPU wins";

            } else if (sequence(usercards) && !sequence(CPUcards)) {
                return "User wins";

            } else if (sequence(CPUcards) && !sequence(usercards)) {
                return "CPU wins";

            } else if (sameSuit(usercards) && !sameSuit(CPUcards)) {
                return "User wins";

            } else if (sameSuit(CPUcards) && !sameSuit(usercards)) {
                return "CPU wins";

            } else if (pair(usercards) && pair(CPUcards)) {
                return "User wins";

            } else if (pair(CPUcards) && !pair(usercards)) {
                return "CPU wins";

            } else{
                return checkHighCard(usercards, CPUcards);
            }
    }




    public class User{

           public static int moneyInHand = 1000;
           public static int currBet = 100;

           public static List<String> cardInHand = new ArrayList<>();


    }


    public class CPU{
        public static List<String> CPUcards = new ArrayList<>();

        public static int CPUmoney = 1000;

        public static int decsion(List<String> cards){

            List<String> temp = new ArrayList<>();


            if(isTrio(cards)){
                return 3;

            } else if(sequence(cards)){
                return 3;

            } else if (sameSuit(cards) || pair(cards)) {
                return 2;

            } else if (checkAceCard(cards, temp) == 1 || checkKing(cards, temp) == 1){
                return 1;
            }
            else{
                return 0;
            }



        }
    }




}

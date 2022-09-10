import java.io.*;


class Main {
    public static InputStreamReader x = new InputStreamReader(System.in);
    public static BufferedReader y = new BufferedReader(x);




    public static void main(String[] args) throws NumberFormatException, IOException {

        System.out.println("Welcome to Teenpatti!!!");
        System.out.println("Do you want to play? Y/N");
        char choicee = (char) y.read();

        if (choicee == 'Y' || choicee == 'y') {

            System.out.println("Let's play!!!");

            System.out.println("You currently have" + " " + "$" + Game.User.moneyInHand);

            System.out.println("Shuffling cards...");

            Game.shuffle(Game.card);
            Game.waitfr(500);

            System.out.println("Handing out the cards....");
            Game.waitfr(500);

            //Picks a carfd and adds it to the current hand
            Game.User.cardInHand.add(Game.pick(Game.card));
            //Prints out card 1
            System.out.println("You have received a card:" + Game.User.cardInHand.get(0));
            Game.waitfr(1000);
            //CPU picks card
            Game.CPU.CPUcards.add(Game.pick(Game.card));
            System.out.println("The computer receives a card:# ");
            //Pick and print card 2
            Game.User.cardInHand.add(Game.pick(Game.card));
            Game.waitfr(1000);
            System.out.println("You have received a card:" + Game.User.cardInHand.get(1));
            Game.waitfr(1000);
            //CPU picks card 2
            Game.CPU.CPUcards.add(Game.pick(Game.card));
            System.out.println("The computer receives a card:# # ");
            Game.waitfr(1000);
            //Pick and print card 3
            Game.User.cardInHand.add(Game.pick(Game.card));
            System.out.println("You have recieved a card :" + Game.User.cardInHand.get(2));
            Game.waitfr(1000);
            //CPU card and pick 3
            Game.CPU.CPUcards.add(Game.pick(Game.card));
            System.out.println("The computer receives a card:# # # ");
            Game.waitfr(1000);


            System.out.println("Your cards are:" + Game.User.cardInHand.get(0) + " " + Game.User.cardInHand.get(1) + " " + Game.User.cardInHand.get(2));

            Game.waitfr(1000);
            Game.waitfr(300);

            System.out.println("Let's begin... You can choose to bet, raise or fold!!!");
            System.out.println("Round 1");
            Game.waitfr(300);
            System.out.println("The current bet is" + " " + "$" + Game.User.currBet);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What do you want to do?");
            System.out.println("1. Bet\n2. Raise\n3. Show\n4. Fold\n5. Pick");
            String str = "";


            while (true) {

                try {
                     str = br.readLine();
                }
                catch(Exception e){
                        System.out.println("Command not found!");
                    }

                    //Bet
                    if (str.equals("Bet") || str.equals("bet")) {
                        if (Game.User.moneyInHand >= Game.User.currBet) {
                            Game.User.moneyInHand -= Game.User.currBet;
                            Game.moneyInPot += Game.User.currBet;

                        }

                        // CPU makes descision
                        Game.waitfr(300);

                        if (Game.CPU.decsion(Game.CPU.CPUcards) == 3) {
                            Game.CPU.CPUmoney -= Game.User.currBet + 200;
                            Game.User.currBet = Game.User.currBet + 200;
                            Game.moneyInPot += Game.User.currBet + 200;
                            System.out.println("CPU raises by 200!");
                            Game.waitfr(300);
                            System.out.println("What do you want to do next?");

                        } else if (Game.CPU.decsion((Game.CPU.CPUcards)) == 2 || Game.CPU.decsion(Game.CPU.CPUcards) == 1) {
                            Game.CPU.CPUmoney -= Game.User.currBet;
                            Game.moneyInPot += Game.User.currBet;
                            System.out.println("CPU has placed a bet!");
                            Game.waitfr(300);
                            System.out.println("What do you want to do next?");

                        } else {
                            System.out.println("User folds! You win!!");
                        }


                        //Raise
                    } else if ((str.equals("Raise") || str.equals("raise"))) {
                        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Raise by what amount?");
                        String st = br.readLine();
                        if (Integer.valueOf(st) == Game.User.moneyInHand) {
                            System.out.println("You're all in!");

                        }
                        if (Integer.valueOf(st) > Game.User.moneyInHand) {
                            System.out.println("Not enough money!!!");

                        } else if (str.equals("Show") || str.equals("show")) {
                            Game.compare(Game.User.cardInHand, Game.CPU.CPUcards);

                        } else {
                            Game.User.moneyInHand -= Game.User.currBet + Integer.valueOf(st);
                            System.out.println("You currently have  " + "$" + Game.User.moneyInHand);
                            Game.User.currBet = Game.User.currBet + Integer.valueOf(st);
                            Game.moneyInPot += Game.User.currBet + Integer.valueOf(st);
                        }
                        // CPU makes descision
                        Game.waitfr(300);

                        if (Game.CPU.decsion(Game.CPU.CPUcards) == 3) {
                            Game.CPU.CPUmoney -= Game.User.currBet + 200;
                            Game.User.currBet = Game.User.currBet + 200;
                            Game.moneyInPot += Game.User.currBet + 200;
                            System.out.println("CPU raises by 200!");
                            Game.waitfr(300);
                            System.out.println("What do you want to do next?");

                        } else if (Game.CPU.decsion((Game.CPU.CPUcards)) == 2 || Game.CPU.decsion(Game.CPU.CPUcards) == 1) {
                            Game.CPU.CPUmoney -= Game.User.currBet;
                            Game.moneyInPot += Game.User.currBet;
                            System.out.println("CPU has placed a bet!");
                            Game.waitfr(300);
                            System.out.println("What do you want to do next?");

                        } else {
                            System.out.println("User folds! You win!!");
                            Game.waitfr(300);
                            System.out.println("What do you want to do next?");
                        }

                    }
                    //Pick
                    else if (str.equals("Pick") || str.equals("pick")) {
                        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Which card would you like to discard?");
                        String st = br.readLine();
                        //checks if card is there then discards from user hand and picks a new one
                        for (int i = 0; i < Game.User.cardInHand.size(); i++) {
                            if (!st.equals(Game.User.cardInHand.get(i))) {
                                System.out.println("Card not in current hand, Please choose the correct card!");
                            } else {
                                Game.discard(Game.User.cardInHand.get(i), Game.User.cardInHand);
                                String c = Game.pick(Game.card);
                                Game.User.cardInHand.add(c);
                                System.out.println(c);

                            }
                        }

                    }
                    else if(str.equals("Show") || str.equals("show")){
                        System.out.println(Game.compare(Game.User.cardInHand, Game.CPU.CPUcards) + " " + Game.moneyInPot );
                        Game.waitfr(300);
                        System.out.println("Your cards were:" + Game.User.cardInHand);
                        Game.waitfr(300);
                        System.out.println("CPU cards were:" + Game.CPU.CPUcards);
                        System.exit(0);
                    }

                    else{
                        System.out.println("Command not identified, please enter the commands above!");

                    }

                }

                }
            }

        }




















package gameEvents;

import character.Character;
import character.races.Race;
import character.types.*;
import potions.*;
import weapons.*;

import java.util.Scanner;

public final class GameEvent {

    private static final String ANSI_RESET  = "\u001B[0m";
    private static final String ANSI_GREEN  = "\u001B[32m";

    private static final String pattern = "Choose action\n";

    // all of the characters interacting with
    // the main character in the game
    // ------------------------------------------------------------------------------
    private static final Character lydia = new Character("Lydia", Race.BRETON, new Warrior());
    private static final Character ralaf = new Character("Ralaf", Race.VIKING, new Warrior());
    private static final Character harold = new Character("Harold", Race.BRETON, new Assassin());
    private static final Character vilas = new Character("Vilas", Race.ORK, new Warrior());
    // ------------------------------------------------------------------------------

    // Creating instances of this class
    // is not allowed
    private GameEvent() { }

    public static void generate(String task1, String task2, String task3, Character character) {
        // equipping characters
        lydia.getType().setWeapon(new Sword());
        harold.getType().setWeapon(new Sword());
        vilas.getType().setWeapon(new Bow());

        System.out.println(pattern + "(1) " + task1
                + "\n(2) " + task2
                + "\n(3) " + task3);

        String action = getAction();

        initEvents(action, task1, task2, task3, character);
    }

    private static void initEvents(String action, String task1, String task2,
                                   String task3, Character character) {
        if (task1.equalsIgnoreCase("walk outside the house")
                && task2.equalsIgnoreCase("talk to Lydia")
                && task3.equalsIgnoreCase("eat breakfast")) {
            event1(action, character);
        } else if (task1.equalsIgnoreCase("talk to Ralaf")
                && task2.equalsIgnoreCase("travel to the forest")
                && task3.equalsIgnoreCase("travel to Whiterun")) {
            event2(action, character);
        } else if (task1.equalsIgnoreCase("fight")
                && task2.equalsIgnoreCase("talk")
                && task3.equalsIgnoreCase("stealth kill")) {
            event3(action, character);
        } else if (task1.equalsIgnoreCase("fight")
                && task2.equalsIgnoreCase("run")
                && task3.equalsIgnoreCase("hide")) {
            event4(action, character);
        } else if (task1.equalsIgnoreCase("travel to Solitude with Lydia")
                && task2.equalsIgnoreCase("buy potions at the Riverwood store")
                && task3.equalsIgnoreCase("fight Lydia")) {
            event5(action, character);
        }
    }

    // methods for displaying game events
    // ------------------------------------------------------------------------------
    private static void event1(String action, Character character) {
        switch (action) {
            case "1":
                character.walk("the outside of his house");
                generate("talk to Ralaf", "travel to the forest", "travel to Whiterun", character);
                break;
            case "2":
                character.talkTo(lydia);
                if (character.isEscorted())
                    generate("travel to Solitude with Lydia", "buy potions at the Riverwood store", "fight Lydia", character);
                break;
            case "3":
                character.eat("breakfast");
                break;
        }
    }

    private static void event2(String action, Character character) {
        switch (action) {
            case "1":
                character.talkTo(ralaf);
                if (character.isTaskAccepted()) {
                    System.out.println("Prepare to confront Harold...");
                    generate("fight", "talk", "stealth kill", character);
                }
                break;
            case "2":
                character.walk("the forest");
                System.out.println("There's bandit " + vilas.getName() + " approaching your way...");
                generate("fight", "run", "hide", character);
                break;
            case "3":
                character.walk("Whiterun");
                break;
        }
    }

    private static void event3(String action, Character character) {
        switch (action) {
            case "1":
                fight(character, harold);

                if (harold.isDead())
                    System.out.println(ANSI_GREEN + "Quest completed!\n" + ANSI_RESET);

                if (!character.isDead())
                    usePotion(new HealthPotion(), character);
                break;
            case "2":
                character.talkTo(harold);
                if (character.isTaskAccepted()) {
                    fight(character, harold);

                    if (harold.isDead())
                        System.out.println(ANSI_GREEN + "Quest completed!\n" + ANSI_RESET);

                    if (!character.isDead())
                        usePotion(new HealthPotion(), character);
                }
                break;
            case "3":
                character.getType().getWeapon().stealthKill(harold);
                System.out.println(ANSI_GREEN + "Quest completed!\n" + ANSI_RESET);
                break;
        }
    }

    private static void event4(String action, Character character) {
        switch (action) {
            case "1":
                fight(character, vilas);
                if (!character.isDead())
                    usePotion(new HealthPotion(), character);
                break;
            case "2":
                character.run();
                break;
            case "3":
                System.out.println(vilas.getName() + " can't see you.");
                break;
        }
    }

    private static void event5(String action, Character character) {
        switch (action) {
            case "1":
                character.walk("Solitude");
                break;
            case "2":
                System.out.println("Merchant: Welcome to the Riverwood store!");
                choosePotions(character);
                break;
            case "3":
                fight(character, lydia);

                if (!character.isDead())
                    usePotion(new HealthPotion(), character);
                break;
        }
    }
    // ------------------------------------------------------------------------------

    private static void choosePotions(Character character) {
        String potion = getPotion();
        int count = getCount();
        Potion p;

        switch (potion.toLowerCase()) {
            case "h":
                p = new HealthPotion();
                character.buy(p, count);
                usePotion(p, character);
                break;
            case "m":
                p = new MagicPotion();
                character.buy(p, count);
                usePotion(p, character);
                break;
            case "a":
                p = new AgilityPotion();
                character.buy(p, count);
                usePotion(p, character);
                break;
        }
    }

    private static void usePotion(Potion potion, Character character) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Use potion? (y/n) ");
        String answer = sc.next();

        if (answer.equalsIgnoreCase("y")) {
            if (potion != null)
                potion.use(character);
        }

    }

    private static void fight(Character character1, Character character2) {
        while (!character1.isDead() && !character2.isDead()) {
            if (character1.getType().getHealthLevel() > 0)
                character1.attack(character2);

            if (character2.getType().getHealthLevel() > 0)
                character2.attack(character1);
        }
    }

    private static String getAction() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Action: ");
        String action = sc.next();

        if (action.equals("1")
                || action.equals("2")
                || action.equals("3")) {
            return action;
        } else {
            System.out.println("There's no such option in the game");
            return getAction();
        }
    }

    private static String getPotion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Choose potion to buy (health(h) / magic(m) / agility(a)): ");
        String potion = sc.next();

        if (potion.equalsIgnoreCase("h")
                || potion.equalsIgnoreCase("m")
                || potion.equalsIgnoreCase("a"))
            return potion;
        else {
            System.out.println("Non existent potion");
            return getPotion();
        }
    }

    private static int getCount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many potions would you like to buy? (from 1 to 10) ");
        int number;

        if (sc.hasNextInt()) {
            number = sc.nextInt();

            if (number > 0 && number <= 10)
                return number;
            else {
                System.out.println("You can't buy more than 10 and you have to buy at least 1");
                return getCount();
            }
        } else {
            System.out.println("Please, enter a number");
            return getCount();
        }
    }
}

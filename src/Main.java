import character.Character;
import character.races.Race;
import character.types.*;
import gameEvents.GameEvent;
import weapons.*;

import java.util.Scanner;

public class Main {

    private static final String ANSI_RESET  = "\u001B[0m";
    private static final String ANSI_GREEN  = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    private static Character character;

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN + "~~Elder Scrolls - The Game~~\n" + ANSI_RESET);
        String answer;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Start game? (y/n) ");
            answer = sc.next();

            if (answer.equalsIgnoreCase("y"))
                startGame();
        } while (!answer.equalsIgnoreCase("n"));
    }

    private static void startGame() {
        String name, race, type;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your character's name: ");
        name = sc.next();
        race = getRace();
        type = getType();

        // creating main character...
        createCharacter(name, race, type);

        // starting point of the game
        character.wakeUp("Riverwood");

        // generating game event...
        GameEvent.generate(
                "walk outside the house",
                "talk to Lydia",
                "eat breakfast",
                 character
        );

        if (character.isDead())
            System.out.println(ANSI_RED + "~~GAME OVER~~\n" + ANSI_RESET);
    }

    private static String getRace() {
        String race;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your character's race(ork / breton / elf / viking): ");
        race = sc.next();

        if (race.equalsIgnoreCase(Race.ORK.name())
                || race.equalsIgnoreCase(Race.BRETON.name())
                || race.equalsIgnoreCase(Race.ELF.name())
                || race.equalsIgnoreCase(Race.VIKING.name())) {
            return race;
        } else {
            System.out.println("There's no such race in the game");
            return getRace();
        }
    }

    private static String getType() {
        String type;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your character's types (warrior(w) / mage(m) / assassin(a)): ");
        type = sc.next();

        if (type.equalsIgnoreCase("w")
                || type.equalsIgnoreCase("m")
                || type.equalsIgnoreCase("a")) {
            return type;
        } else {
            System.out.println("There's no such types in the game");
            return getType();
        }
    }

    private static String getWeaponName() {
        String weapon;
        Scanner sc = new Scanner(System.in);

        if (character.getType() instanceof Warrior
                || character.getType() instanceof Assassin) {
            System.out.print("Pick the weapons of your choice (sword / axe / bow): ");
            weapon = sc.next();

            if (weapon.equalsIgnoreCase("sword")
                    || weapon.equalsIgnoreCase("axe")
                    || weapon.equalsIgnoreCase("bow")) {
                return weapon;
            } else {
                System.out.println("There's no such weapons in the game");
                return getWeaponName();
            }
        } else {
            System.out.print("Pick the weapons of your choice (flames(f) / lightning bolts(lb)): ");
            weapon = sc.next();

            if (weapon.equalsIgnoreCase("f")
                    || weapon.equalsIgnoreCase("lb")) {
                return weapon;
            } else {
                System.out.println("There's no such weapons in the game");
                return getWeaponName();
            }
        }
    }

    private static Weapon getWeapon(String name) {
        Weapon weapon = null;

        if (character.getType() instanceof Warrior
                || character.getType() instanceof Assassin) {
            switch (name.toLowerCase()) {
                case "sword":
                    weapon = new Sword();
                    break;
                case "axe":
                    weapon = new Axe();
                    break;
                case "bow":
                    weapon = new Bow();
                    break;
            }
        } else {
            switch (name.toLowerCase()) {
                case "f":
                    weapon = new Flame();
                    break;
                case "lb":
                    weapon = new LightningBolt();
                    break;
            }
        }

        return weapon;
    }

    private static void createCharacter(String name, String race, String type) {
        character = new Character();
        character.setName(name);
        Race characterRace = Race.valueOf(race.toUpperCase());
        character.setRace(characterRace);

        // setting type for a character...
        buildCharacter(type);

        // equipping character...
        String weaponName = getWeaponName();
        Weapon weapon = getWeapon(weaponName);
        equip(weapon, character);
    }

    private static void buildCharacter(String type) {
        switch (type.toLowerCase()) {
            case "w":
                character.setType(new Warrior());
                break;
            case "m":
                character.setType(new Mage());
                break;
            case "a":
                character.setType(new Assassin());
                break;
        }
    }

    private static void equip(Weapon weapon, Character ...characters) {
        for (Character character : characters)
            character.getType().setWeapon(weapon);
    }
}

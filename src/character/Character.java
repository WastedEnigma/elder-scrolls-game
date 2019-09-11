package character;

import character.races.Race;
import character.types.*;
import potions.AgilityPotion;
import potions.HealthPotion;
import potions.MagicPotion;
import potions.Potion;

import java.util.Scanner;

public class Character {

    private String name;
    private Race race;
    private Type type;
    private boolean taskAccepted;
    private boolean escorted;
    private boolean dead;
    private Potion[] potions = new Potion[1];

    public Character() { }

    public Character(String name, Race race, Type type) {
        this.name = name;
        this.race = race;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isTaskAccepted() {
        return taskAccepted;
    }

    public void setTaskAccepted(boolean taskAccepted) {
        this.taskAccepted = taskAccepted;
    }

    public boolean isEscorted() {
        return escorted;
    }

    public void setEscorted(boolean escorted) {
        this.escorted = escorted;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public void wakeUp(String location) {
        System.out.println(race + " " + name + " is awake in " + location);
    }

    public void eat(String foodOfTheDay) {
        int strength = type.getStrengthLevel();
        System.out.println(race + " " + name + " is eating " + foodOfTheDay);
        strength += 5;
        type.setStrengthLevel(strength);
    }

    public void walk(String location) {
        if (!escorted)
            System.out.println(race + " " + name + " is walking to " + location);
        else
            System.out.println(race + " " + name + " is walking to " + location + " with Lydia");
    }

    public void run() {
        int startingAgility = type.getAgilityLevel();
        int agility = type.getAgilityLevel();

        System.out.println(race + " " + name + " is running...");
        agility--;
        type.setAgilityLevel(agility);

        if (agility <= 0) {
            System.out.println(race + " " + name + " is tired");
            type.setAgilityLevel(startingAgility);
        }
    }

    public void talkTo(Character character) {
        String answer;

        if (character.name.equalsIgnoreCase("Lydia")
                && character.race == Race.BRETON
                && character.type instanceof Warrior) {
            System.out.println(character.name + ": Good morning, " + name + "! Do you want me to come with you? (y/n) ");
            answer = getAnswer();

            if (answer.equalsIgnoreCase("y")) {
                System.out.println(character.name + " is your escort now.");
                escorted = true;
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println(character.name + ": As you wish. I'll stay here.");
                escorted = false;
            }
        } else if (character.name.equalsIgnoreCase("Ralaf")
                && character.race == Race.VIKING
                && character.type instanceof Warrior) {
            System.out.println(character.name + ": Hey, " + name + "! I have a task for you. Interested? (y/n)");
            answer = getAnswer();

            if (answer.equalsIgnoreCase("y")) {
                System.out.println(character.name + ": Good. I need to you to track down " +
                        "and kill the assassin named Harold. He lives in the eastern side of " +
                        "Riverwood in Black Valley, not far from here.");
                taskAccepted = true;
            } else if (answer.equalsIgnoreCase("n")) {
                System.out.println(character.name + ": Fine. Perhaps, another time...");
                taskAccepted = false;
            }
        } else if (character.name.equalsIgnoreCase("Harold")
                && character.race == Race.BRETON
                && character.type instanceof Assassin) {
            System.out.println(character.name + ": Hold! Are you willing to fight me? (y/n)");
            answer = getAnswer();

            if (answer.equalsIgnoreCase("y")) {
                taskAccepted = true;
            } else if (answer.equalsIgnoreCase("n")) {
                taskAccepted = false;

                System.out.println(character.name + ": Oh, so do you want to join my assassins guild then? (y/n)");
                answer = getAnswer();

                if (answer.equalsIgnoreCase("y")) {
                    System.out.println(character.name + ": Congratulations! Now you are one of us");
                } else if (answer.equalsIgnoreCase("n")) {
                    System.out.println(character.name + ": Alright then. Good luck to you, traveler");
                }
            }
        }
    }

    public void buy(Potion potion, int count) {
        String exactPotion = "";

        if (potion instanceof HealthPotion)
            exactPotion = "health potion";
        else if (potion instanceof MagicPotion)
            exactPotion = "magic potion";
        else if (potion instanceof AgilityPotion)
            exactPotion = "agility potion";

        // adding potions to array
        for (int i = 0; i < count; i++) {
            System.out.println(name + " has purchased " + exactPotion);
            add(potion);
        }
    }

    public void attack(Character character) {
        int health = character.type.getHealthLevel();
        int harmLevel = type.getWeapon().getHarmLevel();

        // hit opponent with the right weapon
        type.getWeapon().hit(character);
        health -= harmLevel;

        System.out.println(character.name + " has lost " + harmLevel + "% of health");

        if (health > 0)
            System.out.println("(" + character.name + ") - " + health + "% health left");

        character.type.setHealthLevel(health);

        if (health <= 0) {
            System.out.println(character.name + " is dead");
            character.dead = true;
            character.type.setHealthLevel(0);
        }
    }

    private void add(Potion potion) {
        Potion[] newPotions = new Potion[potions.length + 1];

        for (int i = 0; i < potions.length; i++) {
            if (newPotions[i] == null)
                newPotions[i] = potion;
        }

        potions = newPotions;
    }

    private String getAnswer() {
        Scanner sc = new Scanner(System.in);

        System.out.print(name + ": ");
        String answer = sc.next();

        if (answer.equalsIgnoreCase("y")
                || answer.equalsIgnoreCase("n")) {
            return answer;
        } else {
            System.out.println("Invalid answer: should be y or n");
            return getAnswer();
        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", types=\n" + type +
                '}';
    }
}

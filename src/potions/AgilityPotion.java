package potions;

import character.Character;

public class AgilityPotion implements Potion {

    @Override
    public void use(Character character) {
        int agility = character.getType().getAgilityLevel();

        System.out.println(character.getName() + " restored agility on 30%");

        if (agility <= 70) {
            agility += 30;
            character.getType().setAgilityLevel(agility);
        } else
            character.getType().setAgilityLevel(100);
    }
}

package potions;

import character.Character;

public class HealthPotion implements Potion {

    @Override
    public void use(Character character) {
        int health = character.getType().getHealthLevel();

        System.out.println(character.getName() + " restored health on 30%");

        if (health <= 70) {
            health += 30;
            character.getType().setHealthLevel(health);
        } else
            character.getType().setHealthLevel(100);
    }
}

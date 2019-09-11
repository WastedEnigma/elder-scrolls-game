package weapons;

import character.Character;

public interface Weapon {

    int getHarmLevel();
    void hit(Character character);

    default void stealthKill(Character character) {
        System.out.println(character.getName() + " was killed without even noticing");
    }
}

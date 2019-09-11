package potions;

import character.Character;

@FunctionalInterface
public interface Potion {

    void use(Character character);
}

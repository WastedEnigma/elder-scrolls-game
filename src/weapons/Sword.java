package weapons;

import character.Character;

public class Sword implements Weapon {

    @Override
    public int getHarmLevel() {
        return 5;
    }

    @Override
    public void hit(Character character) {
        System.out.println(character.getName() + " was hit with a sword");
    }

    @Override
    public String toString() {
        return "Sword{ harmLevel=" + getHarmLevel() +
                "}";
    }
}

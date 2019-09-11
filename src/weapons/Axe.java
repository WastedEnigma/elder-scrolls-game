package weapons;

import character.Character;

public class Axe implements Weapon {

    @Override
    public int getHarmLevel() {
        return 10;
    }

    @Override
    public void hit(Character character) {
        System.out.println(character.getName() + " was hit with an axe");
    }

    @Override
    public String toString() {
        return "Axe{ harmLevel=" + getHarmLevel() +
                "}";
    }
}

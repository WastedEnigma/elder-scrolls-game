package weapons;

import character.Character;

public class Flame implements Weapon {

    @Override
    public int getHarmLevel() {
        return 20;
    }

    @Override
    public void hit(Character character) {
        System.out.println(character.getName() + " was hit with the flames");
    }

    @Override
    public String toString() {
        return "Flame{ harmLevel=" + getHarmLevel() +
                "}";
    }
}

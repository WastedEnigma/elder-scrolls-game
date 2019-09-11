package weapons;

import character.Character;

public class Bow implements Weapon {

    @Override
    public int getHarmLevel() {
        return 15;
    }

    @Override
    public void hit(Character character) {
        System.out.println(character.getName() + " was shot with a bow");
    }

    @Override
    public String toString() {
        return "Bow{ harmLevel=" + getHarmLevel() +
                "}";
    }
}

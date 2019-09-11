package weapons;

import character.Character;

public class LightningBolt implements Weapon {

    @Override
    public int getHarmLevel() {
        return 25;
    }

    @Override
    public void hit(Character character) {
        System.out.println(character.getName() + " was hit with the lightning bolts");
    }

    @Override
    public String toString() {
        return "LightningBolt{ harmLevel=" + getHarmLevel() +
                "}";
    }
}

package potions;

import character.Character;

public class MagicPotion implements Potion {

    @Override
    public void use(Character character) {
        int magic = character.getType().getMagicLevel();

        System.out.println(character.getName() + " restored magic on 30%");

        if (magic <= 70) {
            magic += 30;
            character.getType().setMagicLevel(magic);
        } else
            character.getType().setMagicLevel(100);
    }
}

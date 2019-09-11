package character.types;

public class Warrior extends Type {

    public Warrior() {
        setStrengthLevel(30);
        setAgilityLevel(20);
        setMagicLevel(0);
        setHealthLevel(100);
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "agilityLevel=" + getAgilityLevel() +
                ", strengthLevel=" + getStrengthLevel() +
                ", magicLevel=" + getMagicLevel() +
                ", healthLevel=" + getHealthLevel() +
                ", weapons=\n" + getWeapon() +
                '}';
    }
}

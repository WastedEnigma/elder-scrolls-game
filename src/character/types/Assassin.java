package character.types;

public class Assassin extends Type {

    public Assassin() {
        setStrengthLevel(20);
        setAgilityLevel(30);
        setMagicLevel(0);
        setHealthLevel(100);
    }

    @Override
    public String toString() {
        return "Assassin{" +
                "agilityLevel=" + getAgilityLevel() +
                ", strengthLevel=" + getStrengthLevel() +
                ", magicLevel=" + getMagicLevel() +
                ", healthLevel=" + getHealthLevel() +
                ", weapons=\n" + getWeapon() +
                '}';
    }
}

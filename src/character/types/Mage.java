package character.types;

public class Mage extends Type {

    public Mage() {
        setStrengthLevel(10);
        setAgilityLevel(20);
        setMagicLevel(30);
        setHealthLevel(100);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "agilityLevel=" + getAgilityLevel() +
                ", strengthLevel=" + getStrengthLevel() +
                ", magicLevel=" + getMagicLevel() +
                ", healthLevel=" + getHealthLevel() +
                ", weapons=\n" + getWeapon() +
                '}';
    }
}

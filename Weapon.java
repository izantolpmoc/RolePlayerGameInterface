
public abstract class Weapon extends Item {

    protected double Damage;
    protected DamageType DamageType;
    protected String Name;
    protected double Price;

    public Weapon(double damage, double price, String name) {
        super(name,price);
        Damage = damage;
    }

    abstract public String ascii_art();

    public String attack(Destructible destructible) {
        String text = "";
        if(destructible.HealthPoints > 0) destructible.TakeDamage(Damage, DamageType);
        else text += destructible.toString() + " a été vaincu !";
        return text;
    }

    public String attack(Monster monster) {
        String text ="";
        text += monster.Name + ": " + monster.HealthPoints + "/100\n";
        monster.TakeDamage(Damage, DamageType);
        if(monster.HealthPoints <= 0) text += "\n" + monster.Name + " a été vaincu(e) !";
        return text;
    }

    public String attack(Obstacle obstacle) {
        String text = "";
        text += obstacle.Name + ": " + obstacle.HealthPoints + "/100\n";
        obstacle.TakeDamage(Damage, DamageType);
        if(obstacle.HealthPoints <= 0) text += "\n" + obstacle.Name + " a été détruit(e) !";
        return text;
    }

    public String ToString() {
        return this.getClass().getName();
    }
}

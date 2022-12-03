import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {
    public Player(String username, Gender gender) {
        Username = username;
        Gender = gender;

        if(gender == Gender.MALE)
            Weapon = new BareHands(5);
    }

    // attributes
    private String Username;
    private Gender Gender;
    public double HealthPoints = 100;
    protected double Strength;
    protected double Constitution;
    protected double Dexterity;
    protected double Intelligence;
    protected double Wisdom;
    protected double Charisma;
    public DamageType ResistantTo;
    public DamageType WeakTo;
    protected double XP = 0;
    private double Level = 0;

    protected double Money = 0;
    protected ArrayList<Item> Inventory = new ArrayList();
    protected Weapon Weapon = new BareHands();

    public String attack(Destructible destructible) {
        return Weapon.attack(destructible);
    }

    public String attack(Monster monster) {
        return Weapon.attack(monster);
    }

    public String attack(Obstacle obstacle) {
        return Weapon.attack(obstacle);
    }

    public String TakeDamage(double damage, DamageType type) {
        String dialog = "";
        switch (type) {
            case FIRE:
                if(ResistantTo == DamageType.FIRE) HealthPoints -= (damage - (damage / 10));
                else if(WeakTo == DamageType.FIRE) HealthPoints -= (damage + (damage / 10));
                else HealthPoints -= damage;
                if(HealthPoints > 0)
                    dialog += "Vous perdez de la vie: "+ HealthPoints +"/100\n";
                if(HealthPoints <= 0)
                    dialog += "\nVous êtes mort(e)";
                break;
            case NEUTRAL:
                if(ResistantTo == DamageType.NEUTRAL) HealthPoints -= (damage - (damage / 10));
                else if(WeakTo == DamageType.NEUTRAL) HealthPoints -= (damage + (damage / 10));
                else HealthPoints -= damage;
                if(HealthPoints > 0)
                    dialog += "Vous perdez de la vie: "+ HealthPoints +"/100\n";
                if(HealthPoints <= 0)
                    dialog += "\nVous êtes mort(e)";
                break;
            case POISON:
                if(ResistantTo == DamageType.POISON) HealthPoints -= (damage - (damage / 10));
                else if(WeakTo == DamageType.POISON) HealthPoints -= (damage + (damage / 10));
                else HealthPoints -= damage;
                if(HealthPoints > 0)
                    dialog += "Vous perdez de la vie: "+ HealthPoints +"/100\n";
                if(HealthPoints <= 0)
                    dialog += "\nVous êtes mort(e)" +ConsoleColors.RESET;
                break;
            case HEMORRAGY:
                if(ResistantTo == DamageType.HEMORRAGY) HealthPoints -= (damage - (damage / 10));
                else if(WeakTo == DamageType.HEMORRAGY) HealthPoints -= (damage + (damage / 10));
                else HealthPoints -= damage;
                if(HealthPoints > 0)
                    dialog += "Vous perdez de la vie: "+ HealthPoints +"/100\n";
                if(HealthPoints <= 0)
                    dialog += "\nVous êtes mort(e)";
                break;
        }

        return dialog;
    }

    public void Heal(double points) { HealthPoints += points; }

    public JPanel Eat(JPanel actionPanel, JPanel dialogPanel) {
        //DOESNT WORK
        JPanel inventory = new JPanel();
        inventory.setVisible(false);
        inventory.setBounds(100, 100, 1050, 300);
        inventory.setBackground(Color.black);
        inventory.setForeground(Color.white);

        JTextArea inventoryContent = new JTextArea();
        inventoryContent.setBounds(100, 100, 800, 250);
        inventoryContent.setBackground(Color.black);
        inventoryContent.setForeground(Color.white);
        inventoryContent.setFont(new Font("Monospaced", Font.PLAIN, 18));
        inventoryContent.setLineWrap(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(400, 450, 400, 150);
        buttonsPanel.setBackground(Color.red);
        buttonsPanel.setLayout(new GridLayout(6, 2));

        inventory.add(inventoryContent);
        inventory.add(buttonsPanel);
        inventory.setVisible(true);

        String dialog = "";
        if(HealthPoints >= 100)
            dialog += "Votre barre de vie est déjà pleine.\n";
        else
            dialog += "Souhaitez vous manger ?\n";
        dialog += "-------------------------\n\n";
        inventoryContent.setText(dialog);



        for (int i = 0; i<Inventory.size(); i++) {

            if((Inventory.get(i) instanceof Consumable))
            {
                JButton btn = new JButton(Inventory.get(i).Name);
                btn.setBackground(Color.black);
                btn.setForeground(Color.white);
                btn.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn);
                btn.setActionCommand("eat");
                btn.addActionListener(new InventoryAction(i, this, inventoryContent, inventory, buttonsPanel, actionPanel, dialogPanel));
            }
        }

        JButton btn1 = new JButton("Annuler");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> {
            inventory.setVisible(false);
            inventoryContent.setVisible(false);
            buttonsPanel.setVisible(false);
            dialogPanel.setVisible(true);
            actionPanel.setVisible(true);
        });

        return inventory;
    }

    public String Additem(Item item) {
        String text ="";
        Inventory.add(item);
        text += "\n"+ item.Name + " a été ajouté(e) à votre inventaire.";
        if (item instanceof Weapon) text += ((Weapon) item).ascii_art();
        return text;
    }

    public String ReceiveMoney(double amount) {
        Money += amount;
        return "\nVous avez reçu "+ amount+ "€";
    };

    public JPanel ChangeWeapon(JPanel actionPanel, JPanel dialogPanel) {
        JPanel inventory = new JPanel();
        inventory.setVisible(false);
        inventory.setBounds(100, 100, 1050, 300);
        inventory.setBackground(Color.black);
        inventory.setForeground(Color.white);

        JTextArea inventoryContent = new JTextArea();
        inventoryContent.setBounds(100, 100, 800, 250);
        inventoryContent.setBackground(Color.black);
        inventoryContent.setForeground(Color.white);
        inventoryContent.setFont(new Font("Monospaced", Font.PLAIN, 18));
        inventoryContent.setLineWrap(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(400, 450, 400, 150);
        buttonsPanel.setBackground(Color.red);
        buttonsPanel.setLayout(new GridLayout(6, 2));

        inventory.add(inventoryContent);
        inventory.add(buttonsPanel);
        inventory.setVisible(true);

        String dialog = "";
        dialog += "Souhaitez vous changer d'arme ?\n";
        dialog += "-------------------------\n\n";
        inventoryContent.setText(dialog);

        for (int i = 0; i<Inventory.size(); i++) {
            //dialog += i + " - " + Inventory.get(i).Name + ": " + Inventory.get(i).Price + "€\n";

            if((Inventory.get(i) instanceof Weapon))
            {
                JButton btn = new JButton(Inventory.get(i).Name + ": " + Inventory.get(i).Price + "€");
                btn.setBackground(Color.black);
                btn.setForeground(Color.white);
                btn.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn);
                btn.addActionListener(new InventoryAction(i, this, inventoryContent, inventory, buttonsPanel, actionPanel, dialogPanel));
            }

        }
        JButton btn1 = new JButton("Annuler");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> {
            inventory.setVisible(false);
            inventoryContent.setVisible(false);
            buttonsPanel.setVisible(false);
            dialogPanel.setVisible(true);
            actionPanel.setVisible(true);
        });

        return inventory;
    }

    public String gainXP(double xp) {
        String text = "";
        if (XP < 100) XP += xp;
        if (XP >= 100) text += levelUp(XP-100);

        return text;
    }

    public String levelUp(double newXP) {
        String text = "";
        if(HealthPoints <= 0) return text;
        Level ++;
        text += "\nVous venez de passer de passer au niveau " + Level + "\n";
        XP = newXP;
        text += ReceiveMoney(10);
        if(HealthPoints <= 50) Heal(30);
        else HealthPoints = 100;

        return text;
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Map extends Item {

    public Map() {
        super(NAME, PRICE);
    }

    private static final double PRICE = 0;
    private static final String NAME = "Carte de Paris";
    private String[][] map = new String[][]{
            {"🏢","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","\uD83D\uDEA9"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🏢","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","⛲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🏢","🌲","🌲","🌲","🏢","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🏢","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"}};


    //Map where the events are pre-defined
    public static final int[][] MAPELEMENTS = new int[][]{
            {4,1,1,1,1,1,1,1,1,9},
            {1,2,2,2,2,3,2,2,5,1},
            {1,3,2,2,2,2,2,4,2,1},
            {1,2,5,4,2,2,2,5,2,1},
            {1,2,3,3,0,5,5,3,2,1},
            {1,2,2,2,2,2,2,2,2,1},
            {1,3,2,4,2,2,3,4,2,1},
            {1,2,3,5,2,2,2,2,2,1},
            {1,2,5,2,2,2,3,4,5,1},
            {1,4,3,1,1,1,1,1,1,1}};

    // Map displayed to the user
    public static final String[][] MAPBASE = new String[][]{
            {"🏢","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","\uD83D\uDEA9"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🏢","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","⛲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🏢","🌲","🌲","🌲","🏢","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"},
            {"🌲","🏢","🌲","🌲","🌲","🌲","🌲","🌲","🌲","🌲"}};


    int x = 9;
    int y = 0;
    int playerPos = MAPELEMENTS[x][y];

    JTextArea dialogContent;
    JPanel choicePanel, textPanel, actionPanel, buttonsPanel, sellPanel, buyPanel;
    JButton btn1, btn2, btn3;

    public JPanel move(Direction direction, Player player, JPanel choicePanel, JPanel textPanel) {
        this.choicePanel = choicePanel;
        this.textPanel = textPanel;

        this.map[x][y] = Map.MAPBASE[x][y];

        switch(direction) {
            case UP :
                if(x > 0) x-=1;
                break;
            case DOWN :
                if(x < 9) x+=1;
                break;
            case LEFT :
                if(y > 0) y-=1;
                break;
            case RIGHT:
                if(y < 9) y+=1;
                break;
        }

        playerPos = MAPELEMENTS[x][y];
        this.print2D();
        return this.Action(player);
    }

    public String print2D()
    {
        String result ="";
        this.map[x][y] = "\uD83D\uDC64";
        for (String[] row : map)
            result += Arrays.toString(row) + " \n";
        System.out.println(result);
        return result;
    }

    //defines all the events the player can be confronted to and the actions he can perform in reaction
    public JPanel Action(Player player)
    {
        //region layout
        actionPanel = new JPanel();
        actionPanel.setBounds(100, 100, 1050, 300);
        actionPanel.setBackground(Color.black);
        actionPanel.setForeground(Color.white);
        actionPanel.setLayout(new GridLayout(1, 1));

        dialogContent = new JTextArea();
        dialogContent.setBounds(100, 100, 800, 2000);
        dialogContent.setBackground(Color.black);
        dialogContent.setForeground(Color.white);
        dialogContent.setFont(new Font("Monospaced", Font.PLAIN, 18));
        dialogContent.setLineWrap(true);
        dialogContent.setWrapStyleWord(true);

        //makes sure interface is up-to-date
        if (sellPanel != null)
            actionPanel.remove(sellPanel);

        if (buyPanel != null)
            actionPanel.remove(buyPanel);

        //Jscrollpane is used to make sure all text content is displayed to the user
        JScrollPane scroll = new JScrollPane(dialogContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(800, 300));
        scroll.setVisible(true);
        actionPanel.add(scroll);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(400, 450, 400, 150);
        buttonsPanel.setBackground(Color.red);
        buttonsPanel.setLayout(new GridLayout(6, 1));

        actionPanel.add(buttonsPanel);
        dialogContent.setVisible(true);
        actionPanel.setVisible(true);
        buttonsPanel.setVisible(true);
        //end region

        String dialog;

        switch(playerPos) {
            //Big reward
            case 0:
                dialog = "Vous repérez un objet brillant sous un banc. Vous vous rapprochez. UNE ROLEX ! Sans aucune hésitation, vous ramassez le butin et le revendez dans une petite boutique pour la coquette somme de 7800€.";
                dialog += player.ReceiveMoney(7800);
                dialogContent.setText(dialog);

                btn1= new JButton("Suivant");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
                break;

            //Hostile environment
            case 1:
                dialog = "Vous marchez sur un trottoir 'miné'. Une forte dextérité peut vous aider à esquiver les crottes de chien. \n";
                dialog += "Votre dextérité: " + player.Dexterity;
                if(player.Dexterity > 5)
                    dialog += "\nVous arrivez à avancer sans soucis.";
                else
                {
                    dialog += "\nVous marchez sur une crotte";
                    dialog += player.TakeDamage(5, DamageType.POISON);
                }
                dialogContent.setText(dialog);

                btn1= new JButton("Suivant");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
                break;

            //Trash (obstacle)
            case 2:
                dialog = "Une tonne d'encombrants vous bloque la route.\n";
                var trash = new Trash();

                dialog += "Que faites vous ?";
                dialogContent.setText(dialog);

                btn1= new JButton("Attaquer avec l'arme");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> fight(player, trash, new BrokenBottle()));

                btn2= new JButton("Autre direction");
                btn2.setBackground(Color.black);
                btn2.setForeground(Color.white);
                btn2.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn2);
                btn2.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));

                if(player.Dexterity >= 8) {
                    JButton btn3= new JButton("Escalader");
                    btn3.setBackground(Color.black);
                    btn3.setForeground(Color.white);
                    btn3.setFont(new Font("Monospaced", Font.PLAIN, 18));
                    buttonsPanel.add(btn3);
                    btn3.addActionListener(e ->
                    {
                        hidePanel(dialogContent, actionPanel, buttonsPanel);
                        buttonsPanel.remove(btn3);
                    });
                }
            break;

            //Prostitute (monster)
            case 3:
                var prostitute = new Prostitute();
                dialog = "Une drôle de femme vous aborde en sortant d'une camionette, elle vous réclame de l'argent contre un service sexuel que vous n'avez pas demandé.\n";
                dialog += "Que faites vous ?";

                dialogContent.setText(dialog);

                btn1= new JButton("Attaquer avec l'arme");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> {
                    hidePanel(dialogContent, actionPanel, buttonsPanel);
                    fight(player, prostitute, new BagOfUsedCondoms());
                });

                btn2= new JButton("Fuir");
                btn2.setBackground(Color.black);
                btn2.setForeground(Color.white);
                btn2.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn2);
                btn2.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
                break;

            //StreetSeller (shop/monster)
            case 4:
                dialog = "MALBORO, MALBORO, MARRONS CHAUDS, MARRONS CHAUDS, 1 EURO, 1 EUROOOO. Un groupe de vendeurs à la sauvette sont prêts à marchander avec vous.\n";
                var sellerInventory = new ArrayList<Item>();
                sellerInventory.add(new Fireworks());
                sellerInventory.add(new Syringe());
                sellerInventory.add(new HotChestnuts());
                var seller = new StreetSeller("Vendeur du coin", sellerInventory);

                dialog += "Que faites vous ?";
                dialogContent.setText(dialog);

                btn1= new JButton("Acheter");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> {
                    //hides current display and shows seller's inventory
                    actionPanel.setVisible(false);
                    scroll.setVisible(false);
                    dialogContent.setVisible(false);
                    buttonsPanel.setVisible(false);
                    buyPanel = seller.Sell(player, choicePanel, textPanel, actionPanel);
                    actionPanel.removeAll();
                    actionPanel.add(buyPanel);
                    actionPanel.setVisible(true);

                });

                btn2= new JButton("Vendre");
                btn2.setBackground(Color.black);
                btn2.setForeground(Color.white);
                btn2.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn2);
                btn2.addActionListener(e -> {
                    //hides current display and shows player's inventory
                    actionPanel.setVisible(false);
                    scroll.setVisible(false);
                    dialogContent.setVisible(false);
                    buttonsPanel.setVisible(false);
                    sellPanel = seller.Buy(player, choicePanel, textPanel, actionPanel);
                    actionPanel.removeAll();
                    actionPanel.add(sellPanel);
                    actionPanel.setVisible(true);
                });

                btn3= new JButton("Fuir");
                btn3.setBackground(Color.black);
                btn3.setForeground(Color.white);
                btn3.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn3);
                btn3.addActionListener(e -> {
                    hidePanel(dialogContent, actionPanel, buttonsPanel);
                    System.out.println(ConsoleColors.RED + "Les vendeurs ne vous laissent pas partir si facilement. Vous allez devoir vous battre." + ConsoleColors.RESET);
                    fight(player, new AngrySeller());
                    if(player.HealthPoints > 0) player.ReceiveMoney(10);
                });
                break;

            //Hostile environment
            case 5:
                dialog = "Une nuée de pigeons vous survole. Une forte dextérité peut vous aider à esquiver leurs fientes.\n";
                dialog += "Votre dextérité: " + player.Dexterity;
                if(player.Dexterity > 5)
                    dialog += "\nVous arrivez à avancer sans soucis.";
                else
                {
                    dialog += "\nVous recevez une fiente sur votre visage.";
                    dialog += player.TakeDamage(5, DamageType.POISON);
                }

                dialogContent.setText(dialog);

                btn1= new JButton("Suivant");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));

                break;

            //End of the game
            case 9:
                if(player.Money >= 50) {
                    dialog = "Vous apercevez au loin un chauffeur, Yvan avait raison !\n";
                    dialog += "Vous vous approchez et lui donnez ses 50€\n";
                    dialog += "Vous rentrez enfin chez vous...\n\n";
                    dialog += "Vous avez gagné !";
                    dialogContent.setText(dialog);

                }
                else {
                    dialog ="Vous n'avez réussi à obtenir que " + player.Money + "/50€\n";
                    dialog +="Vous voyez un chauffeur au loin mais celui-ci vous fait comprendre que vous n'avez pas assez d'argent.\n\n";
                    dialog +="Vous avez perdu...";
                    dialogContent.setText(dialog);

                }
                btn1= new JButton("Fin");
                btn1.setBackground(Color.black);
                btn1.setForeground(Color.white);
                btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn1);
                btn1.addActionListener(e -> new RPGMain());
                break;
        }
        return actionPanel;
    }

    //All actions related to fighting return a string that can be displayed in the dialog panel
    private void fight(Player player, Obstacle obstacle, Item newItem) {
        String text = "";

        while(obstacle.HealthPoints > 0)
            text += player.attack(obstacle);

        if (player.WeakTo == obstacle.DamageType)
            text += player.TakeDamage(5, obstacle.DamageType);

        if(player.HealthPoints > 0) text += player.Additem(newItem) + "\n";
        text += player.gainXP(10);

        actionPanel.setVisible(false);
        dialogContent.setVisible(false);
        buttonsPanel.setVisible(false);
        buttonsPanel.remove(btn1);
        if(btn2 != null)
            buttonsPanel.remove(btn2);
        if(btn3 != null)
            buttonsPanel.remove(btn3);

        dialogContent.setAutoscrolls(true);
        dialogContent.setText(text);


        btn1= new JButton("Suivant");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
        textPanel.setVisible(false);
        choicePanel.setVisible(false);
        buttonsPanel.setVisible(true);
        dialogContent.setVisible(true);
        actionPanel.setVisible(true);
    }



    //The fight goes on until one of them dies.
    private void fight(Player player, Monster monster, Item newItem) {
        String text = "";

        while(monster.HealthPoints > 0 && player.HealthPoints > 0)
        {
            if(player.HealthPoints > 0)
                text += player.attack(monster);

            if(monster.HealthPoints > 0)
                text += monster.Attack(player);
        }

        if(player.HealthPoints > 0) text += player.Additem(newItem) + "\n";
        text += player.gainXP(25);
        actionPanel.setVisible(false);
        dialogContent.setVisible(false);
        buttonsPanel.setVisible(false);
        buttonsPanel.remove(btn1);
        if(btn2 != null)
            buttonsPanel.remove(btn2);
        if(btn3 != null)
            buttonsPanel.remove(btn3);

        dialogContent.setAutoscrolls(true);
        dialogContent.setText(text);


        btn1= new JButton("Suivant");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
        textPanel.setVisible(false);
        choicePanel.setVisible(false);
        buttonsPanel.setVisible(true);
        dialogContent.setVisible(true);
        actionPanel.setVisible(true);
    }

    //This method is only used when player does not receive an item as a reward
    private void fight(Player player, Monster monster) {
        String text = "";

        while(monster.HealthPoints > 0 && player.HealthPoints > 0)
        {
            if(player.HealthPoints > 0)
                text += player.attack(monster);

            if(monster.HealthPoints > 0)
                text += monster.Attack(player);
        }

        if(player.HealthPoints > 0) text += player.ReceiveMoney(10) + "\n";
        text += player.gainXP(25);
        actionPanel.setVisible(false);
        dialogContent.setVisible(false);
        buttonsPanel.setVisible(false);
        buttonsPanel.remove(btn1);
        if(btn2 != null)
            buttonsPanel.remove(btn2);
        if(btn3 != null)
            btn3.setVisible(false);
            //buttonsPanel.remove(btn3);

        dialogContent.setAutoscrolls(true);
        dialogContent.setText(text);


        btn1= new JButton("Suivant");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> hidePanel(dialogContent, actionPanel, buttonsPanel));
        textPanel.setVisible(false);
        choicePanel.setVisible(false);
        buttonsPanel.setVisible(true);
        dialogContent.setVisible(true);
        actionPanel.setVisible(true);
    }

    //Method used to hide the current interface and show the menu where the user can choose a new action
    private void hidePanel(JTextArea dialogContent, JPanel actionPanel, JPanel buttonsPanel) {
        dialogContent.setVisible(false);
        actionPanel.setVisible(false);
        buttonsPanel.setVisible(false);
        textPanel.setVisible(true);
        choicePanel.setVisible(true);
    }
}
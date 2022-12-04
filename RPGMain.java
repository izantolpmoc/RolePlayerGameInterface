import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;


public class RPGMain {

    Font normalFont;
    String playerName;
    JFrame window;
    JLabel hpLabel, hpLabelNumber, weaponLabel, weaponLabelNumber, xpLabel, xpLabelNumber;
    JPanel titlePanel, startBtnPanel, dialogPanel, actionPanel, promptPanel, mapPanel, playerPanel, moveActionPanel;
    JButton choice1, choice2, choice3, choice4, choice5, choice6, firstChoice, secondChoice, thirdChoice, fourthChoice, fifthChoice;
    JTextArea dialogContent, mapContent;
    JTextField username;
    int timesClicked = 0;
    Gender playerGender;
    Player player;
    Map map;


    public static void main(String[] args) {

        new RPGMain();

    }

    public RPGMain() {
        normalFont = new Font("Monospaced", Font.PLAIN, 18);

        //HOME SCREEN
        window = new JFrame("Paris Survival");
        window.setSize(1280, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);

        titlePanel = new JPanel();
        JLabel titlePanelLabel = new JLabel("Paris Survival");
        titlePanelLabel.setForeground(Color.white);
        titlePanelLabel.setFont(new Font("Monospaced", Font.PLAIN, 70));
        titlePanel.setBounds(100, 100, 1050, 150);
        titlePanel.setBackground(Color.RED);

        titlePanel.add(titlePanelLabel);

        startBtnPanel = new JPanel();
        startBtnPanel.setBounds(300, 500, 600, 100);
        startBtnPanel.setBackground(Color.black);
        JButton startBtn = new JButton("START");
        startBtn.setBackground(Color.black);
        startBtn.setForeground(Color.white);
        startBtn.setFont(normalFont);
        //starts the game onclick of main button
        startBtn.addActionListener(e -> InitGame());

        startBtnPanel.add(startBtn);

        window.add(startBtnPanel);
        window.add(titlePanel);

    }

    public void InitGame() {
        //hides home screen
        titlePanel.setVisible(false);
        startBtnPanel.setVisible(false);

        //Main game interface Layout definition
        dialogPanel = new JPanel();
        dialogPanel.setBounds(100, 100, 1050, 250);
        dialogPanel.setBackground(Color.black);

        dialogContent = new JTextArea();
        dialogContent.setBounds(100, 100, 800, 250);
        dialogContent.setBackground(Color.black);
        dialogContent.setForeground(Color.white);
        dialogContent.setFont(normalFont);
        dialogContent.setLineWrap(true);
        dialogContent.setWrapStyleWord(true);
        dialogPanel.add(dialogContent);

        actionPanel = new JPanel();
        actionPanel.setBounds(400, 450, 400, 150);
        actionPanel.setBackground(Color.black);
        actionPanel.setLayout(new GridLayout(6, 1));

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 10, 1050, 50);
        playerPanel.setBackground(Color.red);
        playerPanel.setForeground(Color.white);
        playerPanel.setLayout(new GridLayout(1, 7));

        hpLabel = new JLabel("HP: ");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        xpLabel = new JLabel("XP: ");
        xpLabel.setFont(normalFont);
        xpLabel.setForeground(Color.white);
        playerPanel.add(xpLabel);

        xpLabelNumber = new JLabel();
        xpLabelNumber.setFont(normalFont);
        xpLabelNumber.setForeground(Color.white);
        playerPanel.add(xpLabelNumber);

        weaponLabel = new JLabel("Arme :");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponLabelNumber = new JLabel(new BareHands().ToString());
        weaponLabelNumber.setFont(normalFont);
        weaponLabelNumber.setForeground(Color.white);
        playerPanel.add(weaponLabelNumber);


        choice1 = new JButton();
        choice2 = new JButton();
        choice3 = new JButton();
        choice4 = new JButton();
        choice5 = new JButton();
        choice6 = new JButton();

        window.add(dialogPanel);
        window.add(actionPanel);
        window.add(playerPanel);
        playerPanel.setVisible(false);

        //First dialog content
        var YvanDialog = new String[]{
                "X : ET MERDE ",
                "X : Sur quoi j'ai encore trébuché moi...",
                "X : Encore un saoulard qui s'est endormi par terre, c'est vraiment de pire en pire cette ville!",
                "X : Tiens, c'est bizarre quand même je n'ai pas l'impression de l'avoir déjà vu dans le coin..."
        };

        //Dialog skip button
        JButton skipBtn = new JButton("Suivant");
        createActionButton(skipBtn);

        //Displays first dialog dynamically and then asks for user's name
        dialogContent.setText(YvanDialog[0]);
        skipBtn.addActionListener(e -> {
            timesClicked ++;
            if (timesClicked < YvanDialog.length)
                dialogContent.setText(YvanDialog[timesClicked]);
            else {
                ChooseName();
                actionPanel.remove(skipBtn);
            }
        });
    }

    public void ChooseName() {
        actionPanel.setVisible(false);

        promptPanel = new JPanel();
        promptPanel.setBounds(400, 450, 500, 50);
        promptPanel.setBackground(Color.black);
        promptPanel.setForeground(Color.white);
        promptPanel.setLayout(new GridLayout(1, 2));

        dialogContent.setText("Vous sortez d'un sommeil très profond avec un mal de crâne terrible. L'inconnu vous demande comment vous vous appelez. Que répondez-vous ?");
        username = new JTextField();
        username.setBackground(Color.black);
        username.setForeground(Color.white);
        username.setFont(normalFont);

        JButton validateBtn = new JButton("Valider");
        validateBtn.setBackground(Color.black);
        validateBtn.setForeground(Color.white);
        validateBtn.setFont(normalFont);

        //On validate, sets the username and then displays 2nd dialog
        validateBtn.addActionListener(e -> {
            playerName = username.getText();
            var YvanDialog2 = new String[]{
                    "X : Enchanté " + playerName + ", en tout cas tu es vraiment dans un sale état !",
                    "X : Moi c'est Yvan."
            };
            YvanDialog2(YvanDialog2);
        });

        promptPanel.add(username);
        promptPanel.add(validateBtn);
        window.add(promptPanel);
    }
    public void YvanDialog2(String[] dialog) {
        timesClicked = 0;
        promptPanel.setVisible(false);
        actionPanel.setVisible(true);

        JButton skipBtn = new JButton("Suivant");
        createActionButton(skipBtn);

        dialogContent.setText(dialog[0]);
        //Displays dialog content and then asks for user's gender
        skipBtn.addActionListener(e -> {
            timesClicked ++;
            if (timesClicked < dialog.length)
                dialogContent.setText(dialog[timesClicked]);
            else {
                ChooseGender();
                actionPanel.remove(skipBtn);
            }
        });
    }

    public void ChooseGender() {
        dialogContent.setText("Yvan vous aide à vos redresser et vous offre de l'eau. Il semble vous regarder avec curiosité. Vous lui parlez: \n \n");
        dialogContent.setText(dialogContent.getText() + "1- Merci pour ton aide Yvan, à vrai dire je ne sais pas comment je me suis retrouvée seule ici \n");
        dialogContent.setText(dialogContent.getText() + "2 - Merci pour ton aide Yvan, à vrai dire je ne sais pas comment je me suis retrouvé seul ici \n");
        dialogContent.setText(dialogContent.getText() + "3- Merci pour ton aide Yvan, à vrai dire je ne sais pas ce que je fiche ici");
        firstChoice = new JButton("1- [femme]");
        createActionButton(firstChoice);
        firstChoice.setActionCommand("firstChoice");

        secondChoice = new JButton("2- [homme]");
        createActionButton(secondChoice);
        secondChoice.setActionCommand("secondChoice");

        thirdChoice = new JButton("3- [autre]");
        createActionButton(thirdChoice);
        thirdChoice.setActionCommand("thirdChoice");

        firstChoice.addActionListener(new GenderAction());
        secondChoice.addActionListener(new GenderAction());
        thirdChoice.addActionListener(new GenderAction());
    }

    public void ChooseClass() {
        actionPanel.setVisible(true);

        dialogContent.setText("Yvan: Je vois, et qu'est ce que tu fais dans la vie ?");
        firstChoice = new JButton("étudiant");
        createActionButton(firstChoice);
        firstChoice.setActionCommand("firstChoice");

        secondChoice = new JButton("homme d'affaires");
        createActionButton(secondChoice);
        secondChoice.setActionCommand("secondChoice");

        thirdChoice = new JButton("homme politique");
        createActionButton(thirdChoice);
        thirdChoice.setActionCommand("thirdChoice");

        fourthChoice = new JButton("sportif des JO de Paris");
        createActionButton(fourthChoice);
        fourthChoice.setActionCommand("fourthChoice");

        fifthChoice = new JButton("touriste");
        createActionButton(fifthChoice);
        fifthChoice.setActionCommand("fifthChoice");

        if(playerGender == Gender.FEMALE) {
            firstChoice.setText("étudiante");
            secondChoice.setText("femme d'affaires");
            thirdChoice.setText("femme politique");
        }

        actionPanel.setLayout(new GridLayout(5, 1));
        firstChoice.addActionListener(new ClassAction());
        secondChoice.addActionListener(new ClassAction());
        thirdChoice.addActionListener(new ClassAction());
        fourthChoice.addActionListener(new ClassAction());
        fifthChoice.addActionListener(new ClassAction());
    }


    public void YvanDialog3() {
        actionPanel.setVisible(true);

        var dialog = new String[]{
                "Yvan: Ah oui d'accord ça ne rigole pas!",
                "Yvan: Bon écoute, je ne peux pas rester plus longtemps et tous les transports sont en grève aujourd'hui.",
                "Yvan: Si tu veux rentrer chez toi je te suggère d'aller en haut à droite de cette carte, là où il y a un drapeau. \n" +
                        "Tu y trouveras surement des chauffeurs et pour 50€ tu devrais pouvoir obtenir une course!",
                "Yvan: Ce n'est pas grand chose mais je veux bien te donner ces 5€ et la carte.\n\n",
                "Yvan: Bon courage, " + playerName +"."
        };
        timesClicked = 0;
        JButton skipBtn = new JButton("Suivant");
        createActionButton(skipBtn);

        //displays text dynamically and adds map to the frame
        //Starts the game when all text has been displayed
        dialogContent.setText(dialog[0]);
        skipBtn.addActionListener(e -> {
            timesClicked ++;
            if(timesClicked == 2) {
                mapPanel = new JPanel();
                mapPanel.setBounds(875, 510, 400, 300);
                mapPanel.setBackground(Color.white);
                mapPanel.setForeground(Color.white);

                map = new Map();
                mapContent = new JTextArea(map.print2D());
                mapContent.setEditable(false);
                mapContent.setFont(normalFont);
                mapPanel.add(mapContent);
                window.add(mapPanel);
            }
            if (timesClicked < dialog.length) {
                dialogContent.setText(dialog[timesClicked]);
                if (timesClicked == 3) {
                    dialogContent.setText(dialogContent.getText() + player.Additem(map));
                    dialogContent.setText((dialogContent.getText() + player.ReceiveMoney(5)));
                }
            }
            else {
                startGame();
                actionPanel.remove(skipBtn);
            }
        });
    }

    public void startGame() {
        hpLabelNumber.setText(String.valueOf(player.HealthPoints));
        xpLabelNumber.setText(String.valueOf(player.XP));
        playerPanel.setVisible(true);
        actionPanel.setVisible(false);

        //Displays player's possible actions and map
        move(map, player);

    }

    public void move(Map map, Player player) {

            actionPanel.setVisible(true);
            dialogContent.setText("Avancez sur la carte jusqu'à atteindre le drapeau:\n-------------------------\n");

            createActionButton(choice1);
            choice1.setText("Haut");
            choice1.setActionCommand("1");
            choice1.addActionListener(new MoveAction());

            createActionButton(choice2);
            choice2.setText("Bas");
            choice2.setActionCommand("2");
            choice2.addActionListener(new MoveAction());

            createActionButton(choice4);
            choice4.setText("Gauche");
            choice4.setActionCommand("4");
            choice4.addActionListener(new MoveAction());

            createActionButton(choice3);
            choice3.setText("Droite");
            choice3.setActionCommand("3");
            choice3.addActionListener(new MoveAction());

            createActionButton(choice5);
            choice5.setText("Changer d'arme");
            choice5.setActionCommand("5");
            choice5.addActionListener(new MoveAction());

            createActionButton(choice6);
            choice6.setText("Manger");
            choice6.setActionCommand("6");
            choice6.addActionListener(new MoveAction());
    }

    public void endGame() {
        var dialog = "";
        dialog +="Malheureusement, vous n'avez pas survécu à votre périple...\n\n";
        dialog +="Vous avez perdu.";
        dialogContent.setText(dialog);
    }

    public void createActionButton (JButton btn) {
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
        btn.setFont(normalFont);
        actionPanel.add(btn);
    }

    //Sets user's gender depending on which button they clicked
    //Displays interface to choose a class
    public class GenderAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "firstChoice":
                    playerGender = Gender.FEMALE;
                    break;

                case "secondChoice":
                    playerGender = Gender.MALE;
                    break;

                case  "thirdChoice":
                    playerGender = Gender.OTHER;
                    break;
            }
            actionPanel.remove(firstChoice);
            actionPanel.remove(secondChoice);
            actionPanel.remove(thirdChoice);
            actionPanel.setVisible(false);
            ChooseClass();
        }
    }

    //Sets user class
    //Displays 3rd introduction dialog
    public class ClassAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "firstChoice":
                    player = new Student(playerName, playerGender);
                    break;

                case "secondChoice":
                    player = new SalaryMan(playerName, playerGender);
                    break;

                case  "thirdChoice":
                    player = new Politician(playerName, playerGender);
                    break;

                case  "fourthChoice":
                    player = new JOPlayer(playerName, playerGender);
                    break;

                case  "fifthChoice":
                    player = new Tourist(playerName, playerGender);
                    break;
            }
            actionPanel.remove(firstChoice);
            actionPanel.remove(secondChoice);
            actionPanel.remove(thirdChoice);
            actionPanel.remove(fourthChoice);
            actionPanel.remove(fifthChoice);
            actionPanel.setVisible(false);
            YvanDialog3();
        }
    }

    //Displays result of user action or movement on interface
    public class MoveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //ends game if player died before reaching the end of the map
            if(player.HealthPoints > 0 && map.playerPos != 9)
            {
                var chosenDirection = e.getActionCommand();
                switch (chosenDirection) {
                    case "1" :
                        moveActionPanel = map.move(Direction.UP, player, actionPanel, dialogPanel);
                        break;
                    case "2" :
                        moveActionPanel = map.move(Direction.DOWN, player, actionPanel, dialogPanel);
                        break;
                    case "3":
                        moveActionPanel = map.move(Direction.RIGHT, player, actionPanel, dialogPanel);
                        break;
                    case "4":
                        moveActionPanel = map.move(Direction.LEFT, player, actionPanel, dialogPanel);
                        break;
                    case "5" :
                        moveActionPanel = player.ChangeWeapon(actionPanel, dialogPanel);
                        break;
                    case "6" :
                        moveActionPanel = player.Eat(actionPanel, dialogPanel);
                        break;
                }
                System.out.println(player.Weapon.ToString());
                System.out.println(e.getActionCommand());
                mapPanel.setVisible(false);
                mapContent.setEditable(true);
                mapContent.setText(map.print2D());
                mapContent.setEditable(false);
                mapPanel.setVisible(true);

                playerPanel.setVisible(false);
                hpLabelNumber.setText(String.valueOf(player.HealthPoints));
                xpLabelNumber.setText(String.valueOf(player.XP));
                weaponLabelNumber.setText(player.Weapon.ToString());
                playerPanel.setVisible(true);

                dialogPanel.setVisible(false);
                actionPanel.setVisible(false);
                window.add(moveActionPanel);
            }
            else endGame();
        }
    }
}

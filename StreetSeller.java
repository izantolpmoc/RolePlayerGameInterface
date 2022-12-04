import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StreetSeller {

    public StreetSeller(String name, ArrayList inventory)
    {
        Name = name;
        Inventory = inventory;
    }

    private String Name;
    protected ArrayList<Item> Inventory = new ArrayList();

    public JPanel Sell(Player player, JPanel actionPanel, JPanel dialogPanel, JPanel currentActionPanel) {

        //REGION LAYOUT
        JPanel inventory = new JPanel();
        inventory.setVisible(false);
        inventory.setBackground(Color.black);
        inventory.setForeground(Color.white);

        JTextArea inventoryContent = new JTextArea();
        inventoryContent.setPreferredSize(new Dimension(300, 200));
        inventoryContent.setBackground(Color.black);
        inventoryContent.setForeground(Color.white);
        inventoryContent.setFont(new Font("Monospaced", Font.PLAIN, 18));
        inventoryContent.setLineWrap(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.red);
        buttonsPanel.setLayout(new GridLayout(6, 2));

        inventory.add(inventoryContent);
        inventory.add(buttonsPanel);
        inventory.setVisible(true);
        //END REGION

        String text = "";
        text += "Votre argent: " + player.Money + "€\n";
        text += "-------------------------\n";
        inventoryContent.setText(text);

        for (int i = 0; i<Inventory.size(); i++) {

            JButton btn = new JButton(Inventory.get(i).Name + ": " + Inventory.get(i).Price + "€");
            btn.setBackground(Color.black);
            btn.setForeground(Color.white);
            btn.setFont(new Font("Monospaced", Font.PLAIN, 18));
            buttonsPanel.add(btn);
            btn.addActionListener(new SellerAction(i, this, player, inventoryContent, inventory, buttonsPanel, actionPanel, dialogPanel, currentActionPanel));

            if(Inventory.get(i).Price > player.Money)
                btn.setEnabled(false);

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
            currentActionPanel.setVisible(false);
            dialogPanel.setVisible(true);
            actionPanel.setVisible(true);
        });

        return inventory;
    }

    public JPanel Buy(Player player, JPanel actionPanel, JPanel dialogPanel, JPanel currentActionPanel) {
        //REGION LAYOUT
        JPanel buyInventory = new JPanel();
        buyInventory.setVisible(false);
        buyInventory.setBackground(Color.black);
        buyInventory.setForeground(Color.white);

        JTextArea inventoryContent = new JTextArea();
        inventoryContent.setPreferredSize(new Dimension(300, 50));
        inventoryContent.setBackground(Color.black);
        inventoryContent.setForeground(Color.white);
        inventoryContent.setFont(new Font("Monospaced", Font.PLAIN, 18));
        inventoryContent.setLineWrap(true);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.black);
        buttonsPanel.setLayout(new GridLayout(6, 2));

        JScrollPane scroll = new JScrollPane(buttonsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(800, 200));
        scroll.setVisible(true);

        buyInventory.add(inventoryContent);
        buyInventory.add(scroll);
        buyInventory.setVisible(true);
        //END REGION
        
        String text = "";
        text += "Votre argent: " + player.Money + "€\n";
        text += "-------------------------\n";
        inventoryContent.setText(text);


        for (int i = 0; i < player.Inventory.size(); i++) {
            //Displays all player's items except map
            if(!(player.Inventory.get(i) instanceof Map))
            {
                JButton btn = new JButton(player.Inventory.get(i).Name + ": " + player.Inventory.get(i).Price + "€");
                btn.setBackground(Color.black);
                btn.setForeground(Color.white);
                btn.setFont(new Font("Monospaced", Font.PLAIN, 18));
                buttonsPanel.add(btn);
                btn.setActionCommand("buy");
                btn.addActionListener(new SellerAction(i, this, player, inventoryContent, buyInventory, buttonsPanel, actionPanel, dialogPanel, currentActionPanel));
            }
        }
        JButton btn1 = new JButton("Annuler");
        btn1.setBackground(Color.black);
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Monospaced", Font.PLAIN, 18));
        buttonsPanel.add(btn1);
        btn1.addActionListener(e -> {
            buyInventory.setVisible(false);
            inventoryContent.setVisible(false);
            buttonsPanel.setVisible(false);
            currentActionPanel.setVisible(false);
            dialogPanel.setVisible(true);
            actionPanel.setVisible(true);
        });

        return buyInventory;
    }

}

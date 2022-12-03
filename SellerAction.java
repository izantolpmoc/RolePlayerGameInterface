import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerAction implements ActionListener {
    int itemIndex;
    StreetSeller seller;
    Player player;
    JTextArea inventoryContent;
    JPanel inventoryPanel, buttonsPanel, actionPanel, dialogPanel, currentActionPanel;

    public SellerAction(int i, StreetSeller s, Player p, JTextArea inventoryContent, JPanel inventoryPanel, JPanel buttonsPanel, JPanel actionPanel, JPanel dialogPanel, JPanel currentActionPanel){
        this.itemIndex = i;
        this.player = p;
        this.seller = s;
        this.inventoryContent = inventoryContent;
        this.inventoryPanel = inventoryPanel;
        this.buttonsPanel = buttonsPanel;
        this.actionPanel = actionPanel;
        this.dialogPanel = dialogPanel;
        this.currentActionPanel = currentActionPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "buy") {
            var item = player.Inventory.get(itemIndex);
            seller.Inventory.add(item);
            player.Inventory.remove(item);
            player.ReceiveMoney(item.Price);
        }

        else {
            var item = seller.Inventory.get(itemIndex);
            if (player.Money >= item.Price)
            {
                player.Inventory.add(item);
                seller.Inventory.remove(item);
                player.Money -= item.Price;
            }
        }


        inventoryPanel.setVisible(false);
        inventoryContent.setVisible(false);
        buttonsPanel.setVisible(false);
        currentActionPanel.setVisible(false);
        dialogPanel.setVisible(true);
        actionPanel.setVisible(true);
    }
}
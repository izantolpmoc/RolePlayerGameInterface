import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryAction implements ActionListener {
    int itemIndex;
    Player player;
    JTextArea inventoryContent;
    JPanel inventoryPanel, buttonsPanel, actionPanel, dialogPanel;

    public InventoryAction(int i, Player p, JTextArea inventoryContent, JPanel inventoryPanel, JPanel buttonsPanel, JPanel actionPanel, JPanel dialogPanel){
        this.itemIndex = i;
        this.player = p;
        this.inventoryContent = inventoryContent;
        this.inventoryPanel = inventoryPanel;
        this.buttonsPanel = buttonsPanel;
        this.actionPanel = actionPanel;
        this.dialogPanel = dialogPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "eat") {
            var item = player.Inventory.get(itemIndex);

            Consumable consumable = (Consumable) item;
            player.Heal(consumable.HealthPointsRestored);
            player.Inventory.remove(item);
        }

        else {
            var weapon = player.Inventory.get(itemIndex);

            if (player.Inventory.contains(weapon) || (weapon.toString() == new BareHands().toString()))
                player.Weapon = (Weapon) weapon;
        }


        inventoryPanel.setVisible(false);
        inventoryContent.setVisible(false);
        buttonsPanel.setVisible(false);
        dialogPanel.setVisible(true);
        actionPanel.setVisible(true);
    }
}
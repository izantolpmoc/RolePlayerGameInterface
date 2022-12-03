import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                System.out.println("tester");
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                System.out.println("2test2");
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println("3test3");
            }
            return false;
        }
    }
    public MyFrame(String title) {
        super(title);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }
}
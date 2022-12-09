package damas;
import damas.presentacion.damasGUI;

/**
 * @author Nicolas Castro
 * @author Marco Alvarez
*/
public class Main {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new damasGUI().setVisible(true);
            }
        });
    }
}

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener implements MouseListener {
    Simulator simulator;

    public CustomMouseListener(Simulator simulator){
        this.simulator = simulator;
    }

    public void mouseClicked(MouseEvent e) {
        simulator.receiveInput(e);
    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}

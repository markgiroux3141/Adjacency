import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    Simulator simulator;

    public DrawPanel(Simulator simulator){
        this.simulator = simulator;
    }

    public void paintComponent(Graphics g){
        g.clearRect(0,0, DrawGraphics.WINDOW_WIDTH, DrawGraphics.WINDOW_HEIGHT);
        simulator.drawFloorPlan(g);
        if(simulator.getCoords() != null) simulator.drawCoords(g);
        if(simulator.getLines() != null) simulator.drawAdjacency(g);
        if(simulator.getSensorList() != null) simulator.drawSensorList(g);
    }

    public void update(){
        simulator.run();
    }
}

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Simulator {
    public static final int MOUSE_X_OFFSET = -9;
    public static final int MOUSE_Y_OFFSET = -38;
    public static final int SENSOR_LIST_X = 10;
    public static final int SENSOR_LIST_Y = 20;
    public static final int SENSOR_LIST_SPACING = 15;

    public enum STATE{
        IDLE,
        COLLECTING_COORDS
    }

    InputEvent inputEvent;
    BufferedImage floorPlan;
    DrawPanel drawPanel;
    STATE state = STATE.IDLE;
    List<Point> coords = new ArrayList<>();
    float[][] adjacencyMatrix;
    List<Line> lines = new ArrayList<>();
    float threshold = 0.75f;
    String[] sensorList;

    public Simulator(){

    }

    public void run(){

    }

    public void drawFloorPlan(Graphics g){
        if(floorPlan != null){
            g.drawImage(floorPlan, 0, 0, drawPanel);
        }
    }

    public void drawCoords(Graphics g){
        coords.forEach(c ->{
            GraphicsHelper.drawCircle(c.x,c.y,10,g);
        });
    }

    public void drawSensorList(Graphics g){
        for(int i=0;i<sensorList.length;i++){
            g.drawString(sensorList[i], SENSOR_LIST_X, SENSOR_LIST_Y + i * SENSOR_LIST_SPACING);
        }
    }

    public void drawAdjacency(Graphics g){
        lines.forEach(l -> {
            float val = l.getValue();
            g.setColor(new Color(1 - val, 1 - val, 1 - val));
            if(l.getValue() >= threshold) g.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
        });
        g.setColor(new Color(0,0,0));
    }

    public void calculateLines(){
        for(int i=0;i<coords.size();i++){
            for(int n = i+1; n<coords.size();n++){
                lines.add(new Line(coords.get(i), coords.get(n), adjacencyMatrix[i][n]));
            }
        }
    }

    public void captureCoords(){
        MouseEvent e = (MouseEvent)inputEvent;
        int x = e.getX() + MOUSE_X_OFFSET;
        int y = e.getY() + MOUSE_Y_OFFSET;

        if(e.getButton() == MouseEvent.BUTTON1) {
            coords.add(new Point(x,y));
        }
    }

    public void receiveInput(InputEvent e){
        this.inputEvent = e;
        if(state == STATE.COLLECTING_COORDS){
            captureCoords();
        }
        drawPanel.repaint();
    }

    public BufferedImage getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(BufferedImage floorPlan) {
        this.floorPlan = floorPlan;
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public void setDrawPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public List<Point> getCoords() {
        return coords;
    }

    public void setCoords(List<Point> coords) {
        this.coords = coords;
    }

    public float[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(float[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public String[] getSensorList() {
        return sensorList;
    }

    public void setSensorList(String[] sensorList) {
        this.sensorList = sensorList;
    }
}

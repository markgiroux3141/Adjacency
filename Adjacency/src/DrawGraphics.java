import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DrawGraphics extends JFrame implements ActionListener{
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;

    private Simulator simulator = new Simulator();
    private DrawPanel drawPanel = new DrawPanel(simulator);
    private JButton loadImage = new JButton("Load Image");
    private JButton captureSensorCoords = new JButton("Capture Sensor Coords");
    private JButton loadCsv = new JButton("Load CSV File");
    private JButton drawAdjacencies = new JButton("Draw Adjacencies");
    private JButton quitButton = new JButton("Quit");
    private JButton thresholdButton = new JButton("Threshold");
    private JTextField thresholdTextField = new JTextField();
    private CustomMouseListener customMouseListener = new CustomMouseListener(simulator);
    private BufferedImage image;
    private String csvFileName;
    private CsvFile csvFile;
    private boolean capturingCoords = false;

    public DrawGraphics(){
        super("Adjacency");
        simulator.setDrawPanel(drawPanel);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        JPanel paramPanel = new JPanel();
        paramPanel.setLayout((new GridLayout(30,3)));
        p.setLayout(new GridLayout(1,1));
        p.add(loadImage);
        p.add(captureSensorCoords);
        p.add(loadCsv);
        p.add(drawAdjacencies);
        p.add(quitButton);

        paramPanel.add(thresholdTextField);
        paramPanel.add(thresholdButton);
        this.addMouseListener(customMouseListener);
        cp.add(drawPanel, BorderLayout.CENTER);
        cp.add(p, BorderLayout.SOUTH);
        cp.add(paramPanel, BorderLayout.EAST);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);

        loadImage.addActionListener(this);
        captureSensorCoords.addActionListener(this);
        loadCsv.addActionListener(this);
        drawAdjacencies.addActionListener(this);
        quitButton.addActionListener(this);
        thresholdButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        Object s = e.getSource();
        if(s == captureSensorCoords){
            capturingCoords = !capturingCoords;
            if(capturingCoords){
                captureSensorCoords.setText("Stop Capturing Coords");
                simulator.setState(Simulator.STATE.COLLECTING_COORDS);
            }else{
                captureSensorCoords.setText("Capture Sensor Coords");
                simulator.setState(Simulator.STATE.IDLE);
            }
        }else if(s == loadImage){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File file = chooser.getSelectedFile();
                try{
                    image = ImageIO.read(file);
                    simulator.setFloorPlan(image);
                }catch (IOException ex) {
                    System.out.println("Image loading failure");
                }
                drawPanel.repaint();
            } else {
                // do when cancel
            }
        }else if(s == loadCsv){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                csvFileName = chooser.getSelectedFile().getAbsolutePath();
                csvFile = CSVReader.readCsv(csvFileName);
                simulator.setAdjacencyMatrix(Helper.convertCsvToMatrix(csvFile));
                simulator.setSensorList(csvFile.getHeader());
                drawPanel.repaint();
            } else {
                // do when cancel
            }
        }else if(s == drawAdjacencies){
            simulator.calculateLines();
            drawPanel.repaint();
        }else if(s == quitButton){
            System.exit(-1);
        }else if(s == thresholdButton){
            simulator.setThreshold(Float.parseFloat(thresholdTextField.getText()));
            drawPanel.repaint();
        }
    }

    public void run(){
        drawPanel.update();
    }

}

import javax.swing.*;
import java.awt.*;

public class TrackDesignUI extends JFrame {
    private static final int TRACK_WIDTH = 50;
    private static final int TRACK_HEIGHT = 400;
    private static final int OBSTACLE_SIZE = 50;
    private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 500;

    private JPanel trackContainer;
    private JSpinner numTracksSpinner;
    private JSlider trackLengthSlider;

    public TrackDesignUI() {
        setTitle("Interactive Track Design");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create track container
        trackContainer = new JPanel();
        trackContainer.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        trackContainer.setBackground(Color.LIGHT_GRAY);
        trackContainer.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        trackContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Use FlowLayout for horizontal arrangement
        add(trackContainer, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        numTracksSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        numTracksSpinner.addChangeListener(e -> renderTracks());
        controlPanel.add(new JLabel("Number of Tracks:"));
        controlPanel.add(numTracksSpinner);

        trackLengthSlider = new JSlider(100, 1000, TRACK_HEIGHT);
        trackLengthSlider.setMajorTickSpacing(200);
        trackLengthSlider.setPaintTicks(true);
        trackLengthSlider.setPaintLabels(true);
        trackLengthSlider.addChangeListener(e -> renderTracks());
        controlPanel.add(new JLabel("Track Length:"));
        controlPanel.add(trackLengthSlider);

        JButton addTrackButton = new JButton("Add Track");
        JButton removeTrackButton = new JButton("Remove Track");
        addTrackButton.addActionListener(e -> {
            int currentNumTracks = (int) numTracksSpinner.getValue();
            numTracksSpinner.setValue(currentNumTracks + 1);
            renderTracks(); // Update the UI after adding a track
        });
        removeTrackButton.addActionListener(e -> {
            int currentNumTracks = (int) numTracksSpinner.getValue();
            if (currentNumTracks > 1) {
                numTracksSpinner.setValue(currentNumTracks - 1);
                renderTracks(); // Update the UI after removing a track
            }
        });
        controlPanel.add(addTrackButton);
        controlPanel.add(removeTrackButton);

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Initial rendering
        renderTracks();
    }

    private void renderTracks() {
        trackContainer.removeAll();

        int numTracks = (int) numTracksSpinner.getValue();
        int trackLength = trackLengthSlider.getValue();

        for (int i = 0; i < numTracks; i++) {
            TrackPanel track = new TrackPanel(trackLength);
            trackContainer.add(track);
        }

        trackContainer.revalidate();
        trackContainer.repaint();
    }

    private static class TrackPanel extends JPanel {
        public TrackPanel(int length) {
            setPreferredSize(new Dimension(TRACK_WIDTH, length));
            setBackground(Color.YELLOW);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Add two obstacles (squares) adjacent to each other
            addObstacle();
            addObstacle();
        }

        public void addObstacle() {
            ObstaclePanel obstacle = new ObstaclePanel();
            add(obstacle);
        }
    }

    private static class ObstaclePanel extends JPanel {
        public ObstaclePanel() {
            setBackground(Color.RED);
            setPreferredSize(new Dimension(OBSTACLE_SIZE, OBSTACLE_SIZE));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrackDesignUI::new);
    }
}

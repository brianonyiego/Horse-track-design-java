# TrackDesignUI

## Overview

`TrackDesignUI` is a Java Swing application that provides an interactive interface for designing tracks. Users can adjust the number of tracks, change the track length, and dynamically add or remove tracks. Each track contains obstacles for added complexity.

## Features

- Adjustable number of tracks
- Adjustable track length
- Dynamic addition and removal of tracks
- Each track contains fixed obstacles
- Intuitive UI with sliders and spinners for easy configuration

## Code Description

### Main Class: `TrackDesignUI`

#### Constants

- `TRACK_WIDTH`: Width of each track
- `TRACK_HEIGHT`: Initial height of each track
- `OBSTACLE_SIZE`: Size of each obstacle
- `PANEL_WIDTH`: Width of the main panel
- `PANEL_HEIGHT`: Height of the main panel

```java
private static final int TRACK_WIDTH = 50;
private static final int TRACK_HEIGHT = 400;
private static final int OBSTACLE_SIZE = 50;
private static final int PANEL_WIDTH = 600;
private static final int PANEL_HEIGHT = 500;
```

#### Fields

- `trackContainer`: JPanel to hold track panels
- `numTracksSpinner`: JSpinner to adjust the number of tracks
- `trackLengthSlider`: JSlider to adjust the length of the tracks

```java
private JPanel trackContainer;
private JSpinner numTracksSpinner;
private JSlider trackLengthSlider;
```

#### Constructor

The constructor sets up the main JFrame and initializes the control panel and track container.

```java
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
```

### Rendering Tracks

The `renderTracks` method dynamically updates the track container based on the current number of tracks and track length.

```java
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
```

### Track Panel: `TrackPanel`

The `TrackPanel` class represents an individual track. It contains methods to add obstacles.

```java
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
```

### Obstacle Panel: `ObstaclePanel`

The `ObstaclePanel` class represents an obstacle within a track.

```java
private static class ObstaclePanel extends JPanel {
    public ObstaclePanel() {
        setBackground(Color.RED);
        setPreferredSize(new Dimension(OBSTACLE_SIZE, OBSTACLE_SIZE));
    }
}
```

### Main Method

The `main` method sets up the application to run on the Event Dispatch Thread (EDT).

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(TrackDesignUI::new);
}
```

## How to Run

1. Ensure you have Java installed on your machine.
2. Copy the code into a file named `TrackDesignUI.java`.
3. Compile the program using the command:
   ```bash
   javac TrackDesignUI.java
   ```
4. Run the program using the command:
   ```bash
   java TrackDesignUI
   ```

## Sample Output

When you run the program, a window will appear where you can:

- Adjust the number of tracks using the spinner.
- Adjust the length of the tracks using the slider.
- Add or remove tracks using the respective buttons.

## License

This code is provided for educational purposes and is free to use and modify.

---


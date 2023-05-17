import javax.swing.*;
import java.awt.*;

public class Event extends JButton{
    String name;
    int points;
    public Event(String name, int points){
        super();
        setLayout(new GridLayout(2,1));
        add(new JLabel("Name: " + name));
        add(new JLabel("Points: " + points));
        this.name = name;
        this.points=points;
        setSize(200,50);
        setMaximumSize(new Dimension(200,50));
        setMinimumSize(new Dimension(200,50));

    }
}

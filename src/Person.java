import javax.swing.*;
import java.awt.*;

public class Person extends JButton {
    String name;
    int id;
    int points;
    int grade;
    boolean[] events;
    public Person(){
        super();
    }
    public void setText(){
        removeAll();
        setLayout(new GridLayout(2,1));
        add(new JLabel("Name: " + name + ", ID: " + id + ","));
        add(new JLabel("Total Points: " + points + ", Grade: " + grade));
    }
}

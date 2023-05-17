import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;

public class EventCreationScreen extends JFrame {
    public void run(){
        setSize(200,250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextField f = new JTextField();
        JTextField f1 = new JTextField();
        JLabel l = new JLabel("Event Name");
        JLabel l1 = new JLabel("Points");
        JButton c = new JButton("Create Event");
        setLayout(new GridLayout(5,1));
        add(l);
        add(f);
        add(l1);
        add(f1);
        add(c);
        c.addActionListener(p ->{
            Main.createNewEvent(f.getText(),Integer.parseInt(f1.getText()));
            Dialog.showMessage("Event created");
        });
    }
}

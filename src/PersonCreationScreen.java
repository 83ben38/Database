import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;

public class PersonCreationScreen extends JFrame {
    public void run(){
        setSize(200,350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextField f = new JTextField();
        JTextField f1 = new JTextField();
        JComboBox<Integer> f2 = new JComboBox<>();
        f2.addItem(9);
        f2.addItem(10);
        f2.addItem(11);
        f2.addItem(12);
        JLabel l = new JLabel("Name");
        JLabel l1 = new JLabel("Id #");
        JLabel l2 = new JLabel("Grade");
        JButton c = new JButton("Add Person");
        setLayout(new GridLayout(7,1));
        add(l);
        add(f);
        add(l1);
        add(f1);
        add(l2);
        add(f2);
        add(c);
        c.addActionListener(p ->{
            Main.createNewPerson(f.getText(),Integer.parseInt(f1.getText()),(int)f2.getSelectedItem());
            Dialog.showMessage("Person added!");
        });
    }
}

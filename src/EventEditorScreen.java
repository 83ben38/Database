import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;

public class EventEditorScreen  extends JFrame {
    public void run(Event e){
        setSize(200,400);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel l = new JLabel("Search for people to add");
        JTextField t = new JTextField();
        JScrollPane p = new JScrollPane();
        JPanel[] pl = new JPanel[]{new JPanel()};
        p.setViewportView(pl[0]);
        JButton b = new JButton("Enter by id");
        c.ipadx = 200;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(l,c);
        c.gridy = 1;
        add(t,c);
        c.gridy = 2;
        c.gridheight = 5;
        c.ipady = 200;
        add(p,c);
        c.gridy = 7;
        c.ipady = 0;
        c.gridheight = GridBagConstraints.REMAINDER;
        add(b,c);
        pl[0].setLayout(new GridLayout(Main.totalPeople,1));
        pl[0].setSize(200,Main.totalPeople*50);
        for (Person s: Main.people) {
            s.setText();
            pl[0].add(s);
            final Event finalE = e;
            while (s.getActionListeners().length > 0){
                s.removeActionListener(s.getActionListeners()[0]);
            }
            s.addActionListener(r -> {
                Main.setEvent(finalE,s);
            Dialog.showMessage("Person added to event!");
            });
        }
        t.addActionListener(r ->{
            pl[0] = new JPanel();
            p.setViewportView(pl[0]);
            int z = 0;
            for (Person s: Main.people) {
                if (s.name.contains(t.getText())) {
                    pl[0].add(s);
                    z++;
                }
            }
            pl[0].setLayout(new GridLayout(z,1));
            pl[0].setSize(200,z*50);
        });
        b.addActionListener(o ->{
            int z = Dialog.getInteger("What id would you like to add?");
            for (int i = 0; i < Main.totalPeople; i++) {
                if (Main.people.get(i).id == z){
                    Main.setEvent(e,Main.people.get(i));
                    Dialog.showMessage("Person added to event!");
                    return;
                }
            }
            Dialog.showMessage("Id could not be found.");
        });
    }
}

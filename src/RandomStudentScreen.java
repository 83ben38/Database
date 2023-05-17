import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;

public class RandomStudentScreen extends JFrame {
    public void run(){
        JSlider gradeSelector = new JSlider(9,12);
        gradeSelector.setLabelTable(gradeSelector.createStandardLabels(1,9));
        gradeSelector.setPaintLabels(true);
        setLayout(new GridLayout(5,1));
        setSize(200,250);
        add(gradeSelector);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p = new JPanel();
        p.add(new JLabel("All grades"));
        JCheckBox r = new JCheckBox();
        p.add(r);
        add(p);
        JPanel z = new JPanel();
        z.add(new JLabel("Use points"));
        JCheckBox o = new JCheckBox();
        z.add(o);
        add(z);
        JPanel u = new JPanel();
        u.setLayout(new GridLayout(2,1));
        u.add(new JLabel("Minimum points"));
        JTextField f = new JTextField("0");
        u.add(f);
        add(u);
        JButton go = new JButton("Generate a random student");
        add(go);
        go.addActionListener(c ->{
            Person winner = Main.getRandomWinner(gradeSelector.getValue(),Integer.parseInt(f.getText()),o.isSelected(),r.isSelected());
            if (winner != null){
                Dialog.showMessage(winner.name + " won!");
            }
            else{
                Dialog.showMessage("No one fits those criteria.");
            }
        });
    }
}

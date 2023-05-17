import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Screen extends JFrame {
    public void run(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8,1));
        JButton b = new JButton("Add a new Person");
        add(b);
        b.addActionListener(e -> {
            new PersonCreationScreen().run();
        });
        JButton d = new JButton("Create a new Event");
        add(d);
        d.addActionListener(e -> {
            new EventCreationScreen().run();
        });
        setSize(200,400);
        JButton p =new JButton("Add people to events");
        add(p);
        p.addActionListener(e ->{
            new EventSelectorScreen().run();
        });
        JButton r = new JButton("Select a random student");
        add(r);
        r.addActionListener(e ->{
            new RandomStudentScreen().run();
        });
        JButton w = new JButton("Find the top scorer");
        add(w);
        w.addActionListener(e -> {
            int w2 = Dialog.getInteger("What grade would you like to find a winner from? (0 for all grades)");
            if (w2 < 9 || w2 > 12){
                Dialog.showMessage(Main.getTopScorer().name + " is the top scorer overall.");
                return;
            }
            Dialog.showMessage(Main.getTopScorer(w2).name + " is the top scorer from " + w2 + "th grade.");
        });
        JButton report = new JButton("Generate a report");
        add(report);
        report.addActionListener(e -> {
            new  ReportGeneratorScreen().run();
        });
        JButton delete = new JButton("Reset all data");
        add(delete);
        delete.addActionListener(e ->{
            Main.deleteData();
            Dialog.showMessage("Data reset.");
        });
        JButton mass = new JButton("Mass add people");
        add(mass);
        mass.addActionListener(e ->{
            Scanner evil = new Scanner(Dialog.getString("Paste data here."));
            while (evil.hasNext()){
                Main.createNewPerson(evil.next() + evil.next(),evil.nextInt(),evil.nextInt());
            }
        });
    }
}

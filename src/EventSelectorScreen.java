import javax.swing.*;
import java.awt.*;

public class EventSelectorScreen extends JFrame {
    public void run(){
        setSize(200,350);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel l = new JLabel("Search for an event to edit");
        JTextField t = new JTextField();
        final JPanel[] pl = {new JPanel()};
        JScrollPane p = new JScrollPane(pl[0]);
        c.ipadx = 200;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(l,c);
        c.gridy = 1;
        add(t,c);
        c.gridy = 2;
        c.ipady = 200;
        c.gridheight = GridBagConstraints.REMAINDER;
        add(p,c);
        pl[0].setLayout(new GridLayout(Main.totalEvents,1));
        pl[0].setSize(200,Main.totalEvents*50);
        for (Event s: Main.events) {
            pl[0].add(s);
            s.addActionListener(r -> new EventEditorScreen().run(s));
        }
        t.addActionListener(r ->{
            pl[0] = new JPanel();
            p.setViewportView(pl[0]);
            int k = 0;
            for (Event s: Main.events) {
                if (s.name.contains(t.getText())){
                    pl[0].add(s);
                    k++;
                }
            }
            pl[0].setLayout(new GridLayout(k,1));
            pl[0].setSize(200,k*50);
            pl[0].validate();
        });
    }
}

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import svu.csc213.Dialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportGeneratorScreen extends JFrame {
    public void run(){
        setLayout(new GridLayout(8,1));
        setSize(200,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Amount of points for prize 1:"));
        JTextField textField1 = new JTextField("0");
        add(textField1);
        add(new JLabel("Amount of points for prize 2:"));
        JTextField textField2 = new JTextField("1");
        add(textField2);
        add(new JLabel("Amount of points for prize 3:"));
        JTextField textField3 = new JTextField("2");
        add(textField3);
        JPanel button = new JPanel();
        button.add(new JLabel("Award multiple prizes"));
        JCheckBox button2 = new JCheckBox();
        button.add(button2);
        add(button);
        JButton go = new JButton("Generate report");
        add(go);
        go.addActionListener(e-> {
            try {
                File f = new File("./report.xlsx");
                f.delete();
                f.createNewFile();
                XSSFWorkbook w = new XSSFWorkbook();
                Sheet s = w.createSheet();
                Row r = s.createRow(0);
                String[] strings = new String[]{"Prize 1","ID","Prize 2","ID","Prize 3","ID"};
                for (int i = 0; i < strings.length; i++) {
                    r.createCell(i).setCellValue(strings[i]);
                }
                int prize1 = Integer.parseInt(textField1.getText());
                int prize2 = Integer.parseInt(textField2.getText());
                int prize3 = Integer.parseInt(textField3.getText());
                boolean multiple = button2.isSelected();
                ArrayList<Person> winners1 = new ArrayList<>();
                ArrayList<Person> winners2 = new ArrayList<>();
                ArrayList<Person> winners3 = new ArrayList<>();
                for (Person p: Main.people) {
                    if (p.points >= prize3){
                        winners3.add(p);
                        if (multiple){
                            winners2.add(p);
                            winners1.add(p);
                        }
                    }
                    else if (p.points >= prize2){
                        winners2.add(p);
                        if (multiple){
                            winners1.add(p);
                        }
                    }
                    else if (p.points >= prize1){
                        winners1.add(p);
                    }
                }
                for (int i = 1; i <= winners1.size() || i <= winners2.size() || i <= winners3.size(); i++) {
                    r = s.createRow(i);
                    if (i <= winners1.size()){
                        r.createCell(0).setCellValue(winners1.get(i-1).name);
                        r.createCell(1).setCellValue(winners1.get(i-1).id);
                    }
                    if (i <= winners2.size()){
                        r.createCell(2).setCellValue(winners2.get(i-1).name);
                        r.createCell(3).setCellValue(winners2.get(i-1).id);
                    }
                    if (i <= winners3.size()){
                        r.createCell(4).setCellValue(winners3.get(i-1).name);
                        r.createCell(5).setCellValue(winners3.get(i-1).id);
                    }
                }
                s = w.createSheet();
                strings = new String[]{"9th","ID","Points","","10th","ID","Points","","11th","ID","Points","","12th","ID","Points"};
                r = s.createRow(0);
                for (int i = 0; i < strings.length; i++) {
                    r.createCell(i).setCellValue(strings[i]);
                }
                ArrayList<Person> _9th = new ArrayList<>();
                ArrayList<Person> _10th = new ArrayList<>();
                ArrayList<Person> _11th = new ArrayList<>();
                ArrayList<Person> _12th = new ArrayList<>();
                for (Person p: Main.people) {
                    if (p.grade == 9){
                        _9th.add(p);
                    }
                    if (p.grade == 10){
                        _10th.add(p);
                    }
                    if (p.grade == 11){
                        _11th.add(p);
                    }
                    if (p.grade == 12){
                        _12th.add(p);
                    }
                }
                _9th.sort(Comparator.comparingInt(o -> o.points));
                _10th.sort(Comparator.comparingInt(o -> o.points));
                _11th.sort(Comparator.comparingInt(o -> o.points));
                _12th.sort(Comparator.comparingInt(o -> o.points));
                for (int i = 1; i <= _9th.size() || i <= _10th.size() ||i <= _11th.size() ||i <= _12th.size(); i++) {
                    r = s.createRow(i);
                    if (i <= _9th.size()){
                        r.createCell(0).setCellValue(_9th.get(i-1).name);
                        r.createCell(1).setCellValue(_9th.get(i-1).id);
                        r.createCell(2).setCellValue(_9th.get(i-1).points);
                    }
                    if (i <= _10th.size()){
                        r.createCell(4).setCellValue(_10th.get(i-1).name);
                        r.createCell(5).setCellValue(_10th.get(i-1).id);
                        r.createCell(6).setCellValue(_10th.get(i-1).points);
                    }
                    if (i <= _11th.size()){
                        r.createCell(8).setCellValue(_11th.get(i-1).name);
                        r.createCell(9).setCellValue(_11th.get(i-1).id);
                        r.createCell(10).setCellValue(_11th.get(i-1).points);
                    }
                    if (i <= _12th.size()){
                        r.createCell(12).setCellValue(_12th.get(i-1).name);
                        r.createCell(13).setCellValue(_12th.get(i-1).id);
                        r.createCell(14).setCellValue(_12th.get(i-1).points);
                    }
                }
                OutputStream writer = new FileOutputStream(f);
                w.write(writer);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(f);
            } catch (Exception ignored) {
                Dialog.showMessage("Something went wrong. Please try again.");
            }
        });
    }
}

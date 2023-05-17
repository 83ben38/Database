import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    /**
     * The total number of registered events*/
    public static int totalEvents = getTotalEvents();
    /**
     * The total number of registered people*/
    public static int totalPeople = getTotalPeople();
    /**
     * A list of all registered events*/
    public static ArrayList<Event> events = new ArrayList<>();
    /**
     * A list of all registered people*/
    public static ArrayList<Person> people = new ArrayList<>();
    public static void main(String[] args) {
        setUpPeople();
        setUpEvents();
        new Screen().run();
    }

    /**
     * Marks that a person attended an event*/
    public static void setEvent(Event e, Person p){
        //updates the data in the program
        if (!p.events[events.indexOf(e)]){
            p.points += e.points;
        }
        p.events[events.indexOf(e)] = true;
        //finds the file the data is stored in
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException o)
        {
            o.printStackTrace();
        }
        //gets the cell that needs to be changed
        Sheet sheet=wb.getSheetAt(0);
        Row row = sheet.getRow(people.indexOf(p) + 2);
        Cell cell = row.getCell(events.indexOf(e) + 4);
        //changes that cell
        cell.setCellValue(true);
        //updates the points
        cell = row.getCell(2);
        cell.setCellValue(p.points);
        //writes the new data on the sheet
        try
        {
            FileOutputStream fos = new FileOutputStream("./Data.xlsx");
            wb.write(fos);
        } catch(IOException o)
        {
            o.printStackTrace();
        }
    }

    /**
     * Sets up the people by reading the data */
    public static void setUpPeople(){
        for (int i = 0; i < totalPeople; i++) {
            people.add(readPersonData(i+1));
        }
    }
    /**
     * Sets up the events by reading the data */
    public static void setUpEvents(){
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        Row row1 = sheet.getRow(1);
        for (int i = 0; i < totalEvents; i++) {
            events.add(new Event(row.getCell(i+4).getStringCellValue(), (int) row1.getCell(i+4).getNumericCellValue()));
        }
    }
    /**
     * Returns the total events by reading the data */
    public static int getTotalEvents(){
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(1);
        return (int) cell.getNumericCellValue();
    }
    /**
     * Returns the total people by reading the data */
    public static int getTotalPeople(){
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(3);
        return (int) cell.getNumericCellValue();
    }
    /**
     * Adds and event to the events list and adds it to the data */
    public static void createNewEvent(String name, int value){
        Event e = new Event(name,value);
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("./Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException d)
        {
            d.printStackTrace();
        }
        totalEvents++;
        Sheet sheet=wb.getSheetAt(0);
        Row row =sheet.getRow(0);
        Cell cell = row.createCell(3+totalEvents);
        cell.setCellValue(name);
        row = sheet.getRow(1);
        cell = row.getCell(1);
        cell.setCellValue(totalEvents);
        cell = row.createCell(3+totalEvents);
        cell.setCellValue(value);
        for (int i = 0; i < totalPeople; i++) {
            boolean[] r = new boolean[totalEvents];
            System.arraycopy(people.get(i).events,0,r,0,r.length-1);
            r[r.length-1] = false;
            cell = sheet.getRow(2+i).createCell(3+totalEvents);
            cell.setCellValue(false);
            people.get(i).events = r;
        }
        try
        {
            FileOutputStream fos = new FileOutputStream("./Data.xlsx");
            wb.write(fos);
        } catch(IOException p)
        {
            p.printStackTrace();
        }
        events.add(e);
    }

    /**
     * Reads a persons data */
    public static Person readPersonData(int n){
        Person value = new Person();
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("./Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);
        Row row=sheet.getRow(n+1);
        Cell cell=row.getCell(0);
        value.name = cell.getStringCellValue();
        cell = row.getCell(1);
        value.id = (int)cell.getNumericCellValue();
        cell = row.getCell(2);
        value.points = (int)cell.getNumericCellValue();
        cell = row.getCell(3);
        value.grade = (int)cell.getNumericCellValue();
        value.events = new boolean[totalEvents];
        for (int i = 0; i < totalEvents; i++) {
            cell = row.getCell(4+i);
            value.events[i] = cell.getBooleanCellValue();
        }
        return value;
    }
    /**
     * Adds a person to the persons list and adds it to the data */
    public static void createNewPerson(String name, int id, int grade){
        Person value = new Person();
        Workbook wb=null;
        try
        {
            FileInputStream fis=new FileInputStream("./Data.xlsx");
            wb= new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        totalPeople++;
        Sheet sheet=wb.getSheetAt(0);
        Row row=sheet.createRow(totalPeople+1);
        Cell cell = row.createCell(0);
        cell.setCellValue(name);
        value.name = name;
        cell = row.createCell(1);
        cell.setCellValue(id);
        cell = row.createCell(2);
        cell.setCellValue(0);
        cell = row.createCell(3);
        cell.setCellValue(grade);
        value.id = id;
        value.points = 0;
        value.grade = grade;
        value.events = new boolean[totalEvents];
        for (int i = 0; i < totalEvents; i++) {
            cell = row.createCell(4+i);
            cell.setCellValue(false);
            value.events[i] = false;
        }
        cell = sheet.getRow(1).getCell(3);
        cell.setCellValue(totalPeople);
        try
        {
            FileOutputStream fos = new FileOutputStream("./Data.xlsx");
            wb.write(fos);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        people.add(value);
    }

    /**
     * Returns a random person based on the inputted parameters*/
    public static Person getRandomWinner(int grade, int minPoints, boolean points, boolean grades){
        int z = 0;
        for (Person p: people) {
            if ((grades || p.grade == grade) && p.points>=minPoints) {
                if (points){
                    z += p.points-minPoints;
                }
                else {
                    z++;
                }
            }
        }
        z = (int)(Math.random()*z);
        for (Person p: people) {
            if ((grades || p.grade == grade) && p.points>=minPoints) {
                if (points) {
                    z -= p.points;
                    z += minPoints;
                }
                else{
                    z--;
                }
                if (z < 1) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Returns the top scorer from the specified grade*/
    public static Person getTopScorer(int grade){
        int z = 0;
        Person p = null;
        for (Person person : people) {
            if (person.points > z && person.grade == grade) {
                p = person;
                z = person.points;
            }
        }
        return p;
    }
    /**
     * Returns the top scorer from all grades*/
    public static Person getTopScorer(){
        int z = 0;
        Person p = null;
        for (Person person : people) {
            if (person.points > z) {
                p = person;
                z = person.points;
            }
        }
        return p;
    }

    /**
     * Resets the data*/
    public static void deleteData(){
        String[][] table = new String[][]{{"Name","ID","Total Points","Grade"},{"Total Events:", "0", "Total People:", "0"}};
        try{
            File f = new File("./Data.xlsx");
            XSSFWorkbook w = new XSSFWorkbook();
            Sheet s = w.createSheet();
            for (int i = 0; i < table.length; i++) {
                Row r = s.createRow(i);
                for (int j = 0; j < table[i].length; j++) {
                    if (table[i][j].equals("0")){
                        r.createCell(j).setCellValue(0);
                    }
                    else {
                        r.createCell(j).setCellValue(table[i][j]);
                    }
                }
            }
            w.write(new FileOutputStream(f));
        }
        catch(Exception ignored){

        }
    }
}
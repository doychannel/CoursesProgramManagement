package manager;

import console.menu;
import tool.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class learnerList extends ArrayList<learner> {
   

    public void function3() throws ParseException {
        // add, update, delete,display all topics
        
        String[] options = { "Add Learners", "Enter Score", "Display Learner Info",
                "Back to MAIN MENU" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
               addLearner();
                break;
                case 2:
                enterScore();
                    break;
                case 3:
            displayLearner();
                    break;
        
                default:

                    System.out.println("Function 3:LEARNER-MENU BACK!!!");

                    break;
            }
        } while (choice != options.length);
    }

   
    public void saveToLearnerFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                System.out.println("FILE Not created, please create one");
            }
            if(this.size()==0) System.out.println("Empty file");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (learner l : this) {
                oos.writeObject(l);
            }
            oos.close(); fos.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  void loadFromLearnerFile(String fName) {
        
        if (this.size() > 0) {
            this.clear();
        }
        while (true) {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                System.out.println("FILE Not found");
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            learner l;
            while ((l = (learner) (ois.readObject())) != null) {
                this.add(l);
            }
            ois.close();
            fis.close();

            
        } catch (Exception e) {
             break;
        }
    }
    }

    public void addLearner() throws ParseException {
        courseList course = new courseList();
        course.loadFromCourseFile("src/fileio/course.dat");
        Scanner sc = new Scanner(System.in);
        
        String[] options = {"Add learner", "Back to Function LEARNER "};
        
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                String courseID = Validation.checkString("Enter course ID to add Learner to: ");
                for(course c:course){
                    if (!c.getId().equalsIgnoreCase(courseID)) {
                        System.out.println("Course not found"); continue;
                    } else {
                        if(c.getSize()>35){
                            System.out.println("Class is full cannot add more learners");
                        }
                        else
                        {

                        System.out.println("Class still available(<=35)");
                        
                        System.out.println("Class INFO:");
                        System.out.println(c);
                        
                        String id = "";
                        do {
                            id = Validation.checkString("Enter learner id: ");
                        } while (isExistIdPerCourse(id));

                        String name = Validation.checkString("Enter learner name: ");

                        String dob = "";
                        do {
                            System.out.println("Enter date of birth (yyyy/MM/dd): ");
                            dob = sc.nextLine();
                        } while (!Validation.checkDate(dob));

                       double score = Validation.checkScore("Enter score for this course: ");

                            c.addLearner(new learner(id, name, new SimpleDateFormat("yyyy/MM/dd").parse(dob), score));
                            System.out.println("Learner added to course successfully, remember to SAVE changes BEFORE choosing other funcs hihihi!");

                            course.add(c);

                            this.add(new learner(id, name, new SimpleDateFormat("yyyy/MM/dd").parse(dob), score));
                           
                        }
                    }
                         break;
                    }
                   
                
                    break;
                default:
                    System.out.println("RETURNED: ADD MENU");
                    break;
            }
        } while (choice != options.length);
    }

   

    public void enterScore() {
        courseList course = new courseList();
        course.loadFromCourseFile("src/fileio/course.dat");

        String options[] = {"Enter scores ", "Back to Function LEARNER"};
        int choice = 0;

        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    String id = Validation.checkString("Enter learner id: ");
                    boolean learnerFound=false;
                    for(course c:course){
                        if(c.getLearner(id)!=null){
                            learnerFound=true;
                            double oldScore = c.getLearner(id).getScores();
                            double score = Validation.checkFloat("Enter score for this course " + c.getName()+" : ");
                            c.getLearner(id).setScores(score);
                            System.out.println("Score added successfully!");
                            System.out.println("Old score: " + oldScore);
                            System.out.println(" Updated score: " + score);
                            
                            break;
                        }
                        if(!learnerFound)   System.out.println("Learner not found!");
                    }
                   
                    
                    break;
                default:
                    System.out.println("EXIT!"); break;
            }

        } while (choice!= options.length);

    }

    public void displayLearner() {
        courseList course = new courseList();
        course.loadFromCourseFile("src/fileio/course.dat");

        if (this.size() == 0) {// check learner list (add the same time with course)
            System.out.println("No learners to display!");
        } 
        else {
            double sum = 0.0f;
            int count = 0;
            System.out.println("List of data of learners: ");
           //calualte the average score of all courses for each learner_DuyDo
           String id=Validation.checkString("Enter learner id: ");     
           for(course c:course){
                    
                    if(c.getLearner(id)!=null){
                        System.out.println("Score of "+c.getName() + "  :"+ c.getLearner(id).getScores());
                        sum += c.getLearner(id).getScores();
                        count++;
                    }
                }
                
                if (count > 0) {
                    double average = sum / count;
                    if (average < 4) {
                        System.out.println("Average score of all courses: " + average + " (Fail)");
                    } else {
                        System.out.println("Average score of all courses: " + average + " (Passed)");
                    }
                } else {
                    System.out.println("Learner not found in any course.");
                }
            }
            
        }
    

    

    public boolean isExistIdPerCourse(String id) {
        courseList course = new courseList();
        course.loadFromCourseFile("src/fileio/course.dat");
        for(course c: course){
            if(c.getLearner(id)!=null){
                return true;
            }
           
        }

        return false;
    }
}

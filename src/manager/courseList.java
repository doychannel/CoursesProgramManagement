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
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class courseList extends ArrayList<course> {

    public void function2() throws ParseException{
        //add, update, delete,display all topics
        String[] options ={"Add Course", "Update Course", "Delete Course", "Display Course in beginDate asc order", "Back to MAIN MENU"};
        int choice = 0;
      
        do{
            choice=menu.getChoice(options);
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    upDateCourse();
                    break;
                case 3:
                deleteCourse();
                    break;
                case 4:
                    displayCourse();
                    break;
                default:
                   
                System.out.println("Function 2: COURSE- MENU BACK!!!");

                    break;
            }
        } while(choice!=options.length);
    }

    public void saveToCourseFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists())
                System.out.println("FILE Not created, please create one");
            if(this.size()==0) System.out.println("Empty file");
            
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            
            for (course r : this) {
                ois.writeObject(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  void loadFromCourseFile(String fName) {
        
        if (this.size() > 0)
            this.clear();

            while (true){
        try {
            File f = new File(fName);
            if (!f.exists())
                System.out.println("FILE Not found");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            course r;
            while ((r = (course) (ois.readObject())) != null) {
                this.add(r);
            }
        } catch (Exception e) {
            break;
        }
    }
    }
    
    
    public void searchCourse(){
        String options[]={"Seach by topic ID/code","Search by Name of the course","Back to Function 4:SEARCH"};
        int choice =0;
        do{
            choice=menu.getChoice(options);
        switch(choice){
            case 1:
            String topicId =Validation.checkString("Enter RIGHT topic ID/code of the course:");
            boolean found=false;
            for(course t:this){
                
                if(t.getTopicId().equalsIgnoreCase(topicId)){
                    System.out.println(t);
                    found=true;
                    break;
                }
                
            }
            if(!found){
                System.out.println("Not found course with topic id "+topicId);
            }
        
            break;

            case 2:
            String name = Validation.checkString("Enter WHOLE/PART OF name of the course:");
            boolean afound=false;
            for(course t:this){
               
                if(t.getName().toLowerCase().contains(name.toLowerCase())){
                    
                    System.out.println(t);
                    afound=true;
                    break;
                }
                
            }
            if(!afound){
                System.out.println("Not found course with name "+name);
            }
            break;
                
            default:
                System.out.println("EXIT");
        }
        }while(choice!=options.length);
    }

    public void addCourse(course t) {
       
        if(getCourse(t.getId())==null){
            this.add(t);
        }
        else{
            System.out.println("Course already exist");
        }
    }

    public void addCourse() throws ParseException {
        Scanner sc = new Scanner(System.in);
        String[] options = { "Add course", "Back to Function 2: Course" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    
                        String id = "";
                        do {
                            id = Validation.checkString("Enter course id: ");
                        } while (isExistCourseId(id));

                        String name = Validation.checkString("Enter name: ");
                        
                        String type = Validation.checkCourseType("Enter type(online/offline): ");
                        
                        String title = Validation.checkString("Enter title: ");
                        
                        String beginD = "";
                        do {
                            System.out.println("Enter Begin Date (yyyy/MM/dd)");
                            beginD = sc.nextLine();
                        } while (!Validation.checkDate(beginD));

                        String endD = "";
                        do {
                            System.out.println("Enter End Date (yyyy/MM/dd)");
                            endD = sc.nextLine();
                        } while (!Validation.checkDate(endD));

                        double tuition = Validation.checkFloat("Enter tuition fee: ");

                        String topicID=Validation.checkString("Enter topic id: ");

                        boolean status=true;

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                        addCourse(new course(id, name, type, title, sdf.parse(beginD), sdf.parse(endD), tuition, topicID,status));
                     
                    break;
                default:
                    System.out.println("RETURNED: ADD MENU");
                    break;
            }
        } while (choice != options.length);
    }

    public void upDateCourse() throws ParseException {
        Scanner sc = new Scanner(System.in);
        String[] options = { "Update course", "Back to Function 2: Course" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    String id = Validation.checkString("Enter course id: ");                   
                    course t = getCourse(id);
                    
                    if (t == null) {
                        System.out.println("Not found topic!");
                    } 
                    
                    else {
                        System.out.println("UPDATE YOUR COURSE: Enter to keep old value"); 
                        course old = t;// save previous value

                        String newcode = "";
                        do {
                            System.out.println("Enter new ID:");
                            newcode = sc.nextLine();
                            if (newcode.isEmpty()) {
                                newcode = t.getId();
                                 break;
                            }
                        } while (isExistCourseId(newcode));

                        System.out.println("Enter new name:");
                        String name="";
                        do{
                         name = sc.nextLine();
                        if (name.isEmpty())
                            name = t.getName();
                        }
                        while(!name.matches("[A-Za-z0-9]"));
                        
                        String type = "";
                        do {
                            System.out.println("Enter new type: online/offline");
                            type = sc.nextLine();
                            if (type.isEmpty()) {
                                type = t.getType();
                                 break;
                            }
                        } while (!type.equalsIgnoreCase("online") && !type.equalsIgnoreCase("offline"));

                        System.out.println("Enter new title:");
                        String title="";
                        do{
                            title = sc.nextLine();
                        if (title.isEmpty())
                        title = t.getName();
                        }
                        while(!title.matches("[A-Za-z0-9]"));

                            
                        String beginD = "";
                     
                            System.out.println("Enter new begin date: format yyyy/MM/dd");
                            beginD = sc.nextLine();
                            if (beginD.isEmpty()){
                                beginD = t.getBeginD().toString();
                                break;
                            }
                        

                        String endD = "";
                       
                            System.out.println("Enter new end date: format yyyy/MM/dd");
                            endD = sc.nextLine();
                            if (endD.isEmpty()){
                                endD = t.getEndD().toString();
                                break;
                            }
                    

                        System.out.println("Enter new tuition fee: double value:");
                        String tuition="";
                        do{
                         tuition = sc.nextLine();
                        if (tuition.isEmpty())
                            tuition = String.valueOf(t.getTuition());
                        }while (String.valueOf(tuition).compareTo("0")<0|| tuition.matches("[a-zA-Z]+"));
                        //check if tuition is not a double value

                        System.out.println("Enter new topic id:");
                    
                        String topicId="";
                        do{
                            topicId = sc.nextLine();
                        if (topicId.isEmpty())
                        topicId = t.getName();
                        }
                        while(!topicId.matches("[A-Za-z0-9]"));
                        
                            System.out.println("Enter new status:");
                            String status = "true";
                            do{
                                status = sc.nextLine();
                            if (status.isEmpty())
                                status = String.valueOf(t.getStatus());
                                break;
                            }
                            while(!status.contentEquals("true") && !status.contentEquals("false"));
                                
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                    t.setCourseId(newcode);
                    t.setName(name);
                    t.setType(type);
                    t.setTitle(title);
                    t.setBeginD(sdf.parse(beginD));
                    t.setEndD(sdf.parse(endD));
                    t.setTopicId(topicId);
                    t.setTuition(Double.valueOf(tuition));
                    t.setStatus(Boolean.getBoolean(status));


                    System.out.println("Update course successfully!");
                    System.out.println("Old course: " + old);
                    System.out.println("New course after updated: " + t);
                }
                    break;

                default:
                    System.out.println("RETURNED: Update MENU");
                    break;
            }
        } while (choice != options.length);
    }

    public void deleteCourse() {
        String[] options = { "Delete COURSE", "Back to Function 2: COURSE" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                Scanner sc= new Scanner(System.in);
                    String topicId = Validation.checkString("Enter topic code/id of the course to delete course: ");
                    
                    for(course t: this){
                    
                    if (!t.getTopicId().equalsIgnoreCase(topicId)) {
                        System.out.println("Not found course!");
                    } else {
                        boolean deleted = false;
                            System.out.println("Course found: "+t);
                            System.out.println("Confirm 1 to delete (set status to false)");
                        
                        int confirm = sc.nextInt();
                        if (confirm == 1) {
                            t.setStatus(false);
                            System.out.println("Delete course successfully! (status set to false)");
                            deleted = true;
                        }

                        if(!deleted)
                        System.out.println("Deletion cancelled!!!");
                        }
                        
                    }
                    
                
                    break;
                default:
                    System.out.println("RETURNED: Delete MENU");
                    break;
            }
        } while (choice != options.length);
    }
   
    public void displayCourse() {
        if (this.isEmpty()) {
            System.out.println("No course to display!");
        } else {
            System.out.println("List of course: ");
            Collections.sort(this, new Comparator<course>() {
                @Override
                public int compare(course t1, course t2) {
                    int d = t1.getBeginD().compareTo(t2.getBeginD());
                    if (d < 0)
                        return -1;
                    if (d > 0)
                        return 1;
                    return 0;
                }
            });
            for(course t : this){
                if(t.getStatus())
            System.out.println(t);
        }
    }
}

    public course getCourse(String id) {
        for (course t : this) {
            if (t.getId().equalsIgnoreCase(id)) {
                return t;
            }

        }
        return null;
    }

    public boolean isExistCourseId(String id) {
        return getCourse(id) != null;
    }

}

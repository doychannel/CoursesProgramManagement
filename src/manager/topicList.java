package manager;

import console.menu;
import tool.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class topicList extends ArrayList<topic> {
    public void function1(){
        //add, update, delete,display all topics
    
        String[] options ={"Add Topic", "Update Topic", "Delete Topic", "Display Topic in Ascending NAME order", "Back to MAIN MENU"};
        int choice = 0;
        
        do{
            choice=menu.getChoice(options);
            switch (choice) {
                case 1:
                
                    addTopic();
                    break;
                case 2:
                    upDateTopic();
                    break;
                case 3:
                    deleteTopic();
                    break;
                case 4:
                    displayTopic();
                    break;
                
                    
                default:
                   
                System.out.println("Function 1 MENU BACK!!!");

                    break;
            }
        } while(choice!=options.length);
    }

    public void saveToFileTopic(String fName){
        try{
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (topic r : this) {
                fo.writeObject(r);
            }
            fo.close();
            f.close();
            System.out.println("Saved Successfully!!");
        } catch (Exception e) {
            System.out.println(e);
        }
        }
        

    
    public void loadFromFile(String fName){ //load from Binary file
        
        if (this.size() > 0) {
            this.clear();
        }
        while(true){
        try{
            File f = new File(fName);
            if (!f.exists()) {
                System.out.println("Not exist file");
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream((fi));
            topic r;
            while ((r = (topic)(fo.readObject())) != null) {
                this.add(r);
            }
            fo.close();
            fi.close();
        }
        catch(Exception e) {break;}
        
        }
    }
    

    public void searchTopicByName(){
        
        String topicName=Validation.checkString("Enter topic name: ");
        boolean found=false;
        for(topic t:this){
            
            if(t.getName().toLowerCase().contains(topicName.toLowerCase())){
                System.out.println(t); 
                found=true;
                break;
            }
            
            }
            if(!found){
                System.out.println("Not found topic with name contains "+topicName);
        }
    }
    

    public void addTopic(topic topic) {
                if (getTopic(topic.getId()) == null) {
                    this.add(topic);
                    System.out.println("Topic added successfully.");
                } else {
                    System.out.println("Topic with this ID already exists.");
                }
            }

    public void addTopic() {
       
        String[] options = { "Add topic", "Back to Function 1" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    String id = "";
                    do {
                        id = Validation.checkString("Enter id: ");
                    } while (isExistId(id));

                    String name = Validation.checkString("Enter name: ");
                    String type = Validation.checkTopicType("Enter type(short/long): ");
                    String title = Validation.checkString("Enter title: ");
                    String duration = Validation.checkString("Enter duration: ");
                    
                    addTopic(new topic(id, name, type, title, duration));
                    break;
                default:
                    System.out.println("RETURNED: ADD MENU");
                    break;
            }
        } while (choice != options.length);
    }

    public void upDateTopic() {
        Scanner sc = new Scanner(System.in);
        String[] options = { "Update topic", "Back to Function 1" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                
                case 1:
                    String id = Validation.checkString("Enter id: ");
                    topic t = getTopic(id);
                    if (t == null) {
                        System.out.println("Not found topic!");
                    } else {
                        System.out.println("UPDATE YOUR TOPIC: ");
                        topic old = t;// save previous value

                        String newcode = "";
                        do {
                            System.out.println("Enter new ID:");
                            newcode = sc.nextLine();
                            if (newcode.isEmpty()) {
                                newcode = t.getId();
                                break;
                            }
                        } while (isExistId(newcode));

                        System.out.println("Enter new name:");
                        String name = sc.nextLine();
                        if(name.isEmpty()) name=t.getName();

                        String type = "";
                        do {
                            System.out.println("Enter new type:");
                            type = sc.nextLine();
                            if (type.isEmpty()) {
                                type = t.getType();
                                break;
                            }
                        } while (!type.equalsIgnoreCase("long") && !type.equalsIgnoreCase("short"));

                        System.out.println("Enter new title:");
                        String title = sc.nextLine();
                        if (title.isEmpty())
                                title = t.getTitle();

                        
                        System.out.println("Enter new duration:");
                        String duration = sc.nextLine();
                        if (duration.isEmpty())
                            duration = t.getDuration();

                        t.setId(newcode);
                        t.setName(name);
                        t.setType(type);
                        t.setTitle(title);
                        t.setDuration(duration);

                        System.out.println("Update topic successfully!");
                        System.out.println("Old topic: " + old);
                        System.out.println("New topic: " + t);
                        

                    }
                    break;
                default:
                    System.out.println("RETURNED: Function 1 MENU");
                    break;
            }
        } while (choice != options.length);
    }

    public void deleteTopic() {
        Scanner sc = new Scanner(System.in);
        String[] options = { "Delete topic", "Back to Function 1" };
        int choice = 0;
        do {
            choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    String id = Validation.checkString("Enter id: ");
                    topic t = getTopic(id);
                    if (t == null) {
                        System.out.println("Not found topic!");
                    } else {
                        System.out.println("Confirm 1 to delete");
                        int confirm = sc.nextInt();
                        if (confirm == 1) {
                            this.remove(t);
                            System.out.println("Delete topic successfully!");
                        }
                        
                        
                    }
                    System.out.println("Deletion cancelled!!!");
                    break;
                default:
                    System.out.println("RETURNED: Delete MENU");
                    break;
            }
        } while (choice != options.length);
    }

    public void displayTopic() {
        if (this.isEmpty()) {
            System.out.println("No topic to display!");
        } else {
            System.out.println("List of topics: ");
            Collections.sort(this, new Comparator<topic>() {
                @Override
                public int compare(topic t1, topic t2) {
                    int d= t1.getName().compareTo(t2.getName());
                    if(d<0) return -1;
                    if(d>0) return 1;
                    return 0;
                }
            });
            for(topic t : this)
            System.out.println(t);
        }
    }

    public topic getTopic(String id) {
        for (topic t : this) {
            if (t.getId().equals(id)) {
                return t;
            }

        }
        return null;
    }

    public boolean isExistId(String id) {
        return getTopic(id) != null;
    }

}

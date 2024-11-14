package console;

// import manager.ListAll;
import java.text.ParseException;
import java.util.Scanner;

import manager.courseList;
import manager.topicList;
import manager.learnerList;
import manager.topic;
 
public class main {
    public static void main(String[] args) throws ParseException {
       Scanner sc=new Scanner(System.in);

        System.out.println("LOVE YOU MISS VANTTN 3000XXXXXXXX");
        String[] options={"function 1: Manager the Topics",
        "function 2: Manager the Course",
            "function 3: Manager the Learner ",
            "function 4: Search information ", 
            "function 5: Save Topics, Courses and Learner to file.", "Exit"};
        
        String f="src/fileio/course.dat";
        String file="src/fileio/learner.dat";
        String file1="src/fileio/topic.dat";
        
        topicList topicList=new topicList();
        topicList.loadFromFile(file1);
        
        courseList courseList=new courseList();
        courseList.loadFromCourseFile(f);

        learnerList learnerList=new learnerList();
        learnerList.loadFromLearnerFile(file);

        int choice=0;
        do{
            System.out.println("Welcome to the Course Management System MADE BY DUY DO FOR SLEEPLESS NIGHTS");

            choice=menu.getChoice(options);
            switch(choice){
                case 1:
                topicList.function1();
                    break;
                case 2:
                courseList.function2();
                    break;
                case 3:
                learnerList.function3();
                    break;
                case 4: 
                function4();
                break;
                 
                case 5:
                if (topicList.size() > 0) {
                    System.out.println("\nSave changes to Topic File dat Y/N?");
                    String response = sc.nextLine().toUpperCase();
                    if (response.startsWith("Y")) {
                        topicList.saveToFileTopic(file1);
                    }
                }

                if(courseList.size()>0){
                    System.out.println("\nSave changes to Course File dat Y/N?");
                    String response=sc.nextLine().toUpperCase();
                    if(response.startsWith("Y")){
                        courseList.saveToCourseFile(f);;
                    }
                }

                if(learnerList.size()>0){
                    System.out.println("\nSave changes to Learner File dat Y/N?");
                    String response=sc.nextLine().toUpperCase();
                    if(response.startsWith("Y")){
                        learnerList.saveToLearnerFile(file);
                    }
                }
                
                    break;
                default:
                    System.out.println("Exit!!!");
                    break;
            }
        }while(choice!=options.length);
    }

    public static void function4(){
        String options[]={"Search Topic by Name(contains)","Search Course","Back to MAIN MENU"};
        int choice=0;
        topicList topicList=new topicList();
         topicList.loadFromFile("src/fileio/topic.dat");
        courseList courseList=new courseList(); 
        courseList.loadFromCourseFile("src/fileio/course.dat");
        do{
            choice=menu.getChoice(options);
            switch(choice){

            case 1:
            topicList.searchTopicByName();
            break;

            case 2:
            courseList.searchCourse();
            break; 
            default:
            System.out.println(" MENU BACK!!!");
            break;
        }
    }while(choice!=options.length);
}

}

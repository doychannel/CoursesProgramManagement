package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// import model.course;

public class Validation {

    /**
     * Show message and read a String from keyboard
     *
     * @param mess : String, message needs to show before read keyboard
     * @return String from keyboard
     */
    public static String checkCourseType(String mess) {

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(mess);
                String value = sc.nextLine();
                if (!value.equalsIgnoreCase("online") && !value.equalsIgnoreCase("offline") || value.isEmpty()) {
                    throw new Exception("Please input online/offline!");
                }
                return value;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String checkTopicType(String mess) {

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(mess);
                String value = sc.nextLine();
                if (!value.equalsIgnoreCase("long") && !value.equalsIgnoreCase("short") || value.isEmpty()) {
                    throw new Exception("Please input long or short (term)!");
                }
                return value;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String checkString(String mess) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                String value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception("Please input new value!");
                }

                return value;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static float checkFloat(String mess) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                float value = Float.parseFloat(sc.nextLine());
                if (value <= 0) {
                    throw new Exception("Please input greater than 0!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a float value!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static float checkScore(String mess){
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                float value = Float.parseFloat(sc.nextLine());
                if (value <= 0|| value>10) {
                    throw new Exception("Please input greater than 0 and less than 10!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a float value!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int checkInt(String mess) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                int value = Integer.parseInt(sc.nextLine());
                if (value <= 0) {
                    throw new Exception("Please input greater than 0!");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a int value!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String checkYesNo(String mess) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(mess);
                String value = sc.nextLine();
                if (value.isEmpty()) {
                    throw new Exception("Please input Y = Yes Or N = No!");
                }
                if (value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("N")) {
                    return value;
                } else {
                    throw new Exception("Please input Y = Yes Or N = No!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    
    public static boolean checkDate(String dateStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false); // Ensure strict date parsing

        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Manager {
    public static void loadFromFile(ArrayList<Contact> list) {
        File file = new File("Contact.txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    list.add(new Contact(parts[0], Long.parseLong(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Purana Data Nahi Mil paya:");
        }
    }
    public static void saveToFile(ArrayList<Contact> list){
        try (PrintWriter writer = new PrintWriter("Contact.txt")){
            for(Contact c : list){
                writer.println(c.name + "," + c.Phone);
            }
            System.out.println("Data File mein save ho gaya!");
        } catch (IOException e){
            System.out.println("File save krne mai error:" + e.getMessage());
        }
    }
        public static void main(String[] args){
        ArrayList<Contact> myContact = new ArrayList<>();
        loadFromFile(myContact);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\n----Smart Contact Manager Menu:---");
            System.out.println("1. naya Contect add krein:");
            System.out.println("2. Saare Contact dekhein");
            System.out.println("3.Contact Search karein:");
            System.out.println("4.Delete karne liye:");
            System.out.println("5. Number Update krne ke liye");
            System.out.println("6. Program band karein:");
            System.out.println("Apni choice chunein (1-6):");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Naam Likhein:");
                    String name = sc.nextLine();
                    System.out.println("Number likhein:");
                    long Phone = sc.nextLong();
                    myContact.add(new Contact(name, Phone));
                    System.out.println("Contact save ho gaya:");
                    sc.nextLine();
                    break;
                case 2:
                    System.out.println("----Aapki Contact List---");
                    for (Contact c : myContact) {
                        c.display();
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Dhoondne ke liye Naam Likhein:");
                    String searchName = sc.nextLine();
                    boolean found = false;
                    for (Contact c : myContact) {
                        if (c.name.equalsIgnoreCase(searchName)) {
                            System.out.println("Record mil Gaya:");
                            c.display();
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        System.out.println("Afsos! Ye naam nahi hai:");
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println(" Naam Likhein:");
                    String deleteName = sc.nextLine();
                    boolean removed = false;
                    for(int i = 0; i < myContact.size(); i++){
                        if(myContact.get(i).name.equalsIgnoreCase(deleteName)){
                            myContact.remove(i);
                            System.out.println("Contact delete ho gaya:");
                            removed = true;
                            break;
                        }
                    }
                    if(!removed){
                        System.out.println("Afsos! ye naam list main nhi hai:");
                    }
                    break;
                default:
                    System.out.println("Galat choice! Phir se Koshsih karein:");
                case 5:
                    sc.nextLine();
                    System.out.println("Kiska Number badalna hai:");
                    String UpdateName = sc.nextLine();
                    boolean isUpdated = false;
                    for(Contact c: myContact){
                        if(c.name.equalsIgnoreCase(UpdateName)){
                            System.out.println("Naya Number Likein:");
                            long newPhone = sc.nextLong();
                            c.Phone = newPhone;
                            System.out.println("Number Update ho gaya:");
                            isUpdated = true;
                            break;
                        }
                    }
                    if(!isUpdated) {
                        System.out.println("Afsos!  ye naam list mein nhi hai:");
                    }
                    break;
                case 6:
                    saveToFile(myContact);
                    System.out.println("Program Band ho rha hai....");
                    System.exit(1);
                    break;
            }

            }
        }
    }

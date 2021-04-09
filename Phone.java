package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Contacts {
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> number = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private boolean exit = false;

    public void options() {
        switch (optionsInput()) {
            case 0:
                optionsList();
                break;
            case 1:
                addNew();
                break;
            case 2:
                searchContact();
                break;
            case 3:
                modify();
                break;
            case 4:
                delete();
                break;
            case 5:
                showContacts();
                break;
            case 6:
                exitApp();
                break;
        }

    }
    private void delete() {
        System.out.println("Which Contact do you want to delete?");
        in.nextLine();
        String name = in.nextLine();
        if (isFound(name)) {
           delete(number,names.indexOf(name));
           delete(names,names.indexOf(name));
        } else {
            System.out.println("No Contact Found!!");
        }
    }
    private void delete(ArrayList arr,int poz) {
        arr.remove(poz);
    }
    private void modify() {
        System.out.println("Enter contact to modify:");
        in.nextLine();
        String name = in.nextLine();
        if (isFound(name)) {
            System.out.println("Modify Nuber or Name? \n" +
                    "1. Name \n" +
                    "2. Number");
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.println("Enter new Contact Name:");
                in.nextLine();
                modify(names,names.indexOf(name),in.nextLine());
            } else {
                System.out.println("Enter new Number: ");
                in.nextLine();
                modify(number,names.indexOf(name),in.nextLine());
            }
        }
        System.out.println("Contact updated successfully");
    }
    private void modify(ArrayList arr, int poz, String mod) {
        arr.set(poz,mod);
    }
    private void showContacts() {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + " \n" + number.get(i));
        }
    }
    public void optionsList() {
        System.out.println("0 Option list\n" +
                "1. Add New \n" +
                "2. Search Contact \n" +
                "3. Modify \n" +
                "4. Delete \n" +
                "5. Show contacts \n" +
                "6. Exit");
    }

    private void addNew() {
        System.out.println("Enter Contact name:");
        in.nextLine();
        String name = in.nextLine();
        boolean isModified = false;
        if(isFound(name)) {
             while (isFound(name) && !isModified) {
                 System.out.println("Contact already exists do you wish to modify existing one? \n" +
                        "1. Yes \n" +
                        "0. No \n");
                int answer = in.nextInt();
                if (answer == 1) {
                    System.out.println("new number:");
                    in.nextLine();
                    String num = in.nextLine();
                    modify(number, names.indexOf(name), num);
                    isModified = true;
                } else {
                    System.out.println("Enter new name:");
                    in.nextLine();
                    name = in.nextLine();
                }
            }
             if (!isModified){
                 names.add(name);
                 System.out.println("Enter Phone Number:");
                 number.add(in.nextLine());
             }
        } else {
            names.add(name);
            System.out.println("Enter Phone Number:");
            number.add(in.nextLine());
          }
    }


    private void exitApp() {
        this.exit = true;
    }

    public boolean isExit() {
        return exit;
    }
    public int optionsInput() {
        return in.nextInt();
    }
    private void searchContact() {
        System.out.println("Enter contact:");
        String search = in.nextLine();
        in.nextLine();
        if (isFound(search)) {
            int index = names.indexOf(search);
            System.out.println("Number: " + number.get(index));
        } else
            System.out.println("No Contact Found");
    }
    private boolean isFound(String search) {
        return names.contains(search);
    }
}

public class Phone {
    private Contacts contacts = new Contacts();

    public void startApp() {
        contacts.optionsList();
        while (!contacts.isExit()) {
            contacts.options();
        }
    }
}

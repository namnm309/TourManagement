package view;

import java.util.ArrayList;
import utils.Validation;

public class Menu<T> {

    // Get an user choice as an integer
    public int getIntChoice(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " - " + list.get(i));
        }
        System.out.println("------------------------------------");
        return Validation.readInteger("Please choose option from 1->" + list.size() + ": ", 1, list.size());
    }

    // Get an user choice as a object in the list
    public T getObjectChoice(ArrayList<T> list) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " - " + list.get(i));
        }
        System.out.println("------------------------------------");
        int choiceNo = Validation.readInteger("Please choose thing from 1->" + list.size() + ": ", 1, list.size());
        return (choiceNo > 0 && choiceNo <= n) ? list.get(choiceNo - 1) : null;
    }

}

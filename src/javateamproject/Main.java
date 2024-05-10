package javateamproject;

import javateamproject.display.MainDisplay;
import javateamproject.model.Score;

import java.util.*;

public class Main {


    public static void main(String[] args) {

        MainDisplay DM = new MainDisplay();

        try {
            DM.displayview();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

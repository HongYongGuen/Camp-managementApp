package javateamproject.display;

import java.util.Scanner;

public abstract class DisplayView {
    static Scanner sc = new Scanner(System.in);
    public abstract void displayview() throws InterruptedException;

}

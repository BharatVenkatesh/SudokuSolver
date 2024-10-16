import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        int x = 5;
        int[][] table = new int[9][9];
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<table[i].length; j++) {
                table[i][j] = (int)(9*Math.random())+1;
                System.out.print(table[i][j]);
                if((j+1)%3==0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if((i+1)%3==0) {
                System.out.println();
            }
        }
    }
}
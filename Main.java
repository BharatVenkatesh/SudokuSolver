import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] table = new int[9][9];
        try{
            //tries to find a file from the input, appends .txt
            File fil = new File(args[0]);
            Scanner fileReader = new Scanner(fil);

            //puts input from the file into the table, input must have spaces after every value and a new line when its a new row of the sudoku puzzle
            for(int i=0; i<table.length; i++) {
                for(int j=0; j<table[i].length; j++) {
                    table[i][j] = fileReader.nextInt();
                }
                if(fileReader.hasNextLine()) {
                    fileReader.nextLine();
                }
            }

            //prints out the table
            for(int i=0; i<table.length; i++) {
                for(int j=0; j<table[i].length; j++) {
                System.out.print(table[i][j]+" ");
                    if((j+1)%3==0 && j<7) {
                        System.out.print("| ");
                    }
                }
                System.out.println();
                if((i+1)%3==0 && i<7) {
                    System.out.println("---------------------");
                }
            }
            fileReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found ERROR");
            e.printStackTrace();
            return;
        }

        
        
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] table = new int[9][9];
        int[][][] notes = new int[9][9][9];
        try{
            //tries to find a file from the input, appends .txt
            File fil = new File(args[0]);
            Scanner fileReader = new Scanner(fil);

            //puts input from the file into the table, input must have spaces after every value and a new line when its a new row of the sudoku puzzle
            for(int i=0; i<table.length; i++) {
                for(int j=0; j<table[i].length; j++) {
                    table[i][j] = fileReader.nextInt();

                    //filling in notes array with null or possible values for the entry
                    if(table[i][j] == 0) {
                        for(int k=0; k<notes[i][j].length; k++) {
                            notes[i][j][k] = k+1;
                        }
                    }
                    else {
                        notes[i][j] = null;
                    }
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
        //Returns error if file not found
        catch (FileNotFoundException e){
            System.out.println("File not found ERROR");
            e.printStackTrace();
            return;
        }

        
        //iterates through the table and finds all non zero entries and adds it to an arraylist
        for(int i=0; i<table.length; i++) {
            ArrayList<Integer> entries = new ArrayList<Integer>();
            for(int j=0; j<table[i].length; j++) {
                if(table[i][j] != 0) {
                    entries.add(table[i][j]);
                }
            }
            
            //iterate through notes
            for(int j=0; j<notes[i].length; j++) {
                for(int p=0; p<entries.size(); p++) {
                    //if a value in entries is in the notes row then it is removed
                    if(notes[i][j] != null) {
                        for(int k=0; k<notes[i][j].length; k++) {
                            if(entries.get(p) == notes[i][j][k]) {
                                notes[i][j][k] = 0;
                            }
                        }
                    }
                }
            }
        }

        //prints out the row notes
        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                System.out.print("{");
                if(notes[i][j] != null){
                    for(int k=0; k<notes[i][j].length; k++) {
                        if(notes[i][j][k] != 0) {
                            System.out.print(notes[i][j][k]);
                        }
                    }
                }
                System.out.print("}, ");
            }
            System.out.println();
        }
        

        
    }
}
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


        //Goes through columns and rows and if there is a non zero value adds it to a arraylist
        for(int i=0; i<table.length; i++) {
            ArrayList<Integer> entriesR = new ArrayList<Integer>();
            ArrayList<Integer> entriesC = new ArrayList<Integer>();
            for(int j=0; j<table[i].length; j++) {
                if(table[i][j] != 0) {
                    entriesR.add(table[i][j]);
                }

                if(table[j][i] != 0) {
                    entriesC.add(table[j][i]);
                }
            }

            for(int j=0; j<notes[i].length; j++) {
                for(int p=0; p<entriesR.size(); p++) {
                    //if a value in entries is in the notes ROWS then it is removed
                    if(notes[i][j] != null) {
                        for(int k=0; k<notes[i][j].length; k++) {
                            if(entriesR.get(p) == notes[i][j][k]) {
                                notes[i][j][k] = 0;
                            }
                        }
                    }
                }


                for(int p=0; p<entriesC.size(); p++) {
                    //if a value in entries is in the notes COLUMNS then it is removed
                    if(notes[j][i] != null) {
                        for(int k=0; k<notes[j][i].length; k++) {
                            if(entriesC.get(p) == notes[j][i][k]) {
                                notes[j][i][k] = 0;
                            }
                        }
                    }
                }
            }
        }
        
        //creates subtables for each 3x3 grid
        int[][] subtables = new int[9][9];
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                if(i < 3 && j < 3)      {subtables[0][j  +(3*(i%3))] = table[i][j];}
                else if(i < 3 && j < 6) {subtables[1][j-3+(3*(i%3))] = table[i][j];}
                else if(i < 3 && j < 9) {subtables[2][j-6+(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 3) {subtables[3][j  +(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 6) {subtables[4][j-3+(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 9) {subtables[5][j-6+(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 3) {subtables[6][j  +(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 6) {subtables[7][j-3+(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 9) {subtables[8][j-6+(3*(i%3))] = table[i][j];}
            }
        }

        ArrayList<ArrayList<Integer>> entriesG = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<subtables.length; i++) {
            entriesG.add(new ArrayList<Integer>());
            for(int j=0; j<subtables[i].length; j++) {
                if(subtables[i][j] != 0) {
                    entriesG.get(i).add(subtables[i][j]);
                }
            }
        }

        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {

                for(int p=0; p<entriesG.get(i).size(); p++) {
                    //if a value in entries is in the notes GRID then it is removed
                    if(notes[i][j] != null) {
                        for(int k=0; k<notes[i][j].length; k++) {
                            if(i < 3 && j < 3)      {if(notes[0][j  +(3*(i%3))] != null && entriesG.get(i).get(p) == notes[0][j  +(3*(i%3))][k]) {notes[0][j  +(3*(i%3))][k] = 0;}}
                            else if(i < 3 && j < 6) {if(notes[1][j-3+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[1][j-3+(3*(i%3))][k]) {notes[1][j-3+(3*(i%3))][k] = 0;}}
                            else if(i < 3 && j < 9) {if(notes[2][j-6+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[2][j-6+(3*(i%3))][k]) {notes[2][j-6+(3*(i%3))][k] = 0;}}
                            else if(i < 6 && j < 3) {if(notes[3][j  +(3*(i%3))] != null && entriesG.get(i).get(p) == notes[3][j  +(3*(i%3))][k]) {notes[3][j  +(3*(i%3))][k] = 0;}}
                            else if(i < 6 && j < 6) {if(notes[4][j-3+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[4][j-3+(3*(i%3))][k]) {notes[4][j-3+(3*(i%3))][k] = 0;}}
                            else if(i < 6 && j < 9) {if(notes[5][j-6+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[5][j-6+(3*(i%3))][k]) {notes[5][j-6+(3*(i%3))][k] = 0;}}
                            else if(i < 9 && j < 3) {if(notes[6][j  +(3*(i%3))] != null && entriesG.get(i).get(p) == notes[6][j  +(3*(i%3))][k]) {notes[6][j  +(3*(i%3))][k] = 0;}}
                            else if(i < 9 && j < 6) {if(notes[7][j-3+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[7][j-3+(3*(i%3))][k]) {notes[7][j-3+(3*(i%3))][k] = 0;}}
                            else if(i < 9 && j < 9) {if(notes[8][j-6+(3*(i%3))] != null && entriesG.get(i).get(p) == notes[8][j-6+(3*(i%3))][k]) {notes[8][j-6+(3*(i%3))][k] = 0;}}
                        }
                    }
                }
            }
        }
        



        
        //prints out the subtables
        for(int i=0; i<table.length; i++) {
            //System.out.print("{");
            for(int j=0; j<notes[i].length; j++) {
                if(j%3==0 && j!=0) {System.out.println("");}
                System.out.print(subtables[i][j]);
            }
            System.out.println("\n");
        }

        System.out.println();
        //prints out the row notes
        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                System.out.print("{");
                if(notes[i][j] != null){
                    for(int k=0; k<notes[i][j].length; k++) {
                        if(notes[i][j].length == 1) {
                            table[i][j] = notes[i][j][k];
                            notes[i][j][k] = 0;
                        }
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
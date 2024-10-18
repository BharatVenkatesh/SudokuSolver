import java.io.*;
import java.util.*;

public class Main {
    public static int[][] table = new int[9][9];
    public static int[][][] notes = new int[9][9][9];
    public static int[][] subtables = null;
    public static int[][][] notehistory = null;
    public static int[][] subtablehistory = null;

    public static void printTable() {
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
    }

    //Goes through rows and if there is a non zero value, removes it from notes
    public static void checkRows() {
        for(int i=0; i<table.length; i++) {
            ArrayList<Integer> entriesR = new ArrayList<Integer>();
            for(int j=0; j<table[i].length; j++) {
                if(table[i][j] != 0) {
                    entriesR.add(table[i][j]);
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
            }
        }
    }
    
    //Goes through columns and if there is a non zero value, removes it from notes
    public static void checkColumns() {
        for(int i=0; i<table.length; i++) {
            ArrayList<Integer> entriesC = new ArrayList<Integer>();
            for(int j=0; j<table[i].length; j++) {
                if(table[j][i] != 0) {
                    entriesC.add(table[j][i]);
                }
            }

            for(int j=0; j<notes[i].length; j++) {
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
    }

    //creates subtables for each 3x3 grid
    public static int[][] createSubTables() {
        int[][] subtables = new int[9][9];
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                if(i < 3 && j < 3)      {subtables[0][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 3 && j < 6) {subtables[1][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 3 && j < 9) {subtables[2][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 3) {subtables[3][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 6) {subtables[4][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 6 && j < 9) {subtables[5][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 3) {subtables[6][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 6) {subtables[7][j%3+(3*(i%3))] = table[i][j];}
                else if(i < 9 && j < 9) {subtables[8][j%3+(3*(i%3))] = table[i][j];}
            }
        }
        return subtables;
    }

    public static void checkGrid(int[][] subtables) {
        ArrayList<ArrayList<Integer>> entriesG = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<subtables.length; i++) {
            entriesG.add(new ArrayList<Integer>());
            for(int j=0; j<subtables[i].length; j++) {
                if(subtables[i][j] != 0) {
                    entriesG.get(i).add(subtables[i][j]);
                }
            }
        }
        //System.out.println(entriesG);

        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {

                for(int p=0; p<9; p++) {
                    //if a value in entries is in the notes GRID then it is removed
                    if(notes[i][j] != null) {
                        for(int k=0; k<notes[i][j].length; k++) {
                            if(i < 3 && j < 3)      {if(p < entriesG.get(0).size() && entriesG.get(0).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 3 && j < 6) {if(p < entriesG.get(1).size() &&  entriesG.get(1).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 3 && j < 9) {if(p < entriesG.get(2).size() &&  entriesG.get(2).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 6 && j < 3) {if(p < entriesG.get(3).size() &&  entriesG.get(3).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 6 && j < 6) {if(p < entriesG.get(4).size() &&  entriesG.get(4).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 6 && j < 9) {if(p < entriesG.get(5).size() &&  entriesG.get(5).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 9 && j < 3) {if(p < entriesG.get(6).size() &&  entriesG.get(6).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 9 && j < 6) {if(p < entriesG.get(7).size() &&  entriesG.get(7).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                            else if(i < 9 && j < 9) {if(p < entriesG.get(8).size() &&  entriesG.get(8).get(p) == notes[i][j][k]) {notes[i][j][k] = 0;}}
                         }
                    }
                }
            }
        }
    }

    //checks if there is only a single spot where there can be a value within that grid/should also do this for col/rows maybe
    public static void singleInGrid() {
        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                if(notes[i][j] != null) {
                        if(i < 3 && j < 3)      {iterateGrid(i, j);}
                        else if(i < 3 && j < 6) {iterateGrid(i, j);}
                        else if(i < 3 && j < 9) {iterateGrid(i, j);}
                        else if(i < 6 && j < 3) {iterateGrid(i, j);}
                        else if(i < 6 && j < 6) {iterateGrid(i, j);}
                        else if(i < 6 && j < 9) {iterateGrid(i, j);}
                        else if(i < 9 && j < 3) {iterateGrid(i, j);}
                        else if(i < 9 && j < 6) {iterateGrid(i, j);}
                        else if(i < 9 && j < 9) {iterateGrid(i, j);}
                    }
            }
        }
    }
    public static void iterateGrid(int i, int j) {
        for(int p=1; p<9; p++) {
            int count=0;
            for(int k=0; k<notes[i][j].length; k++) {
                if(notes[i][j][k] == p) {
                    count++;
                }
            }
            if(count == 1) {
                table[i][j] = p;
                notes[i][j] = null;
            }
        }
    }
    
    //goes through the notes array and checks if there is any single non zero element, if so then enters it into table
    public static void restruct() {
        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                int index=-1;
                if(notes[i][j] != null) {
                    for(int k=0; k<notes[i][j].length; k++) {
                        if(notes[i][j][k] != 0) {
                            if(index == -1) {
                                index = k;
                            }
                            else if(index != -1) {
                                index = -1;
                                break;
                            }
                        }
                    }
                }
                if(index != -1) {
                    table[i][j] = notes[i][j][index];
                }
            }
        }
    }

    public static boolean isComplete() {
        notehistory = notes.clone();
        if(subtables != null) {
            subtablehistory = subtables.clone();
        }
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<table[i].length; j++) {
                if(table[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isProgress() {
        if(subtables == null || subtablehistory == null) {return true;}

        for(int i=0; i<notes.length; i++) {
            for(int j=0; j<notes[i].length; j++) {
                if(notes[i][j] != null && notehistory[i][j] != null) {
                    if(notes[i][j].length != notehistory[i][j].length) {
                        return true;
                    }
                    for(int k=0; k<notes[i][j].length; k++) {
                        if(notehistory[i][j][k] != notes[i][j][k]) {
                            return true;
                        }
                    }
                }
            }
        }
        for(int i=0; i<subtables.length; i++) {
            if(subtablehistory[i].length != subtables[i].length) {
                return true;
            }
            for(int j=0; j<subtablehistory[i].length; j++) {
                if(subtablehistory[i][j] != subtables[i][j]) {
                        return true;
                }
            }
        }
        

        System.out.println("Was not able to solve sudoku puzzle. May be impossible to solve due to lack of entries.");
        return false;
    }
    public static void main(String[] args) {
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

            //printTable();
            fileReader.close();
        }
        //Returns error if file not found
        catch (FileNotFoundException e){
            System.out.println("File not found ERROR");
            e.printStackTrace();
            return;
        }

        //if 5 cannot be anywhere else in a grid, it must be at that spot, do this check
        while(!isComplete()) {
            /* for(int i=0; i<notes.length; i++) {
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
        } */
        
        System.out.println();
            printTable();
            checkRows();
            checkColumns();
            subtables = createSubTables();
            checkGrid(subtables);
            restruct();
        }


        //--------------------------------------------only--printing--below-----------------------------------------------------------------------------

        
        /* 
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
        //prints out the notes
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
        
        System.out.println();
         */

        //prints out the table
        printTable();

    }
}
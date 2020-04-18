/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Patterning {

    static ArrayList<Integer> firstRow = new ArrayList<>();
    static ArrayList<Integer> secondRow = new ArrayList<>();
    static ArrayList<Integer> thirdRow = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Image Name: ");
        String fileName = input.next();
        System.out.print("Enter Image Width: ");
        int width = input.nextInt();
        ArrayList<Integer> finalList = new ArrayList<>();
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int value;
            int count = 0;
            while ((value = myInputFile.read()) != -1) {
                replace(value);
                count++;
                if (count > width - 1) {
                    finalList.addAll(firstRow);
                    finalList.addAll(secondRow);
                    finalList.addAll(thirdRow);
                    firstRow.clear();
                    secondRow.clear();
                    thirdRow.clear();
                    count = 0;
                }
            }
            String fileNameOutput = "Patterning_" + fileName;
            try (FileOutputStream myOutputFile = new FileOutputStream(fileNameOutput)) {
                for (Integer str : finalList) {
                    myOutputFile.write(str);
                }
                myOutputFile.close();
            } catch (IOException ex) {
                System.out.print("File output error!");
            }
            System.out.println("Your output file name: "+fileNameOutput);
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
    }

    static void replace(int value) {
        if (value < 26) { // lvl 0
            firstRow.addAll(Arrays.asList(0, 0, 0));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(0, 0, 0));
        } else if (value < 52) { // lvl 1
            firstRow.addAll(Arrays.asList(0, 0, 0));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 78) { // lvl 2
            firstRow.addAll(Arrays.asList(255, 0, 0));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 104) { // lvl 3
            firstRow.addAll(Arrays.asList(255, 0, 255));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(0, 0, 255));
        } else if (value < 130) { // lvl 4
            firstRow.addAll(Arrays.asList(255, 0, 255));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(255, 0, 255));
        } else if (value < 156) { // lvl 5
            firstRow.addAll(Arrays.asList(255, 0, 255));
            secondRow.addAll(Arrays.asList(0, 0, 0));
            thirdRow.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 182) { // lvl 6
            firstRow.addAll(Arrays.asList(255, 0, 255));
            secondRow.addAll(Arrays.asList(255, 0, 0));
            thirdRow.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 208) { // lvl 7
            firstRow.addAll(Arrays.asList(255, 255, 255));
            secondRow.addAll(Arrays.asList(255, 0, 0));
            thirdRow.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 234) { // lvl 8
            firstRow.addAll(Arrays.asList(255, 255, 255));
            secondRow.addAll(Arrays.asList(255, 0, 255));
            thirdRow.addAll(Arrays.asList(255, 255, 255));
        } else if (value < 256) { // lvl 9
            firstRow.addAll(Arrays.asList(255, 255, 255));
            secondRow.addAll(Arrays.asList(255, 255, 255));
            thirdRow.addAll(Arrays.asList(255, 255, 255));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halftoning;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class DitheringPart2 {

    static ArrayList<Integer> finalList = new ArrayList<>();
    static ArrayList<Integer> tempList = new ArrayList<>();
    static int[][] ditheringFormat = {
        {0, 128, 32, 60},
        {192, 64, 224, 96},
        {48, 176, 16, 144},
        {240, 112, 208, 80}};

    public static void main(String[] args) {
        String fileName = "chess.raw";
        int width = 300;
        String fileNameOutput = "Dithering2_" + fileName;
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int count = 0;
            int row = 0;
            int column = 0;
            int dataValue = myInputFile.available();
            while (dataValue > 0) {
                compare(myInputFile.read(), ditheringFormat[row][column]);
                if (column == 3) {
                    column = 0;
                } else {
                    column++;
                }
                count++;
                dataValue--;
                if (count > width - 1) {
                    finalList.addAll(tempList);
                    tempList.clear();
                    column = 0;
                    if (row==3){
                        row = 0;
                    }else row++;
                    count = 0;
                }
            }
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }

        try (FileOutputStream myOutputFile = new FileOutputStream(fileNameOutput)) {
            for (Integer str : finalList) {
                myOutputFile.write(str);
            }
            myOutputFile.close();
        } catch (IOException ex) {
            System.out.print("File output error!");
        }
    }

    static void compare(int value, int ditheringValue) {
        if (value > ditheringValue) {
            tempList.add(255);
        } else {
            tempList.add(0);
        }
    }
}

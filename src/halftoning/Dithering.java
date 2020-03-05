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
import java.util.Arrays;

/**
 *
 * @author ASUS
 */
public class Dithering {

    static ArrayList<Integer> finalList = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "chess.raw";
        int width = 300;
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int value;
            int count = 0;
            while ((value = myInputFile.read()) != -1) {
                compareFirstRow(value, myInputFile.read());
                count++;
                if (count > (width) / 2 - 1) {
                    for (int i = 0; i < (width) / 2 - 1; i++) {
                        compareSecondRow(myInputFile.read(), myInputFile.read());
                    }
                    count = 0;
                }
            }
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
        String fileNameOutput = "Dithering_" + fileName;
        try (FileOutputStream myOutputFile = new FileOutputStream(fileNameOutput)) {
            int count = 0;
            for (Integer str : finalList) {
                myOutputFile.write(str);
                count++;
                if (count > width - 1) {
                    myOutputFile.write('\n');
                    count = 0;
                }
            }
            myOutputFile.close();
        } catch (IOException ex) {
            System.out.print("File output error!");
        }
    }

    static void compareFirstRow(int value, int value2) {
        int color1, color2;
        if (Integer.compare(value, 0) > 0) {
            color1 = 255;
        } else {
            color1 = 0;
        }
        if (Integer.compare(value2, 128) > 0) {
            color2 = 255;
        } else {
            color2 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2));
    }

    static void compareSecondRow(int value, int value2) {
        int color1, color2;
        if (Integer.compare(value, 192) > 0) {
            color1 = 255;
        } else {
            color1 = 0;
        }
        if (Integer.compare(value2, 64) > 0) {
            color2 = 255;
        } else {
            color2 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2));
    }
}

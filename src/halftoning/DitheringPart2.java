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
public class DitheringPart2 {

    static ArrayList<Integer> finalList = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "chess.raw";
        int width = 300;
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int value;
            int count = 0;
            while ((value = myInputFile.read()) != -1) {
                compareFirstRow(value, myInputFile.read(),myInputFile.read(),myInputFile.read());
                count++;
                if (count > (width) / 4 - 1) {
                    for (int i = 0; i < (width) / 4 - 1; i++) {
                        compareSecondRow(myInputFile.read(), myInputFile.read(),myInputFile.read(),myInputFile.read());
                    }
                    for (int i = 0; i < (width) / 4 - 1; i++) {
                        compareThirdRow(myInputFile.read(), myInputFile.read(),myInputFile.read(),myInputFile.read());
                    }
                    for (int i = 0; i < (width) / 4 - 1; i++) {
                        compareFourthRow(myInputFile.read(), myInputFile.read(),myInputFile.read(),myInputFile.read());
                    }
                    count = 0;
                }
            }
        } catch (IOException ex) {
            System.out.print("File Error!\n" + ex);
        }
        String fileNameOutput = "Dithering2_" + fileName;
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

    static void compareFirstRow(int value, int value2,int value3, int value4) {
        int color1, color2,color3,color4;
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
        if (Integer.compare(value3, 32) > 0) {
            color3 = 255;
        } else {
            color3 = 0;
        }
        if (Integer.compare(value4, 160) > 0) {
            color4 = 255;
        } else {
            color4 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2,color3,color4));
    }

    static void compareSecondRow(int value, int value2,int value3, int value4) {
        int color1, color2,color3,color4;
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
        if (Integer.compare(value3, 224) > 0) {
            color3 = 255;
        } else {
            color3 = 0;
        }
        if (Integer.compare(value4, 96) > 0) {
            color4 = 255;
        } else {
            color4 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2,color3,color4));
    }
    
    static void compareThirdRow(int value, int value2,int value3, int value4) {
        int color1, color2,color3,color4;
        if (Integer.compare(value, 48) > 0) {
            color1 = 255;
        } else {
            color1 = 0;
        }
        if (Integer.compare(value2, 176) > 0) {
            color2 = 255;
        } else {
            color2 = 0;
        }
        if (Integer.compare(value3, 16) > 0) {
            color3 = 255;
        } else {
            color3 = 0;
        }
        if (Integer.compare(value4, 144) > 0) {
            color4 = 255;
        } else {
            color4 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2,color3,color4));
    }
    
    static void compareFourthRow(int value, int value2,int value3, int value4) {
        int color1, color2,color3,color4;
        if (Integer.compare(value, 240) > 0) {
            color1 = 255;
        } else {
            color1 = 0;
        }
        if (Integer.compare(value2, 112) > 0) {
            color2 = 255;
        } else {
            color2 = 0;
        }
        if (Integer.compare(value3, 208) > 0) {
            color3 = 255;
        } else {
            color3 = 0;
        }
        if (Integer.compare(value4, 80) > 0) {
            color4 = 255;
        } else {
            color4 = 0;
        }
        finalList.addAll(Arrays.asList(color1, color2,color3,color4));
    }
}

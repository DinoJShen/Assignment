/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halftoning;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Dilthering {

    public static void main(String[] args) {
        String fileName = "yoda.raw";
        try (FileInputStream myInputFile = new FileInputStream(fileName)) {
            int value;
            int count = 0;
            int width = 123;
            while ((value = myInputFile.read()) != -1) {
                compareFirstRow(value,myInputFile.read());
                count++;
                if (count > (width-1)/2){
                    for (int i = 0;i<(width-1)/2;i++){
                        compareSecondRow(myInputFile.read(),myInputFile.read());
                    }
                    count = 0;
                }
            }
            
        }catch (IOException ex){
            System.out.print("File Error!\n" + ex);
        }
    }
    
    static void compareFirstRow(int value,int value2){
        if (Integer.compare(value, 0)>0){
            int color = 255;
        }else {
            int color = 0;
        }
        if (Integer.compare(value2,128)>0){
            int color = 255;
        }else{
            int color = 0;
        }
    }
    
    static void compareSecondRow(int value,int value2){
        if (Integer.compare(value, 0)>0){
            int color = 255;
        }else {
            int color = 0;
        }
        if (Integer.compare(value2,128)>0){
            int color = 192;
        }else{
            int color = 64;
        }
    }
}

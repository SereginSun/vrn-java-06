package ru.dataart.academy.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

/**
 * Calculator - class class for working with Zip.
 *
 * @author Vladimir Seregin (SereginSun@yandex.ru)
 * @since 1.0
 */
public class Calculator {

    /**
     * The method calculates the number of times a symbol occurs in all text files of the archive.
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int count = 0;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath));) {
            while (zin.getNextEntry() != null) {
                int bytesRead;
                while ((bytesRead = zin.read()) != -1) {
                    if ((bytesRead == character)) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * The method calculates the maximum word length in all text files in the archive.
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */
    public Integer getMaxWordLength(String zipFilePath) {
        int maxLength = 0;
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (zin.getNextEntry() != null) {
                int bytesRead;
                int currentLength = 0;
                while ((bytesRead = zin.read()) != -1)  {
                    if (bytesRead == ' ' || bytesRead == '\n') {
                        maxLength = Math.max(currentLength, maxLength);
                        currentLength = 0;
                    } else {
                        currentLength++;
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return maxLength;
    }
}

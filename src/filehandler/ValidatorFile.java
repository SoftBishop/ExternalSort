package filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorFile {


    public boolean isFileContainsValidData(String pathToFile,boolean dataType,boolean ascDescSort)
    {
        String currentLine = null;
        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(pathToFile)))
        {
            Pattern pattern = null;
            //проверка для строкового представления
            if(dataType == false)
            {
                pattern = Pattern.compile("^\\S+$");
            }
            //проверка для целочисленного представления
            else if(dataType == true)
            {
                pattern = Pattern.compile("^\\d+$");
            }

            Matcher matcher;

            while ((currentLine = bufferedReader.readLine()) != null)
            {
                matcher = pattern.matcher(currentLine);
                if(!matcher.matches())
                {
                    return false;
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    public boolean isFileSorted(String pathToFile,boolean dataType,boolean ascDescSort)
    {
        String currentLine = null;
        String previousLine = null;

        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(pathToFile)))
        {
            previousLine = bufferedReader.readLine();


            if(dataType == false && ascDescSort ==false)
            {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (currentLine.compareTo(previousLine) <= 0)
                    {
                        return false;
                    }
                    previousLine = currentLine;
                }
            }
            // отсортирован ли файл целочисленно в порядке возрастания
            else if(dataType == true && ascDescSort ==false)
            {
                while ((currentLine = bufferedReader.readLine()) != null)
                {
                    if (Integer.parseInt(currentLine) <= Integer.parseInt(previousLine))
                    {
                        return false;
                    }
                    previousLine = currentLine;
                }
            }
            if(dataType == false && ascDescSort ==true)
            {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (currentLine.compareTo(previousLine) >= 0)
                    {
                        return false;
                    }
                    previousLine = currentLine;
                }
            }
            else if(dataType == true && ascDescSort ==true)
            {
                while ((currentLine = bufferedReader.readLine()) != null)
                {
                    if (Integer.parseInt(currentLine) >= Integer.parseInt(previousLine))
                    {
                        return false;
                    }
                    previousLine = currentLine;
                }
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }
}

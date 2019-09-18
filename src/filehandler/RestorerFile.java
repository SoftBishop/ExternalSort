package filehandler;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestorerFile {

    public String restoreFile(String pathToFile,boolean isFileContainValidData,boolean dataType, boolean ascDescSort)
    {
        if(isFileContainValidData)
        {
            return pathToFile;
        }

        String currentLine = null;

        GeneratorFile generatorFile = new GeneratorFile();
        int redactedIndexer = 1;
        String redactedSortFilePath = null;

        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(pathToFile)))
        {

            String[] partsOfStrings;
            
            Pattern patternLineOrNumber =null;
            Matcher matcherLineOrNumber;


            if(dataType == false)
            {
                patternLineOrNumber = Pattern.compile("^\\S+$");
            }
            else if(dataType == true)
            {
                patternLineOrNumber = Pattern.compile("^\\d+$");
            }


            redactedSortFilePath = generatorFile.generateRedactedFileName(pathToFile,redactedIndexer);
            redactedIndexer++;
            ////Generate redacted sorted file
            try(FileWriter fileWriter =
                        new FileWriter(new File(redactedSortFilePath)))
            {
                while ((currentLine = bufferedReader.readLine()) != null)
                {
                    partsOfStrings = currentLine.trim().split("\\s+");

                    matcherLineOrNumber = patternLineOrNumber.matcher(partsOfStrings[0]);

                    if(matcherLineOrNumber.matches())
                    {
                        fileWriter.write(partsOfStrings[0] + "\r\n");
                    }

                }
                return redactedSortFilePath;

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return redactedSortFilePath;
    }
}

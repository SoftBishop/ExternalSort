package filehandler;


import java.io.*;

public class MergerFile {


    public String mergeSortedFile(String pathToFirstFile,
                                  String PathToSecondFile,
                                  String pathToNameOutput,
                                  boolean sortedIndex,
                                  boolean ascDescSort)
    {

        String currentLineFirstFile = null;
        String currentLineSecondFile = null;

        try(BufferedReader readerFirstFile =
                    new BufferedReader(new FileReader(pathToFirstFile)))
        {

            try(BufferedReader readerSecondFile =
                        new BufferedReader(new FileReader(PathToSecondFile)))
            {
                try(BufferedWriter writerOutputFile =
                            new BufferedWriter(new FileWriter(pathToNameOutput)))
                {
                    currentLineFirstFile = readerFirstFile.readLine();
                    currentLineSecondFile = readerSecondFile.readLine();
                    while(true)
                    {
                        if(currentLineFirstFile == null && currentLineSecondFile == null)
                        {
                            System.out.println("В файлах нет данных. Пустой файл создан по пути: \r\n" +
                                    " " + pathToNameOutput );
                            return pathToNameOutput;
                        }

                        if(currentLineFirstFile == null)
                        {
                            while (currentLineSecondFile != null)
                            {
                                writerOutputFile.write(currentLineSecondFile + "\r\n");
                                currentLineSecondFile = readerSecondFile.readLine();

                            }
                            return pathToNameOutput;
                        }
                        if (currentLineSecondFile == null)
                        {
                            while (currentLineFirstFile != null)
                            {
                                writerOutputFile.write(currentLineFirstFile + "\r\n");
                                currentLineFirstFile = readerFirstFile.readLine();
                            }
                            return pathToNameOutput;
                        }

                        //тип сортировку в лекскографическом или в численном
                        boolean condition = false;
                        if(sortedIndex == false && ascDescSort ==false )
                        {
                            condition = currentLineFirstFile.compareTo(currentLineSecondFile)<=0;
                        }
                        if(sortedIndex == true && ascDescSort ==false)
                        {
                            condition=Integer.parseInt(currentLineFirstFile) <= Integer.parseInt(currentLineSecondFile);
                        }
                        if(sortedIndex == false && ascDescSort ==true)
                        {
                            condition = currentLineFirstFile.compareTo(currentLineSecondFile) >=0;
                        }
                        if(sortedIndex == true && ascDescSort ==true)
                        {
                            condition=Integer.parseInt(currentLineFirstFile) >= Integer.parseInt(currentLineSecondFile);
                        }
                        if(condition)
                        {
                            writerOutputFile.write(currentLineFirstFile + "\r\n");
                            currentLineFirstFile = readerFirstFile.readLine();
                        }
                        else
                        {
                            writerOutputFile.write(currentLineSecondFile + "\r\n");
                            currentLineSecondFile = readerSecondFile.readLine();
                        }

                    }

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
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return pathToNameOutput;
    }
}

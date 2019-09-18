package sort;

import filehandler.GeneratorFile;
import filehandler.MergerFile;
import filehandler.SortFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class ExternalSort {


    public String externalSort(String firstPath,
                               String secondPath,
                               String outputPath,
                               boolean indexSort,
                               boolean ascDescSort) throws IOException
    {
        SortFile sortFile = new SortFile();

        String originalPathFirstFile = firstPath, originalPathSecondFile = secondPath;

        MergerFile mergerFile = new MergerFile();

        firstPath = sortFile.sortFile(firstPath,indexSort,ascDescSort);
        secondPath = sortFile.sortFile(secondPath,indexSort,ascDescSort);

        String resultPath=
                mergerFile.mergeSortedFile(firstPath,secondPath, outputPath,indexSort,ascDescSort);


        if(!firstPath.equals(originalPathFirstFile))
        {
            Files.delete(Paths.get(firstPath));
        }
        if(!secondPath.equals(originalPathSecondFile))
        {
            Files.delete(Paths.get(secondPath));
        }
        return resultPath;
    }


    public void multipleExternalSort(ArrayList arrayListSort,
                                     String lastPathFile,
                                     String outputFilePath,
                                     boolean sortedIndex,
                                     boolean ascDescSort) throws IOException
    {
        GeneratorFile generatorFile = new GeneratorFile();
        Iterator iterator = arrayListSort.iterator();
        String result = null;

        ArrayList junkCollector = new ArrayList();
        String generatorName = null;
        int indexerFile =1;
        while (iterator.hasNext())
        {
            if (arrayListSort.size() == 1)
            {
                externalSort(arrayListSort.get(0).toString(),lastPathFile,outputFilePath,sortedIndex, ascDescSort);
                return;
            }

            if(result == null)
            {
                result = iterator.next().toString();
            }

            generatorName = generatorFile.generateNewSortFileName(result,indexerFile);

            if(iterator.hasNext())
            {
                result = externalSort(result, iterator.next().toString(), generatorName, sortedIndex,ascDescSort);
                junkCollector.add(generatorName);
            }

            result = junkCollector.get(junkCollector.size() - 1).toString();
            junkCollector.remove(junkCollector.size() - 1);

            for (Object junk : junkCollector)
            {
                Files.delete(Paths.get(junk.toString()));
            }

            externalSort(result, lastPathFile, outputFilePath, sortedIndex,ascDescSort);
            indexerFile++;

        }
    }
}

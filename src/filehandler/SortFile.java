package filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class SortFile {


    public String sortFile(String path, boolean sortIndex,boolean ascDescSort) throws IOException
    {

        ValidatorFile validatorFile = new ValidatorFile();
        RestorerFile restorerFile = new RestorerFile();
        DividerFile dividerFile = new DividerFile();
        GeneratorFile generatorFile = new GeneratorFile();
        MergerFile mergerFile = new MergerFile();
        String result = null;


        boolean isValidData = validatorFile.isFileContainsValidData(path,sortIndex,ascDescSort);

        if(!isValidData)
        {
           path = restorerFile.restoreFile(path,isValidData,sortIndex,ascDescSort);
        }

        boolean isSorted = validatorFile.isFileSorted(path,sortIndex,ascDescSort);
        ArrayList chunks;

        if(!isSorted)
        {
            chunks = dividerFile.divideAndCollectChunks(path,sortIndex,ascDescSort);
            ArrayList junkCollector = new ArrayList();

            int indexerFile = 1;

            Iterator iterator = chunks.iterator();

            String generatorName;
            while (iterator.hasNext())
            {

                if(chunks.size() == 1)
                {
                    return chunks.get(0).toString();
                }
                if(result == null)
                {
                    result = iterator.next().toString();
                }
                generatorName = generatorFile.generateNewSortFileName(result,indexerFile);
                result = mergerFile.mergeSortedFile(result,iterator.next().toString(),generatorName,sortIndex,ascDescSort);
                junkCollector.add(generatorName);
                indexerFile++;;
            }

            result =junkCollector.get(junkCollector.size()-1).toString();
            junkCollector.remove(junkCollector.size()-1);

            for (Object junk: junkCollector)
            {
                Files.delete(Paths.get(junk.toString()));
            }

            for (Object chunk: chunks)
            {
                Files.delete(Paths.get(chunk.toString()));
            }
            return result;
        }

        return path;
    }
}

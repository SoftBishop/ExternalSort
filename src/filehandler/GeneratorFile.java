package filehandler;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeneratorFile {

    public String generateChunkName(String filePath, int counter)
    {
        return filePath + "." + "temp_data_" + counter + ".tmp";
    }

    public String generateRedactedFileName(String filePath, int counter)
    {
        return filePath + "." + "sorted_temp_data_" + counter + ".tmp";
    }

    public String generateNewSortFileName(String firstFilePath,int counter)
    {
        Path pathFirstFile = Paths.get(firstFilePath);

        return pathFirstFile.getParent() + File.separator + pathFirstFile.getFileName() + "_" + counter + ".tmp";
    }

}

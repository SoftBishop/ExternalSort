package filehandler;

import sort.MergeSort;

import java.io.*;
import java.util.ArrayList;

public class DividerFile {

    // получение списка путей осортированных "кусков" файла
    public ArrayList<String> divideAndCollectChunks(String filePath,boolean sortedIndex, boolean ascDescSort)
    {
        ArrayList<String> chunksPath = new ArrayList<>();

        try(BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(filePath)))
        {
            String currentLine = null;
            ArrayList arrayList = new ArrayList();

            MergeSort mergeSort = new MergeSort();

            GeneratorFile generatorFile = new GeneratorFile();
            String pathChunk = null;
            int chunkNameIndexer = 1;

            //прочтение файла, с последующим дроблением и сортировкой
            while ((currentLine = bufferedReader.readLine()) != null)
            {
                //получение данных их файла
                arrayList.add(currentLine);

                //если файл имеет больше 500 строк: дробим файл
                if(arrayList.size() == 500)
                {
                    //Генерация нового отссортирванного файла
                    pathChunk = generatorFile.generateChunkName(filePath, chunkNameIndexer);
                    BufferedWriter bufferedWriter =
                            new BufferedWriter(new FileWriter(pathChunk));
                    chunksPath.add(pathChunk);

                    Object[] objArr = arrayList.toArray();


                    arrayList = new ArrayList();

                    //сортировка слиянием
                    mergeSort.mergeSort(objArr,objArr.length,sortedIndex,ascDescSort);

                    //запись в файл
                    for(Object arr : objArr)
                    {
                        bufferedWriter.write(arr.toString() + "\r\n");
                    }
                    bufferedWriter.close();
                    chunkNameIndexer++;
                }
            }

            if(!arrayList.isEmpty())
            {
                pathChunk = generatorFile.generateChunkName(filePath, chunkNameIndexer);
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(pathChunk));
                chunksPath.add(pathChunk);

                Object[] objArr = arrayList.toArray();

                mergeSort.mergeSort(objArr,objArr.length,sortedIndex,ascDescSort);
                for(Object arr : objArr)
                {
                    bufferedWriter.write(arr.toString() + "\r\n");
                }
                bufferedWriter.close();
                chunkNameIndexer++;
            }

            bufferedReader.close();
            return chunksPath;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return chunksPath;
    }
}

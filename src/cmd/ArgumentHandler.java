package cmd;

import java.util.ArrayList;
import java.util.Arrays;

public class ArgumentHandler {



    private String[] arguments;

    private boolean sortedIndex; //false string // true number

    private boolean ascOrDescSort; //false asc // true desc

    private ArrayList paths = new ArrayList();

    private String pathOutPutFile;
    private String filePath;

    public ArgumentHandler(String[] args)
    {
        this.arguments = args;

    }

    //По возрастанию false, по убыванию true
    public boolean ascOrDescSort() {
        return ascOrDescSort;
    }

    //Данные сортировки: false строки, true целые числа
    public boolean strOrNumSort() {
        return sortedIndex;
    }

    //Список аргументов путей файла
    public ArrayList getPaths() {
        return paths;
    }

    //Путь к выходящему файлу
    public String getPathOutPutFile() {
        return pathOutPutFile;
    }

    //Забор файла, не смог реализовать "чисто", является последним файлом для соединения
    public String getFilePath() {
        return filePath;
    }

    //разбор аргументов комадной строки
    public void parseArguments(String[] args)
    {
        switch (args[0])
        {
            case("-a"):
                ascOrDescSort = false;
                chooserPath(1);
                break;

            case("-d"):
                ascOrDescSort = true;
                chooserPath(1);
                break;

            case("-s"):
                sortedIndex = false;
                chooserPath(1);
                break;

            case("-i"):
                sortedIndex = true;
                chooserPath(1);
                break;
            case("-h"):
                System.out.println("sortIt.exe аргументы - [[-a]или[-d]] [[-s]или[i]] [pathOutput] [[path], [path1]...[pathN]]");
                System.out.println("[-a] - Сортировка по возрастанию (необязателен).");
                System.out.println("[-d] - Сортировка по убыванию (необязателен).");
                System.out.println("[-s] - Данные сортировки чила (обязателен).");
                System.out.println("[-i] - Данные сортировки (обязателен).");
                System.out.println("[pathOutput] - Путь выходящего файла (обязателен).");
                System.out.println("[[path], [path1]...[pathN]] - Файлы для сортировки их полные пути. (обязателен хотя бы один)");
                break;
            default:
                System.out.println("Wrong arguments");
        }

    }

    private void chooserPath(int index)
    {
        try {
            if(arguments[index].isEmpty())
            {
                System.out.println("Нехватает параметров");
                return;
            }
            if(arguments[index].equals("-a") || arguments[index].equals("-d") )
            {
                ifFirstArgumentIsAscOrDesc();
            }
            else
            {
                ifFirstArgumentIsStrOrNum();
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.out.print("Заданы неверные параметры");
        }

    }

    private void ifFirstArgumentIsAscOrDesc()
    {
        switch (arguments[1])
        {
            case("-s"):
                sortedIndex = false;
                collectPaths(2);
                break;

            case("-i"):
                sortedIndex = true;
                collectPaths(2);
                break;
            default:
                System.out.println("Wrong arguments");
                break;
        }
    }


    private void ifFirstArgumentIsStrOrNum()
    {
        switch (arguments[1])
        {
            case("-a"):
                ascOrDescSort = false;
                System.out.println("-s");
                collectPaths(2);
                break;

            case("-d"):
                ascOrDescSort = true;
                collectPaths(2);
                break;

            default:
                collectPaths(1);
                ascOrDescSort = false;
                break;
        }
    }


    //Сбор путей файла
    private void collectPaths(int arrIndex)
    {

        if (arguments[arrIndex].isEmpty())
        {
            System.out.println("Отсутствует выходной файл");
            return;
        }
        paths.addAll(Arrays.asList(arguments).subList(arrIndex, arguments.length));
        if(paths.size() == 2)
        {
            pathOutPutFile = paths.get(0).toString();
            filePath = paths.get(1).toString();
            paths = new ArrayList();
            System.out.println(paths.size());
        }

        pathOutPutFile = paths.get(0).toString();
        filePath = paths.get(1).toString();
        paths.remove(0); paths.remove(0);



    }
}


import cmd.ArgumentHandler;
import sort.ExternalSort;

import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {

        if(args.length == 0)
        {
            System.out.println("Не заданы аргументы, для справки вызовите опцию -h");
            return;
        }

        ArgumentHandler argumentHandler = new ArgumentHandler(args);

        argumentHandler.parseArguments(args);

        ExternalSort externalSort = new ExternalSort();

        ArrayList arrayList = argumentHandler.getPaths();

        externalSort.multipleExternalSort(argumentHandler.getPaths(),
                argumentHandler.getFilePath(),
                argumentHandler.getPathOutPutFile(),
                argumentHandler.strOrNumSort(),
                argumentHandler.ascOrDescSort());

    }
}

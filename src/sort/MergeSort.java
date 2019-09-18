package sort;

import java.util.ArrayList;

public class MergeSort  {


    //Классический алгоритм сортировки данных, с допоненными критериями сортировки
    public void mergeSort(Object[] arrObj, int arrLength, boolean sortedIndex, boolean ascDescSort)
    {
        if(arrLength<2)
        {
            return;
        }

        int middle = arrLength/2;
        Object[] left = new Object[middle];
        Object[] right = new Object[arrLength-middle];

        for(int i = 0; i<middle; i++)
        {
            left[i] = arrObj[i];
        }
        for(int i = middle; i<arrLength; i++)
        {
            right[i-middle] = arrObj[i];
        }

        mergeSort(left,middle,sortedIndex,ascDescSort);
        mergeSort(right,arrLength-middle,sortedIndex,ascDescSort);

        merge(arrObj, left, right, middle, arrLength - middle, sortedIndex,ascDescSort);

    }

    public void merge(Object[] arr, Object[] leftArray, Object[] rightArray,
                      int left, int right, boolean sortedIndex ,boolean ascDescSort )
    {
        int i =0, j=0, k=0;
        if(sortedIndex == false && ascDescSort == false)
        {
            while (i < left && j < right)
            {
                //сравнение в лексикографическом порядке
                if (leftArray[i].toString().compareTo(rightArray[j].toString())<=0)
                {
                    arr[k++] = leftArray[i++];
                }
                else {
                    arr[k++] = rightArray[j++];
                }
            }
        }
        if(sortedIndex == true && ascDescSort == false)
        {
            while (i < left && j < right)
            {
                //сравнение в целых числах
                if (Integer.parseInt(leftArray[i].toString()) <= (Integer.parseInt(rightArray[j].toString())))
                {
                    arr[k++] = leftArray[i++];
                }
                else {
                    arr[k++] = rightArray[j++];
                }
            }
            if(sortedIndex == false && ascDescSort == true)
            {
                while (i < left && j < right)
                {
                    //сравнение в лексикографическом порядке
                    if (leftArray[i].toString().compareTo(rightArray[j].toString())>=0)
                    {
                        arr[k++] = leftArray[i++];
                    }
                    else {
                        arr[k++] = rightArray[j++];
                    }
                }
            }
            if(sortedIndex == true && ascDescSort ==true) {
                while (i < left && j < right) {
                    //сравнение в целых числах
                    if (Integer.parseInt(leftArray[i].toString()) >= (Integer.parseInt(rightArray[j].toString()))) {
                        arr[k++] = leftArray[i++];
                    } else {
                        arr[k++] = rightArray[j++];
                    }
                }
            }
            while (i < left)
            {
                arr[k++] = leftArray[i++];
            }
            while (j < right)
            {
                arr[k++] = rightArray[j++];
            }
        }
    }
}

ublic static void mergeSortInner(int[] array, int left, int right, int cutoff) {
        if (right - left < cutoff) insertionSort(array, left, right);

        else if (left < right) {
            int middle = (left + right) / 2; 
            mergeSortInner(array, left, middle, cutoff);
            mergeSortInner(array, middle + 1, right, cutoff);
            merge(array, left, middle, right);
        }
    }
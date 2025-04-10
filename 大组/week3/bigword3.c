#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// 插入排序
void insertSort(int arr[], int n) {
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

// 合并两个子数组
void merge(int arr[], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[n1], R[n2];

    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    int i = 0, j = 0, k = l;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// 归并排序
void mergeSort(int arr[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;

        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);

        merge(arr, l, m, r);
    }
}

// 交换两个元素
void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

// 分区函数
int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return i + 1;
}

// 快速排序
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

// 计数排序
void countSort(int arr[], int n) {
    int max = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max)
            max = arr[i];
    }

    int* count = (int*)calloc(max + 1, sizeof(int));
    int* output = (int*)malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        count[arr[i]]++;

    for (int i = 1; i <= max; i++)
        count[i] += count[i - 1];

    for (int i = n - 1; i >= 0; i--) {
        output[count[arr[i]] - 1] = arr[i];
        count[arr[i]]--;
    }

    for (int i = 0; i < n; i++)
        arr[i] = output[i];

    free(count);
    free(output);
}

// 获取最大数
int getMax(int arr[], int n) {
    int mx = arr[0];
    for (int i = 1; i < n; i++)
        if (arr[i] > mx)
            mx = arr[i];
    return mx;
}

// 对每个数位进行计数排序
void countingSortForRadix(int arr[], int n, int exp) {
    int* output = (int*)malloc(n * sizeof(int));
    int count[10] = {0};

    for (int i = 0; i < n; i++)
        count[(arr[i] / exp) % 10]++;

    for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];

    for (int i = n - 1; i >= 0; i--) {
        output[count[(arr[i] / exp) % 10] - 1] = arr[i];
        count[(arr[i] / exp) % 10]--;
    }

    for (int i = 0; i < n; i++)
        arr[i] = output[i];

    free(output);
}

// 基数计数排序
void radixCountSort(int arr[], int n) {
    int m = getMax(arr, n);

    for (int exp = 1; m / exp > 0; exp *= 10)
        countingSortForRadix(arr, n, exp);
}

// 测试不同大数据量下的排序用时
void test_different_large_sizes() {
    int sizes[] = {10000, 50000, 200000};
    int num_sizes = sizeof(sizes) / sizeof(sizes[0]);

    for (int i = 0; i < num_sizes; i++) {
        int size = sizes[i];
        int* arr = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }

        clock_t start, end;
        double cpu_time_used;

        // 插入排序
        int* arr_insert = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr_insert[j] = arr[j];
        }
        start = clock();
        insertSort(arr_insert, size);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("Insertion Sort with %d elements took %f seconds\n", size, cpu_time_used);
        free(arr_insert);

        // 归并排序
        int* arr_merge = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr_merge[j] = arr[j];
        }
        start = clock();
        mergeSort(arr_merge, 0, size - 1);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("Merge Sort with %d elements took %f seconds\n", size, cpu_time_used);
        free(arr_merge);

        // 快速排序
        int* arr_quick = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr_quick[j] = arr[j];
        }
        start = clock();
        quickSort(arr_quick, 0, size - 1);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("Quick Sort with %d elements took %f seconds\n", size, cpu_time_used);
        free(arr_quick);

        // 计数排序
        int* arr_count = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr_count[j] = arr[j];
        }
        start = clock();
        countSort(arr_count, size);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("Count Sort with %d elements took %f seconds\n", size, cpu_time_used);
        free(arr_count);

        // 基数计数排序
        int* arr_radix = (int*)malloc(size * sizeof(int));
        for (int j = 0; j < size; j++) {
            arr_radix[j] = arr[j];
        }
        start = clock();
        radixCountSort(arr_radix, size);
        end = clock();
        cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
        printf("Radix Count Sort with %d elements took %f seconds\n", size, cpu_time_used);
        free(arr_radix);

        free(arr);
    }
}

// 测试大量小数据量下的排序用时
void test_many_small_sizes() {
    int num_trials = 100000;
    int size = 100;
    int* arr = (int*)malloc(size * sizeof(int));

    clock_t start, end;
    double cpu_time_used;

    // 插入排序
    start = clock();
    for (int i = 0; i < num_trials; i++) {
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }
        insertSort(arr, size);
    }
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Insertion Sort for %d times of %d elements took %f seconds\n", num_trials, size, cpu_time_used);

    // 归并排序
    start = clock();
    for (int i = 0; i < num_trials; i++) {
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }
        mergeSort(arr, 0, size - 1);
    }
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Merge Sort for %d times of %d elements took %f seconds\n", num_trials, size, cpu_time_used);

    // 快速排序
    start = clock();
    for (int i = 0; i < num_trials; i++) {
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }
        quickSort(arr, 0, size - 1);
    }
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Quick Sort for %d times of %d elements took %f seconds\n", num_trials, size, cpu_time_used);

    // 计数排序
    start = clock();
    for (int i = 0; i < num_trials; i++) {
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }
        countSort(arr, size);
    }
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Count Sort for %d times of %d elements took %f seconds\n", num_trials, size, cpu_time_used);

    // 基数计数排序
    start = clock();
    for (int i = 0; i < num_trials; i++) {
        for (int j = 0; j < size; j++) {
            arr[j] = rand() % 1000000;
        }
        radixCountSort(arr, size);
    }
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("Radix Count Sort for %d times of %d elements took %f seconds\n", num_trials, size, cpu_time_used);

    free(arr);
}

// 生成测试数据并保存到文件
void generate_and_save_data(const char* filename, int size) {
    FILE* file = fopen(filename, "w");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    for (int i = 0; i < size; i++) {
        int num = rand() % 1000000;
        fprintf(file, "%d\n", num);
    }

    fclose(file);
}

// 从文件读取数据并排序
void read_and_sort_data(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    int size = 0;
    int num;
    while (fscanf(file, "%d", &num) != EOF) {
        size++;
    }
    rewind(file);

    int* arr = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        fscanf(file, "%d", &arr[i]);
    }
    fclose(file);

    // 插入排序
    int* arr_insert = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        arr_insert[i] = arr[i];
    }
    insertSort(arr_insert, size);
    free(arr_insert);

    // 归并排序
    int* arr_merge = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        arr_merge[i] = arr[i];
    }
    mergeSort(arr_merge, 0, size - 1);
    free(arr_merge);

    // 快速排序
    int* arr_quick = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        arr_quick[i] = arr[i];
    }
    quickSort(arr_quick, 0, size - 1);
    free(arr_quick);

    // 计数排序
    int* arr_count = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        arr_count[i] = arr[i];
    }
    countSort(arr_count, size);
    free(arr_count);

    // 基数计数排序
    int* arr_radix = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        arr_radix[i] = arr[i];
    }
    radixCountSort(arr_radix, size);
    free(arr_radix);

    free(arr);
}

int main() {
    srand(time(NULL));

    // 测试不同大数据量下的排序用时
    test_different_large_sizes();

    // 测试大量小数据量下的排序用时
    test_many_small_sizes();

    // 生成测试数据并保存到文件
    generate_and_save_data("test_data.txt", 10000);

    // 从文件读取数据并排序
    read_and_sort_data("test_data.txt");

    return 0;
}
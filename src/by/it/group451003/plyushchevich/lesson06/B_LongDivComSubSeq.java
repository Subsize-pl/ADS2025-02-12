package by.it.group451003.plyushchevich.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая кратная подпоследовательность

Дано:
    целое число 1≤n≤1000
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] делится на предыдущий
    т.е. для всех 1<=j<k, A[i[j+1]] делится на A[i[j]].

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    4
    3 6 7 12

    Sample Output:
    3
*/

public class B_LongDivComSubSeq {


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        // Чтение длины последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        // Чтение самой последовательности
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // Создаем массив для хранения длин наибольших кратных подпоследовательностей
        int[] dp = new int[n];

        // Инициализируем массив: минимальная длина подпоследовательности для каждого элемента - 1 (сам элемент)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Заполняем массив dp
        for (int i = 1; i < n; i++) {
            // Проверяем все предыдущие элементы
            for (int j = 0; j < i; j++) {
                // Если текущий элемент делится на предыдущий и длина подпоследовательности может быть увеличена
                if (m[i] % m[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // Находим максимальное значение в массиве dp
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > result) {
                result = dp[i];
            }
        }

        return result;
    }
}

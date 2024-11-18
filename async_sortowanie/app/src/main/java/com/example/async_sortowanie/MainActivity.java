package com.example.async_sortowanie;



import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText inputSize;
    private Button startButton;
    private ProgressBar progressBar;
    private SortingView sortingView;

    private ExecutorService executorService;
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSize = findViewById(R.id.inputSize);
        startButton = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progressBar);
        sortingView = findViewById(R.id.sortingView);

        executorService = Executors.newSingleThreadExecutor();
        uiHandler = new Handler(Looper.getMainLooper());

        startButton.setOnClickListener(v -> {
            String input = inputSize.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Nie podałeś ilosci elementow", Toast.LENGTH_SHORT).show();
                return;
            }

            int size = Integer.parseInt(input);
            if (size <= 0) {
                Toast.makeText(this, "Liczba nie może być ujemna", Toast.LENGTH_SHORT).show();
                return;
            }

            startSorting(size);
        });
    }

    private void startSorting(int size) {
        int[] array = genereowanie_tabeli(size);

        progressBar.setProgress(0);
        sortingView.setArray(array);

        executorService.execute(() -> {
            bubbleSort(array, size);
            uiHandler.post(() -> {
                Toast.makeText(this, "Sortowanie zakończone!", Toast.LENGTH_SHORT).show();
                progressBar.setProgress(100);
                sortingView.setArray(array);
            });
        });
    }

    private int[] genereowanie_tabeli(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(500) + 1;
        }
        return array;
    }

    private void bubbleSort(int[] array, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }

            int progress = (int) (((i + 1) / (float) size) * 100);
            uiHandler.post(() -> {
                progressBar.setProgress(progress);
                sortingView.setArray(Arrays.copyOf(array, array.length));
            });

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

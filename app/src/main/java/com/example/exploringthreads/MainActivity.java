package com.example.exploringthreads;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LookHere!"; //special tag
    String builder = "";//to hold result

    private boolean done1 = false;//check numbers were done printing
    private int performed = 0;// when equal to 2 then letters done printing



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // initialing the tasks to be performed
        Runnable printA = new PrintChar('a', 1000);
        Runnable printB = new PrintChar('b', 1000);
        Runnable printnum = new PrintNum(1000);

        //creating the threads to run on
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(printnum);

        // start() invokes the run method overridden by the class extending the runnable interface
        thread1.start();
        thread2.start();
        thread3.start();

    }

    // class for printing a given char a certain number of times
    class PrintChar implements Runnable {
        private char c; // The character to print
        private int amount; // The number of times to repeat


        //constructor with char and number to print
        public PrintChar(char c, int amount) {
            this.c = c;
            this.amount = amount;
        }

        @Override
        //performed when invoking start method of class instance
        public void run() {
            for (int i = 0; i < amount; i++) {
                builder += " "+ c;
            }
            performed++;

            if(done1 && performed==2)
                Log.d(TAG, builder);


        }
    }

    // class for printing numbers in range from 1 to a given number
    class PrintNum implements Runnable {
        private int n;

        //contructor
        public PrintNum(int n) {
            this.n = n;
        }

        @Override
        //invoked using start
        public void run() {
            for (int i = 1; i <= n; i++) {
                builder += " "+ i;
            }

            done1 = true;


            if(done1 && performed==2)
            Log.d(TAG, builder);

        }
    }


}
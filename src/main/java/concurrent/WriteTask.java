package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable {
    TabulatedFunction function;
    double value;

    WriteTask(TabulatedFunction function, double value) {
        this.function = function;
        this.value = value;
    }

    @Override
    public void run() {
        int count = function.getCount();

        for (int i = 0; i < count; i++) {
            function.setY(i, value);
            System.out.printf("Writing for index %d complete\n", i);
        }
    }
}

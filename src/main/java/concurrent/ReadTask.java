package concurrent;

import functions.TabulatedFunction;

public class ReadTask implements Runnable {
    TabulatedFunction function;

    public ReadTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        int count = function.getCount();
        for (int i = 0; i < count; i++) {
            System.out.printf("After read: i = %d, x = %f, y = %f\n", i, function.getX(i), function.getY(i));
        }
    }
}

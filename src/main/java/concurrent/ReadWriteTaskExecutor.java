package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread threadRead = new Thread(new ReadTask(function));
        Thread threadWrite = new Thread(new WriteTask(function, 0.5));

        threadRead.start();
        threadWrite.start();
    }
}

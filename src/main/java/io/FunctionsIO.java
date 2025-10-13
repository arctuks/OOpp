package io;

import functions.TabulatedFunction;
import functions.Point;
import java.io.*;

public final class FunctionsIO {
    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream os = new DataOutputStream(outputStream);
        os.writeInt(function.getCount());

        for (Point point : function) {
            os.writeDouble(point.x);
            os.writeDouble(point.y);
        }

        os.flush();
    }
}

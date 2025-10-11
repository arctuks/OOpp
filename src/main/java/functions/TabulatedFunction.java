package functions;

import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point> {
    int getCount();                       // Получение количества табулированных значений

    double getX(int index);               // Получение значения аргумента x по номеру индекса

    double getY(int index);               // Получение значения y по номеру индекса

    void setY(int index, double value);   // Присвоить значение y по номеру индекса

    int indexOfX(double x);               // Получение индекса х

    int indexOfY(double y);               // Получание индекса у

    double leftBound();                   // Получение самого левого х

    double rightBound();                  // Получение самого правого х
}

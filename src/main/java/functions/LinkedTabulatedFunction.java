package functions;

import java.util.Collection;

public class LinkedTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    protected int count = 0; // количество строк в таблице
    private Node head;

    LinkedTabulatedFunction(double[] xValues, double[] yValues) {
        while (count < xValues.length) addNode(xValues[count], yValues[count]);
    }
    LinkedTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        if (xFrom == xTo) {
            while (count-- > 0) {
                addNode(xFrom, source.apply(xFrom));
            }
            return;
        }

        double deltaX = (xTo - xFrom) / (count - 1);
        addNode(xFrom, source.apply(xFrom));
        count--;

        while (--count > 0) {
            xFrom += deltaX;
            addNode(xFrom, source.apply(xFrom));
        }
        addNode(xTo, source.apply(xTo));
    }

    @Override
    public int indexOfX(double x) {
        if (head.x == x) return 0;
        Node temp = head.next;
        int index = 1;
        while (temp != head) {
            if (temp.x == x) return index;
            temp = temp.next;
            index++;
        }

        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if (head.y == y) return 0;

        Node temp = head.next;
        int index = 1;

        while (temp != head) {
            if (temp.y == y) return index;
            temp = temp.next;
            index++;
        }

        return -1;
    }

    @Override
    public int getCount() {return count;}

    @Override
    public double leftBound() {return head.x;}

    @Override
    public double rightBound() {return head.prev.x;}

    @Override
    public double getX(int index) { return getNode(index).x; }

    @Override
    public double getY(int index) {return getNode(index).y; }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }


    protected int floorIndexOfX(double x) {
        if (x < head.x) return 0;

        Node temp = head;
        int index = -1;
        while ((temp.x < x) && (index < count)) {
            temp = temp.next;
            index++;
        }
        return index;
    }


    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return getY(0);
        }
        double x0 = getX(0);
        double x1 = getX(1);
        double y0 = getY(0);
        double y1 = getY(1);

        return y0 + ((y1 - y0) / (x1 - x0)) * (x - x0);
    }

    protected double extrapolateRight(double x) {
        if (count == 1) return getY(0);

        double x0 = getX(count - 2);
        double x1 = getX(count - 1);
        double y0 = getY(count - 2);
        double y1 = getY(count - 1);

        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return getY(0);
        }

        double x0 = getX(floorIndex);
        double x1 = getX(floorIndex + 1);
        double y0 = getY(floorIndex);
        double y1 = getY(floorIndex + 1);

        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public void insert(double x, double y) {
        return;
    }

    @Override
    public void remove(int index) {
        if (count == 1) {
            head = null;
            count--;
            return;
        }

        Node removable = getNode(index);

        if (index == 0) head = removable.next;

        removable.prev.next = removable.next;
        removable.next.prev = removable.prev;

        removable.next = null;
        removable.prev = null;

        count--;
    }

    private Node getNode(int index) {
        Node temp;
        if (index <= count / 2) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = head;
            for (int i = 0; i < count - index; i++) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    private void addNode(double x, double y) {
        Node newNode = new Node();

        if (count == 0) {
            head = newNode;
            head.next = head;
            head.prev = head;
            head.x = x;
            head.y = y;

            count++;
            return;
        }

        Node last = head.prev;

        last.next = newNode;
        newNode.prev = last;
        head.prev = newNode;
        newNode.next = head;

        newNode.x = x;
        newNode.y = y;

        count++;
    }
}

package Java;

public class test {
    public static int Fibonacci(int cal){
        if (cal <= 1)
            return cal;
        return Fibonacci(cal - 1) + Fibonacci(cal - 2);
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(46));
    }
}

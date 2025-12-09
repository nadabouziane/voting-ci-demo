
public class App {
    public static void main(String[] args){
        Calculator c = new Calculator();
        System.out.println("Sum:"+c.calc(5,3,"s"));
        System.out.println("Mul:"+c.calc(5,3,"m"));
    }
}

class Calculator {
    public int calc(int a,int b,String t){
        if(t.equals("s")) return a+b;
        if(t.equals("m")) return a*b;
        return 0;
    }
}

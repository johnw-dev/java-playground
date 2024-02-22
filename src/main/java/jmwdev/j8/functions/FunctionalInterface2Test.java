package jmwdev.j8.functions;

@FunctionalInterface
interface MyClassOperator {
    MyClass myApply(MyClass a, MyClass b);
}

record MyClass(String a, String b) {
}

public class FunctionalInterface2Test {
    public static void main(String[] args) {
        MyClass a_ = new MyClass("a", "b");
        MyClass b_ = new MyClass("a", "b");
        MyClassOperator operator = (a, b) -> new MyClass(a.a() + b.a(), a.b() + b.b());
        MyClass res = operator.myApply(a_, b_);
        System.out.println(res);
    }

}

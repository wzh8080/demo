package com.example.test.extend;

public class Test {
    public static void main(String[] args) {
        Parent p = new Parent();
        Child c = new Child();

        System.out.println(p.static_name);
        System.out.println(Parent.static_name);

        System.out.println(c.static_name);
        System.out.println(Child.static_name);

        c.staticMethod();
        //c.staticFinalMethod();
        Child.staticMethod();
        //Child.staticFinalMethod();

        p.method();
        c.method();
        Parent c1 = new Child();
        c1.method();
        c1.staticMethod();

        System.out.println(Child.FINAL_NAME);
        System.out.println(Child.FINAL_NAME2);
        System.out.println("Child.FINAL_NAME3 ========= "+Child.FINAL_NAME3);
        System.out.println(Parent.FINAL_NAME);
        System.out.println(Parent.FINAL_NAME2);


        //c.staticParentInterfaceMethod();
        //Child.staticParentInterfaceMethod();
        ParentInterface.staticParentInterfaceMethod();
    }
}

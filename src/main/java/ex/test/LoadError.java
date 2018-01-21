package ex.test;

public class LoadError {

    public static LoadError instance = new LoadError();

    LoadError() {
        throw new NullPointerException("init LoadError exception!");
    }

    public void print() {
        System.out.println("LoadError print()");
    }

    public static LoadError getInstance(){
        return instance;
    }




}

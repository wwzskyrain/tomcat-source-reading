package ex.test;

public class LoadErrorMain {


    public static void main(String[] args) {

        try {
            LoadError.getInstance().print();
        } catch (Exception e) {
            System.out.println("1." + e.toString());
        } catch (Error error) {
            System.out.println("2." + error.toString());
        }

        try {
            LoadError.getInstance().print();
        } catch (Exception e) {
            System.out.println("3." + e.toString());
        } catch (Error error) {
            System.out.println("4." + error.toString());
        }


    }

}

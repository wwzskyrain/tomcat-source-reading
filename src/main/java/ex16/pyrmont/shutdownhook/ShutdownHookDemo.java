package ex16.pyrmont.shutdownhook;

public class ShutdownHookDemo {


    public void start(){
        System.out.println("Demo start()");

        ShutdownHook shutdownHook = new ShutdownHook();

        Runtime.getRuntime().addShutdownHook(shutdownHook);

    }

    public static void main(String[] args) {

        ShutdownHookDemo shutdownHookDemo = new ShutdownHookDemo();

        shutdownHookDemo.start();
        try {
            System.in.read();
        }catch (Exception e){
            System.out.println("read catch!!!");
        }

    }


    class ShutdownHook extends Thread{

        @Override
        public void run() {
            System.out.println("Shutting down!!!");
        }
    }

}

package bean;

public class Foodie implements Runnable {

    private Baozi baozi;

    public Foodie(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (baozi) {

                if (!baozi.isFlag()) {
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("吃货开始吃" + baozi.getWrapper() + baozi.getStuffing() + "的包子");

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("吃货吃完包子了，让包子铺继续生产包子");
                baozi.setFlag(false);
                baozi.notify();
            }
        }
    }
}

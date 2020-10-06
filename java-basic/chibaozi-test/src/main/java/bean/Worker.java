package bean;

public class Worker implements Runnable {

    private Baozi baozi;

    public Worker(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (baozi) {

                if (baozi.isFlag()) {
                    try {
                        baozi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count % 2 == 0) {
                    baozi.setWrapper("薄皮");
                    baozi.setStuffing("白菜肉馅");
                    System.out.println("包子铺开始生产" + baozi.getWrapper() + baozi.getStuffing() + "的包子");
                } else {
                    baozi.setWrapper("冰皮");
                    baozi.setStuffing("牛肉馅");
                    System.out.println("包子铺开始生产" + baozi.getWrapper() + baozi.getStuffing() + "的包子");
                }

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("包子铺生产" + baozi.getWrapper() + baozi.getStuffing() + "的包子完成，可以开吃了");
                baozi.setFlag(true);
                baozi.notify();
            }
        }
    }
}

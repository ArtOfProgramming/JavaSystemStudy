import bean.Baozi;
import bean.Foodie;
import bean.Worker;

public class BaoziTest {

    public static void main(String[] args) {
        Baozi baozi = new Baozi();
        new Thread(new Worker(baozi)).start();
        new Thread(new Foodie(baozi)).start();


    }
}

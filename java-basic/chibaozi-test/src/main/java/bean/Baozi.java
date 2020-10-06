package bean;

public class Baozi {

    private String  wrapper;
    private String stuffing;
    private boolean flag = false;

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public String getStuffing() {
        return stuffing;
    }

    public void setStuffing(String stuffing) {
        this.stuffing = stuffing;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

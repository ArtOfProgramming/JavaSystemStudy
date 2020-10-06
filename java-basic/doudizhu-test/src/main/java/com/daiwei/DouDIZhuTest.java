package com.daiwei;

import com.daiwei.bean.Card;
import com.daiwei.bean.Dutu;
import com.daiwei.bean.FaPaiYuan;
import java.util.ArrayList;
import java.util.List;

public class DouDIZhuTest {

    public static void main(String[] args) {
        Card card = new Card();
        List<Dutu> dutus = new ArrayList<>();
        FaPaiYuan faPaiYuan = new FaPaiYuan("发牌员");
        for (int i = 0; i < 3; i++) {
            dutus.add(new Dutu("赌徒" + i));
        }

        faPaiYuan.xiPai(card);
        faPaiYuan.faPai(card, dutus);

        for (Dutu dutu : dutus) {
            dutu.show();
        }
    }
}

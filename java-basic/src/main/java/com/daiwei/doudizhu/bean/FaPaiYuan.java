package com.daiwei.doudizhu.bean;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FaPaiYuan {

    private String Name;

    public void xiPai(Card card) {
        Collections.shuffle(card.getList());
    }

    public void faPai(Card card, List<Dutu> dutus) {
        if (card.getList() != null && card.getList().size() == 54) {
            int dizhu = new Random().nextInt(3);
            for (int i = 0; i < card.getList().size(); i++) {
                if ((i + 3) < card.getList().size()) {
                    switch (i % 3) {
                        case 0:
                            dutus.get(0).getList().add(card.getList().get(i));
                            break;
                        case 1:
                            dutus.get(1).getList().add(card.getList().get(i));
                            break;
                        case 2:
                            dutus.get(2).getList().add(card.getList().get(i));
                            break;
                        default:
                            break;
                    }
                } else {
                    dutus.get(dizhu).getList().add(card.getList().get(i));
                }
            }
        } else {
            System.out.println("牌数不对");
        }
    }

    public FaPaiYuan(String name) {
        Name = name;
    }

    public FaPaiYuan() {
    }
}

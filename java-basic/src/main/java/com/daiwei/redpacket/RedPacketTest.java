package com.daiwei.redpacket;

import java.util.Arrays;
import java.util.Date;

public class RedPacketTest {

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket("红包测试");
        redPacket.setOwnerName("daiwei");
        redPacket.setOpenWay(new OpenModeImpl());

        Person person = new Person();
        System.out.println(person.FormatDate(new Date()));

//        List<Person> list1 = new ArrayList<>();
//        List<Person> list2 = new ArrayList<>();
//        int[] list1 = new int[]{1, 2, 3, 4, 5};
//        int[] list2 = new int[]{6, 7, 8, 9, 10};
        Person[] list1 = new Person[5];
        Person[] list2 = new Person[5];
        for (int i = 0; i < 5; i++) {
//            list1.add(new Person("a人员" + i));
//            list2.add(new Person("b人员" + i));
            list1[i] = new Person("a人员" + i);
            list2[i] = new Person("b人员" + i);
        }
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));
        System.arraycopy(list1, 0, list2, 0, 3);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));


    }
}

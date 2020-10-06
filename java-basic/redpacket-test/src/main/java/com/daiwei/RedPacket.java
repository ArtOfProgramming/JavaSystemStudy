package com.daiwei;

public class RedPacket extends RedPacketFrame {

    public RedPacket(String title) {
        super(title);
    }

    @Override
    public void setOwnerName(String ownerName) {
        super.setOwnerName(ownerName);
    }

    @Override
    public void setOpenWay(OpenMode openWay) {
        super.setOpenWay(openWay);
    }
}

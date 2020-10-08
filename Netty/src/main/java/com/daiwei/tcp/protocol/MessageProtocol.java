package com.daiwei.tcp.protocol;

import io.netty.util.CharsetUtil;
import java.util.Arrays;

/**
 * 协议包
 */
public class MessageProtocol {

    private int len; // 关键
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public MessageProtocol(int len, byte[] content) {
        this.len = len;
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
            "len=" + len +
            ", content=" + new String(content, CharsetUtil.UTF_8) +
            '}';
    }
}

package com.daiwei.Buffer;

import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;

/**
 * NIO Buffer组件
 * get() 获取当前posion值 position++, get(int index) 获取指定值，position不变。 put(T value),put(int index, T value) 同理
 *  intBuffer.asReadOnlyBuffer() 返回一个只读buffer
 *  MappedByteBuffer 可让文件直接在内存修改（堆外内存），操作系统不需要拷贝一次
 */
public class BasicBuffer {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }

        // 将buffer转换，读写切换 (标志变换)
        /**
         * public final Buffer flip() {
         *         limit = position;
         *         position = 0;
         *         mark = -1;
         *         return this;
         *     }
         */
        intBuffer.flip();
        intBuffer.position(1);
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}

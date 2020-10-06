package com.daiwei.nio.ScatteringAndGathering;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering: 将数据写入到buffer时，可以采用buffer数组，依次写入 【分散】
 * Gathering: 从buffer读取数据，可以采用buffer数组,依次读入。
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        // 绑定通道到Socket
        serverSocketChannel.socket().bind(inetSocketAddress);

        // 创建Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        final int MESSAGE_LENGTH = 8;

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;

            while (byteRead < MESSAGE_LENGTH) {
                long len = socketChannel.read(byteBuffers);
                byteRead += len;
                System.out.println("byteRead=" + byteRead);
                // 使用流打印
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "postion=" + byteBuffer.position() + "limit=" + byteBuffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < MESSAGE_LENGTH) {
                long len = socketChannel.write(byteBuffers);
                byteWrite += len;
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead=" + byteRead + "byteWrite=" + byteWrite + "length=" + MESSAGE_LENGTH);
        }
    }
}

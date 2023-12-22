package zero_copy;

import java.nio.ByteBuffer;

// �㿽������:
// 1. ͨ������ֱ�Ӳ��������ڴ�(ֱ���ڴ�)�����ݲ���Ҫ�洢��JVM�Ķ��ڴ���
// 2. ������Ҫͨ��native�������ò���ϵͳ���ں˺���
// ʹ�ó���: Netty, Kafka
public class BaseZeroCopy {

    public void showExecuteInterval() {
        long startTime = System.currentTimeMillis();
        testAccessHeapMemory();
        testAccessDirectMemory();
        long endTime = System.currentTimeMillis();
        System.out.println("Execute interval: " + (endTime - startTime) + " ms");
    }

    // ByteBuffer.allocate()
    // ʹ��HeapByteBufferֱ����JVM��������"��"�ڴ��з���ռ�
    // ��ӵ����ݿ���ֱ��ͨ��buffer�鿴
    private void testAccessHeapMemory() {
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        for (int j = 0; j < 200; j++) buffer.putInt(j);
        buffer.flip(); // ���õ�д�����ʵλ��
        for (int j = 0; j < 200; j++) buffer.getInt();
        buffer.clear();
    }

    // ʹ��DirectByteBuffer���ò���ϵͳ�ں˺��������ڴ�ռ䣬����ָ���ڴ�ռ��ָ��
    // DirectByteBuffer() {
    //    base = unsafe.allocateMemory(size);
    //    address = base;  Ȼ��ָ��(����)address����(������ڴ��ַ)
    // }
    // TODO: javaͨ��native���ط�������openjdk�ײ�c���Ե�ʵ��
    // unsafe.cpp
    // UNSAFE_ENTRY(..)
    //    void* x = os::malloc(sz, mtInternal); �����ڴ�ռ䣬������ָ���ڴ�ռ��ָ��
    //    return addr_to_java(x);  ��ָ��ת��java����
    private void testAccessDirectMemory() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
        for (int j = 0; j < 200; j++) buffer.putInt(j);
        buffer.flip();
        for (int j = 0; j < 200; j++) buffer.getInt();
        buffer.clear();
    }
}

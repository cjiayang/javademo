package net.jyou.iodemo.bytestream;


import org.junit.jupiter.api.Test;

class CopyBytesTest {
    CopyBytes copyBytes = new CopyBytes();
    @Test
    void copyFileByte() {
        copyBytes.copyFileByte("life.txt", "out.txt");
    }

    @Test
    void copyFileByteBuffer() {
        copyBytes.copyFileByteBuffer("life.txt", "bufferedbByteOut.txt");
    }

    @Test
    void copyFileBytes() {
        copyBytes.copyFileBytes("life.txt", "byteArrayOut.txt");
    }
}

package net.jyou.iodemo.charstream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyCharactersTest {

    @Test
    void copyFileCharacter() {
        CopyCharacters copydemo = new CopyCharacters();
        copydemo.copyFileCharacter("xanadu.txt", "charout.txt");
    }
}

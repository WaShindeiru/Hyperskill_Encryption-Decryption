package encryptdecrypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionShiftTest {

    private EncryptionShift encryptionShift;

    public EncryptionShiftTest() {
        encryptionShift = new EncryptionShift();
    }

    @Test
    void encrypt() {
        String aha = "WZe";
        int key = 2;

        String result = encryptionShift.encrypt(aha, key);

        String correct = "YBg";
        assertEquals(correct, result);
    }
}
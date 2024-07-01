package org.griddynamics.encryptdecrypt.encrypt;

import org.griddynamics.encryptdecrypt.encrypt.EncryptionShift;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionShiftTest {

    private final EncryptionShift encryptionShift;

    public EncryptionShiftTest() {
        encryptionShift = new EncryptionShift();
    }

    @Test
    void basicEncrypt() {
        String toBeEncrypted = "WZe";
        int key = 2;

        String result = encryptionShift.encrypt(toBeEncrypted, key);

        String correct = "YBg";
        assertEquals(correct, result);
    }

    @Test
    void basicDecrypt() {
        String toBeDecrypted = "YBgc";
        int key = 3;

        String result = encryptionShift.decrypt(toBeDecrypted, key);

        String correct = "VYdz";
        assertEquals(correct, result);
    }
}
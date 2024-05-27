package encryptdecrypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUnicodeTest {

    private final EncryptionUnicode encryptionUnicode;

    public EncryptionUnicodeTest() {
        encryptionUnicode = new EncryptionUnicode();
    }

    @Test
    void basicEncrypt() {
        String toBeEncrypted = "1GTl ";
        int key = 4;

        String result = encryptionUnicode.encrypt(toBeEncrypted, key);

        String correct = "5KXp$";
        assertEquals(correct, result);
    }

    @Test
    void basicDecrypt() {
        String toBeDecrypted = "{-[`%";
        int key = 5;

        String result = encryptionUnicode.decrypt(toBeDecrypted, key);

        String correct = "v(V[ ";
        assertEquals(correct, result);
    }
}
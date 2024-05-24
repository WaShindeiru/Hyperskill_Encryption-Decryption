package encryptdecrypt;

public class EncryptionUnicode implements IEncrypt {

    static int maxCharValue = 255;

    @Override
    public String encrypt(String toEncrypt, int key) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = toEncrypt.charAt(i);

            int newCode = currentChar + key;
            if (newCode > maxCharValue) {
                newCode = newCode - (maxCharValue + 1);
            }

            char newChar = (char) (newCode);
            encrypted.append(newChar);
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String toEncrypt, int key) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = toEncrypt.charAt(i);

            int newCode = currentChar - key;
            if (newCode < 0) {
                newCode = newCode + (maxCharValue + 1);
            }

            char newChar = (char) (newCode);
            encrypted.append(newChar);
        }

        return encrypted.toString();
    }
}
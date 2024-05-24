package encryptdecrypt;

public class EncryptionShift implements IEncrypt{

    public static int a_code = 97;
    public static int z_code = 122;
    public static int code_distance = 122 - 97 + 1;

    @Override
    public String encrypt(String toEncrypt, int key) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = toEncrypt.charAt(i);

            if (Character.isAlphabetic(currentChar)) {
                int newCode = (int) (currentChar + key);
                if (newCode > z_code) {
                    newCode = newCode - code_distance;
                }

                char newChar = (char) (newCode);
                encrypted.append(newChar);

            } else {
                encrypted.append(currentChar);
            }
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String toEncrypt, int key) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = toEncrypt.charAt(i);

            if (Character.isAlphabetic(currentChar)) {
                int newCode = (int) (currentChar - key);
                if (newCode < a_code) {
                    newCode = newCode + code_distance;
                }

                char newChar = (char) (newCode);
                encrypted.append(newChar);

            } else {
                encrypted.append(currentChar);
            }
        }

        return encrypted.toString();
    }
}
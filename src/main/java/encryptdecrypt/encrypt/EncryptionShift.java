package encryptdecrypt.encrypt;

public class EncryptionShift implements IEncrypt {

    public static int a_code = 97;
    public static int z_code = 122;
    public static int code_distance = z_code - a_code + 1;

    public static int A_code = 65;
    public static int Z_code = 90;
    public static int uppercase_code_distance = Z_code - A_code + 1;

    @Override
    public String encrypt(String toEncrypt, int key) {

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = toEncrypt.charAt(i);

            if (Character.isAlphabetic(currentChar)) {
                if (Character.isLowerCase(currentChar)) {
                    int newCode = (int) (currentChar + key);
                    if (newCode > z_code) {
                        newCode = newCode - code_distance;
                    }

                    char newChar = (char) (newCode);
                    encrypted.append(newChar);

                } else {
                    int newCode = (int) (currentChar + key);
                    if (newCode > Z_code) {
                        newCode = newCode - uppercase_code_distance;
                    }

                    char newChar = (char) (newCode);
                    encrypted.append(newChar);
                }

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
                if (Character.isLowerCase(currentChar)) {
                    int newCode = (int) (currentChar - key);
                    if (newCode < a_code) {
                        newCode = newCode + code_distance;
                    }

                    char newChar = (char) (newCode);
                    encrypted.append(newChar);

                } else {
                    int newCode = (int) (currentChar - key);
                    if (newCode < A_code) {
                        newCode = newCode + uppercase_code_distance;
                    }

                    char newChar = (char) (newCode);
                    encrypted.append(newChar);

                }

            } else {
                encrypted.append(currentChar);
            }
        }

        return encrypted.toString();
    }
}

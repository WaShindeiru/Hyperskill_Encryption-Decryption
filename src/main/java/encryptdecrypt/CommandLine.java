package encryptdecrypt;

public class CommandLine {

    private FileAccess fileAccess;
    private IEncrypt encryption;

    public CommandLine() {
        fileAccess = new FileAccess();
    }

    public static IEncrypt getEncryption(String name) throws Exception {
        switch (name) {
            case "shift":
                return new EncryptionShift();

            case "unicode":
                return new EncryptionUnicode();

            default:
                throw new Exception("Error: Unknown encryption name: " + name);
        }
    }

    public int handleInput(String[] args) {

        String mode = "enc";
        int key = 0;
        String data = "";

        boolean isDataPresent = false;
        boolean fileInput = false;
        boolean fileOutput = false;

        String inputFilePath = "";
        String outputFilePath = "";

        String algorithmName = "shift";

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-in")) {
                inputFilePath = args[++i];
                fileInput = true;

            } else if (args[i].equals("-out")) {
                outputFilePath = args[++i];
                fileOutput = true;

            } else if (args[i].equals("-alg")) {
                algorithmName = args[++i];

            } else if (args[i].equals("-mode")) {
                mode = args[++i];

            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[++i]);

            } else if (args[i].equals("-data")) {
                data = args[++i];
                isDataPresent = true;
            }
        }

        try {
            encryption = getEncryption(algorithmName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }

        String wordToEncrypt = "";

        if (fileInput && !isDataPresent) {
            try {
                wordToEncrypt = fileAccess.readFromFile(inputFilePath);
            } catch (Exception e) {
                System.out.println("Error");
                return 1;
            }
        } else {
            wordToEncrypt = data;
        }

        String result = "";

        switch (mode) {
            case "enc":
                result = encryption.encrypt(wordToEncrypt, key);
                break;

            case "dec":
                result = encryption.decrypt(wordToEncrypt, key);
                break;
        }

        if (fileOutput) {
            try {
                fileAccess.writeToFile(outputFilePath, result);
            } catch (Exception e) {
                System.out.println("Error");
                return 1;
            }
        } else {
            System.out.println(result);
        }

        return 0;
    }
}

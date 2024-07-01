package org.griddynamics.encryptdecrypt;

import org.griddynamics.encryptdecrypt.CommandLine;
import org.griddynamics.encryptdecrypt.encrypt.EncryptionShift;
import org.griddynamics.encryptdecrypt.encrypt.EncryptionUnicode;
import org.griddynamics.encryptdecrypt.encrypt.IEncrypt;
import org.griddynamics.encryptdecrypt.internal.FileAccess;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

   private static FileAccess fileAccess = new FileAccess();
   private static String filePath = "./src/test/resources/test.txt";
   private static CommandLine commandLine = new CommandLine();

   @Test
   void testGetEncryptionShift() {
      String encryptionType = "shift";
      IEncrypt encryption = null;

      try {
         encryption = CommandLine.getEncryption(encryptionType);
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(encryption);
      if (encryption != null) {
         boolean result = (encryption instanceof EncryptionShift);
         assertTrue(result);
      }
   }

   @Test
   void testGetEncryptionUnicode() {
      String encryptionType = "unicode";
      IEncrypt encryption = null;

      try {
         encryption = CommandLine.getEncryption(encryptionType);
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(encryption);
      if (encryption != null) {
         boolean result = (encryption instanceof EncryptionUnicode);
         assertTrue(result);
      }
   }

   @Test
   void testGetWrongEncryption() {
      String encryptionType = "wrong";

      assertThatThrownBy(() -> CommandLine.getEncryption(encryptionType)).isInstanceOf(Exception.class);
   }

   @Test
   void testHandleInputEncryptShift() {
      String path = "./src/test/resources/test.txt";
      String[] args = {"-mode", "enc", "-key", "2", "-alg", "shift", "-data", "WZe",
            "-out", path};

      commandLine.handleInput(args);

      String result = null;
      try {
         result = fileAccess.readFromFile(path).trim();
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(result);
      String correct = "YBg";
      assertThat(result).isEqualTo(correct);
   }

   @Test
   void testHandleInputDecryptShift() {
      String path = "./src/test/resources/test.txt";
      String[] args = {"-mode", "dec", "-key", "3", "-alg", "shift", "-data", "YBgc",
            "-out", path};

      commandLine.handleInput(args);

      String result = null;
      try {
         result = fileAccess.readFromFile(path).trim();
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(result);
      String correct = "VYdz";
      assertThat(result).isEqualTo(correct);
   }

   @Test
   void testHandleInputDecryptShiftReadFromFileWithBothDataAndInputPresent() {
      String inputPath = "./src/test/resources/test2.txt";
      try {
         fileAccess.writeToFile(inputPath, "YBgc");
      } catch (Exception e) {
         fail(e.getMessage());
      }

      String path = "./src/test/resources/test.txt";
      String[] args = {"-in", inputPath, "-mode", "dec", "-key", "3", "-alg", "shift",
            "-out", path, "-data", "YBgc"};

      commandLine.handleInput(args);

      String result = null;
      try {
         result = fileAccess.readFromFile(path).trim();
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(result);
      String correct = "VYdz";
      assertThat(result).isEqualTo(correct);
   }

   @Test
   void testIncorrectAlgorithmName() {
      String algorithmName = "wrong";
      String path = "./src/test/resources/test.txt";
      String[] args = {"-mode", "dec", "-key", "3", "-alg", algorithmName, "-data", "YBgc",
            "-out", path};

      int result = commandLine.handleInput(args);
      assertThat(result).isEqualTo(1);
   }

   @Test
   void testTryToReadFromFileWhenTheFileDoesntExist() {
      String inputPath = "./src/test/resources/test2.txt";
      Path pathTemp = Paths.get(inputPath);

      try {
         Files.delete(pathTemp);
      } catch (IOException swallow) {}

      String path = "./src/test/resources/test.txt";
      String[] args = {"-in", inputPath, "-mode", "dec", "-key", "3", "-alg", "shift",
            "-out", path};

      int result = commandLine.handleInput(args);
      assertThat(result).isEqualTo(1);
   }

   @Test
   void testHandleInputDecryptShiftReadFromFile() {
      String inputPath = "./src/test/resources/test2.txt";
      try {
         fileAccess.writeToFile(inputPath, "YBgc");
      } catch (Exception e) {
         fail(e.getMessage());
      }

      String path = "./src/test/resources/test.txt";
      String[] args = {"-in", inputPath, "-mode", "dec", "-key", "3", "-alg", "shift",
            "-out", path};

      commandLine.handleInput(args);

      String result = null;
      try {
         result = fileAccess.readFromFile(path).trim();
      } catch (Exception e) {
         fail(e.getMessage());
      }

      assertNotNull(result);
      String correct = "VYdz";
      assertThat(result).isEqualTo(correct);
   }
}
package org.griddynamics.encryptdecrypt.encrypt;

public interface IEncrypt {

    String encrypt(String toEncrypt, int key);
    String decrypt(String toEncrypt, int key);
}

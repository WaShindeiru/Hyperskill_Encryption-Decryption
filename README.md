# Hyperskill_Encryption-Decryption

### Project link:
https://hyperskill.org/projects/46

## How to run
```zsh
mvn clean package
java -jar ./target/EncryptionDecryptionFinished-1.0-SNAPSHOT.jar -mode enc -key 6 -alg unicode -data "Welcome to GridDynamics" -out ./test.txt
java -jar ./target/EncryptionDecryptionFinished-1.0-SNAPSHOT.jar -mode dec -key 6 -alg unicode -in ./test.txt
```

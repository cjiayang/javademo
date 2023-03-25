package net.jyou.encryptdemo.rsa;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import static net.jyou.util.PrintUtil.println;

/**
 * @author Joey Chen
 * @created 2023/3/13 14:53
 */
public class RSAdemo {
    public static void main(String[] args) {
        generateKeyPair();
        encrypt();
    }

    @SneakyThrows
    public static void generateKeyPair() {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = generator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();

        println("---------------公钥信息---------------");
        println("publickey exponent: ", publicKey.getPublicExponent().toString());
        println("publickey modulus: ", publicKey.getModulus().toString());
        println("publickey format: ", publicKey.getFormat());

        println("---------------私钥信息---------------");
        println("privateKey exponent: ", privateKey.getPrivateExponent().toString());
        println("privateKey modulus: ", privateKey.getModulus().toString());
        println("privateKey format: ", privateKey.getFormat());
    }


    @SneakyThrows
    public static void encrypt() {
        //公钥加密
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        BigInteger modulus = new BigInteger("25277185865001286861504887079571360247656416421519814850974172264876772412741441183152225986920295906278758764623216993724561451927293631859267659729719679352577459944775478289947434030629785997306950778566714727273282832641532926429477817792231773612612788269526532863171614974139922419013452197002351359009289127247145481411683274630650660176476648783728778319641601675455244326345629502348596824815130386578744136548626202795370493762489812180185273667126669734826955047877774499536603274471938229121892579347759486245825447554020701596341211803979138420252932428065978766325049570949509981550365094619084370412897",10);
        BigInteger publicExponent = new BigInteger("65537");
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus,publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        String format = publicKey.getFormat();
        System.out.println("format:" + format);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[]  encryptData = cipher.doFinal("Hello,RSA!".getBytes());
        BigInteger result = new BigInteger(1, encryptData);
        System.out.println("hexData: " + result.toString(16));
        System.out.println("encryptData: " + new BigInteger(1, encryptData));
        //私钥解密
        BigInteger privateExponent = new BigInteger("14869636184954981344455771725570512101986630793274843553253997945096574558316993178419332109705024155435326405732927731801318608666142047836647177709688920733019802756472359346816964831818211690406562298641722277195582236703373955363163683252878703146116862629584149739442685385324571295912623747669738498006817178396181806852928009004704996899205583304000842022363319707830586494992529890413731705300946675986218373872963300385993473193684489609562508133709084541034068877613962296789311676758687296904035436827677724971753134223403841049318266550441822943178843679751515669807966818965265225761097430204402944075697",10);
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus,privateExponent);
        PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptData = cipher.doFinal(encryptData);
        println("decryptData:" + new String(decryptData));
    }

}

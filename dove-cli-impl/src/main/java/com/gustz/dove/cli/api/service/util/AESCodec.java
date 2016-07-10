package com.gustz.dove.cli.api.service.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * TODO: AES codec <br/>
 * 
 * secret key length: 16b <br/> 
 * mode:    ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128 <br/> 
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/ 
 * 
 * @author ZHENFENG ZHANG
 * @since [ Aug 15, 2015 ]
 */
public abstract class AESCodec {

    private static final Charset CHARSET = Charset.forName("utf-8");

    // algorithm
    private static final String AES_ALGO_KEY = "AES";

    private static final String MD5_ALGO_KEY = "MD5";

    // cipher algorithm
    private static final String CIPHER_ALGO = "AES/ECB/PKCS5Padding";

    /** 
     * Encrypt
     * 
     * @param seed
     * @param data
     * @return string encrypt data
     * @throws Exception 
     */
    public static String encrypt(final String seed, String data) throws Exception {
        return encrypt(seed, data, CHARSET);
    }

    /** 
     * Encrypt
     * 
     * @param seed
     * @param data
     * @param charset
     * @return string encrypt data
     * @throws Exception 
     */
    public static String encrypt(final String seed, String data, Charset charset) throws Exception {
        data = DatatypeConverter.printBase64Binary(data.getBytes(charset));
        byte[] _byData = DatatypeConverter.parseBase64Binary(data);
        // get keyt 
        byte[] keyt = getSecretKeyt(seed, charset);
        //
        Cipher cipher = Cipher.getInstance(CIPHER_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyt, AES_ALGO_KEY));
        // do
        return DatatypeConverter.printBase64Binary(cipher.doFinal(_byData));
    }

    /** 
     * Decrypt
     * 
     * @param seed
     * @param data
     * @return string decrypt data
     * @throws Exception 
     */
    public static String decrypt(final String seed, String data) throws Exception {
        return decrypt(seed, data, CHARSET);
    }

    /** 
     * Decrypt
     * 
     * @param seed
     * @param data
     * @param charset
     * @return string decrypt data
     * @throws Exception 
     */
    public static String decrypt(final String seed, String data, Charset charset) throws Exception {
        // get keyt
        byte[] keyt = getSecretKeyt(seed, charset);
        //
        Cipher cipher = Cipher.getInstance(CIPHER_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyt, AES_ALGO_KEY));
        // do
        byte[] _retVal = cipher.doFinal(DatatypeConverter.parseBase64Binary(data));
        return new String(_retVal, charset);
    }

    /** 
     * Get secret keyt
     * 
     * @param seed
     * @param charset
     * @return secret keyt
     * @throws NoSuchAlgorithmException 
     */
    private static byte[] getSecretKeyt(final String seed, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest _md5 = MessageDigest.getInstance(MD5_ALGO_KEY);
        _md5.update(seed.getBytes(charset));
        //
        return _md5.digest();
    }

}

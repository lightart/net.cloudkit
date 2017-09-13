
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.harmony.jretools.keytool.ArgumentsParser;
import org.apache.harmony.jretools.keytool.CertExporter;
import org.apache.harmony.jretools.keytool.KeyCertGenerator;
import org.apache.harmony.jretools.keytool.KeytoolException;
import org.apache.harmony.jretools.keytool.KeytoolKSLoaderSaver;
import org.apache.harmony.jretools.keytool.KeytoolParameters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Decoder;

public class KeyUtil {

    public static void genKey(File key1File, String alias, String password, int validity, String cn, String ou, String o, String l, String st, String c) throws NumberFormatException, KeytoolException, IOException, InvalidKeyException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, SignatureException, CertificateException {
        System.out.println(key1File.getPath());
        String[] args = new String[]{"-genkey", "-alias", alias, "-keypass", password, "-storepass", password, "-keyalg", "RSA", "-keysize", "1024", "-validity", validity + "", "-dname", "CN=,OU=,O=,L=,ST=,C=", "-keystore", key1File.getPath()};
        KeytoolParameters param = ArgumentsParser.parseArgs(args);
        KeyCertGenerator.genKey(param);
        if(param.isNeedSaveKS()) {
            KeytoolKSLoaderSaver.saveStore(param);
        }

    }

    public static void exportCert(File key1File, File key2File, String alias, String password) throws NumberFormatException, KeytoolException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchProviderException {
        String[] args = new String[]{"-export", "-alias", alias, "-storepass", password, "-rfc", "-keystore", key1File.getPath(), "-file", key2File.getPath()};
        KeytoolParameters param = ArgumentsParser.parseArgs(args);
        CertExporter.exportCert(param);
    }

    public static void exportPrivateKey(byte[] keyStore, File descFile, String alias, String password) throws Exception {
        CertificateCoder.exportPrivateKey(keyStore, descFile, alias, password);
    }

    public static byte[] encryptByKey1(byte[] data, byte[] key1, String alias, String password) throws CoderException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        return CertificateCoder.encryptByPrivateKey(data, key1, alias, password);
    }

    public static byte[] decryptByKey2(byte[] securityFile, byte[] key2) throws CoderException, InvalidKeyException, CertificateException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        return CertificateCoder.decryptByPublicKey(securityFile, key2);
    }

    public static byte[] encryptByKey2(byte[] data, byte[] key2) throws CoderException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, CertificateException, IOException {
        return CertificateCoder.encryptByPublicKey(data, key2);
    }

    public static byte[] decryptByKey1(byte[] securityFile, byte[] privateKey, String alias, String password) throws CoderException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        return CertificateCoder.decryptByPrivateKey(securityFile, privateKey, alias, password);
    }

    public static byte[] sign(byte[] data, byte[] privateKey, String alias, String password) throws Exception {
        return CertificateCoder.sign(data, privateKey, alias, password);
    }

    public static boolean verifySign(byte[] data, byte[] sign, byte[] publicKey) throws Exception {
        return CertificateCoder.verify(data, sign, publicKey);
    }

    public static int verifyXMLSign(InputStream xmlIS, byte[] publicKeyData) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(xmlIS);
        NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
        if(nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        } else {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(publicKeyData));
            PublicKey publicKey = certificate.getPublicKey();
            DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));
            XMLSignature signature = fac.unmarshalXMLSignature(valContext);
            boolean coreValidity = signature.validate(valContext);
            if(!coreValidity) {
                boolean sv = signature.getSignatureValue().validate(valContext);
                System.out.println("signature validation status: " + sv);
                Iterator i = signature.getSignedInfo().getReferences().iterator();

                for(int j = 0; i.hasNext(); ++j) {
                    boolean refValid = ((Reference)i.next()).validate(valContext);
                    if(!refValid) {
                        return j + 1;
                    }
                }

                return 0;
            } else {
                return -1;
            }
        }
    }

    public static PublicKey getPublicKeyFromPPK(String file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Map<String, String> keyMap = parseKV(file);
        String publicKeyInfo = (String)keyMap.get("Public-Lines");
        byte[] decodedPubKey = (new BASE64Decoder()).decodeBuffer(publicKeyInfo);
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(decodedPubKey));
        int leng = dis.readInt();
        byte[] tmpBytes = new byte[leng];
        dis.readFully(tmpBytes);
        new String(tmpBytes);
        BigInteger publicExponent = readInt(dis);
        BigInteger modulus = readInt(dis);
        dis.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        return publicKey;
    }

    public static PublicKey getPublicKeyFromPPKPublic(String file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        BufferedReader r = null;
        String result = "";

        try {
            r = new BufferedReader(new FileReader(file));
            int n = 0;

            String line;
            while((line = r.readLine()) != null) {
                ++n;
                if(n > 2 && !line.startsWith("-")) {
                    result = result + line;
                }
            }
        } finally {
            r.close();
        }

        byte[] decodedPubKey = (new BASE64Decoder()).decodeBuffer(result);
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(decodedPubKey));
        int var5 = dis.readInt();
        byte[] tmpBytes = new byte[var5];
        dis.readFully(tmpBytes);
        new String(tmpBytes);
        BigInteger publicExponent = readInt(dis);
        BigInteger modulus = readInt(dis);
        dis.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
        PublicKey var12 = keyFactory.generatePublic(rsaPublicKeySpec);
        return var12;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getPublicKeyFromPPKPublic("/Users/south/Downloads/123.pub"));
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private static void testPPK() {
        String file = "/Users/south/Downloads/123.ppk";

        try {
            Map<String, String> keyMap = parseKV(file);
            String publicKeyInfo = (String)keyMap.get("Public-Lines");
            byte[] decodedPubKey = (new BASE64Decoder()).decodeBuffer(publicKeyInfo);
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(decodedPubKey));
            int leng = dis.readInt();
            byte[] tmpBytes = new byte[leng];
            dis.readFully(tmpBytes);
            new String(tmpBytes);
            BigInteger publicExponent = readInt(dis);
            BigInteger modulus = readInt(dis);
            dis.close();
            byte[] decodedPriKey = (new BASE64Decoder()).decodeBuffer((String)keyMap.get("Private-Lines"));
            dis = new DataInputStream(new ByteArrayInputStream(decodedPriKey));
            BigInteger privateExponent = readInt(dis);
            BigInteger primeP = readInt(dis);
            BigInteger primeQ = readInt(dis);
            BigInteger iqmp = readInt(dis);
            BigInteger primeExponentP = privateExponent.mod(primeP.subtract(BigInteger.ONE));
            BigInteger primeExponentQ = privateExponent.mod(primeQ.subtract(BigInteger.ONE));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
            keyFactory.generatePublic(rsaPublicKeySpec);
            RSAPrivateCrtKeySpec rsaPrivateKeySpec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, iqmp);
            PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
            System.out.println(privateKey);
        } catch (Exception var22) {
            var22.printStackTrace();
        }

    }

    private static BigInteger readInt(DataInputStream dis) throws IOException {
        int leng = dis.readInt();
        byte[] tmpBytes = new byte[leng];
        dis.readFully(tmpBytes);
        return new BigInteger(1, tmpBytes);
    }

    private static Map<String, String> parseKV(String file) throws IOException {
        HashMap<String, String> kv = new HashMap();
        BufferedReader r = null;

        try {
            r = new BufferedReader(new FileReader(file));
            String k = null;

            String line;
            while((line = r.readLine()) != null) {
                int idx = line.indexOf(": ");
                if(idx > 0) {
                    k = line.substring(0, idx);
                    if(!"Public-Lines".equals(k) && !"Private-Lines".equals(k)) {
                        kv.put(k, line.substring(idx + 2));
                    }
                } else {
                    String s = (String)kv.get(k);
                    if(s == null) {
                        s = "";
                    }

                    s = s + line;
                    kv.put(k, s);
                }
            }
        } finally {
            r.close();
        }

        return kv;
    }
}












------------------------------------------------------------------------------------------------------------------------

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.harmony.luni.util.Base64;
import sun.misc.BASE64Encoder;

public abstract class Certificate {
    public static final String KEY_STORE = "JKS";
    public static final String X509 = "X.509";
    public static final int BATCH_SIZE = 102;

    public CertificateCoder() {
    }

    private static PrivateKey getPrivateKey(byte[] keyStore, String alias, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore ks = getKeyStore(keyStore, password);
        PrivateKey key = (PrivateKey)ks.getKey(alias, password.toCharArray());
        return key;
    }

    public static PublicKey getPublicKey(byte[] certificateBs) throws CertificateException, IOException {
        Certificate certificate = getCertificate(certificateBs);
        PublicKey key = certificate.getPublicKey();
        return key;
    }

    private static Certificate getCertificate(byte[] certificateBs) throws CertificateException, IOException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream in = new ByteArrayInputStream(certificateBs);
        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();
        return certificate;
    }

    public static Certificate getCertificateByKeyStore(byte[] keyStore, String alias, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore ks = getKeyStore(keyStore, password);
        Certificate certificate = ks.getCertificate(alias);
        return certificate;
    }

    private static KeyStore getKeyStore(byte[] keyStore, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        InputStream is = new ByteArrayInputStream(keyStore);
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }

    public static byte[] encryptByPrivateKey(byte[] data, byte[] keystore, String alias, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PrivateKey privateKey = getPrivateKey(keystore, alias, password);
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(1, privateKey);
        byte[] bs = new byte[0];

        for(int i = 0; i < data.length; i += 102) {
            byte[] subarray = ArrayUtils.subarray(data, i, i + 102);
            byte[] doFinal = cipher.doFinal(subarray);
            bs = ArrayUtils.addAll(bs, doFinal);
        }

        return bs;
    }

    public static byte[] decryptByPrivateKey(byte[] data, byte[] keyStore, String alias, String password) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        PrivateKey privateKey = getPrivateKey(keyStore, alias, password);
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(2, privateKey);
        byte[] bs = new byte[0];

        for(int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            bs = ArrayUtils.addAll(bs, doFinal);
        }

        return bs;
    }

    public static void exportPrivateKey(byte[] keyStore, File descFile, String alias, String password) throws Exception {
        PrivateKey privateKey = getPrivateKey(keyStore, alias, password);
        BASE64Encoder e = new BASE64Encoder();
        FileWriter fw = new FileWriter(descFile);
        fw.write("-----BEGIN RSA PRIVATE KEY-----");
        fw.write(e.encode(privateKey.getEncoded()));
        fw.write("/n");
        fw.write("-----END RSA PRIVATE KEY-----");
        fw.close();
    }

    public static byte[] encryptByPublicKey(byte[] data, byte[] certificateBs) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, CertificateException, IOException {
        PublicKey publicKey = getPublicKey(certificateBs);
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(1, publicKey);
        byte[] bs = new byte[0];

        for(int i = 0; i < data.length; i += 102) {
            byte[] subarray = ArrayUtils.subarray(data, i, i + 102);
            byte[] doFinal = cipher.doFinal(subarray);
            bs = ArrayUtils.addAll(bs, doFinal);
        }

        return bs;
    }

    public static byte[] decryptByPublicKey(byte[] data, byte[] certificateBs) throws CertificateException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        PublicKey publicKey = getPublicKey(certificateBs);
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(2, publicKey);
        byte[] bs = new byte[0];

        for(int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            bs = ArrayUtils.addAll(bs, doFinal);
        }

        return bs;
    }

    public static boolean verifyCertificate(byte[] certificateBs) {
        return verifyCertificate(new Date(), certificateBs);
    }

    public static boolean verifyCertificate(Date date, byte[] certificateBs) {
        boolean status = true;

        try {
            Certificate certificate = getCertificate(certificateBs);
            status = verifyCertificate(date, certificate);
        } catch (Exception var4) {
            status = false;
        }

        return status;
    }

    private static boolean verifyCertificate(Date date, Certificate certificate) {
        boolean status = true;

        try {
            X509Certificate x509Certificate = (X509Certificate)certificate;
            x509Certificate.checkValidity(date);
        } catch (Exception var4) {
            status = false;
        }

        return status;
    }

    public static byte[] sign(byte[] sign, byte[] keyStore, String alias, String password) throws Exception {
        X509Certificate x509Certificate = (X509Certificate)getCertificateByKeyStore(keyStore, alias, password);
        KeyStore ks = getKeyStore(keyStore, password);
        PrivateKey privateKey = (PrivateKey)ks.getKey(alias, password.toCharArray());
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(sign);
        return signature.sign();
    }

    public static boolean verify(byte[] data, byte[] sign, byte[] certificateBs) throws Exception {
        X509Certificate x509Certificate = (X509Certificate)getCertificate(certificateBs);
        PublicKey publicKey = x509Certificate.getPublicKey();
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    public static boolean verifyCertificate(Date date, byte[] keyStore, String alias, String password) {
        boolean status = true;

        try {
            Certificate certificate = getCertificateByKeyStore(keyStore, alias, password);
            status = verifyCertificate(date, certificate);
        } catch (Exception var6) {
            status = false;
        }

        return status;
    }

    public static boolean verifyCertificate(byte[] keyStore, String alias, String password) {
        return verifyCertificate(new Date(), keyStore, alias, password);
    }

    public static void main(String[] args) {
        try {
            byte[] bs = FileUtils.readFileToByteArray(new File("/Users/Administrator/Downloads/test.pub"));
            bs = Base64.decode(bs);
            System.out.println(new String(bs));
            Object o = getPublicKey(bs);
            System.out.println(o);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}

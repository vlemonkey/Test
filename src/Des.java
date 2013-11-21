

/*字符串 DESede(3DES) 加密*/
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

public class Des {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish

	private static final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
			0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
			0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2 }; // 24字节的密钥

	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		if (null == str) {
			return "";
		}else {
			byte[] encoded = encryptMode(keyBytes, str.getBytes()); 
			return byte2hex(encoded);
		}
	}
	
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}else {
			byte[] desc = hex2byte(str);
			return new String(decryptMode(keyBytes, desc));
		}
	}
	
	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	private static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	private static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/** 
     * 将二进制转化为16进制字符串 
     *  
     * @param b 
     *            二进制字节数组 
     * @return String 
     */  
	private static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }
    
    /** 
     * 十六进制字符串转化为2进制 
     *  
     * @param hex 
     * @return 
     */  
    private static byte[] hex2byte(String hex) { 
    	int size = hex.length() / 2;
        byte[] ret = new byte[size];  
        byte[] tmp = hex.getBytes();  
        for (int i = 0; i < size; i++) {  
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);  
        }  
        return ret;  
    }  
    
    /** 
     * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF 
     *  
     * @param src0 
     *            byte 
     * @param src1 
     *            byte 
     * @return byte 
     */  
    private static byte uniteBytes(byte src0, byte src1) {  
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))  
                .byteValue();  
        _b0 = (byte) (_b0 << 4);  
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))  
                .byteValue();  
        byte ret = (byte) (_b0 ^ _b1);  
        return ret;  
    }  
	

	public static void main(String[] args) {
		String str = "0100000021A7F2FA327A5AB4EC19D4CD790E60ABD73C6D66C39165289534D66CA66BA2A371132C53E2697489F7165F046050ABA71E";
		System.out.println(str.length());
//		String szSrc = "adfa";
//		System.out.println("before:" + encode(szSrc));
//		System.out.println("after:" + decode(encode(szSrc)));
	}
}
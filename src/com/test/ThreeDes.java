package com.test;

/*字符串 DESede(3DES) 加密*/
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDes {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
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
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
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
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp+":";  
            } else {  
                hs = hs + stmp+":";  
            }  
        }  
        return hs.toUpperCase();  
    }
    
    public static byte[] hexStr2ByteArray(String in) {
		byte[] out = new byte[in.length() / 2];
		int left;//高四位
		int right;//低四位
		for (int i = 0; i < in.length(); i += 2) {
			char c1 = in.charAt(i);
			if (c1 >= '0' && c1 <= '9') {
				left = c1 - '0';
			} else if (c1 >= 'a' && c1 <= 'f') {
				left = c1 - 'a' + 10;
			} else {
				left = c1 - 'A' + 10;
			}
			char c2 = in.charAt(i + 1);
			if (c2 >= '0' && c2 <= '9') {
				right = c2 - '0';
			} else if (c2 >= 'a' && c2 <= 'f') {
				right = c2 - 'a' + 10;
			} else {
				right = c2 - 'A' + 10;
			}
			out[i / 2] = (byte)(((left & 0xf) << 4)| (right & 0xf));
		}
		return out;
	}
	

	public static void main(String[] args) {

		// 添加新安全算法,如果用JCE就要把它添加进去
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 }; // 24字节的密钥

		String szSrc = "460023711723730460023711723730";
		System.out.println("加密前的字符串:" + szSrc);

		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
		for (byte b : encoded) {
			System.out.print(b + " ");
		}
		System.out.println("\n-----------------------------------");
		
		String strDesc = byte2hex(encoded);
		System.out.println("对应16进制：" + strDesc);

		byte[] desc = hexStr2ByteArray("AC");
		for (byte b : desc) {
			System.out.print(b + " ");
		}
		System.out.println("\n-----------------------------------");
		
		//byte[] srcBytes = decryptMode(keyBytes, desc);
		//System.out.println("解密后的字符串:" + (new String(srcBytes)));
	}
}
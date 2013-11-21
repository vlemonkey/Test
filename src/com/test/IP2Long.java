package com.test;

public class IP2Long {

	 //��127.0.0.1��ʽ��IP��ַת����ʮ��������������û�н����κδ�����   
    public static long ipToLong(String strIp) {   
        long[] ip = new long[4];   
        //���ҵ�IP��ַ�ַ�����.��λ��   
        int position1 = strIp.indexOf(".");   
        int position2 = strIp.indexOf(".", position1 + 1);   
        int position3 = strIp.indexOf(".", position2 + 1);   
        //��ÿ��.֮����ַ���ת��������   
         ip[0] = Long.parseLong(strIp.substring(0, position1));   
         ip[1] = Long.parseLong(strIp.substring(position1+1, position2));   
         ip[2] = Long.parseLong(strIp.substring(position2+1, position3));   
         ip[3] = Long.parseLong(strIp.substring(position3+1));   
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];   
     }   
       
    //��ʮ����������ʽת����127.0.0.1��ʽ��ip��ַ   
    public static String longToIP(long longIp) {   
         StringBuffer sb = new StringBuffer("");   
        //ֱ������24λ   
         sb.append(String.valueOf((longIp >>> 24)));   
         sb.append(".");   
        //����8λ��0��Ȼ������16λ   
         sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));   
         sb.append(".");   
        //����16λ��0��Ȼ������8λ   
         sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));   
         sb.append(".");   
        //����24λ��0   
         sb.append(String.valueOf((longIp & 0x000000FF)));   
        return sb.toString();   
     }   
    /** *//** 
      * @param args 
      */   
    public static void main(String[] args) {   
         String ipStr = "192.168.0.1";   
        long longIp = IP2Long.ipToLong(ipStr);   
         System.out.println("192.168.0.1 ��������ʽΪ��" + longIp);   
         System.out.println("����" + longIp + "ת�����ַ���IP��ַ��"   
                 + IP2Long.longToIP(longIp));   
        //ip��ַת���ɶ�������ʽ���   
         System.out.println("192.168.0.1 �Ķ�������ʽΪ��" + Long.toBinaryString(longIp));   
   
     }   
}

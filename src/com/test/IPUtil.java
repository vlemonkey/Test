package com.test;

public class IPUtil{
    
    public static void main(String[] args){
        String ip="202.99.192.68";
        String [] ips=ip.split("[.]");
        byte [] ipbs=new byte[4];
        //IP地址压缩成4字节,如果要进一步处理的话,就可以转换成一个int了.
        for(int i=0;i<4 ;i++){
            int m=Integer.parseInt(ips[i]);
            byte b=(byte) m;
            if(m>127){ //如果超出byte表数范围,则变成负数
                b=(byte)(127-m);
            }
            System.out.println(b);
            ipbs[i]=b;
        }
        //把4字节的数组解成IP
        ip="";
        for(int i=0;i<4;i++){
            String tmp=String.valueOf(ipbs[i]);
            if(ipbs[i]<0){
                tmp=String.valueOf(127+Math.abs(ipbs[i]));
            }
            if(i<3){
                ip+=tmp+".";
            }else{
                ip+=tmp;
            }
        }
        System.out.println(ip);
    }
}

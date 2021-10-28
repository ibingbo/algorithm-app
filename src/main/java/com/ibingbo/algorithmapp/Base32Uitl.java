package com.ibingbo.algorithmapp;

/**
 * 写一个程序实现base32的编码和解码。 base32编码的原理：对于任意的二进制字节数组，将连续的5个bit进行编码，5个bit可以表示32个不同的数，分别映射到 a-z（26个字母）、0-5（6个数字），比如00000
 * 映射到a、00001映射到b、00010映射到c、00011映射到d、...、11100映射到3，11110映射到4、11111映射到5。这样任意的二进制字节数组都可以用这32个字符编码。如果字节数组的长度不是5
 * 的整倍数，需要在末位进行补充0。
 *  
 * 比如对于字符数组 "ABC"，ASCII表中A对应的数值是65、B是66、C是67，整个字符数组的二进制编码是  01000001  01000010 01000011，按照5个一组并进行末尾补零，变成 01000  00101
 *  00001 00100  00110，对应的base32的编码是  ifbeg。 写一个类来实现base32的编码和解码功能，对于解码函数如果输入不是合法的base32编码则直接抛出异常
 *
 * @author zhangbingbing
 * @date 2021/1/28
 */
public class Base32Uitl {

    private static final String CODE_TABLE = "abcdefghijklmnopqrstuvwxyz012345";

    /**
     * 编码
     *
     * @param data
     * @return
     */
    public static byte[] encode(byte[] data) {
        //计算字符串的长度，每个 byte 为8位二进制数，1到5位二进制数需要1个字符，6到10位为2个，依此类推
        int length = (data.length * 8 + 4) / 5;
        StringBuilder result = new StringBuilder(length);
        //当前 byte[] 索引
        int cur_data_idx = 0;
        //当前 byte
        byte cur_data_byte = 0;
        // 编码表中索引值
        int index;
        //当前二进制位索引
        int bit_idx;
        for (int i = 0; i < length; i++) {
            index = 0;
            // 每5个位计算一个编码表中的索引值
            for (int j = 0; j < 5; j++) {
                bit_idx = i * 5 + j;
                if (bit_idx == data.length * 8) {
                    break;
                }
                if (bit_idx % 8 == 0) {
                    cur_data_byte = data[cur_data_idx++];
                }
                index <<= 1;
                index |= (cur_data_byte & 128) == 0 ? 0 : 1;
                cur_data_byte <<= 1;
            }
            result.append(CODE_TABLE.charAt(index));
        }
        return result.toString().getBytes();
    }

    /**
     * 解码
     *
     * @param data
     * @return
     */
    public static byte[] decode(byte[] data) {
        String value = new String(data).toLowerCase();
        int length = value.length() * 5 / 8;
        byte[] result = new byte[length];
        int cur_value_idx = 0;
        int cur_code_idx = 0;
        int k;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 8; j++) {
                k = i * 8 + j;
                if (k == value.length() * 5) {
                    break;
                }
                if (k % 5 == 0) {
                    cur_code_idx = CODE_TABLE.indexOf(value.charAt(cur_value_idx++));
                    if (cur_value_idx == value.length() && value.length() % 8 != 0) {
                        cur_code_idx <<= value.length() * 5 % 8;
                    }
                }
                result[i] <<= 1;
                result[i] |= (byte) ((cur_code_idx & 16) == 0 ? 0 : 1);
                cur_code_idx <<= 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ABC";
        System.out.println(new String(encode(s.getBytes())));
        System.out.println(new String(decode("ifbed".getBytes())));
    }

}

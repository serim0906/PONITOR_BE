package pebite.Ponitor_BE.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHA256 {

    private static SHA256 sha256 = null;

    public static SHA256 getInstance(){
        if(sha256 == null){
            sha256 = new SHA256();
        }
        return sha256;
    }

    public String encrypt(String data) throws NoSuchAlgorithmException {
        String retVal = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());

            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();

            for(int i=0; i<byteData.length; i++) {
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for(int i=0; i<byteData.length;i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            retVal = hexString.toString();
        } catch(NoSuchAlgorithmException e){
            log.error("encrypt SHA256 Error : {}", e.toString());
        }
        return retVal;
    }

}

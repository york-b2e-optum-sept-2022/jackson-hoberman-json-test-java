package net.yorksolutions.jsontest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service
public class JsonTestService {

    public HashMap getIp(HttpServletRequest request){
        HashMap map = new HashMap();
        map.put("ip", request.getRemoteAddr());

        return map;
    }

    // https://www.baeldung.com/spring-rest-http-headers
    public HashMap getHeaders(@RequestHeader Map<String, String> headerInfo) {
        HashMap map = new HashMap();
        headerInfo.forEach((key, value) -> {
            map.put(key, value);
        });
        return map;
    }

    public HashMap getDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT") );

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT") );

        String dateString = dateFormat.format(date);
        String timeString = timeFormat.format(date);

        HashMap map = new HashMap();
        map.put("date", dateString);
        map.put("time", timeString);
        map.put("milliseconds_since_epoch", Instant.now().toEpochMilli() );

        return map;
    }

    public HashMap echo(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] tokenList = uri.split("/");

        HashMap map = new HashMap();
        for (int i = 2; i < tokenList.length; i+=2) {
            String key = tokenList[i];

            String value = "";
            int valueIndex = i + 1;
            if (valueIndex < tokenList.length) {
                value = tokenList[valueIndex];
            }

            map.put(
                    key,
                    value
            );
        }

        return map;
    }

    //https://www.baeldung.com/java-md5
    public HashMap md5(String inputText){
        HashMap map = new HashMap();
        try {
            String text = inputText;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            map.put("text", inputText);
            map.put("md5", myHash);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return map;
    }

    public String code(HttpServletRequest request){
        return "alert(\" Your IP address is: " + request.getRemoteAddr() + "\");";
    }

    public String cookie(HttpServletRequest request){
        return "alert(\" Your IP address is: " + request.getRemoteAddr() + "\");";
    }
}
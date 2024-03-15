package com.springboot.mysql.crud.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringBootMysqlCrudUtils {

    public static Date getDate() {

        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        Date dateInUTC = Date.from(utc.toInstant());
        return dateInUTC;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String reminderEmail) {
        if (StringUtils.isEmpty(reminderEmail))
            return false;

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(reminderEmail);
        if (matcher.matches()) {
            return false;
        }
        return true;
    }

    public static Date convertStringToDate(String inputDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            return formatter.parse(inputDate);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertDateToString(Date inputDate) {
        try {
            SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
            return newFormat.format(inputDate);
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean isValidDate(String dateMM, String dateDD, String dateYYYY) {
        String dateStr = addZero(dateMM) + "/" + addZero(dateDD) + "/" + dateYYYY;
        if (!dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String addZero(String data) {
        int dataInt = Integer.parseInt(data);
        if (dataInt >= 1 && dataInt <= 9) {
            return "0" + dataInt;
        } else {
            return data;
        }
    }

    int expiryTimeInMinute = 100;

    public boolean checkExpiry(Date createdDate) {
        Date todayDate = getDate();
        long diff = todayDate.getTime() - createdDate.getTime();
        long diffMinutes = diff / (60 * 1000);
        if (diffMinutes > expiryTimeInMinute) {
            return false;
        }
        return true;
    }

    public String getDataFromRestTemplate(String url, HttpHeaders headers, String requestBody, HttpMethod method) {
        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public String convertObjectToString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public Object convertStingToObject(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, Object.class);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public static void checkExpiry() throws Exception {
        if (isExpire())
            throw new Exception("Bad Practice!!!");
    }

    private static boolean isExpire() {
        String date = "Mar-30-2024 01:00:00 AM";
        if (date.isEmpty() || date.trim().equals("")) {
            return false;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a"); // Jan-20-2015 1:30:55 PM
            Date d = null;
            Date d1 = null;
            String today = getToday("MMM-dd-yyyy hh:mm:ss a");
            try {
                d = sdf.parse(date);
                d1 = sdf.parse(today);
                if (d1.compareTo(d) < 0) {// not expired
                    return false;
                } else if (d.compareTo(d1) == 0) {// both date are same
                    if (d.getTime() < d1.getTime()) {// not expired
                        return false;
                    } else if (d.getTime() == d1.getTime()) {//expired
                        return true;
                    } else {//expired
                        return true;
                    }
                } else {//expired
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return true;
            }
        }
    }

    private static String getToday(String format) {
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }

    public static String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        String password = "";

        password += lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())) + "";
        password += capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length())) + "";
        password += specialCharacters.charAt(random.nextInt(specialCharacters.length())) + "";
        password += numbers.charAt(random.nextInt(numbers.length())) + "";

        for (int i = 4; i < length; i++) {
            password += combinedChars.charAt(random.nextInt(combinedChars.length())) + "";
        }
        return password;
    }
}

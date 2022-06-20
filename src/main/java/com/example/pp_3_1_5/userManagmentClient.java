package com.example.pp_3_1_5;

import com.example.pp_3_1_5.model.User;
import org.springframework.http.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class userManagmentClient {

    static RestTemplate restTemplate = new RestTemplate();
    static String url = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {




        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity responseEntity = getListUsers(requestEntity);
        headers.set("Cookie", responseEntity.getHeaders().get("Set-Cookie")
                .stream().collect(Collectors.joining(";")));



        getListUsers(requestEntity);



        User sysUser = new User();
        sysUser.setId(3L);
        sysUser.setName("James");
        sysUser.setLastName("Brown");
        sysUser.setAge(19);
        requestEntity = new HttpEntity<>(sysUser, headers);

        setSingleUser(requestEntity);

        sysUser.setName("Thomas");
        sysUser.setLastName("Shelby");
        requestEntity = new HttpEntity<>(sysUser, headers);

        updateUser(requestEntity);

        deleteUser(requestEntity);

    }


    public static ResponseEntity getListUsers(HttpEntity<Object> requestEntity) {

        ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, List.class);

            System.out.println("response " + responseEntity.getBody());

            return responseEntity;


    }

    private static void setSingleUser(HttpEntity<Object> requestEntity) {


        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("response " + responseEntity.getBody());


    }

    private static void updateUser(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        System.out.println("response " + responseEntity.getBody());


    }

    private static void deleteUser(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/3", HttpMethod.DELETE, requestEntity, String.class);

        System.out.println("response " + responseEntity.getBody());


    }


}

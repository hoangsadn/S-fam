package com.example.demo.SpringBootPushNotification.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PushNotificationRequest {


	private String title;
    private String message;


    private String topic;
    private List<String> listToken;
    private String token;




}
package com.example.demo.SpringBootPushNotification.service;

import com.example.demo.SpringBootPushNotification.model.PushNotificationRequest;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Log4j2
@Service
public class FCMService {

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response+ " msg "+jsonOutput);
    }

    public void sendMessageCustomDataWithTopic(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getMessageWithDataCustomWithTopic(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + data.get("topic") + ", " + response+ " msg "+jsonOutput);
    }


    public void sendMessageWithoutData(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutData(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
    }

    public void sendMessageToTokenWithData(Map<String, String> data,PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data,request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private String sendMultiAndGetResponse(MulticastMessage message) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().sendMulticast(message).getResponses().toString();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return Message.builder().setNotification(
                new Notification(request.getTitle(), request.getMessage()))
                .putAllData(data).setToken(request.getToken())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        return Message.builder().setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }


    private Message getMessageWithDataCustomWithTopic(Map<String, String> data, PushNotificationRequest request) {
        return  getPreconfiguredMessageBuilder(request).
                putAllData(data).setTopic(request.getTopic())
                .build();
    }

    private MulticastMessage getPreconfiguredMultiMessageWithData(Map<String, String> data, PushNotificationRequest request) {
        return MulticastMessage.builder().setNotification(
                new Notification(request.getTitle(), request.getMessage()))
                .putAllData(data)
                .addAllTokens(request.getListToken())
                .build();
    }
    public void sendMessageCustomDataWithManyToken(Map<String, String> data, PushNotificationRequest request)
            throws FirebaseMessagingException {
        MulticastMessage message = getPreconfiguredMultiMessageWithData(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendMultiAndGetResponse(message);
        logger.info("Sent multi message with data: " + response+ " msg "+jsonOutput);
    }
}
package com.example.demo.warning;

import com.example.demo.SpringBootPushNotification.model.PushNotificationRequest;
import com.example.demo.SpringBootPushNotification.service.PushNotificationService;
import com.example.demo.event.EventRequest;
import com.example.demo.family.Family;
import com.example.demo.family.FamilyService;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Log4j2
public class WarningService {
    private PushNotificationService pushNotificationService;
    private final AppUserService appUserService;
    private final FamilyService familyService;
    private Set<AppUser> appUserSet;

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    public String sendWarningNotification(String email) {
        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);
        Family family = familyService.getFamilyById(appUser.get().getFamily().getId());
        List<String> listToken = new ArrayList<>();

        appUserSet = family.getMemberSet();
        for (AppUser user : appUserSet) {
            if(appUser.get()!= user) {
                listToken.add(user.getFirebaseToken());
            }
        }
        LocalDateTime date = LocalDateTime.now().plusMinutes(1);

        String minutes = String.valueOf(date.getMinute());
        String Hour = String.valueOf(date.getHour());
        String Day = String.valueOf(date.getDayOfMonth());
        String Month = String.valueOf(date.getMonthValue());
        CronTrigger cronTrigger
                = new CronTrigger("0 " + minutes + " " + Hour + " " + Day + " " + Month + " *");
        taskScheduler.schedule(RunnableTask(appUser.get(),listToken), cronTrigger);

        return "send warning success";

    }
    public Runnable RunnableTask(AppUser appUser,List<String> listToken) {

        return new Runnable() {

            @Override
            public void run() {
                PushNotificationRequest request = new PushNotificationRequest();
                request.setTitle("Khẩn cấp");
                request.setMessage(appUser.getFullName()+" Cần giúp đỡ ngay!!!");
                request.setListToken(listToken);

                log.info("runable ", request);

                Map<String, String> map = new HashMap<>();
                map.put("type","Warning");
                map.put("id",appUser.getId().toString());

                pushNotificationService.sendPushNotificationCustomDataWithManyToken(map,request);
            }

        };
    }
}

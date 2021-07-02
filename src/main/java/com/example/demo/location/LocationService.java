package com.example.demo.location;

import com.example.demo.note.Note;
import com.example.demo.user.AppUser;
import com.example.demo.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    private AppUserService appUserService;
    private LocationRepository locationRepository;

    @Transactional
    public String updateLocation(String email, LocationRequest request) {

        Optional<AppUser> appUser = appUserService.findAppUserByEmail(email);
        if (!appUser.isPresent())
        {
            return  "user not found";
        }

        Optional <Location> location = locationRepository.findByAppUser(appUser.get());
        if (!location.isPresent()) {
            return "location not found";
        }
        location.get().setLatitude(request.getLatitude());
        location.get().setLongitude(request.getLongitude());


        locationRepository.save(location.get());

        return "update success";
    }

    public Location getLocationByEmail(String email) {
        return locationRepository.findByAppUser_Email(email).get();
    }
}

package com.example.demo.schedule;

import com.example.demo.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ScheduleRepository extends JpaRepository <Schedule,Long> {

    //List<Schedule> findAllByAppUserSchedule(AppUser appUser);


    Optional<Schedule> findByIdAndAppUserScheduleId(Long id,Long appUserId);
    //List<Schedule> findByAppUserSchedule(Long id);
}

package com.example.demo.bgTasks;


import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class BackgroundTasks {
    private static final Logger log= LoggerFactory.getLogger(BackgroundTasks.class);

    @Async("thPlExectr")
    @Scheduled(fixedRate = 5000)
    public void backTask(){
        log.info("Hi Vineet! I am a background task");
    }
}

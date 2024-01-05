package a.b.c.schedule;

import a.b.c.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@SpringBootApplication
@EnableScheduling
@ConditionalOnProperty( prefix = "spring.scheduler.test"
                      , name = "use-yn"
                      , havingValue= "Y"
                      , matchIfMissing = true )
public class TestSchedule {

    @Autowired
    private TestService testService;


    @Scheduled( cron="${spring.scheduler.test.cron}" )
    public void scheduler(){

        testService.execute();

    }

}

package com.battlejawn.Scheduler;

import com.battlejawn.Service.HeroService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class HeroHealthReplenishScheduler {
    private final HeroService heroService;
    private final Logger logger = Logger.getLogger(HeroHealthReplenishScheduler.class.getName());

    public HeroHealthReplenishScheduler(HeroService heroService){
        this.heroService = heroService;
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void replenishAllHeroesHealth() {
        logger.info("Inside replenishAllHeroesHealth service class. This job runs once a day at 1 am.");
        try {
            heroService.restAllHeroes();
        }catch(Exception e) {
            throw new RuntimeException("replenishAllHeroesHealth failed. Error: ", e);
        }
    }
}

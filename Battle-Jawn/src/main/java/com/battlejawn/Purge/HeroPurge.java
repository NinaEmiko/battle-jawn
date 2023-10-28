package com.battlejawn.Purge;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import com.battlejawn.Repository.HeroRepository;
import org.springframework.stereotype.Service;

@Service
public class HeroPurge {

     private final HeroRepository heroRepository;
     private Logger logger = Logger.getLogger(HeroPurge.class.getName());

     public HeroPurge(HeroRepository heroRepository) {
         this.heroRepository = heroRepository;
     }

     @Scheduled(cron = "0 0 */6 * * ?")
     public void purgeHeroes() {
         logger.info("Inside purgeHeroes");
         LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
//         heroRepository.deleteByDateBefore(oneDayAgo);

     }
    
}

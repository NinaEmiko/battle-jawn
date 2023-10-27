package com.battlejawn.Purge;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import com.battlejawn.Repository.ToonRepository;
import org.springframework.stereotype.Service;

@Service
public class HeroPurge {

     private final ToonRepository toonRepository;
     private Logger logger = Logger.getLogger(HeroPurge.class.getName());

     public HeroPurge(ToonRepository toonRepository) {
         this.toonRepository = toonRepository;
     }

     @Scheduled(cron = "0 0 */6 * * ?")
     public void purgeHeroes() {
         logger.info("Inside purgeHeroes");
         LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
//         toonRepository.deleteByDateBefore(oneDayAgo);

     }
    
}

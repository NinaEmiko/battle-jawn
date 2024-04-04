package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleHistoryMessage;
import com.battlejawn.Entities.Battle.BattleSession;
import com.battlejawn.Entities.Enemy.Enemy;
import com.battlejawn.Entities.Hero.Hero;
import com.battlejawn.Repository.BattleSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.logging.Logger;
@Service
@AllArgsConstructor
public class BattleSessionService {
    private final BattleSessionRepository battleSessionRepository;
    private final EnemyService enemyService;
    private final HeroService heroService;
    private final ExperienceProcessorService experienceProcessorService;
    private final BattleHistoryMessageService battleHistoryMessageService;
    private final Logger logger = Logger.getLogger(BattleSessionService.class.getName());

    public BattleSession getBattleSessionById(Long id) {
        logger.info("Inside getBattleSessionById service method. Battle Session ID: " + id + ".");
        Optional<BattleSession> battleSession = battleSessionRepository.findById(id);
        if (battleSession.isPresent()) {
            return battleSession.get();
        } else {
            throw new EntityNotFoundException("BattleSession with ID " + id + " not found");
        }
    }

    @Transactional
    public BattleSession createNewBattleSession(Long heroId) {
        logger.info("Inside createNewBattleSession service method. Hero ID: " + heroId + ".");
        try {
            Hero hero = heroService.getHeroById(heroId);
            int heroLevel = hero.getLevel();
            Enemy enemy = enemyService.createNewEnemy(heroLevel);

            BattleSession battleSession = new BattleSession();

            battleSession.setEnemyId(enemy.getId());
            battleSession.setHeroId(heroId);

            battleSessionRepository.save(battleSession);

            BattleHistoryMessage battleHistoryMessage = battleHistoryMessageService.createNewMessage(battleSession.getId(), "You encountered an enemy!");
            Long battleHistoryMessageId = battleHistoryMessage.getId();
            battleSession.setBattleHistoryMessageId(battleHistoryMessageId);

            return battleSession;
        } catch(Exception e) {
            throw new RuntimeException("Failed to create new battle session: " + e.getMessage());
        }
    }

    @Transactional
    public String processEndOfBattle(Long battleSessionId, String battleResult) {
        logger.info("Inside processEndOfBattle service method. Battle Session ID: " + battleSessionId + ". Battle Result: " + battleResult + ".");
        String endOfBattleMessage = "Default message.";
        Optional<BattleSession> optionalBattleSession = battleSessionRepository.findById(battleSessionId);
        if (optionalBattleSession.isPresent()) {
            BattleSession battleSession = optionalBattleSession.get();
            Hero hero = heroService.getHeroById(battleSession.getHeroId());
            Enemy enemy = enemyService.getEnemyById(battleSession.getEnemyId());

            endOfBattleMessage = experienceProcessorService.processExperience(hero, enemy, battleResult);
        }
        return endOfBattleMessage;
    }
}
package com.battlejawn.Service;

import com.battlejawn.Entities.Battle.BattleStatus;
import com.battlejawn.Repository.BattleStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BattleStatusService {
    private final BattleStatusRepository battleStatusRepository;

    public BattleStatus findBattleStatusById(Long battleStatusId) {
        Optional<BattleStatus> optionalBattleStatus = battleStatusRepository.findById(battleStatusId);
        if (optionalBattleStatus.isPresent()){
            return optionalBattleStatus.get();
        } else {
            throw new EntityNotFoundException("BattleStatus with ID " + battleStatusId + " not found");
        }
    }
    public BattleStatus saveBattleStatus(BattleStatus battleStatus){
        return battleStatusRepository.save(battleStatus);
    }
}

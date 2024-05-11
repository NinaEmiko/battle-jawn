package com.battlejawn.HeroMove;

import com.battlejawn.Entities.Battle.BattleStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BlockTest {
    @Test
    void useBlockTrueTest() {
        BattleStatus battleStatus = new BattleStatus();
        Block block = new Block();
        Block spyBlock = spy(block);
        when(spyBlock.useBlock(battleStatus)).thenReturn(true);
        boolean blocked = spyBlock.useBlock(battleStatus);
        Assertions.assertEquals(true, blocked);
    }
    @Test
    void useBlockFalseTest() {
        BattleStatus battleStatus = new BattleStatus();
        Block block = new Block();
        Block spyBlock = spy(block);
        when(spyBlock.useBlock(battleStatus)).thenReturn(false);
        boolean blocked = spyBlock.useBlock(battleStatus);
        Assertions.assertEquals(false, blocked);
    }
}

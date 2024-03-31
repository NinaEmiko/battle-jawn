package com.battlejawn.Randomizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RandomizerTest {
    @Test
    void getRandomIntTest() {
        Randomizer randomizer = new Randomizer();
        int result = randomizer.getRandomInt(2);
        Assertions.assertTrue(result > 0);
        Assertions.assertTrue(result < 3);
    }

}

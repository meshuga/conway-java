package com.github.meshuga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GenerationBuilderTest {
    
    @Mock
    private ConwaysWorldRules worldRules;
    
    private GenerationBuilder sut;

    @Before
    public void setUp() throws Exception {
        sut = new GenerationBuilder(worldRules);
    }

    @Test
    public void onOneAliveCellFileShouldReturnAliveGeneration() throws Exception {
        // GIVEN
        String fileName = "src/test/resources/testInitialAliveMap.txt";

        // WHEN
        Generation generation = sut.buildInitial(fileName, 1);

        // THEN
        assertThat(generation.isCellAlive(0, 0)).isTrue();
    }

    @Test
    public void onOneDeadCellFileShouldReturnDeadGeneration() throws Exception {
        // GIVEN
        String fileName = "src/test/resources/testInitialDeadMap.txt";

        // WHEN
        Generation generation = sut.buildInitial(fileName, 1);

        // THEN
        assertThat(generation.isCellAlive(0, 0)).isFalse();
    }

    @Test(expected = RuntimeException.class)
    public void onOneInvalidCellFileShouldThrowException() throws Exception {
        // GIVEN
        String fileName = "src/test/resources/testInitialInvalidMap.txt";

        // WHEN
        sut.buildInitial(fileName, 1);
    }

    @Test
    public void onNextGenerationWhenShouldBeAliveShouldCreateAliveCell() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.ALIVE}
        };
        Generation generation = new Generation(worldMap, 1);
        when(worldRules.countNeighbours(any(Generation.class), anyInt(), anyInt())).thenReturn(3);
        when(worldRules.shouldBeAlive(anyInt(), anyBoolean())).thenReturn(true);

        //WHEN
        Generation result = sut.buildNewGeneration(generation);

        // THEN
        verify(worldRules).countNeighbours(generation, 0, 0);
        verify(worldRules).shouldBeAlive(3, true);
        assertThat(result.isCellAlive(0, 0)).isTrue();
    }

    @Test
    public void onNextGenerationWhenShouldBeDeadShouldCreateDeadCell() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.ALIVE}
        };
        Generation generation = new Generation(worldMap, 1);
        when(worldRules.countNeighbours(any(Generation.class), anyInt(), anyInt())).thenReturn(3);
        when(worldRules.shouldBeAlive(anyInt(), anyBoolean())).thenReturn(false);

        //WHEN
        Generation result = sut.buildNewGeneration(generation);

        // THEN
        verify(worldRules).countNeighbours(generation, 0, 0);
        verify(worldRules).shouldBeAlive(3, true);
        assertThat(result.isCellAlive(0, 0)).isFalse();
    }
}

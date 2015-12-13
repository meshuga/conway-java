package com.github.meshuga;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConwaysWorldRulesTest {
    
    private ConwaysWorldRules sut;

    @Before
    public void setUp() throws Exception {
        sut = new ConwaysWorldRules();
    }

    @Test
    public void testUnderPopulation() throws Exception {
        assertThat(sut.shouldBeAlive(0, true)).isFalse();
        assertThat(sut.shouldBeAlive(1, true)).isFalse();
    }

    @Test
    public void testLiveInNewState() throws Exception {
        assertThat(sut.shouldBeAlive(2, true)).isTrue();
        assertThat(sut.shouldBeAlive(3, true)).isTrue();
    }

    @Test
    public void testOverPopulation() throws Exception {
        assertThat(sut.shouldBeAlive(4, true)).isFalse();
        assertThat(sut.shouldBeAlive(5, true)).isFalse();
        assertThat(sut.shouldBeAlive(6, true)).isFalse();
    }

    @Test
    public void testReproduction() throws Exception {
        assertThat(sut.shouldBeAlive(0, true)).isFalse();
        assertThat(sut.shouldBeAlive(1, true)).isFalse();
        assertThat(sut.shouldBeAlive(3, false)).isTrue();
        assertThat(sut.shouldBeAlive(4, true)).isFalse();
        assertThat(sut.shouldBeAlive(5, true)).isFalse();
        assertThat(sut.shouldBeAlive(6, true)).isFalse();
        assertThat(sut.shouldBeAlive(7, true)).isFalse();
        assertThat(sut.shouldBeAlive(8, true)).isFalse();
    }

    @Test
    public void testCountNeighboursWithoutShifting() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.ALIVE, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD}
        };
        Generation generation = new Generation(worldMap, 3);

        // WHEN
        int result = sut.countNeighbours(generation, 1, 1);

        //THEN
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testCountNeighboursWitLeftShifting() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.ALIVE},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD}
        };
        Generation generation = new Generation(worldMap, 3);

        // WHEN
        int result = sut.countNeighbours(generation, 1, 0);

        //THEN
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testCountNeighboursWitRightShifting() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.ALIVE, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD}
        };
        Generation generation = new Generation(worldMap, 3);

        // WHEN
        int result = sut.countNeighbours(generation, 1, 2);

        //THEN
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testCountNeighboursWitUpShifting() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.ALIVE, LifeIndicator.DEAD}
        };
        Generation generation = new Generation(worldMap, 3);

        // WHEN
        int result = sut.countNeighbours(generation, 0, 1);

        //THEN
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testCountNeighboursWitDownShifting() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD, LifeIndicator.ALIVE, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD},
            {LifeIndicator.DEAD, LifeIndicator.DEAD, LifeIndicator.DEAD}
        };
        Generation generation = new Generation(worldMap, 3);

        // WHEN
        int result = sut.countNeighbours(generation, 2, 1);

        //THEN
        assertThat(result).isEqualTo(1);
    }
}

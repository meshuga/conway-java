package com.github.meshuga;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class GenerationTest {

    private ByteArrayOutputStream stdOut;

    @Before
    public void setUp() throws Exception {
        stdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdOut));
    }

    @Test
    public void onAliveCellShouldReturnTrue() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.ALIVE}
        };
        Generation sut = new Generation(worldMap, 1);

        // WHEN
        boolean result = sut.isCellAlive(0, 0);

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    public void onDeadCellShouldReturnFalse() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD}
        };
        Generation sut = new Generation(worldMap, 1);

        // WHEN
        boolean result = sut.isCellAlive(0, 0);

        // THEN
        assertThat(result).isFalse();
    }

    @Test
    public void onValidAliveMapShouldPrintIt() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.ALIVE}
        };
        Generation sut = new Generation(worldMap, 1);

        // WHEN
        sut.printWorld();

        // THEN
        assertThat(stdOut.toString()).isEqualTo("*\n\n");
    }

    @Test
    public void onValidDeadMapShouldPrintIt() throws Exception {
        // GIVEN
        LifeIndicator[][] worldMap = {
            {LifeIndicator.DEAD}
        };
        Generation sut = new Generation(worldMap, 1);

        // WHEN
        sut.printWorld();

        // THEN
        assertThat(stdOut.toString()).isEqualTo("_\n\n");
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }
}

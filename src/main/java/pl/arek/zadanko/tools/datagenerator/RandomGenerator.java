/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.tools.datagenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class RandomGenerator {

    /**
     * generate simple random int where numbers is in range 0 - max
     *
     * @param max
     * @return
     */
    protected int intRandom(int max) {
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }

    /**
     * generate simple random float where numbers is in range 0 - max
     *
     * @param max
     * @return
     */
    protected float floatRandom(double max) {
        return (float) ThreadLocalRandom.current().nextDouble(1, max + 1);
    }
}

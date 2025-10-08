package com.seidoropentrends.classes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

    // === SUM TEST ===
    @Test
    public void testSuma() {
        assertEquals(5, calc.suma(2, 3));
        assertEquals(0, calc.suma(-2, 2));
        assertEquals(-5, calc.suma(-2,-3));
        assertEquals(110, calc.suma(50,60));
    }

    @Test
    public void testSumaLimits(){
        assertEquals(0, calc.suma(0, 0));
        assertEquals(Integer.MAX_VALUE, calc.suma(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, calc.suma(Integer.MIN_VALUE, 0));
        // Overflow case
        assertEquals(Integer.MIN_VALUE, calc.suma(Integer.MAX_VALUE, 1));
    }

    // === SUBTRACTION TEST ===
    @Test
    public void testResta(){
        assertEquals(1, calc.resta(3, 2));
        assertEquals(-4, calc.resta(-2, 2));
        assertEquals(1, calc.resta(-2, -3));
        assertEquals(-220, calc.resta(50, 270));
    }

    @Test
    public void testRestaLimits(){
        assertEquals(0, calc.resta(0, 0));
        assertEquals(Integer.MAX_VALUE, calc.resta(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, calc.resta(Integer.MIN_VALUE,0));
        // Overflow case
        assertEquals(Integer.MAX_VALUE, calc.resta(Integer.MIN_VALUE, 1));
    }

    // === MULTIPLICATION TEST ===
    @Test
    public void testMultiplicatio(){
        assertEquals(6, calc.multiplica(2, 3));
        assertEquals(-4, calc.multiplica(-2, 2));
        assertEquals(6, calc.multiplica(-2, -3));
        assertEquals(0, calc.multiplica(5,0));
        assertEquals(144, calc.multiplica(12,12));
    }

    @Test
    public void testMultiplicacioLimits(){
        assertEquals(0, calc.multiplica(0, 0));
        assertEquals(0, calc.multiplica(Integer.MAX_VALUE, 0));
        assertEquals(0, calc.multiplica(0, Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, calc.multiplica(Integer.MAX_VALUE, 1));
        assertEquals(Integer.MIN_VALUE, calc.multiplica(Integer.MIN_VALUE, 1));
        // Overflow case
        assertEquals(-2, calc.multiplica(Integer.MAX_VALUE, 2));
    }

    // === DIVISION TEST ===
    @Test
    public void testDivideix(){
        assertEquals(2, calc.divideix(6, 3));
        assertEquals(-2, calc.divideix(-6, 3));
        assertEquals(3, calc.divideix(-6,-2));
        assertEquals(0, calc.divideix(0,4));
        assertEquals(20, calc.divideix(100,5));
    }

    @Test
    public void testDivisioValoresLimite() {
        assertEquals(Integer.MAX_VALUE, calc.divideix(Integer.MAX_VALUE, 1));
        assertEquals(Integer.MIN_VALUE, calc.divideix(Integer.MIN_VALUE, 1));
        assertEquals(-1, calc.divideix(Integer.MAX_VALUE, -Integer.MAX_VALUE));
        assertEquals(0, calc.divideix(1, Integer.MAX_VALUE));
        // IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> calc.divideix(10, 0));
    }


    // === MAXIMUM TEST ===
    @Test
    public void testMaxim(){
        assertEquals(3, calc.maxim(1, 3));
        assertEquals(5, calc.maxim(5, 3));
        assertEquals(4, calc.maxim(4, 4));
        assertEquals(0, calc.maxim(-3, 0));
        assertEquals(-1, calc.maxim(-4, -1));
    }

    @Test
    public void testMaximLimits() {
        assertEquals(Integer.MAX_VALUE, calc.maxim(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, calc.maxim(Integer.MAX_VALUE, 0));
        assertEquals(0, calc.maxim(Integer.MIN_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, calc.maxim(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    // === SQUARE ROOT TEST ===
    @Test
    public void testArrelQuadrada(){
        assertEquals(3, calc.arrelQuadrada(9), 0.000001);
        assertEquals(1, calc.arrelQuadrada(1),0.000001);
        assertEquals(0, calc.arrelQuadrada(0),0.000001);
        assertEquals(5, calc.arrelQuadrada(25),0.000001);
    }

    @Test
    public void testArrelQuadradaIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> calc.arrelQuadrada(-3));
    }

    // === POSITIVE TEST ===
    @Test
    public void testEsPositiu(){
        assertTrue(calc.esPositiu(3));
        assertFalse(calc.esPositiu(0));
        assertFalse(calc.esPositiu(-3));
        assertTrue(calc.esPositiu(Integer.MAX_VALUE));
        assertFalse(calc.esPositiu(Integer.MIN_VALUE));
        assertFalse(calc.esPositiu(Integer.MIN_VALUE+1));
    }

    // === POWER TEST ===
    @Test
    public void testPotencia(){
        assertEquals(8, calc.potencia(2, 3));
        assertEquals(9, calc.potencia(-3,2));
        assertEquals(1, calc.potencia(3,0));
        assertEquals(1, calc.potencia(0,0));
    }

    @Test
    public void testPotenciaIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> calc.potencia(3, -1));
        assertThrows(IllegalArgumentException.class, () -> calc.potencia(0,-1));
    }
}
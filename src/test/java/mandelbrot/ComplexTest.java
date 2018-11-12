package mandelbrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testReal(){
        assertEquals(Complex.ZERO, Complex.ONE.real(0.0));
        assertEquals(Complex.ONE, Complex.I.real(1.0));
        assertEquals(minusOne, Complex.ZERO.real(-1.0));
        assertEquals(new Complex(real,0.0), new Complex(real, imaginary).real(real));
        assertEquals(new Complex(-10.1,0.0), Complex.ONE.real(-10.1));
        assertEquals(new Complex(-real, 0.0), new Complex(-real, 0.0).real(-real));
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
     assertEquals(Complex.I, minusI.reciprocal());
     assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testAdd(){
        assertEquals(minusOne, Complex.ZERO.add(minusOne));
        assertEquals(Complex.ONE, Complex.ZERO.add(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.add(minusI));
        assertEquals( new Complex(real, imaginary),
                new Complex(real-1,imaginary-1).add(onePlusI));
    }

    @Test
    void testMultiply(){
        assertEquals(Complex.ZERO, Complex.ZERO.multiply(oneMinusI));
        assertEquals(Complex.ONE, minusOne.multiply(minusOne));
        assertEquals(Complex.ONE, minusI.multiply(Complex.I));
        assertEquals(new Complex(0.0,8.0),
                new Complex(-2.0,-2.0).multiply(new Complex(-2.0,-2.0)));
        assertEquals( new Complex(0.0,244),
                new Complex(real, imaginary).multiply(new Complex(imaginary, real)));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testPow(){
        assertEquals(Complex.ZERO, Complex.ZERO.pow(2));
        assertEquals(Complex.ONE, Complex.ZERO.pow(0));
        assertEquals(twoI, onePlusI.pow(2));
        assertEquals(new Complex(0,-2), oneMinusI.pow(2));
        assertEquals(new Complex(-2,2), onePlusI.pow(3));
        assertEquals(new Complex(122,-597),
                new Complex(2,3).pow(5));
        assertEquals(new Complex(-119,-120),
                new Complex(2,3).pow(4));
        assertEquals(Complex.ONE, new Complex(2,3).pow(0));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testSquaredModulus(){
        assertEquals(0.0, Complex.ZERO.squaredModulus(), Helpers.EPSILON);
        assertEquals(2.0, oneMinusI.squaredModulus(), Helpers.EPSILON);
        assertEquals(2.0, onePlusI.squaredModulus(), Helpers.EPSILON);
        assertEquals(1.0, Complex.I.squaredModulus(), Helpers.EPSILON);
        assertEquals(1.0, Complex.ONE.squaredModulus(), Helpers.EPSILON);
        assertEquals(244.0, new Complex(real,imaginary).squaredModulus(), Helpers.EPSILON);
        assertEquals(0.2, new Complex(0.4,-0.2).squaredModulus(), Helpers.EPSILON);
    }

    @Test
    void testModulus(){
        assertEquals(0.0, Complex.ZERO.modulus(), Helpers.EPSILON);
        assertEquals(1.0, Complex.I.modulus(), Helpers.EPSILON);
        assertEquals(1.0, Complex.ONE.modulus(), Helpers.EPSILON);
        assertEquals(5.0, new Complex(3.0,-4.0).modulus(), Helpers.EPSILON);
        assertEquals(5.0, new Complex(-3.0,4.0).modulus(), Helpers.EPSILON);
        assertEquals(5.0, new Complex(-3.0,-4.0).modulus(), Helpers.EPSILON);
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
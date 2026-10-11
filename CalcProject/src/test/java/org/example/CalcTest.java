package org.example;

import org.junit.rules.ExpectedException;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class CalcTest {
    private Calculator calculator;

    @BeforeClass(alwaysRun = true)
    //@BeforeClass(alwaysRun = true)
    public void setUp(){
        System.out.println("setUp");
        calculator = new Calculator();
    }

    @Test(groups = "math")
    public void testAddition(){
        System.out.println("Addition");
        int result = calculator.add(1, 2);
        int expected = 3;
        Assert.assertEquals(result,expected, "The result should be equla to the expected value");
        Assert.assertTrue(result > 0, "the result should be greater than 0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "math")
    public void testDivision(){
        System.out.println("division");
        calculator.divide(10,0);
    }

    @Test(groups = "math")
    public void testSoftAssertions(){
        System.out.println("assert");
        SoftAssert softAssert = new SoftAssert();

        int sum = calculator.add(1, 10);
        int diff = calculator.subtract(10, 1);
        softAssert.assertEquals(sum, 11, "message 1");
        softAssert.assertEquals(diff, 9, "message 2");

        softAssert.assertAll();
    }

    @DataProvider(name = "multiplicationData")
    public Object[][] multiplicationData(){
        return new Object[][]{
                {2,3,6},
                {2,4,8}
        };
    }

    @Test(dataProvider = "multiplicationData", groups = "math")
    public void testMultiplication(int a, int b, int expected){
        System.out.println("multi test");
        int result = calculator.multiply(a,b);
        Assert.assertEquals(result, expected, "multiply method test run");
    }

    @Parameters({"login", "password"})
    @Test
    public void loginTest(@Optional("default") String login,@Optional("pass") String password){
        System.out.println("login test");
        Assert.assertNotNull(login, "login");
        Assert.assertNotNull(password, "password");
    }

    @DataProvider(name = "trigonometryData")
    public Object[][] trigonometryData() {
        return new Object[][]{
                {"sin", 0.0, 0.0},
                {"sin", Math.PI / 2, 1.0},
                {"cos", 0.0, 1.0},
                {"cos", Math.PI, -1.0},
                {"tan", 0.0, 0.0},
                {"tan", Math.PI / 4, 1.0},
                {"cot", Math.PI / 2, 0.0},
                {"cot", Math.PI / 4, 1.0}
        };
    }

    @Test(dataProvider = "trigonometryData", groups = "trigonometry")
    public void testTrigonometry(String function, double angle, double expected) {
        System.out.println("trig test: " + function);
        double delta = 1e-9;
        switch (function) {
            case "sin":
                Assert.assertEquals(calculator.sin(angle), expected, delta, "sin test failed");
                break;
            case "cos":
                Assert.assertEquals(calculator.cos(angle), expected, delta, "cos test failed");
                break;
            case "tan":
                Assert.assertEquals(calculator.tan(angle), expected, delta, "tan test failed");
                break;
            case "cot":
                Assert.assertEquals(calculator.cot(angle), expected, delta, "cot test failed");
                break;
            default:
                Assert.fail("Unknown function: " + function);
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "trigonometry")
    public void testTanUndefined() {
        System.out.println("tan undefined test");
        calculator.tan(Math.PI / 2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = "trigonometry")
    public void testCotUndefined() {
        System.out.println("cot undefined test");
        calculator.cot(0.0);
    }

}

package com.training.vehicles.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.training.vehicles.services.CalculatorService.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    @Test
    void shouldAddTwoIntegers() {
        // given
        String a = "10";
        String b = "20";

        // when
        int result = add(a, b);

        // then
        Assertions.assertEquals(30, result);
    }

    @Test
    void shouldThrowExceptionOnInvalidFirstArgument() {
        // given
        String a = "wrong-number";
        String b = "20";

        // when
        Throwable throwable = catchThrowable(() -> add(a, b));

        // then
        assertThat(throwable)
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("For input string: \"wrong-number\"");
    }

    @Test
    void shouldThrowExceptionOnInvalidSecondArgument() {
// given
        String a = "10";
        String b = "wrong-number";
// when
        Throwable throwable = catchThrowable(() -> add(a, b));
// then
        assertThat(throwable)
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("For input string: \"wrong-number\"");
    }

    @Test
    void shouldThrowExceptionOnEmptyFirstArgument() {

        // given
        String a = "";
        String b = "10";

        // when
        Throwable throwable = catchThrowable(() -> add(a, b));

        // then
        assertThat(throwable)
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("For input string: \"\"");
    }

    @Test
    void shouldThrowExceptionOnEmptySecondArgument() {

        // given
        String a = "10";
        String b = "";

        // when
        Throwable throwable = catchThrowable(() -> add(a, b));

        // then
        assertThat(throwable)
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("For input string: \"\"");
    }

    @Test
    void shouldThrowExceptionOnIntegerOverFlow() {

        // given
        String a = Integer.MAX_VALUE + "";
        String b = "11";

        // when
        Throwable throwable = catchThrowable(() -> add(a, b));

        // then
        assertThat(throwable)
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("integer overflow");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhileFirstArgumentIsNull() {

        // given
        String a = null;
        String b = "11";

        try {
            add(null, b);
            fail("Exception wasn't thrown!");
        }
        catch (IllegalArgumentException exception) {
            assertEquals("Arguments 'a' and 'b' are required.", exception.getMessage());
        }
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhileSecondArgumentIsNull() {

        // given
        String a = "10";
        String b = null;

        // when
        Throwable throwable = catchThrowable(() -> add(a, null));

        // then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Arguments 'a' and 'b' are required.");
    }

}
package ru.job4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class StackTest {

    @Test
    public void when3PushThen3Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertThat(stack.poll()).isEqualTo(3);
        Assertions.assertThat(stack.poll()).isEqualTo(2);
        Assertions.assertThat(stack.poll()).isEqualTo(1);
    }

    @Test
    public void when1PushThen1Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        Assertions.assertThat(stack.poll()).isEqualTo(1);
    }

    @Test
    public void when2PushThen2Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        Assertions.assertThat(stack.poll()).isEqualTo(2);
        Assertions.assertThat(stack.poll()).isEqualTo(1);
    }

}
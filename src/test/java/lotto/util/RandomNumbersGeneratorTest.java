package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class RandomNumbersGeneratorTest {
    private final NumbersGenerator generator = new RandomNumbersGenerator();

    @Test
    @DisplayName("6개의 숫자를 생성한다.")
    void generateSixNumbers(){
        List<Integer> numbers = generator.generate();
        assertThat(numbers).hasSize(6);
    }

    @RepeatedTest(10)
    @DisplayName("생성된 숫자는 1부터 45 사이의 값이다.")
    void generateNumbersInRange(){
        List<Integer> numbers = generator.generate();
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }

    @RepeatedTest(10)
    @DisplayName("생성된 숫자는 중복되지 않는다.")
    void generateUniqueNumbers(){
        List<Integer> numbers = generator.generate();
        assertThat(new HashSet<>(numbers)).hasSize(6);
    }

    @Test
    @DisplayName("생성된 리스트는 불변이다.")
    void generateUnmodifiableList(){
        List<Integer> numbers = generator.generate();
        assertThat(numbers).isUnmodifiable();
    }
}
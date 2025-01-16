package basic.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedWithValueSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest // 테스트에 파마미터를 사용함을 나타낸다.
    @ValueSource(strings = {"Check three parameters", "JUnit in Action", "Ready Go!"}) //  문자열 배열을 입력 값으로 지정할 수 있게 된다.
    // 메서드의 인자로 전달된다.
    void testWordsInSentence(String sentence) {
        assertEquals(3, wordCounter.countWords(sentence));
    }
}
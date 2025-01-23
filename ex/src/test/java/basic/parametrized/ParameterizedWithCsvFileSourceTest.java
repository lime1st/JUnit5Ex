package basic.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedWithCsvFileSourceTest {
    private final WordCounter wordCounter = new WordCounter();

    @ParameterizedTest  //  파라미터 사용을 명시
    @CsvFileSource(resources = "/word_counter.csv") //  csv 파일을 파라미터의 소스로 사용하도록 한다.
    void testWordsInSentence(int expected, String sentence) {
        assertEquals(expected, wordCounter.countWords(sentence));
    }
}
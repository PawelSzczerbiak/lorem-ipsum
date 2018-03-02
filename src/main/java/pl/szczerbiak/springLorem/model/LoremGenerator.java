package pl.szczerbiak.springLorem.model;

import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class LoremGenerator {

    @Pattern(regexp = "[0-9]+", message = "Input must be a positive integer")
    private String number;
    private Type type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // Private constructor: no one should ever make an instance of this class
    private LoremGenerator() {
    }

    /**
     * Maximum number of paragraph to be generated
     */
    private final int PARAGRAPHS_MAX_NUMBER = 10;
    /**
     * Number of words and sentences is restricted
     * by the size of the paragraph times the maximum number of paragraph
     */
    private final int SENTENCES_IN_PARAGRAPH = 11; // TODO: calculate dynamically
    private final int WORDS_IN_PARAGRAPH = 95;  // TODO: calculate dynamically

    private static final String PARAGRAPH = "Lorem ipsum dolor sit amet, " +
            "consectetur adipiscing elit, sed do eiusmod tempor " +
            "incididunt ut labore et dolore magna aliqua. Turpis " +
            "nunc eget lorem dolor. Vitae auctor eu augue ut lectus " +
            "arcu bibendum at varius. Volutpat maecenas volutpat blandit " +
            "aliquam etiam erat velit scelerisque in. Nunc eget lorem " +
            "dolor sed viverra. Odio ut sem nulla pharetra diam. Elit " +
            "sed vulputate mi sit amet mauris commodo quis imperdiet. " +
            "Ut pharetra sit amet aliquam. Mauris in aliquam sem fringilla " +
            "ut morbi tincidunt augue interdum. Neque ornare aenean euismod " +
            "elementum. Vitae suscipit tellus mauris a diam maecenas sed enim.";

    public static String getParagraph() {
        return PARAGRAPH;
    }

    /**
     * @return text that will be displayed on the screen
     */
    public String generateText() {
        /**
         * Conversion to int
         * We know that it is of the format [0-9]+
         */
        int n = Integer.parseInt(number);
        String text = "";
        // The only special case that matters
        if (n == 0) {
            return text;
        }
        switch (type) {
            case PARAGRAPH:
                // NOTE: text must be wrapped by <p></p>
                text = Stream.generate(() -> getParagraph())
                        .limit(n < PARAGRAPHS_MAX_NUMBER ? n : PARAGRAPHS_MAX_NUMBER)
                        .collect(joining("</p><p>"));
                break;
            case SENTENCE:
                String sentences[] = getSentenceTemplate(n).split("\\.");
                text = Arrays.stream(sentences).limit(n)
                        .collect(joining(".", "", "."));
                break;
            case WORD:
                String words[] = getWordTemplate(n).split("\\s");
                text = Arrays.stream(words).limit(n)
                        .map(i -> i.replace(",", "")
                                .replace(".", "")
                                .toLowerCase())
                        .collect(joining(" "));
                break;
        }
        return text;
    }

    /**
     * @param n number of sentences to be displayed
     * @return number of paragraphs containing n
     */
    private String getSentenceTemplate(int n) {
        final int SENTENCES_MAX_NUMBER = PARAGRAPHS_MAX_NUMBER * SENTENCES_IN_PARAGRAPH;
        return Stream.generate(() -> getParagraph())
                .limit(n < SENTENCES_MAX_NUMBER ?
                        (int) Math.ceil(1. * n / SENTENCES_IN_PARAGRAPH) : PARAGRAPHS_MAX_NUMBER)
                .collect(joining(" "));
    }

    /**
     * @param n number of words to be displayed
     * @return number of paragraphs containing n
     */
    private String getWordTemplate(int n) {
        final int WORD_MAX_NUMBER = PARAGRAPHS_MAX_NUMBER * WORDS_IN_PARAGRAPH;
        return Stream.generate(() -> getParagraph())
                .limit(n < WORD_MAX_NUMBER ?
                        (int) Math.ceil(1. * n / WORDS_IN_PARAGRAPH) : PARAGRAPHS_MAX_NUMBER)
                .collect(joining(" "));
    }
}

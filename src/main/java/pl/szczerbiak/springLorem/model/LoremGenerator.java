package pl.szczerbiak.springLorem.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class LoremGenerator {

    /**
     * Maximum number of paragraph to be generated
     * Number of words and sentences is restriced by the size of the template
     */
    private final int MAX_NUMBER = 10;

    @Pattern(regexp = "[0-9]+", message = "Input must be a positive integer")
    private String number;
    private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static final String template = "Lorem ipsum dolor sit amet, " +
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

    public static String getTemplate() {
        return template;
    }

    // Private constructor: no one should ever make an instance of this class
    private LoremGenerator() {
    }

    public String generateText() {
        // conversion to int
        // we know that it is of the format [0-9]+
        int n = Integer.parseInt(number);
        String text = "";
        switch (type) {
            case "paragraph":
                // NOTE: text must be wrapped by <p></p>
                text = Stream.generate(() -> getTemplate())
                        .limit(n <= MAX_NUMBER ? n : MAX_NUMBER)
                        .collect(joining("</p><p>"));
                break;
            case "sentence":
                String sentences[] = getTemplate().split("\\.");
                text = Arrays.stream(sentences).limit(n)
                        .collect(joining(".","","."));
                break;
            case "word":
                String words[] = getTemplate().split("\\s");
                text = Arrays.stream(words).limit(n)
                        .map(i -> i.replace(",", "").replace(".", ""))
                        .collect(joining(" "));
                break;
        }
        return text;
    }
}

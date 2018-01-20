package pl.szczerbiak.springLorem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class LoremGenerator {
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

    private LoremGenerator() {
    }

    public static String generateText(int n, String type) {
        String text = "";
        switch (type) {
            case "paragraph":
                text = Stream.generate(() -> getTemplate())
                        .limit(n)
                        .collect(joining("\n"));
                break;
            case "sentence":
                String sentences[] = getTemplate().split("\\.");
                // Functional programming
                text = Arrays.stream(sentences).limit(n)
                        .collect(joining("."));
                // Old school
//                for (int i = 0; i < n; i++) {
//                    text += sentences[i] + ". ";
//                }
                break;
            case "word":
                String words[] = getTemplate().split("\\s");
                // Functional programming
                text = Arrays.stream(words).limit(n)
                        .map(i -> i.replace(",", "").replace(".", ""))
                        .collect(joining(" "));
                // Old school
//                for (int i = 0; i < n; i++) {
//                    text += words[i].replace(",", "").replace(".", "") +" ";
//                }
                break;
        }
        return text;
    }
}

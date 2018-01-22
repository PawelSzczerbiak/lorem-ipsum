package pl.szczerbiak.springLorem.model;

public enum Type {
    WORD("words"), SENTENCE("sentences"), PARAGRAPH("paragraphs");
    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

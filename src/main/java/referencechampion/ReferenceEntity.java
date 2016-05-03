package referencechampion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceEntity implements Reference {

    private Map<String, String> fields;
    private List<String> fieldNames;
    private String type;
    private int authors;

    public ReferenceEntity(HashMap<String, String> fields, String type) {
        this(type);
        this.fields = fields;
        this.authors = 1;
    }

    public ReferenceEntity(String type) {
        this(type, ReferenceCollection.getReference(type));

    }

    public ReferenceEntity(String type, List<String> fieldNames) {
        this.type = type;
        this.fieldNames = fieldNames;
        this.fields = new HashMap<String, String>();
    }

    @Override
    public void addValue(String fieldName, String value) {
        this.fields.put(fieldName, value);
    }

    @Override
    public String getField(String key) {
        return fields.get(key);
    }

    @Override
    public List<String> getFields() {
        return fieldNames;
    }

    @Override
    public void changeKey(String newKey) {
        fields.remove("key");
        fields.put("key", newKey);
    }

    @Override
    public void addAuthor() {
        authors++;
        List<String> names = new ArrayList();
        for (String name : getFields()) {
            names.add(name);
            if (name.equals("author")) {
                names.add("author" + authors);
            }
        }
        fieldNames = names;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(ReferenceEntity.class)) {
            return false;
        }

        ReferenceEntity book = (ReferenceEntity) obj;

        return book.getField("key").equals(this.getField("key"));
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append("\n");
        sb.append("\n");
        for (String fieldName : fieldNames) {
            if (fields.get(fieldName) != null && !fields.get(fieldName).isEmpty()) {               
                if (!fieldName.contains("author")) {
                    sb.append(fieldName);
                } else {
                    sb.append("author");
                }
                sb.append(" = ");
                sb.append(fields.get(fieldName));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public int howManyAuthors() {
        return authors;
    }

    @Override
    public boolean contains(String string) {
        for (String value : fields.values()) {
            if (value.contains(string)) {
                return true;
            }
        }
        return false;
    }

    public void setDefaultKey() {
        //palautetaan default-avain tyyliin vih2004
        String writer = "";
        String year = getField("year");
        if (authors > 1) {
            // monella authorilla vain kaksi merkkiä
            writer = getField("author").substring(0, Math.min(2, writer.length()));
            for (int i = 2; i <= authors; i++) {
                writer += getField("author" + i).substring(0, Math.min(2, writer.length()));
            }
        } else if (getField("author") != null) {
            writer = getField("author");
        } else if (getType().equals("book") && getField("editor") != null) {
            writer = getField("editor");
        } else if (getField("journal") != null) {
            writer = getField("journal");
        }

        writer = writer.substring(0, Math.min(3, writer.length()));
        addValue("key", writer.trim().toLowerCase() + year);
    }

    @Override
    public boolean validate() {
        if (getType().equals("book")) {
            if (!validField(getField("author")) && !validField(getField("editor"))) {
                return false;
            }
        }

        List<String> requirements = ReferenceCollection.getReferenceRequirements(getType());

        for (String req : requirements) {
            if (!validField(getField(req))) {
                return false;
            }
        }

        if (!validField(getField("key"))) {
            setDefaultKey();
        } //laittaa tyhjään key-kenttään default-avaimen
        return true;
    }

    private boolean validField(String field) {
        return field != null && !field.isEmpty();
    }
}

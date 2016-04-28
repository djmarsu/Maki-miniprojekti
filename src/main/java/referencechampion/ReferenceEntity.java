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
        for (String fieldName : fieldNames) {
            if (!fields.get(fieldName).isEmpty()) {
                sb.append("\t");
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
    public int getAuthors() {
        return authors;
    }

    @Override
    public boolean contains(String string) {
        for (String value : fields.values()) {
            if (value.contains(string)) return true;
        }
        return false;
    }
}

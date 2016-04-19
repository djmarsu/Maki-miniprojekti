package referencechampion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceEntity implements Reference {

    private Map<String, String> fields;
    private List<String> fieldNames;
    private String type;

    public ReferenceEntity(HashMap<String, String> fields, String type) {
        this(type);
        this.fields = fields;
    }

    public ReferenceEntity(String type) {
        this(type, ReferenceCollection.getReference(type));

    }

    public ReferenceEntity(String type, List<String> fieldNames) {
        this.type = type;
        this.fieldNames = fieldNames;
        this.fields = new HashMap<String, String>();
    }

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
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(ReferenceEntity.class)) {
            return false;
        }

        ReferenceEntity book = (ReferenceEntity) obj;

        return book.getField("title").equals(this.getField("title"))
                && book.getField("author").equals(this.getField("author"));
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        for (String fieldName : fieldNames) {
            if (!fields.get(fieldName).isEmpty()) {
                sb.append("\t");
                sb.append(fieldName);
                sb.append(" = ");
                sb.append(fields.get(fieldName));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}

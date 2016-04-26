package referencechampion;

import java.io.FileWriter;
import java.io.IOException;

public class Translator {

    private FileWriter fw;

    public Translator(FileWriter fw) {
        this.fw = fw;
    }
    
    public Translator() {
    }

    public String translateReference(Reference reference) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(reference.getType());
        sb.append("{");
        sb.append(reference.getField("key"));
        sb.append(",\n");
        for (String field : reference.getFields()) {
            appendField(sb, field, reference);
        }
        sb.append("}\n");
        writeInFile(sb.toString());
        return sb.toString();
    }
    private void appendField(StringBuilder sb, String field, Reference reference) {
        if (reference.getField(field) != null && !reference.getField(field).isEmpty() && !field.equals("key")) {
            sb.append("\t");
            sb.append(field);
            sb.append(" = ");
            inputParam(sb, reference.getField(field));
        }
    }
    
    public void setFileWriter(FileWriter fw){
        this.fw = fw;
    }

    private String compileUmlauts(String s) {
        String ret = capsuleUpperCases(s);
        ret = ret.replace("ä", "\\\"{a}")
                .replace("ö", "\\\"{o}")
                .replace("å", "\\aa");

        return ret;
    }

    private String capsuleUpperCases(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                sb.append("{");
                sb.append(s.charAt(i));
                sb.append("}");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private void inputParam(StringBuilder sb, String field) {
        sb.append("\"");
        String compiledfield = compileUmlauts(field);
        sb.append(compiledfield);
        sb.append("\",\n");
    }

    private void writeInFile(String bibtexString) throws IOException {
        fw.write(bibtexString);
        fw.flush();
    }
}

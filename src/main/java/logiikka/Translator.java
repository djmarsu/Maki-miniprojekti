/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.FileWriter;
import referencechampion.Book;

/**
 *
 * @author airta
 */
public class Translator {

    private FileWriter fw;

    public Translator(FileWriter fw) {
        this.fw = fw;
    }

    public void translateBook(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append("@book{");
        sb.append(book.getField("key"));
        sb.append(",\n");
        for (String field : book.getFields()) {
            appendField(sb, field, book);
        }
        sb.append("}");

        writeInFile(sb.toString());
    }

    private void appendField(StringBuilder sb, String field, Book book) {
        sb.append(field);
        sb.append(" = ");
        inputParam(sb, book.getField(field));
    }

    private void inputParam(StringBuilder sb, String field) {
        sb.append("\"");
        sb.append(field);
        sb.append("\",\n");
    }

    private void writeInFile(String bibtexString) {
        try {
            fw.write(bibtexString);
        } catch (Exception e) {
            
        }

    }
}

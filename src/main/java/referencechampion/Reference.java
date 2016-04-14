/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package referencechampion;

import java.util.Set;

/**
 *
 * @author airta
 */
public interface Reference {

    String getField(String key);

    Set<String> getFields();
}

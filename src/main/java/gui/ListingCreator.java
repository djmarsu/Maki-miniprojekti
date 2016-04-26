
package gui;

import gui.actionlisteners.RemoveReference;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import referencechampion.Reference;
import referencechampion.ReferenceBase;


public class ListingCreator {
    
    public static List<ListedReference> createListedReferences(List<Reference> references, Container container, ReferenceBase base, ActionListener al) {

        List<ListedReference> list = new ArrayList<ListedReference>();
        for (Reference r : references) {
            ListedReference lr = new ListedReference(r, container, new RemoveReference(r, base), al);

            list.add(lr);
        }

        return list;
    }

    public static void clearListedReferences(List<ListedReference> references) {
        if (references != null) {
            for (ListedReference lr : references) {
                lr.clear();
            }
            references.clear();
        }
    }
}

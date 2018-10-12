//import data_structures.*;
import java.util.Iterator;

public class ProductLookup {

    DictionaryADT lookupList;

    // Constructor.  There is no argument-less constructor, or default size.
    // The user must specify the maximum size of the structure.
    public ProductLookup(int maxSize){
        lookupList = new Hashtable(maxSize);
    }

    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item){
        boolean success = lookupList.insert(SKU, item);
        if (!success) System.out.println("Item " + SKU + " not added.");

    }

    // Returns the StockItem associated with the given SKU, if it is
    // in the ProductLookup, null if it is not.
    public StockItem getItem(String SKU){
        return (StockItem)lookupList.getValue(SKU);
    }

    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU){
        StockItem temp = getItem(SKU);
        if (temp == null) return -0.01f;
        return temp.getRetail();
    }

    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU){
        StockItem temp = getItem(SKU);
        if (temp == null) return -0.01f;
        return temp.getCost();
    }

    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU){
        StockItem temp = getItem(SKU);
        if (temp == null) return null;
        return temp.getDescription();
    }

    // Deletes the StockItem associated with the SKU if it is
    // in the ProductLookup.  Returns true if it was found and
    // deleted, otherwise false.
    public boolean deleteItem(String SKU){
        return lookupList.remove(SKU);
    }

    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll(){
        Iterator iter = values();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    // Prints a directory of all StockItems from the given vendor,
    // in sorted order (ordered by SKU).
    public void print(String vendor){
        Iterator iter = values();
        StockItem item;
        while (iter.hasNext()){
            item = (StockItem) iter.next();
            if (item.getVendor().compareTo(vendor)==0)
                System.out.println(item);
        }
    }

    // An iterator of the SKU keys.
    public Iterator<String> keys(){
        return lookupList.keys();
    }

    // An iterator of the StockItem values.
    public Iterator<StockItem> values(){
        return lookupList.values();
    }
}

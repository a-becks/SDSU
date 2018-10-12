import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        UnorderedListADT<String> list = new LinkedList<>();
        list.add("hello", 1);
        list.add("there", 1);
        list.remove(1);
        SOP(list.size() + "");
        System.out.println("Done");
    }

    private static void SOP(String string){
        System.out.println(string);
    }
    private static UnorderedListADT<String> createEmptyList(){
        UnorderedListADT<String> list = new LinkedList<String>();
        list.clear();
        return list;
    }

    private static UnorderedListADT<String> createOneItemList(){
        UnorderedListADT<String> list = new LinkedList<String>();
        list.addFirst("One");
        return list;
    }


    private static UnorderedListADT<String> createThreeItemList(){
        UnorderedListADT<String> list = new LinkedList<String>();
        list.addLast("One");
        list.addLast("Two");
        list.addLast("Three");
        return list;
    }


}

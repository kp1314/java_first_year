package dictionary;

public class TestOrderedLinkedList extends dictionary.TestDictionary {

    @Override
    public void setUp() {
        d = new OrderedLinkedList<String, Integer>();
    }

    @Override
    public void tearDown() {
        d = null;
    }

}

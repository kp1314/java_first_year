package dictionary;

public class TestBinarySearchTree extends dictionary.TestDictionary {

    @Override
    public void setUp() {
        d = new BinarySearchTree<String, Integer>();
    }

    @Override
    public void tearDown() {
        d = null;
    }

}

package dynamicArray;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class DynamicArrayTest {

    private final static Logger LOGGER = Logger.getLogger(DynamicArrayTest.class.getName());
//    private final static Logger LOGGER = Logger.getAnonymousLogger();
    @Test
    public void testFindNullForDynamicArray(){
        List<Integer> arrayList = Arrays.asList(1,2,3);
        boolean result = arrayList.remove(null);
        LOGGER.info(String.valueOf(result));

    }

    // ################  Dynamic Array Methods Checking  #############
    @Test
    public void testAddAndToString(){
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        // add elements to the dynamic array
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        // call: toString()
        LOGGER.info(dynamicArray.toString());
    }
}

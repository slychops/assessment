package mobiquityinc.packer.io;

import mobiquityinc.packer.exception.APIException;
import mobiquityinc.packer.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FileReaderTest {

    @Test
    public void testPathIsValid() {
        FileReader fileReader = new FileReader();
        fileReader.getFile("src/test/resources/sample-input");
    }

    @Test
    public void testPathIsInvalid() {
        FileReader fileReader = new FileReader();
        assertThrows(APIException.class, () -> fileReader.getFile("src/test/resources/sample-input/234234235234"));
    }

    @Test
    public void testPathIsDirectory() {
        FileReader fileReader = new FileReader();
        assertThrows(APIException.class, () -> fileReader.getFile("src/test/resources/"));
    }

    @Test
    public void testThatLinesReturned() {
        FileReader fileReader = new FileReader();

        List<String> fileAsString = fileReader.getFile("src/test/resources/small-input");
        List<String> expected = Arrays.asList("81 : (1,53.38,€45)", "8 : (1,15.3,€34)");

        assertEquals(expected, fileAsString);
    }

}

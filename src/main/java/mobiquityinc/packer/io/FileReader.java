package mobiquityinc.packer.io;

import mobiquityinc.packer.exception.APIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileReader {

    public List<String> getFile(String filePath) {

        File file = new File(filePath);

        APIException.checkCondition(file.exists(), "File does not exist.");
        APIException.checkCondition(file.isFile(), "Path is not a file.");

        try {
            return Files.readAllLines(file.toPath());
        }
        catch (IOException e) {
            throw new APIException(e.getMessage(), e);
        }

    }
}

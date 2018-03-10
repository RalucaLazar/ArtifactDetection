package helpers;

import entity.SegmentRepository;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SegmentDeserializer {
	Logger logger = LoggerUtil.logger(getClass());

	public SegmentRepository deserializeSegmentsFromFile(String pathToFile){
		SegmentRepository deserializedRepository = null;
	    try
	    {
	      logger.info("Now Deserializing Repository Class !!!");
	      FileInputStream inputFileStream = new FileInputStream(pathToFile);
	      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
	      deserializedRepository= ( SegmentRepository)objectInputStream.readObject();
	      objectInputStream.close();
	      inputFileStream.close();
	    }
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }
		return deserializedRepository;
	}

}

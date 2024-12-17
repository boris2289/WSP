package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {

    public static Object deserializeObject(FileInputStream fileIn) {
        try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        } catch (Exception e) {
            System.err.println("Error deserializing object: " + e.getMessage());
            return null;
        }
    }

    public static void serializeObject(Object obj, FileOutputStream fileOut) {
        try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);
        } catch (Exception e) {
            System.err.println("Error serializing object: " + e.getMessage());
        }
    }
}

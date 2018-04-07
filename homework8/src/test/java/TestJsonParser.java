import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.l81.JsonConverter;
import ru.otus.l81.LabRat;

public class TestJsonParser {
   private Gson gson = new Gson();

    @Test
    public void testString() throws IllegalAccessException {
        JsonConverter.setObject("abc");
        JsonConverter.setArrayWithInts(null);
        String jsonStringFromGSON = gson.toJson("abc");
        String myParse = JsonConverter.convertToJson();
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testInteger() throws IllegalAccessException {
        JsonConverter.setObject(123);
        JsonConverter.setArrayWithInts(null);
        String myParse = JsonConverter.convertToJson();
        String jsonStringFromGSON = gson.toJson(123);
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testBoolean() throws IllegalAccessException {
        JsonConverter.setObject(true);
        JsonConverter.setArrayWithInts(null);
        String jsonStringFromGSON = gson.toJson(true);
        String myParse = JsonConverter.convertToJson();
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testArray() throws IllegalAccessException {
        JsonConverter.setArrayWithInts(new int[]{1, 2, 3});
        JsonConverter.setObject(null);
        String myParse = JsonConverter.convertToJson();
        String jsonStringFromGSON = gson.toJson(new int[]{1, 2, 3});
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testLabRat() throws IllegalAccessException {
        LabRat labRat = new LabRat();
        JsonConverter.setObject(labRat);
        JsonConverter.setArrayWithInts(null);
        String jsonStringFromGSON = gson.toJson(labRat);
        String myParse = JsonConverter.convertToJson();

        LabRat labRat1 = gson.fromJson(myParse, LabRat.class);
        LabRat labRat2 = gson.fromJson(jsonStringFromGSON, LabRat.class);
        Assert.assertEquals(labRat1, labRat2);
    }

    @Test
    public void nullTest() throws IllegalAccessException {
        JsonConverter.setObject(null);
        JsonConverter.setArrayWithInts(null);
        String myParse = JsonConverter.convertToJson();
        boolean result = myParse == null;
        Assert.assertEquals(false, result);
    }
}
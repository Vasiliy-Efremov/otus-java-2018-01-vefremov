import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import ru.otus.l81.JsonConverter;
import ru.otus.l81.LabRat;

public class TestJsonParser {
   private Gson gson = new Gson();

    @Test
    public void testString() throws IllegalAccessException {
        String myParse = JsonConverter.convertToJson("abc");
        String jsonStringFromGSON = gson.toJson("abc");
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testInteger() throws IllegalAccessException {
        String myParse = JsonConverter.convertToJson(123);
        String jsonStringFromGSON = gson.toJson(123);
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testBoolean() throws IllegalAccessException {
        String jsonStringFromGSON = gson.toJson(true);
        String myParse = JsonConverter.convertToJson(true);
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testArrayWithInt() throws IllegalAccessException {
        String myParse = JsonConverter.convertToJson(new int[]{1, 2, 3});
        String jsonStringFromGSON = gson.toJson(new int[]{1, 2, 3});
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testArrayWithStrings() throws IllegalAccessException {
        String myParse = JsonConverter.convertToJson(new String[]{"1", "2", "3"});
        String jsonStringFromGSON = gson.toJson(new String[]{"1", "2", "3"});
        Assert.assertEquals(jsonStringFromGSON, myParse);
    }

    @Test
    public void testLabRat() throws IllegalAccessException {
        LabRat labRat = new LabRat();
        String jsonStringFromGSON = gson.toJson(labRat);
        String myParse = JsonConverter.convertToJson(labRat);
        LabRat labRat1 = gson.fromJson(myParse, LabRat.class);
        LabRat labRat2 = gson.fromJson(jsonStringFromGSON, LabRat.class);
        Assert.assertEquals(labRat1, labRat2);
    }

    @Test
    public void nullTest() throws IllegalAccessException {
        String myParse = JsonConverter.convertToJson(null);
        boolean result = myParse == null;
        Assert.assertEquals(false, result);
    }
}
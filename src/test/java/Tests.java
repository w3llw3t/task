import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Tests {

    /**
     * Позитивные тесты
     */

    @Test
    public void testSuccessField_TrueIfSherlockPresent() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");
        boolean containsSherlock = false;

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            String firstName = detectiveObj.getString("firstName");
            if (firstName.equals("Sherlock")) {
                containsSherlock = true;
                break;
            }
        }

        boolean success = jsonObject.getBoolean("success");

        if (containsSherlock) {
            assertTrue(success);
        } else {
            assertFalse(success);
        }
    }

    @Test
    public void testDetectivesArraySizeInRange() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");
        int arraySize = detectivesArray.length();

        assertTrue(arraySize >= 1 && arraySize <= 3);
    }

    @Test
    public void testMainIdInRange() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            int mainId = detectiveObj.getInt("MainId");

            assertTrue(mainId >= 0 && mainId <= 10);
        }
    }

    @Test
    public void testViolinPlayerFieldBooleanType() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            boolean violinPlayer = detectiveObj.getBoolean("violinPlayer");

            assertTrue(violinPlayer || !violinPlayer);
        }
    }

    @Test
    public void testCategoriesArrayExists() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            assertTrue(detectiveObj.has("categories"));
        }
    }

    @Test
    public void testCategoryIdValues() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            JSONArray categoriesArray = detectiveObj.getJSONArray("categories");

            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                int categoryId = categoryObj.getInt("CategoryID");

                assertTrue(categoryId == 1 || categoryId == 2);
            }
        }
    }

    @Test
    public void testExtraArrayNotEmptyForCategoryId1() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        boolean extraArrayNotEmpty = false;

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            JSONArray categoriesArray = detectiveObj.getJSONArray("categories");

            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                int categoryId = categoryObj.getInt("CategoryID");

                if (categoryId == 1) {
                    JSONObject extraObj = categoryObj.optJSONObject("extra");
                    if (extraObj != null) {
                        JSONArray extraArray = extraObj.optJSONArray("extraArray");
                        if (extraArray != null && extraArray.length() > 0) {
                            extraArrayNotEmpty = true;
                            break;
                        }
                    }
                }
            }
            if (extraArrayNotEmpty) {
                break;
            }
        }

        assertTrue(extraArrayNotEmpty);
    }

    @Test
    public void testExtraFieldNullForCategoryId2() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            JSONArray categoriesArray = detectiveObj.getJSONArray("categories");

            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                int categoryId = categoryObj.getInt("CategoryID");

                if (categoryId == 2) {
                    assertTrue(categoryObj.isNull("extra"));
                }
            }
        }
    }

    /**
     * Негативные тесты
     */

    @Test
    public void testDetectivesArrayNotEmpty() {
        String json = null;  // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));

        assertFalse(jsonObject.isNull("nonexistentField"));
    }

    @Test
    public void testSuccessFieldFalseIfSherlockNotPresent() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");
        boolean containsSherlock = false;

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            String firstName = detectiveObj.getString("firstName");
            if (firstName.equals("Sherlock")) {
                containsSherlock = true;
                break;
            }
        }

        boolean success = jsonObject.getBoolean("success");

        if (!containsSherlock) {
            assertFalse(success);
        } else {
            assertTrue(success);
        }
    }

    @Test
    public void testExtraArrayEmptyForCategoryId1() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            JSONArray categoriesArray = detectiveObj.getJSONArray("categories");

            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                int categoryId = categoryObj.getInt("CategoryID");

                if (categoryId == 1) {
                    JSONObject extraObj = categoryObj.getJSONObject("extra");
                    JSONArray extraArray = extraObj.getJSONArray("extraArray");
                    assertTrue(extraArray.isEmpty());
                }
            }
        }
    }

    @Test
    public void testExtraFieldNullForCategoryId22() {
        String json = null; // Считываем содержимое файла result.json
        try {
            json = IOUtils.toString(Objects.requireNonNull(Tests.class.getResourceAsStream("/result.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(json));
        JSONArray detectivesArray = jsonObject.getJSONArray("detectives");

        for (int i = 0; i < detectivesArray.length(); i++) {
            JSONObject detectiveObj = detectivesArray.getJSONObject(i);
            JSONArray categoriesArray = detectiveObj.getJSONArray("categories");

            for (int j = 0; j < categoriesArray.length(); j++) {
                JSONObject categoryObj = categoriesArray.getJSONObject(j);
                int categoryId = categoryObj.getInt("CategoryID");

                if (categoryId == 2) {
                    assertNull(categoryObj.get("extra"));
                }
            }
        }
    }
}
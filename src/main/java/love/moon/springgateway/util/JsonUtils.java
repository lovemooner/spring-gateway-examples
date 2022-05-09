package love.moon.springgateway.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kk
 * @date 2020/8/12 18:25
 */
public class JsonUtils {

    private static Gson gson = new Gson();

    public static final String EMPTY_JSON_STRING = "[]";



    public static Map<String, String> jsonToStringMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return new HashMap<String, String>();
        }
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> result = gson.fromJson(json, type);
        return result;
    }


    public static String mapToJson(Map map) {
        if (map == null || map.isEmpty()) {
            return EMPTY_JSON_STRING;
        }
        return gson.toJson(map);
    }

    //转换json结果 没有引号
    public static String listToJsonNoQuote(List list) {
        if (list == null) {
            return EMPTY_JSON_STRING;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || EMPTY_JSON_STRING.equals(json)) {
            return null;
        }
        return gson.<T>fromJson(jsonStandardizing(json), clazz);
    }

    //排除无用字符 规范json
    public static String jsonStandardizing(String json) {
        return json.replace("\n", "").replace("\r", "").replace(" ", "").replace("[]", "{}");
    }

    public static String objectToJson(Object object) {
        if (object == null) {
            return EMPTY_JSON_STRING;
        }
        return gson.toJson(object);
    }

    /**
     * 对象里包含html元素。contain html
     * @param object
     * @return
     */
    public static String objectCHToJson(Object object) {
        if (object == null) {
            return EMPTY_JSON_STRING;
        }
        GsonBuilder gb =new GsonBuilder();
        gb.disableHtmlEscaping();
        return gb.create().toJson(object);
//    return gson.toJson(object);
    }


    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || EMPTY_JSON_STRING.equals(json)) {
            return null;
        }
        return gson.fromJson(json,clazz);
    }

    public static <T> List<T> jsonArrayToList(String jsonStr, Class<T> clazz, List<T> results) {
        if(results==null){
            results=new ArrayList<T>();
        }
        List<T> list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {}.getType());
        if (list!=null&&!list.isEmpty()) {
            for (T t : list) {
                results.add(gson.<T>fromJson(gson.toJson(t), clazz));
            }
        }
        return results;
    }

    public static boolean isEmpty(String jsonStr){
        return "[]".equals(jsonStr.trim());
    }
}

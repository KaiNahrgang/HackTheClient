import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(decodeString("QUM=")); // AP
        System.out.println(decodeString("QUMkMw==")); // AP$1
        System.out.println(decodeString("Uw==")); // S
        System.out.println(decodeString("aX1pdg==")); // init

        invokeAPInit();

        // code is "078849", but get the correct digit for the numpad
        Map<String, String> digitMap = getDigitMapping();
        StringBuffer code = new StringBuffer();
        code.append(digitMap.get("0"));
        code.append(digitMap.get("7"));
        code.append(digitMap.get("8"));
        code.append(digitMap.get("8"));
        code.append(digitMap.get("4"));
        code.append(digitMap.get("9"));

        System.out.println("code: " + code);
    }

    private static void invokeAPInit() {
        String[] arrayOfString = new String[4];
        arrayOfString[0] = ".";
        arrayOfString[1] = "ClasLoader";
        arrayOfString[2] = "AP";

        try {
            cNew cN = new cNew(c.class.getClassLoader(), new File(arrayOfString[1]));
            Class localClass = cN.loadClass(arrayOfString[2]);
            Method localMethod = localClass.getMethod("init", null);
            localMethod.invoke(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Map<String, String> getDigitMapping() {

        Map digitMap = new HashMap<>();

        digitMap.put(decodeString("OQ=="), decodeString("MA=="));
        digitMap.put(decodeString("Mw=="), decodeString("MQ=="));
        digitMap.put(decodeString("MA=="), decodeString("Mg=="));
        digitMap.put(decodeString("OA=="), decodeString("Mw=="));
        digitMap.put(decodeString("Nw=="), decodeString("NA=="));
        digitMap.put(decodeString("NQ=="), decodeString("NQ=="));
        digitMap.put(decodeString("NA=="), decodeString("Ng=="));
        digitMap.put(decodeString("Ng=="), decodeString("Nw=="));
        digitMap.put(decodeString("Mg=="), decodeString("OA=="));
        digitMap.put(decodeString("MQ=="), decodeString("OQ=="));

        return digitMap;
    }

    private static String decodeString(String encodedString) {

        Class<?> classC;
        String decodedString = "";
        try {
            classC = ClassLoader.getSystemClassLoader().loadClass(c.class.getName());
            Method method = classC.getMethod("a", String.class);
            decodedString = (String) method.invoke(null, encodedString);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return decodedString;

    }

}

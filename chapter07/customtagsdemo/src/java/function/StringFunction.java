package function;
public class StringFunction {
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
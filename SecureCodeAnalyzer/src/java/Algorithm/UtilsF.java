package Algorithm;

import java.util.Arrays;
import java.util.List;

public class UtilsF {

    //for EXP02-J
    static String arrCollection[] = new String[]{"HashSet", "TreeSet", "LinkedHashSet", "LinkedList", "ArrayList", "ArrayDeque", "HashMap", "TreeMap", "LinkedHashMap"};
    static String primitiveType[] = new String[]{"int", "short", "long", "double", "float", "boolean"};
    static String wrappedType[] = new String[]{"Integer", "Short", "Long", "Double", "Float", "Boolean"};
    static List<String> collectionN = Arrays.asList(arrCollection);

    public static boolean isArray(String cad) {
        int count = 0;
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == '[') {
                count++;
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

    public static String nameOfArray(String cad) {
        return cad.split("=")[0];
    }

    //for DCL02-J
    public static boolean doItHaveFinal(String cad) {
        String auxCad = cad.substring(4);
        if (auxCad.startsWith("final")) {
            return true;
        }
        return false;
    }

    public static String[] getGoodAndBad(String cad) {
        String auxCad = cad.substring(4);
        String bad = "", good = "";
        int c = 1;
        for (int i = 0; i < auxCad.length(); i++) {
            if (auxCad.substring(i, i + 1).equals("(")) {
                c++;
            } else if (auxCad.substring(i, i + 1).equals(")")) {
                c--;
            }
            if (c == 0) {
                bad = "for(" + auxCad.substring(0, i + 1);
                good = "for(final" + auxCad.substring(0, i + 1);
                break;
            }
        }
        return new String[]{good, bad};
    }

    //for EXP06-J
    public static String getGoodAssert(String cad, String nameBool) {
        String auxCad = cad.substring(6);
        String result = "boolean " + nameBool + " = " + auxCad;
        result += "\n";
        result += "assert " + nameBool + ";";
        return result;
    }

    //NUM10-J
    public static boolean isBigDecimal(String cad) {
        if (cad.startsWith("newBigDecimal")) {
            return true;
        }
        return false;
    }

    public static boolean isAGoodNewBigDecimal(String cad) {
        if (cad.startsWith("newBigDecimal(\"")) {
            return true;
        }
        return false;
    }

    public static String getGoodBigDecimal(String cad) {
        String auxCad = cad.substring(14);
        auxCad = auxCad.substring(0, auxCad.length() - 1);
        return "new BigDecimal(\"" + auxCad + "\")";
    }

    //NUM09-J
    public static boolean isForWithFloat(String cad) {
        if (cad.startsWith("for(float")) {
            return true;
        }
        return false;
    }

    public static boolean isFowWithDouble(String cad) {
        if (cad.startsWith("for(double")) {
            return true;
        }
        return false;
    }

    public static String headerFor(String cad) {
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == '{') {
                return cad.substring(0, i);
            }
        }
        return "Error in headerFor";
    }
    //for EXP04-J

    public static boolean isCollection(String text) {
        // TODO Auto-generated method stub
        String cads[] = text.split("=");
        for (final String var : collectionN) {
            try {
                if (cads[1].startsWith("new" + var + "<")) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static String getNameCollection(String text) {
        return text.split("=")[0];
    }

    public static String getTypeCollection(String cad) {
        // TODO Auto-generated method stub
        int index = cad.indexOf("<");
        String result = cad.substring(index + 1);
        return result.substring(0, result.indexOf(">"));
    }

    public static boolean isEqualType(String cad1, String cad2) {
        if (cad1.equals(cad2)) {
            return true;
        }
        for (int i = 0; i < primitiveType.length; i++) {
            if (cad1.equals(primitiveType[i]) && cad2.equals(wrappedType[i])) {
                return true;
            }
            if (cad2.equals(primitiveType[i]) && cad1.equals(wrappedType[i])) {
                return true;
            }
        }
        return false;
    }
}

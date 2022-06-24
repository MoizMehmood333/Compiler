//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerMethods {
    public CheckerMethods() {
    }

    public static boolean isCharacter(String s) {
        Pattern p = Pattern.compile("\\'(\\s|\\W|\\w)\\'");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isString(String s) {
        Pattern p = Pattern.compile("\\\"(\\s|\\W|\\w)*\\\"");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isIdentifiers(String s) {
        Pattern p = Pattern.compile("[A-Za-z_][\\w]*[A-Za-z0-9]|[A-Za-z]");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isIntegerConstant(String s) {
        Pattern p = Pattern.compile("[+-]?\\d+");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isFloatConstant(String s) {
        Pattern p = Pattern.compile("[+-]?\\d*[\\.]\\d+");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isAlphabet(String s) {
        Pattern p = Pattern.compile("[A-Za-z]");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isDigit(String s) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    public static boolean isKeyword(String s) {
        boolean b = false;

        for (int i = 0; i < Main.keyWords.size(); ++i) {
            if (s.equals(((ArrayList) Main.keyWords.get(i)).get(1))) {
                b = true;
                break;
            }
        }

        return b;
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '&' || c == '|' || c == '!' || c == '<' || c == '>' || c == '=';
    }

    public static boolean isPunctuator(String s) {
        boolean b = false;

        for (int i = 0; i < Main.punctuators.size(); ++i) {
            if (s.equals(Main.punctuators.get(i))) {
                b = true;
                break;
            }
        }

        return b;
    }




}

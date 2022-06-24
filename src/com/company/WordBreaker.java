//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordBreaker {
    public static int lineNo = 0;
    public static int flagForCmnt = 0;
    public static ArrayList<ArrayList<String>> breakerWords = new ArrayList();

    public WordBreaker() {
    }

    public static void main(String[] args) throws Exception {
    }

    public ArrayList<ArrayList<String>> getList() {
        return breakerWords;
    }

    public static ArrayList<ArrayList<String>> Break_Words(String path) throws Exception {
        File sourceCode = new File(path);
        Scanner scan = new Scanner(sourceCode);
        lineNo = 0;
        flagForCmnt = 0;


        while(scan.hasNextLine()) {
            ++lineNo;
            WordBreaker(scan.nextLine(), lineNo);
        }

        return breakerWords;
    }

    public static void WordBreaker(String s, int lineNo) {
        String temp = "";

        for(int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '?' && flagForCmnt == 0) {
                if (s.charAt(i + 1) == '?') {
                    break;
                }

                if (s.charAt(i + 1) == '*') {
                    flagForCmnt = 1;
                    if (i == s.length() - 2) {
                        break;
                    }

                    if (i < s.length() - 2) {
                        for(i += 2; i < s.length(); ++i) {
                            if (s.charAt(i) == '*' && s.charAt(++i) == '?') {
                                flagForCmnt = 0;
                                break;
                            }
                        }
                    }
                } else {
                    temp = temp + s.charAt(i);
                }
            } else if (flagForCmnt == 1) {
                while(i < s.length()) {
                    if (s.charAt(i) == '*' && s.charAt(i + 1) == '?') {
                        flagForCmnt = 0;
                        ++i;
                        break;
                    }

                    ++i;
                }
            } else if (s.charAt(i) == ' ') {
                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }
            } else if (isOperator(s.charAt(i))) {
                char c = s.charAt(i);
                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                temp = temp + c;
                if (i == s.length() - 1) {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                    break;
                }

                switch(c) {
                    case '!':
                    case '%':
                    case '*':
                    case '/':
                    case '<':
                    case '=':
                    case '>':
                        if (s.charAt(i + 1) == '=') {
                            temp = temp + s.charAt(i + 1);
                            ++i;
                        }
                        break;
                    case '&':
                        if (s.charAt(i + 1) == '&') {
                            temp = temp + s.charAt(i + 1);
                            ++i;
                        }
                        break;
                    case '+':
                        if (s.charAt(i + 1) == '+' || s.charAt(i + 1) == '=') {
                            temp = temp + s.charAt(i + 1);
                            ++i;
                        }
                        break;
                    case '-':
                        if (s.charAt(i + 1) == '-' || s.charAt(i + 1) == '=') {
                            temp = temp + s.charAt(i + 1);
                            ++i;
                        }
                        break;
                    case '|':
                        if (s.charAt(i + 1) == '|') {
                            temp = temp + s.charAt(i + 1);
                            ++i;
                        }
                }

                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }
            } else if (isPunctuator(s.charAt(i))) {
                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                temp = temp + s.charAt(i);
                breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                temp = "";
            } else if (s.charAt(i) == '"') {
                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                temp = temp + s.charAt(i);
                ++i;

                label175:
                while(true) {
                    while(true) {
                        while(true) {
                            if (i >= s.length()) {
                                break label175;
                            }

                            if (s.charAt(i) != '"') {
                                if (s.charAt(i) == '\\' && (s.charAt(i + 1) == '"' || s.charAt(i + 1) == '\\')) {
                                    temp = temp + s.charAt(i);
                                    temp = temp + s.charAt(i + 1);
                                    i += 2;
                                } else {
                                    temp = temp + s.charAt(i);
                                    ++i;
                                }
                            } else if (s.charAt(i) == '"') {
                                temp = temp + s.charAt(i);
                                break label175;
                            }
                        }
                    }
                }

                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }
            } else if (s.charAt(i) == '\'') {
                if (temp != "") {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                temp = temp + s.charAt(i);
                if (i != s.length() - 1) {
                    if (i == s.length() - 2) {
                        temp = temp + s.charAt(i + 1);
                        ++i;
                    } else {
                        int j;
                        if (s.charAt(i + 1) == '\\') {
                            for(j = 0; j < 3; ++j) {
                                temp = temp + s.charAt(i + 1);
                                ++i;
                            }
                        } else {
                            for(j = 0; j < 2; ++j) {
                                temp = temp + s.charAt(i + 1);
                                ++i;
                            }
                        }
                    }
                }

                if (temp != "" && i != s.length() - 2) {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }
            } else if (s.charAt(i) == '.') {
                if (temp != "" && !CheckerMethods.isDigit(temp)) {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                temp = temp + s.charAt(i);
                if (i == s.length() - 1) {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }

                if (i == s.length() - 1) {
                    break;
                }

                if (!CheckerMethods.isDigit(Character.toString(s.charAt(i + 1)))) {
                    if (temp != "") {
                        breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                        temp = "";
                    }
                } else {
                    while(i != s.length() - 1 && s.charAt(i + 1) != ' ' && s.charAt(i + 1) != '.' && s.charAt(i + 1) != '\r' && s.charAt(i + 1) != '\n' && !isOperator(s.charAt(i + 1)) && !isPunctuator(s.charAt(i + 1)) && s.charAt(i + 1) != '\'' && s.charAt(i + 1) != '"') {
                        temp = temp + s.charAt(i + 1);
                        ++i;
                    }

                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                }
            } else {
                if (s.charAt(i) != ' ' && s.charAt(i) != '\t') {
                    temp = temp + s.charAt(i);
                }

                if (i == s.length() - 1) {
                    breakerWords.add(new ArrayList(Arrays.asList(temp, String.valueOf(lineNo))));
                    temp = "";
                    ++i;
                }
            }
        }

    }

    public static boolean isPunctuator(char c) {
        return c == ':' || c == ';' || c == ',' || c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == '?';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '=' || c == '<' || c == '>' || c == '!' || c == '&' || c == '|';
    }
}

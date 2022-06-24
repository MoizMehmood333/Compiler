//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<ArrayList<String>> tokens = new ArrayList();
    public static ArrayList<ArrayList<String>> keyWords = new ArrayList();
    public static ArrayList<ArrayList<String>> Operators;
    public static ArrayList<String> punctuators;







    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<String>> words = WordBreaker.Break_Words("/Users/Muhammad Moiz/Desktop/Compiler/Example.txt");
        String classifcation = "";
        String word = "";
        String lineNo = "";
        FileWriter tokensFile = new FileWriter("/Users/Muhammad Moiz/Desktop/Compiler/Tokens.txt");
        tokensFile.write("");

        for(int i = 0; i < words.size(); ++i) {
            word = (String)((ArrayList)words.get(i)).get(0);
            lineNo = (String)((ArrayList)words.get(i)).get(1);
            char charAtIndex0 = word.charAt(0);
            if (CheckerMethods.isAlphabet(Character.toString(charAtIndex0))) {
                if (CheckerMethods.isIdentifiers(word)) {
                    classifcation = "";
                    if (CheckerMethods.isKeyword(word)) {
                        classifcation = isKeyword(word);
                    }

                    if (classifcation != "") {
                        tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                        tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                    } else {
                        classifcation = "Identifier";
                        tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                        tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                    }
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (charAtIndex0 == '_') {
                if (CheckerMethods.isIdentifiers(word)) {
                    classifcation = "Identifier";
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (charAtIndex0 == '"') {
                if (CheckerMethods.isString(word)) {
                    classifcation = "String Constant";
                    word = word.substring(1, word.length() - 1);
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (charAtIndex0 == '\'') {
                if (CheckerMethods.isCharacter(word)) {
                    classifcation = "Character Constant";
                    word = word.substring(1, word.length() - 1);
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (CheckerMethods.isDigit(Character.toString(charAtIndex0))) {
                if (CheckerMethods.isIntegerConstant(word)) {
                    classifcation = "Integer Constant";
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                    tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                } else if (CheckerMethods.isFloatConstant(word)) {
                    classifcation = "Float Constant";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (charAtIndex0 == '.') {
                if (CheckerMethods.isFloatConstant(word)) {
                    classifcation = "Float Constant";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else if (word.equals(".")) {
                    classifcation = ".";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Token";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else if (CheckerMethods.isPunctuator(Character.toString(charAtIndex0))) {
                classifcation = isPunctuator(word);
                tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
            } else if (CheckerMethods.isOperator(charAtIndex0)) {
                if (issOperator(word)) {
                    classifcation = isOperator(word);
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                } else {
                    classifcation = "Invalid Keyword";
                    tokensFile.write("( " + classifcation + ", " + word + " ," + lineNo + " )");
                    tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
                }
            } else {
                classifcation = "Invalid Token";
                tokensFile.write("( " + classifcation + " ," + word + " ," + lineNo + " )");
                tokens.add(new ArrayList(Arrays.asList(classifcation, word, lineNo)));
            }

            tokensFile.write("\n");
        }

        int endTokenLineNo = Integer.parseInt(lineNo);
        tokens.add(new ArrayList(Arrays.asList("$", "$", endTokenLineNo)));
        tokensFile.close();
        System.out.println(tokens);

        SyntaxAnalyzer.token(tokens);

    }



    public static String isPunctuator(String s) {
        String classPart = "Invalid_token";

        for(int i = 0; i < punctuators.size(); ++i) {
            if (s.equals(punctuators.get(i))) {
                classPart = (String)punctuators.get(i);
                break;
            }
        }

        return classPart;
    }

    public static String isKeyword(String s) {
        String classPart = "Invalid_token";

        for(int i = 0; i < keyWords.size(); ++i) {
            if (s.equals(((ArrayList)keyWords.get(i)).get(1))) {
                classPart = (String)((ArrayList)keyWords.get(i)).get(0);
                break;
            }
        }

        return classPart;
    }

    public static String isOperator(String s) {
        String classPart = "Invalid_token";

        for(int i = 0; i < Operators.size(); ++i) {
            if (s.equals(((ArrayList)Operators.get(i)).get(1))) {
                classPart = (String)((ArrayList)Operators.get(i)).get(0);
                break;
            }
        }

        return classPart;
    }

    public static boolean issOperator(String s) {
        boolean b = false;

        for(int i = 0; i < Operators.size(); ++i) {
            if (s.equals(((ArrayList)Operators.get(i)).get(1))) {
                b = true;
                break;
            }
        }

        return b;
    }

    static {
        keyWords.add(new ArrayList(Arrays.asList("Void", "void")));
        keyWords.add(new ArrayList(Arrays.asList("Throw", "throw")));
        keyWords.add(new ArrayList(Arrays.asList("Throws", "throws")));
        keyWords.add(new ArrayList(Arrays.asList("Inherit", "inherits")));
        keyWords.add(new ArrayList(Arrays.asList("This", "this")));
        keyWords.add(new ArrayList(Arrays.asList("Super", "super")));
        keyWords.add(new ArrayList(Arrays.asList("Get", "get")));
        keyWords.add(new ArrayList(Arrays.asList("New", "new")));
        keyWords.add(new ArrayList(Arrays.asList("Add", "add")));
        keyWords.add(new ArrayList(Arrays.asList("Default", "default")));
        keyWords.add(new ArrayList(Arrays.asList("DataType", "int")));
        keyWords.add(new ArrayList(Arrays.asList("DataType", "double")));
        keyWords.add(new ArrayList(Arrays.asList("DataType", "char")));
        keyWords.add(new ArrayList(Arrays.asList("String", "string")));
        keyWords.add(new ArrayList(Arrays.asList("For", "for")));
        keyWords.add(new ArrayList(Arrays.asList("While", "while")));
        keyWords.add(new ArrayList(Arrays.asList("If", "if")));
        keyWords.add(new ArrayList(Arrays.asList("Else", "else")));
        keyWords.add(new ArrayList(Arrays.asList("Do", "do")));
        keyWords.add(new ArrayList(Arrays.asList("Break", "break")));
        keyWords.add(new ArrayList(Arrays.asList("Continue", "continue")));
        keyWords.add(new ArrayList(Arrays.asList("Boolean", "boolean")));
        keyWords.add(new ArrayList(Arrays.asList("Arrays", "arrays")));
        keyWords.add(new ArrayList(Arrays.asList("ArrayList", "arrayList")));
        keyWords.add(new ArrayList(Arrays.asList("Class", "class")));
        keyWords.add(new ArrayList(Arrays.asList("Extends", "extends")));
        keyWords.add(new ArrayList(Arrays.asList("Static", "static")));
        keyWords.add(new ArrayList(Arrays.asList("Public", "public")));
        keyWords.add(new ArrayList(Arrays.asList("Override", "override")));
        keyWords.add(new ArrayList(Arrays.asList("Abstract", "abstract")));
        keyWords.add(new ArrayList(Arrays.asList("Final", "final")));
        keyWords.add(new ArrayList(Arrays.asList("Try", "try")));
        keyWords.add(new ArrayList(Arrays.asList("Catch", "catch")));
        keyWords.add(new ArrayList(Arrays.asList("Interface", "interface")));
        keyWords.add(new ArrayList(Arrays.asList("Return", "return")));
        keyWords.add(new ArrayList(Arrays.asList("Switch", "switch")));
        keyWords.add(new ArrayList(Arrays.asList("Main", "main")));


        Operators = new ArrayList();
        Operators.add(new ArrayList(Arrays.asList("PM", "+")));
        Operators.add(new ArrayList(Arrays.asList("PM", "-")));
        Operators.add(new ArrayList(Arrays.asList("MDM", "*")));
        Operators.add(new ArrayList(Arrays.asList("MDM", "/")));
        Operators.add(new ArrayList(Arrays.asList("MDM", "%")));
        Operators.add(new ArrayList(Arrays.asList("<", "<")));
        Operators.add(new ArrayList(Arrays.asList(">", ">")));
        Operators.add(new ArrayList(Arrays.asList("RO", "<=")));
        Operators.add(new ArrayList(Arrays.asList("RO", ">=")));
        Operators.add(new ArrayList(Arrays.asList("RO", "!=")));
        Operators.add(new ArrayList(Arrays.asList("RO", "==")));
        Operators.add(new ArrayList(Arrays.asList("=", "=")));
        Operators.add(new ArrayList(Arrays.asList("AO", "&&")));
        Operators.add(new ArrayList(Arrays.asList("OR", "||")));
        Operators.add(new ArrayList(Arrays.asList("CA", "+=")));
        Operators.add(new ArrayList(Arrays.asList("CA", "*=")));
        Operators.add(new ArrayList(Arrays.asList("CA", "-=")));
        Operators.add(new ArrayList(Arrays.asList("CA", "+=")));
        Operators.add(new ArrayList(Arrays.asList("CA", "/=")));
        Operators.add(new ArrayList(Arrays.asList("Inc_Dec", "--")));
        Operators.add(new ArrayList(Arrays.asList("Inc_Dec", "++")));
        Operators.add(new ArrayList(Arrays.asList("NO", "!")));
        punctuators = new ArrayList(Arrays.asList(";", ",", "(", ")", ":", "{", "}", "[", "]", "?"));
    }
}

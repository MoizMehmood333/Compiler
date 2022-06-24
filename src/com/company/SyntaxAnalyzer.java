package com.company;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.sound.midi.MidiFileFormat;
import javax.sound.midi.Soundbank;
import javax.sql.CommonDataSource;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;

public class SyntaxAnalyzer {

    public static int  index = 0;
    public static ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();

    public static String classPart = "";
    public static int lineNo;

    public static void token( ArrayList<ArrayList<String>> tokensFromLexical){
        tokens = tokensFromLexical;
        Boolean b = false;

        if (S() == true){

            b = true;
            System.out.println("s is working");

            if (tokens.get(index).get(0).matches("$")){
                b  = true;
            }


        }

        if (b == true) {
            System.out.println("Error Free Syntax");
        }

        else {
            System.out.println("Error at line " + tokens.get(index).get(2) + ", ClassPart is: " + tokens.get(index).get(0) + " and Value is: " + tokens.get(index).get(1) );
        }


    }


    public static boolean S(){

        boolean b = false;

        if(tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final")  ||
                tokens.get(index).get(0).matches("Class") ||  tokens.get(index).get(0).matches("Public") ){
            if (Defs()){
                if (tokens.get(index).get(0).matches("Public")){
                    index++;
                    if (tokens.get(index).get(0).matches("Class")){
                        index++;
                        if (tokens.get(index).get(0).matches("Identifier")){
                            index++;
                            if (Inht()){
                                 if (Throws()){
                                    if (tokens.get(index).get(0).matches("\\{")){
                                        index++;
                                       if (tokens.get(index).get(0).matches("Public")){
                                            index++;
                                            if (tokens.get(index).get(0).matches("Static")){
                                                index++;
                                                if (tokens.get(index).get(0).matches("Void")){
                                                    index++;
                                                    if (tokens.get(index).get(0).matches("Main")){
                                                        index++;
                                                         if (tokens.get(index).get(0).matches("\\(")){
                                                            index++;
                                                            if (tokens.get(index).get(0).matches("\\)")){
                                                                index++;
                                                                if (tokens.get(index).get(0).matches("\\{")){
                                                                    index++;
                                                                    if (Mst()){
                                                                        if (tokens.get(index).get(0).matches("}")){
                                                                            index++;
                                                                            if (CBody()){
                                                                                if (tokens.get(index).get(0).matches("}")){
                                                                                    index++;
                                                                                    if (Defs()){
                                                                                        b = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return b;
    }

    public static boolean Defs(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("Class")){
            if (ClassDefs()) {
                if (Defs()) {
                    b = true;
                }
            }
        }
        else if (tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("\\$")){
            b = true;
        }


        return b;
    }

    public static boolean ClassDefs(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final" ) || tokens.get(index).get(0).matches("Class")){
            if (AbsFinal()){
                if (tokens.get(index).get(0).matches("Class")){
                    index++;
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (Inht()){
                            if (Throws()){
                                if (tokens.get(index).get(0).matches("\\{")){
                                    index++;
                                    if (CBody()){
                                        if (tokens.get(index).get(0).matches("}")){
                                            index++;
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b;
    }

    public static boolean AccessModifier() {
        boolean b = false;

        if (tokens.get(index).get(0).matches("Pubic")){
            index++;
            b= true;
        }
        else if (tokens.get(index).get(0).matches("Private")){
            index++;
            b = true;
        }
        else {
            if (tokens.get(index).get(0).matches("Static") || tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final")
             || tokens.get(index).get(0).matches("DataType") ||  tokens.get(index).get(0).matches("Identifier") ||  tokens.get(index).get(0).matches("ArrayList")){
                b  = true;
            }
        }
        return b;
    }

    public static boolean Static(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Static") ){
            index++;
            b = true;
        }
        else {
            if ( tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final")
                    || tokens.get(index).get(0).matches("DataType") ||  tokens.get(index).get(0).matches("Identifier") ||
                    tokens.get(index).get(0).matches("ArrayList") ){
                b = true;
            }
        }


        return b;
    }

    public static boolean Inht(){
        boolean b = false;

        if(tokens.get(index).get(0).matches("Extends")){
                index++;
                if (tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    b =  true;
                }

        }
        else {
            if (tokens.get(index).get(0).matches("Throws") || tokens.get(index).get(0).matches("\\{")){
                b = true;
            }
        }
        return b;
    }

    public static boolean CBody(){
        boolean b = false;
                if (tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Private") || tokens.get(index).get(0).matches("Static")
                || tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType")
                || tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("ArrayList")){

                    if (AccessModifier()){
                        if (Static()){
                            if (AbsFinal()){
                                if (CBody2()){
                                    if (CBody()){
                                        b = true;
                                    }
                                }
                            }
                        }
                    }

                }
                else {
                        if (tokens.get(index).get(0).matches("}")){
                            b = true;
                        }
                }

        return b;
    }

    public static boolean CBody2(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (CBody7()){
                b = true;
            }
        }
        else if(tokens.get(index).get(0).matches("DataType")) {
            index++;
            if (CBody3()){
                b = true;
            }
        }
        else{
                if (tokens.get(index).get(0).matches("ArrayList")){
                    if (tokens.get(index).get(0).matches("ArrayList")){
                        index++;
                        if (tokens.get(index).get(0).matches("<")){
                            index++;
                            if (A1()){
                                if (tokens.get(index).get(0).matches(">")){
                                    index++;
                                    if (tokens.get(index).get(0).matches("Identifier")){
                                        index++;
                                        if (CBody5()){
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
        return b;
    }

    public static boolean CBody3(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (CBody6()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\["))
        {
            if (tokens.get(index).get(0).matches("\\[")){
                index++;
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (ArrDec_()){
                        if (tokens.get(index).get(0).matches("Identifier")){
                            index++;
                            if (tokens.get(index).get(0).matches("\\(")){
                                index++;
                                if (Args()){
                                    if (tokens.get(index).get(0).matches("\\)")){
                                        index++;
                                        if (Throws()){
                                             if (tokens.get(index).get(0).matches("\\{")){
                                                index++;
                                                if (Mst()){
                                                    if(tokens.get(index).get(0).matches("}")){
                                                        index++;
                                                        b = true;
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return b;
    }

    public static boolean CBody4(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("=")){
            if (tokens.get(index).get(0).matches("=")){
                index++;
                if (tokens.get(index).get(0).matches("New")){
                    index++;
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (tokens.get(index).get(0).matches("\\(")){
                            index++;
                            if (Pl()){
                                if (tokens.get(index).get(0).matches("\\)")){
                                    index++;
                                    if (tokens.get(index).get(0).matches(";")){
                                        index++;
                                        b = true;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Args()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    if (Throws()){
                        if (tokens.get(index).get(0).matches("\\{")){
                            index++;
                            if (Mst()){
                                if (tokens.get(index).get(0).matches("}")){
                                    index++;
                                    b  = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean CBody5(){
        boolean b = false;
        if ( tokens.get(index).get(0).matches("=")){
            index++;
            if (tokens.get(index).get(0).matches("New")){
                index++;
                if (tokens.get(index).get(0).matches("ArrayList")){
                    index++;
                    if (tokens.get(index).get(0).matches("<")){
                        index++;
                        if (A1()){
                            if (tokens.get(index).get(0).matches(">")){
                                index++;
                                if (tokens.get(index).get(0).matches("\\(")){
                                    index++;
                                    if (tokens.get(index).get(0).matches("\\)")){
                                        index++;
                                        if (tokens.get(index).get(0).matches(";")){
                                            index++;
                                            b= true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Args()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    if (Throws()){
                        if (tokens.get(index).get(0).matches("\\{")){
                            index++;
                            if (Mst()){
                                if (tokens.get(index).get(0).matches("}")){
                                    index++;
                                    b= true;
                                }
                            }
                        }
                    }
                }
            }
        }


        return b;
    }

    public static boolean CBody6(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    if (Val()){
                        if (tokens.get(index).get(0).matches(";")){
                            index++;
                            b = true;
                        }
                    }

                }
            }
        }
        else if(tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Args()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    if (Throws()){
                        if (tokens.get(index).get(0).matches("\\{")){
                            index++;
                            if (Mst()){
                                if (tokens.get(index).get(0).matches("}")){
                                    index++;
                                    b = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean CBody7(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if(tokens.get(index).get(0).matches("Add")){
                index++;
                if (tokens.get(index).get(0).matches("\\(")){
                    index++;
                    if (Cl1()){
                        if (tokens.get(index).get(0).matches("\\)")){
                            index++;
                            if (tokens.get(index).get(0).matches(";")){
                                index++;
                                b = true;
                            }
                        }
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                if (CBody4()){
                    b = true;
                }
        }
        return b;
    }


    //It has been defined in C_body! but still wrote it here for understanding / learning purposes

    public static boolean Constructor(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Private") || tokens.get(index).get(0).matches("Identifier")){
            if (AccessModifier()){
                if (tokens.get(index).get(0).matches("Identifier"))
                {
                    index++;
                    if (tokens.get(index).get(0).matches("\\(")){
                        index++;
                        if (Args()){
                            if (tokens.get(index).get(0).matches("\\)")){
                                index++;
                                if (tokens.get(index).get(0).matches("\\{")){
                                    index++;
                                    if (Mst()){
                                        if (tokens.get(index).get(0).matches("}")){
                                            index++;
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        return b;
    }

    public static boolean Args(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("DataType")){
            index++;
            if (Args2()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\)")){
            b = true;
        }
        return b;
    }

    public static boolean Args1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(",")){
            index++;
            if (Args3()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\)")){
            b = true;
        }

        return b;
    }

    public static boolean Args2(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (Args1()){
                b  = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (Args1()){
                            b = true;
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean Args3(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (Args4()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (Args1()){
                            b = true;
                        }
                    }
                }

            }

        }

        return b;
    }

    public static boolean Args4(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)")){
            if(Args1()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (Args1()){
                b = true;
            }
        }
        return b;
    }

    // Following CFG's have already been left factored or defined in SST and C_Body but being defined here just for the sake of understanding / learning purpose



    public static boolean Dec1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Private") ||
                tokens.get(index).get(0).matches("Static") ||  tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType")){

            if (AccessModifier()){
                if (Static()){
                        if (Final()){
                            if (tokens.get(index).get(0).matches("DataType")){
                                index++;
                                if (tokens.get(index).get(0).matches("Identifier")){
                                    index++;
                                    if (Init()){
                                        if(List()){
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                }
            }
        }
        return b;
    }

    public static boolean FuncDef(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Private") ||  tokens.get(index).get(0).matches("Abstract") ||
                tokens.get(index).get(0).matches("Static") ||  tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType") ||
                tokens.get(index).get(0).matches("ArrayList") ||  tokens.get(index).get(0).matches("Identifier")){
            if (AccessModifier()){
                if (Static()){
                    if (AbsFinal()){
                        if (ReturnType()){
                            if ( tokens.get(index).get(0).matches("Identifier")){
                                index++;
                                if ( tokens.get(index).get(0).matches("\\(")){
                                    index++;
                                    if (Args()){
                                        if ( tokens.get(index).get(0).matches("\\)")){
                                            index++;
                                            if (Throws()){
                                                if ( tokens.get(index).get(0).matches("\\{")){
                                                    index++;
                                                    if (Mst()){
                                                        if ( tokens.get(index).get(0).matches("}")){
                                                            index++;
                                                            b = true;
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean ObjDec(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("Identifier")){
            index++;
            if ( tokens.get(index).get(0).matches("Identifier")){
                index++;
                if ( tokens.get(index).get(0).matches("=")){
                    index++;
                    if ( tokens.get(index).get(0).matches("New")){
                        index++;
                        if ( tokens.get(index).get(0).matches("Identifier")){
                            index++;
                            if ( tokens.get(index).get(0).matches("\\(")){
                                index++;
                                if (Pl()){
                                    if ( tokens.get(index).get(0).matches("\\)")){
                                        index++;
                                        if ( tokens.get(index).get(0).matches(";")){
                                            index++;
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b;
    }

    public static boolean ObjDec1(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Private") ||  tokens.get(index).get(0).matches("Static")
        ||  tokens.get(index).get(0).matches("Final") ||  tokens.get(index).get(0).matches("Identifier")){
            if (AccessModifier()){
                if (Static()){
                    if (Final()){
                        if ( tokens.get(index).get(0).matches("Identier")){
                            index++;
                            if ( tokens.get(index).get(0).matches("Identifier")){
                                index++;
                                if ( tokens.get(index).get(0).matches("=")){
                                    index++;
                                    if ( tokens.get(index).get(0).matches("New")){
                                        index++;
                                        if ( tokens.get(index).get(0).matches("Identifier")){
                                            index++;
                                            if ( tokens.get(index).get(0).matches("\\(")){
                                                index++;
                                                if (Pl()){
                                                    if ( tokens.get(index).get(0).matches("\\)")){
                                                        index++;
                                                        if ( tokens.get(index).get(0).matches(";")){
                                                            index++;
                                                            b = true;

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean DataType(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("DataType")) {
                index++;
                b = true;
            }
        return b;
    }




    //Unused CFG's end here! There may also be some down below!!

    public static boolean ReturnType(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("DataType")){
            index++;
            if (RT()){
                b = true;
            }
        }
        else if(tokens.get(index).get(0).matches("ArrayList")){
            index++;
            if (tokens.get(index).get(0).matches("<")){
                index++;
                if (A1()){
                    if (tokens.get(index).get(0).matches(">")){
                        index++;
                        b = true;
                    }
                }
            }
        }
        else if(tokens.get(index).get(0).matches("Identifier")){
            index++;
            b = true;
        }
        return b;
    }

    public static boolean RT(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    b = true;
                }
            }
        }
        else if (tokens.get(index).get(0).matches("Identifier")){
            b = true;
        }
        return b;
    }



    //Unused CFG since defined in SST
    public static boolean ArrDec(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType")){
            if (Final()){
                if (tokens.get(index).get(0).matches("DataType")){
                    index++;
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (tokens.get(index).get(0).matches("\\[")){
                            index++;
                            if (tokens.get(index).get(0).matches("]")){
                                index++;
                                if (ArrDec_()){
                                    if (Val()){
                                        if (tokens.get(index).get(0).matches(";")){
                                            index++;
                                            b = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b;
    }

    public static boolean ArrDec_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    b = true;
                }
            }
        }
        else if (tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches(">")) {
            b = true;
        }
        return b;
    }

    public static boolean Val(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("=")){
                index++;
                if (Ad()){
                    b = true;
                }
            }
        return b;
    }


    public static boolean Ad(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("New")){
            index++;
            if (tokens.get(index).get(0).matches("DataType")){
                index++;
                if(tokens.get(index).get(0).matches("\\[")){
                    index++;
                    if (Oe()){
                        if (tokens.get(index).get(0).matches("]")){
                            index++;
                            if (Ad1()){
                                b = true;
                            }
                        }
                    }
                }


            }
        }
        else if (tokens.get(index).get(0).matches("\\{")){
            index++;
            if (Ad2()){
                if (tokens.get(index).get(0).matches("}")){
                    index++;
                    if (Ad3()){
                        b = true;
                    }
                }

            }
        }

        return b;
    }

    public static boolean Ad1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Ad1()){
                        b = true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches(";")){
                b = true;
        }
        return b;
    }

    public static boolean Ad2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\{")){
            index++;
            if (Pl()){
                if (tokens.get(index).get(0).matches("}")){
                    index++;
                    if (Ad3()){
                        b = true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
        ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec") ||
                tokens.get(index).get(0).matches("}") ){
            if (Pl()){
                b = true;
            }
        }

        return b;
    }

    public static boolean Ad3(){
        boolean b = false;
        if (tokens.get(index).get(0).matches(",")){
            index++;
            if (Ad4()){
                b = true;

            }

        }
        else if( tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("}")){
            b = true;
        }
        return b;
    }


    public static boolean Ad4(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\{")){
            index++;
            if (Pl()){
                if (tokens.get(index).get(0).matches("}")){
                    index++;
                    if (Ad3()){
                        b = true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec") ||
                tokens.get(index).get(0).matches("}")){
            if (Pl()){
                if (Ad3()){
                    b =true;
                }
            }
        }

        return b;
    }

    public static boolean ArrDec1(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("Private") || tokens.get(index).get(0).matches("Public") || tokens.get(index).get(0).matches("Static") ||
                    tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType")){
                if (AccessModifier()){
                    if (Static()){
                        if (Final()){
                            if (tokens.get(index).get(0).matches("DataType")){
                                index++;
                                if (tokens.get(index).get(0).matches("Identifier")){
                                    index++;
                                    if (tokens.get(index).get(0).matches("\\[")){
                                        index++;
                                        if (tokens.get(index).get(0).matches("]")){
                                            index++;
                                            if (ArrDec_()){
                                                if (Val()){
                                                    if (tokens.get(index).get(0).matches(";")){
                                                        index++;

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return b;
    }


    public static boolean TryCatch(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Try")){
            index++;
            if (tokens.get(index).get(0).matches("\\{")){
                index++;
                if (Mst()){
                    if (tokens.get(index).get(0).matches("}")){
                        index++;
                        if (tokens.get(index).get(0).matches("Catch")){
                            index++;
                            if (tokens.get(index).get(0).matches("\\(")){
                                index++;
                                if (tokens.get(index).get(0).matches("Identifier")){
                                    index++;
                                    if (tokens.get(index).get(0).matches("Identifier")){
                                        index++;
                                        if (tokens.get(index).get(0).matches("\\)")){
                                            index++;
                                            if (tokens.get(index).get(0).matches("\\{")){
                                                index++;
                                                if (Mst()){
                                                    if (tokens.get(index).get(0).matches("}")){
                                                        index++;
                                                        b =true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean Throw(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Throw")){
            index++;
            if (tokens.get(index).get(0).matches("New")){
                index++;
                if (tokens.get(index).get(0).matches("Identifier")){
                    index++;
                        if (tokens.get(index).get(0).matches("\\(")){
                        index++;
                        if (Pl()){
                            if (tokens.get(index).get(0).matches("\\)")){
                                index++;
                                b = true;
                            }
                        }

                    }
                }
            }
        }

        return b;
    }

    public static boolean Throws(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Throws")){
            index++;
            if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                b = true;
            }
        }
        else if(tokens.get(index).get(0).matches("\\{")){
            b = true;
        }
        return b;
    }

    public static boolean SwitchSt(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Switch")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (Oe()){
                    if (tokens.get(index).get(0).matches("\\)")){
                        index++;
                        if (tokens.get(index).get(0).matches("\\{")){
                            index++;
                            if (Case()){
                                {
                                   if ( tokens.get(index).get(0).matches("}")) {
                                    index++;
                                    b = true;
                                }
                                }
                            }
                        }
                    }
                }
            }
        }

        return b;
    }

    public static boolean Case(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Case")){
            index++;
            if (Const()){
                if (tokens.get(index).get(0).matches(":")){
                    index++;
                    if (Mst()){
                        if(Case()){
                            b = true;
                        }
                    }
                }

            }
        }

        else if (tokens.get(index).get(0).matches("Default")){
            index++;
            if (tokens.get(index).get(0).matches(":")){
                index++;
                if (Mst()){
                    b = true;
                }
            }
        }
        return b;
    }

    public static boolean Return(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Return")){
            index++;
            if (Return_()){
                b = true;
            }
        }
        return b;
    }
    public static boolean Return_(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Identifier")
            || tokens.get(index).get(0).matches("Integer Constant") || tokens.get(index).get(0).matches("String Constant")||
                    tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("Character Constant") ||
                    tokens.get(index).get(0).matches("True") || tokens.get(index).get(0).matches("False") || tokens.get(index).get(0).matches("Inc_Dec") ||
                    tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!")
            ){
                if (Oe()){
                    if (tokens.get(index).get(0).matches(";")){
                        index++;
                        b = true;
                    }
                }
            }
        return b;
    }
    public static boolean Break(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("Break")){
                index++;
                if (tokens.get(index).get(0).matches(";")){
                    index++;
                    b = true;
                }
            }
        return b;
    }
    public static boolean Continue(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Continue")){
            index++;
            if (tokens.get(index).get(0).matches(";")){
                index++;
                b = true;
            }
        }
        return b;
    }
    public static boolean ArrayList(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("ArrayList")){
            index++;
            if (tokens.get(index).get(0).matches("<")){
                index++;
                if (A1()){
                    if (tokens.get(index).get(0).matches(">")){
                        index++;
                        if (tokens.get(index).get(0).matches("Identifier")){
                            index++;
                            if (tokens.get(index).get(0).matches("=")){
                                 index++;
                                 if (tokens.get(index).get(0).matches("New")){
                                     index++;
                                     if (tokens.get(index).get(0).matches("ArrayList")){
                                         index++;
                                         if (tokens.get(index).get(0).matches("<")){
                                             index++;
                                             if (A1()){
                                                 if (tokens.get(index).get(0).matches(">")){
                                                     index++;
                                                     if (tokens.get(index).get(0).matches("\\(")){
                                                         index++;
                                                         if (tokens.get(index).get(0).matches("\\)")){
                                                             index++;
                                                             b = true;
                                                         }
                                                     }
                                                 }
                                             }
                                         }
                                     }
                                 }
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    public static boolean A1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("DataType")){
            index++;
            if (A2()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            b = true;
        }
        else if (tokens.get(index).get(0).matches("ArrayList")){
            index++;
            if (tokens.get(index).get(0).matches("<")){
                index++;
                if(A1()){
                    if (tokens.get(index).get(0).matches(">")){
                        index++;
                        b = true;
                    }
                }
            }
        }

        return b;
    }



    public static boolean A2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (tokens.get(index).get(0).matches("]")){
                index++;
                if (ArrDec_()){
                    b = true;
                }
            }
        }
        else if (tokens.get(index).get(0).matches(">")){
            b = true;
        }

        return b;
    }


    public static boolean ArrayList1(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("Abstract") || tokens.get(index).get(0).matches("Final")
                    || tokens.get(index).get(0).matches("Public") ||  tokens.get(index).get(0).matches("Private") ||
                    tokens.get(index).get(0).matches("ArrayList")){
                if (AccessModifier()){
                    if(AbsFinal()){
                        if (tokens.get(index).get(0).matches("ArrayList")){
                            index++;
                            if (tokens.get(index).get(0).matches("<")){
                                index++;
                                if (A1()){
                                    if (tokens.get(index).get(0).matches(">")){
                                        index++;
                                        if (tokens.get(index).get(0).matches("Identifier")) {
                                            index++;
                                            if (tokens.get(index).get(0).matches("=")){
                                                index++;
                                                if (tokens.get(index).get(0).matches("New")) {
                                                    index++;
                                                    if (tokens.get(index).get(0).matches("ArrayList")) {
                                                        index++;
                                                        if (tokens.get(index).get(0).matches("<")) {
                                                            index++;
                                                            if (A1()) {
                                                                if (tokens.get(index).get(0).matches(">")) {
                                                                    index++;
                                                                    if (tokens.get(index).get(0).matches("\\(")) {
                                                                        index++;
                                                                        if (tokens.get(index).get(0).matches("\\)")) {
                                                                            index++;
                                                                            if (tokens.get(index).get(0).matches(";")) {
                                                                                index++;
                                                                                b = true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        return b;
    }



    public static boolean CreateList(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (tokens.get(index).get(0).matches(".")){
                index++;
                if (tokens.get(index).get(0).matches("Add")){
                    index++;
                    if (tokens.get(index).get(0).matches("\\(")){
                        index++;
                        if (Cl1()){
                            if (tokens.get(index).get(0).matches("\\)"))
                            {
                                index++;
                                b = true;
                            }
                        }

                    }
                }
            }
        }
        return b;
    }
    public static boolean Cl1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Identifier")
                || tokens.get(index).get(0).matches("Integer Constant") || tokens.get(index).get(0).matches("String Constant")||
                tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("True") || tokens.get(index).get(0).matches("False") || tokens.get(index).get(0).matches("Inc_Dec") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!")){
            if (Oe()){
                b = true;
            }

        }
        else if(tokens.get(index).get(0).matches("New")){
            index++;
            if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                if (tokens.get(index).get(0).matches("\\(")){
                    index++;
                    if (Pl()){
                        if (tokens.get(index).get(0).matches("\\)")){
                            index++;
                            b  =true;
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean GetList(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (tokens.get(index).get(0).matches(".")){
                index++;
                if (tokens.get(index).get(0).matches("Get")){
                    index++;
                    if (tokens.get(index).get(0).matches("\\(")){
                        index++;
                        if (Oe()){
                            if (tokens.get(index).get(0).matches("\\)")){
                                index++;
                                b  = true;
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    public static boolean Sst(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("While")) {
            if (While()) {
                b = true;
            }
        }
            else if (tokens.get(index).get(0).matches("For")){
                if (For()){
                    b = true;
                }
            }
            else if (tokens.get(index).get(0).matches("If")){
                if (IfElse()){
                    b = true;
                }
            }
            else if (tokens.get(index).get(0).matches("Do")){
                if (DoWhile()){
                    b = true;
                }
            }

            else if (tokens.get(index).get(0).matches("Return")){
                if (Return()){
                    b = true;
                }
            }
            else if (tokens.get(index).get(0).matches("Try")){
                if (TryCatch()){
                    b = true;
                }
            }
            else if (tokens.get(index).get(0).matches("Throw")){
                if (Throw()){
                    b = true;
                }
            }
            else if (tokens.get(index).get(0).matches("Switch")){
                if (SwitchSt()){
                    b = true;
                }
            }

            else if (tokens.get(index).get(0).matches("Break")){
                if (Break()){
                    b = true;
                }
            }

            else if (tokens.get(index).get(0).matches("Continue")){
                if (Continue()){
                    b = true;
                }
            }

            else if (tokens.get(index).get(0).matches("ArrayList")){
                if (ArrayList()){
                    b = true;
                }
            }

            else if (tokens.get(index).get(0).matches("Final") || tokens.get(index).get(0).matches("DataType")){
                if (Final()){
                    if (tokens.get(index).get(0).matches("DataType")){
                        index++;
                        if (tokens.get(index).get(0).matches("Identifier")){
                            index++;
                            if (Sst1()){
                                b = true;
                            }
                        }
                    }
                }
            }

            else if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Identifier")){
                if (Th()){
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (Sst2()){
                            b = true;
                        }
                    }
                }
            }



        return b;
    }
    public static boolean Sst1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\[")){
                index++;
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (ArrDec_()){
                        if (Val()){
                            if (tokens.get(index).get(0).matches(";")){
                                index++;
                                b =true;
                            }
                        }
                    }
                }
        }

        else if (tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches(",")){
            if (Init()){
                if(List()){
                    b = true;
                }
            }
        }



        return b;
    }
    public static boolean Sst2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Inc_Dec")){
            index++;
            if (tokens.get(index).get(0).matches(";")){
                index++;
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if (Sst7()){
                b = true;
            }
        }
        else  if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (tokens.get(index).get(0).matches("=")){
                index++;
                if (tokens.get(index).get(0).matches("New")){
                    index++;
                    if (tokens.get(index).get(0).matches("Identifier")){
                        index++;
                        if (tokens.get(index).get(0).matches("\\(")){
                            index++;
                            if (Pl()){
                                if (tokens.get(index).get(0).matches("\\)")){
                                    index++;
                                    if (tokens.get(index).get(0).matches(";")){
                                        index++;
                                        b = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if( tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("CA")){

            if (AsgnOp()){
                if (Oe()){
                    if (tokens.get(index).get(0).matches(";")){
                        index++;
                        b=true;
                    }
                }
            }

        }
        else if (tokens.get(index).get(0).matches("\\[") || tokens.get(index).get(0).matches("\\)")){
            if (Sst3()){
                b = true;
            }
        }

        return b;
    }
    public static boolean Sst3(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Array()){
                        if (Sst6()){
                            b = true;
                        }
                    }
                }

            }
        }
        else if (tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Pl()){
                if (tokens.get(index).get(0).matches("\\)")){
                index++;
                 if (Sst4()){
                    b  = true;
                  }
                }
            }
        }
        return b;
    }


    public static boolean Sst3_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (Sst5()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("Get")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (Oe()){
                    if (tokens.get(index).get(0).matches("\\)")){
                        if (Sst6()){
                            b  = true;
                        }
                    }
                }
            }
        }
        return b;
    }


    public static boolean Sst4(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(";")){
            index++;
            b  = true;
        }
        else if (tokens.get(index).get(0).matches(".")){
            index++;
            if (Sst3_()){
                b  = true;
            }
        }

        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Array()){
                        if (Sst6()){
                            b =  true;
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean Sst5(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(".")){
            index++;
            if (Sst3_()){
                b  = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Array()){
                        if (Sst6()){
                            b = true;
                        }
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("Inc_Dec") || tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("CA")
        || tokens.get(index).get(0).matches("\\(")){
            if (Sst8()){
                b = true;
            }
        }
        return b;
    }
    public static boolean Sst6(){
        boolean b = false;
            if (tokens.get(index).get(0).matches(".")){
                index++;
                if (tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (Sst5()){
                        b = true;
                    }
                }
            }

            else if (tokens.get(index).get(0).matches("Inc_Dec") || tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("CA")
                    || tokens.get(index).get(0).matches("\\(")){
                if (Sst8()){
                    b  = true;
                }
            }

        return b;
    }
    public static boolean Sst7(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("Add")){
                index++;
                if (tokens.get(index).get(0).matches("\\(")){
                    index++;
                    if (Cl1()){
                        if (tokens.get(index).get(0).matches("\\)")){
                            index++;
                            if (tokens.get(index).get(0).matches(";")){
                                index++;
                                b = true;
                            }
                        }
                    }
                }
            }
            else if (tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Get")){
                if (Sst3_()){
                    b = true;
                }
            }
        return b;
    }
    public static boolean Sst8(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Inc_Dec")){
            index++;
            if (tokens.get(index).get(0).matches(";")){
                index++;
                b  =  true;
            }
        }


        return b;
    }
    public static boolean Dec(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("DataType")){
            index++;
            if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                if (Init()){
                    if (List()){
                        b = true;
                    }
                }
            }
        }


        return b;
    }
    public static boolean List(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(";")){
            index++;
            b  = true;
        }

        else if (tokens.get(index).get(0).matches(",")){
            index++;
            if (tokens.get(index).get(0).matches("Identifier")){
                index++;
                if (Init()){
                    if (List()){
                        b=  true;
                    }
                }
            }
        }
        return b;
    }
    public static boolean Init(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("=")){
            index++;
            if (Oe()){
                b =  true;
            }
        }
        else if (tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches(",")){
            b = true;
        }
        return b;
    }
    public static boolean While(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("While")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (Oe()){
                    if (tokens.get(index).get(0).matches("\\)")){
                        index++;
                        if (Body()){
                            b  =  true;
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean Body(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(";")){
            index++;
            b = true;
        }
        else if (tokens.get(index).get(0).matches("While") || tokens.get(index).get(0).matches("If") || tokens.get(index).get(0).matches("Do")
        || tokens.get(index).get(0).matches("Return") || tokens.get(index).get(0).matches("For") || tokens.get(index).get(0).matches("Try")
        || tokens.get(index).get(0).matches("Throw") || tokens.get(index).get(0).matches("Switch") || tokens.get(index).get(0).matches("Break")
        || tokens.get(index).get(0).matches("Continue") || tokens.get(index).get(0).matches("ArrayList") || tokens.get(index).get(0).matches("Final")
        || tokens.get(index).get(0).matches("DataType") || tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super")
        || tokens.get(index).get(0).matches("Identifier")){
            if (Sst()){
                b = true;
            }
        }

        else if (tokens.get(index).get(0).matches("\\{")){
            index++;
            if (Mst()){
                if (tokens.get(index).get(0).matches("}")){
                    index++;
                    b = true;
                }
            }
        }

        return b;
    }
    public static boolean Mst(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("While") || tokens.get(index).get(0).matches("If") || tokens.get(index).get(0).matches("Do")
                || tokens.get(index).get(0).matches("Return") || tokens.get(index).get(0).matches("For") || tokens.get(index).get(0).matches("Try")
                || tokens.get(index).get(0).matches("Throw") || tokens.get(index).get(0).matches("Switch") || tokens.get(index).get(0).matches("Break")
                || tokens.get(index).get(0).matches("Continue") || tokens.get(index).get(0).matches("ArrayList") || tokens.get(index).get(0).matches("Final")
                || tokens.get(index).get(0).matches("DataType") || tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super")
                || tokens.get(index).get(0).matches("Identifier")){

            if (Sst()){
                if (Mst()){
                    b = true;
                }
            }

        }

        else if (tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("}")){
            b = true;
        }
        return b;
    }

    public static boolean IfElse(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("If")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (Oe()){
                    if (tokens.get(index).get(0).matches("\\)")){
                        index++;
                        if (Body()){
                            if (OElse()){
                                b = true;
                            }
                        }
                    }
                }
            }
        }

        return b;
    }



    public static boolean OElse(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Else")){
            index++;
            if (Body()){
                b = true;
            }
        }
        return b;
    }
    public static boolean DoWhile(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("Do")){
            index++;
            if (tokens.get(index).get(0).matches("\\{")){
                index++;
                if (Mst()){
                    if (tokens.get(index).get(0).matches("}")){
                        index++;
                        if (tokens.get(index).get(0).matches("While")){
                             index++;
                             if (tokens.get(index).get(0).matches("\\(")){
                                 index++;
                                 if (Oe()){
                                     if (tokens.get(index).get(0).matches("\\)")){
                                         index++;
                                          if (tokens.get(index).get(0).matches(";")){
                                              index++;
                                              b = true;
                                          }
                                     }
                                 }
                             }
                        }
                    }
                }
            }
        }
        return b;
    }

    public static boolean For(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("For")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (C1()){
                    if (C2()){
                        if (tokens.get(index).get(0).matches(";")){
                            index++;
                            if (C3()){
                                if (tokens.get(index).get(0).matches("\\)")){
                                    index++;
                                    if (Body()){
                                        b = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    public static boolean C1() {
        boolean b = false;
        if (tokens.get(index).get(0).matches("DataType")) {
            if (Dec()) {
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Identifier")) {
            if (AsgnSt()) {
                if (tokens.get(index).get(0).matches(";")) {
                    index++;
                    b = true;
                }
            }
        }
        else if (tokens.get(index).get(0).matches(";")) {
                index++;
            b = true;
            }
            return b;

    }
    public static boolean C2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (Oe()){
                b = true;
            }
        }

        else if (tokens.get(index).get(0).matches(";") ){
            b = true;
        }

        return b;
    }
    public static boolean C3(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Inc_Dec") || tokens.get(index).get(0).matches("This") ||  tokens.get(index).get(0).matches("Super")
        ||  tokens.get(index).get(0).matches("Identifier")){
         if (IncDecSt()){
             if ( tokens.get(index).get(0).matches(";")){
                 index++;
                 b = true;
             }
         }
        }
        else if ( tokens.get(index).get(0).matches(";")){
                index++;
                b = true;
        }

        return b;
    }
    public static boolean AsgnSt(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("This") ||  tokens.get(index).get(0).matches("Super") ||  tokens.get(index).get(0).matches("Identifier")){
            if (Th()){
                if ( tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (X()){
                        if (AsgnOp()){
                            if (Oe()){
                                b = true;
                            }
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean Th(){
        boolean b = false;
            if ( tokens.get(index).get(0).matches("This")){
                index++;
                if ( tokens.get(index).get(0).matches("\\.")){
                    index++;
                    b  = true;
                }
            }

            else if ( tokens.get(index).get(0).matches("Super")){
                index++;
                if ( tokens.get(index).get(0).matches(".")){
                    index++;
                    b  = true;
                }
            }

            else  if ( tokens.get(index).get(0).matches("Identifier")){
                b  = true;
            }
        return b;
    }
    public static boolean AsgnOp(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("=")){
            index++;
            b  = true;
        }
        else if ( tokens.get(index).get(0).matches("CA")){
            index++;
            b = true;
        }

        return b;
    }
    public static boolean IncDecSt(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("Inc_Dec")){
            index++;
            if (Th()){
                if ( tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (X()){
                        b = true;
                    }
                }
            }
        }

        else if ( tokens.get(index).get(0).matches("This") ||  tokens.get(index).get(0).matches("Super") ||  tokens.get(index).get(0).matches("Identifier")){
            if (Th()){
                if ( tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (X()){
                        if (tokens.get(index).get(0).matches("Inc_Dec")){
                            index++;
                            b = true;
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean X(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Pl()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    if (X1()){
                        b = true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if (X3()){
                b = true;
            }
        }

        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (X2()){
                        b = true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("MDM") || tokens.get(index).get(0).matches("PM") || tokens.get(index).get(0).matches("RO")
    || tokens.get(index).get(0).matches("AO") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("CA") || tokens.get(index).get(0).matches(";") ||
                tokens.get(index).get(0).matches(",") ||  tokens.get(index).get(0).matches("\\)") ||  tokens.get(index).get(0).matches("}") ||
                tokens.get(index).get(0).matches("]") ||  tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("Inc_Dec")){

                b = true;
        }
        return b;
    }
    public static boolean X1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if (X3()){
                b = true;
            }
        }

        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (X2()){
                        b  = true;
                    }
                }
            }
        }

        else if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (X()){
                b = true;
            }
        }
        return b;
    }
    public static boolean X2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if (X3()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (X2()){
                        b = true;
                    }
                }
            }
        }

        else if (tokens.get(index).get(0).matches("MDM") || tokens.get(index).get(0).matches("PM") || tokens.get(index).get(0).matches("RO")
                || tokens.get(index).get(0).matches("AO") || tokens.get(index).get(0).matches("OR")|| tokens.get(index).get(0).matches("CA") || tokens.get(index).get(0).matches(";") ||
                tokens.get(index).get(0).matches(",") ||  tokens.get(index).get(0).matches("\\)") ||  tokens.get(index).get(0).matches("}") ||
                tokens.get(index).get(0).matches("]") ||  tokens.get(index).get(0).matches("=") || tokens.get(index).get(0).matches("Inc_Dec")){
            b = true;
        }


        return b;
    }
    public static boolean X3(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Identifier")){
            index++;
            if (X()){
                b  = true;
            }
        }

        else if (tokens.get(index).get(0).matches("Get")){
            index++;
            if (tokens.get(index).get(0).matches("\\(")){
                index++;
                if (Oe()){
                    if (tokens.get(index).get(0).matches("\\)")){
                        index++;
                        if (X2()){
                            b = true;
                        }
                    }
                }
            }
        }

        return b;
    }
    public static boolean Pl(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (Oe()){
                if (PL2()){
                    b = true;
                }
            }
        }

        else if (tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")){
                b = true;
        }

        return b;
    }
    public static boolean PL2(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")){
            b = true;
        }
        if (tokens.get(index).get(0).matches(",")){
            index++;
            if (Oe()){
                if (PL2()){
                    b = true;
                }
            }
        }
        return b;
    }
    public static boolean Oe(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (Ae()){
                if (Oe_()){
                    b  = true;
                }
            }
        }
        return b;
    }
    public static boolean Oe_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("OR")){
            index++;
            if (Ae()){
                if (Oe_()){
                    b = true;
                }
            }
        }

        else if(tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
        || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";")){
            b = true;
        }
        return b;
    }
    public static boolean Ae(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (Re()){
                if (Ae_()){
                    b  = true;
                }
            }
        }
        return b;
    }
    public static boolean Ae_(){
        boolean b = false;
        if (tokens.get(index).get(0).matches("AO")){
            index++;
            if (Re()){
                if (Ae_()){
                    b = true;
                }
            }
        }

        else if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR")){
            b = true;
        }
        return b;
    }
    public static boolean Re(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (E()){
                if (Re_()){
                    b  = true;
                }
            }
        }
        return b;
    }
    public static boolean Re_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("RO")){
            index++;
            if (E()){
                if (Re_()){
                    b = true;
                }
            }
        }

        else if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")){
            b = true;
        }

        return b;
    }
    public static boolean E(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (T()){
                if (E_()){
                    b  = true;
                }
            }
        }

        return b;
    }
    public static boolean E_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("PM")){
            index++;
            if (T()){
                if (E_()){
                    b = true;
                }
            }
        }

        else if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")
        || tokens.get(index).get(0).matches("RO")){
            b = true;
        }
        return b;
    }
    public static boolean T(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("This") || tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Integer Constant")
                || tokens.get(index).get(0).matches("String Constant") || tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") ||tokens.get(index).get(0).matches("Identifier") || tokens.get(index).get(0).matches("Character Constant") ||
                tokens.get(index).get(0).matches("\\(") || tokens.get(index).get(0).matches("!") || tokens.get(index).get(0).matches("Inc_Dec")){
            if (F()){
                if (T_()){
                    b  = true;
                }
            }
        }

        return b;
    }
    public static boolean T_(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("MPM")){
            index++;
            if (F()){
                if (T_()){
                    b = true;
                }
            }
        }

        else if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")
                || tokens.get(index).get(0).matches("RO")|| tokens.get(index).get(0).matches("PM")){
            b = true;
        }

        return b;
    }


    public static boolean F(){
        boolean b = false;

        if ( tokens.get(index).get(0).matches("This") ||  tokens.get(index).get(0).matches("Super") || tokens.get(index).get(0).matches("Identifier")){
            if (Th()){
                if (tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (F1()){
                        b = true;
                    }
                }
            }
        }

        else if ( tokens.get(index).get(0).matches("Integer Constant") || tokens.get(index).get(0).matches("String Constant") ||
                tokens.get(index).get(0).matches("Float Constant") || tokens.get(index).get(0).matches("True")
                ||tokens.get(index).get(0).matches("False") || tokens.get(index).get(0).matches("Character Constant")){

            if (Const()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    b = true;
                }
            }
        }

        else  if (tokens.get(index).get(0).matches("!")){
            index++;
            if(F()){
                b = true;
            }
        }

        else if (tokens.get(index).get(0).matches("Inc_Dec")){
            index++;
            if (Th()){
                if (tokens.get(index).get(0).matches("Identifier")){
                    index++;
                    if (X()){
                        b = true;
                    }
                }
            }
        }
        return b;
    }

    public static boolean F1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("\\.")){
            index++;
            if (F1_()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Z1()){
                        b = true;
                    }
                }
            }
        }
        else if(tokens.get(index).get(0).matches("Inc_Dec")){
            index++;
            b  = true;
        }
        else if (tokens.get(index).get(0).matches("\\(")){
            index++;
            if (Pl()){
                if (tokens.get(index).get(0).matches("\\)")){
                    index++;
                    if (F2()){
                        b  = true;
                    }
                }

            }
        }
        else if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")
                || tokens.get(index).get(0).matches("RO")|| tokens.get(index).get(0).matches("PM") ||   tokens.get(index).get(0).matches("MPM") ){
                b = true;
        }

        return b;
    }
    public static boolean F1_(){
        boolean b = false;
        if ( tokens.get(index).get(0).matches("Get") ){
            index++;
            if ( tokens.get(index).get(0).matches("\\(") ){
                index++;
                if (Oe()){
                    if ( tokens.get(index).get(0).matches("\\)") ){
                        index++;
                        if (Z1()){
                            b  = true;
                        }
                    }
                }
            }
        }
        else if ( tokens.get(index).get(0).matches("Identifier") ){
            index++;
            b  = true;
        }
        return b;
    }
    public static boolean F2(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")
                || tokens.get(index).get(0).matches("RO")|| tokens.get(index).get(0).matches("PM") ||   tokens.get(index).get(0).matches("MPM") ){
            b = true;
        }

        else if (tokens.get(index).get(0).matches(".") || tokens.get(index).get(0).matches("\\[") ){
            if (Z()){
                b  = true;
            }
        }

        return b;
    }
    public static boolean Z(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(".") ){
            index++;
            if (F1_()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[") ){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]") ){
                    index++;
                    if (Z1()){
                        b = true;
                    }
                }
            }
        }

        return b;
    }
    public static boolean Z1(){
        boolean b = false;

        if (tokens.get(index).get(0).matches(".") ){
            index++;
            if (F1_()){
                b = true;
            }
        }
        else if (tokens.get(index).get(0).matches("\\[") ){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]") ){
                    index++;
                    if (Z1()){
                        b = true;
                    }
                }
            }
        }

        if (tokens.get(index).get(0).matches("Inc_Dec"))
        {
            index++;
            b = true;
        }

        if (tokens.get(index).get(0).matches(",") || tokens.get(index).get(0).matches("\\)") || tokens.get(index).get(0).matches("}")
                || tokens.get(index).get(0).matches("]") || tokens.get(index).get(0).matches(";") || tokens.get(index).get(0).matches("OR") || tokens.get(index).get(0).matches("AO")
                || tokens.get(index).get(0).matches("RO")|| tokens.get(index).get(0).matches("PM") ||   tokens.get(index).get(0).matches("MPM")){
            b = true;
        }
        return b;
    }
    public static boolean Const(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Integer Constant")){
            index++;
            b  = true;
        }
        if (tokens.get(index).get(0).matches("String Constant")){
            index++;
            b  = true;
        }
        if (tokens.get(index).get(0).matches("Float Constant")){
            index++;
            b  = true;
        }
        if (tokens.get(index).get(0).matches("True")){
            index++;
            b  = true;
        }
        if (tokens.get(index).get(0).matches("False")){
            index++;
            b  = true;
        }
        if (tokens.get(index).get(0).matches("Character Constant")){
            index++;
            b  = true;
        }


        return b;
    }


    public static boolean Array(){
        boolean b = false;

        if(tokens.get(index).get(0).matches("\\[")){
            index++;
            if (Oe()){
                if (tokens.get(index).get(0).matches("]")){
                    index++;
                    if (Array()){
                        b= true;
                    }
                }
            }
        }
        else if (tokens.get(index).get(0).matches("\\.") || tokens.get(index).get(0).matches("Inc_Dec") || tokens.get(index).get(0).matches("=") ||
                tokens.get(index).get(0).matches("CA") || tokens.get(index).get(0).matches("\\(")){
                b = true;
        }
        return b;
    }

    public static boolean AbsFinal(){
        boolean b = false;
            if (tokens.get(index).get(0).matches("Abstract")){
                index++;
                b = true;
            }
            else if (tokens.get(index).get(0).matches("Final")){
                index++;
                b = true;
            }
            else if (tokens.get(index).get(0).matches("Class") || tokens.get(index).get(0).matches("DataType") || tokens.get(index).get(0).matches("Identifier")
            || tokens.get(index).get(0).matches("ArrayList")){
                 b = true;
            }
        return b;
    }


    public static boolean Final(){
        boolean b = false;

        if (tokens.get(index).get(0).matches("Final")){
            index++;
            b= true;
        }
        if (tokens.get(index).get(0).matches("DataType")){
            b  = true;
        }
        return b;
    }























}

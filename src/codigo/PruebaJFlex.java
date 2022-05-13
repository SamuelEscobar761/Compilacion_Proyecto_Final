package codigo;

import java_cup.runtime.Symbol;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PruebaJFlex {
    private static String code = "#include <iostream>\n" +
            "#include <cmath>\n" +
            "int main() {\n";

    public static void main(String[] args) {
        // Asignación del nombre de archivo por defecto que usará la aplicación
        String archivo = "src/codigo/prueba.txt";
        analisisLexico(archivo);
        analisisSintactico(archivo);
        System.out.println(code);
        FileWriter w = null;
        PrintWriter pw;
        try{
            w = new FileWriter("PruebaJFlex.c");
            pw = new PrintWriter(w);
            pw.println(code);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (null != w)
                w.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    private static void analisisLexico(String archivo){
        try{
            // Se trata de leer el archivo y analizarlo en la clase que se ha creado con JFlex
            BufferedReader buffer = new BufferedReader(new FileReader(archivo));
            Lexer analizadorJFlex = new Lexer(buffer);

            while(true){

                // Obtener el token analizado y mostrar su información
                Tokens token = analizadorJFlex.yylex();

                if (token == null){
                    code += "}";
                    break;
                }
                System.out.println(token + ": " + analizadorJFlex.yytext());
                if(token.toString().equals("Identificador")){
                    code += "\tfloat " + analizadorJFlex.yytext() + " ";
                }else if(token.toString().equals("Igual")){
                    code += analizadorJFlex.yytext();
                }else if(token.toString().equals("Coseno")) {
                    code += " std::cos(";
                }else if(token.toString().equals("Seno")) {
                    code += " std::sin(";
                }else if(token.toString().equals("Tangente")) {
                    code += " std::tan(";
                }else if(token.toString().equals("Numero")){
                    code += analizadorJFlex.yytext() + ");\n";
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private static void analisisSintactico(String archivo){
        Sintax s = null;
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(archivo));
            s = new Sintax(new LexerCup(buffer));
            // Se trata de leer el archivo y analizarlo en la clase que se ha creado con JFlex

            s.parse();
            System.out.println("Analisis sintactico ejecutado correctamente");

        }
        catch (Exception e){
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + sym.right + 1 + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
        }
    }
}
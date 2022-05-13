package codigo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal {
    public static void main(String[] args) throws Exception {
        String ruta1 = "C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/Lexer.flex";
        String ruta2 = "C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/Sintax.cup"};
        generar(ruta1, ruta2, rutaS);
    }

    public static void generar(String ruta1, String ruta2, String[] rutaS) throws Exception {
        File archivo;
        archivo = new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);

        Path rutaSym = Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/sym.java");
        if(Files.exists(rutaSym)){
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/sym.java"),
                Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/sym.java")
        );
        Path rutaSintax = Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/Sintax.java");
        if(Files.exists(rutaSintax)){
            Files.delete(rutaSintax);
        }
        Files.move(
                Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/Sintax.java"),
                Paths.get("C:/Users/samue/IdeaProjects/Compilacion_Proyecto_Final/src/codigo/Sintax.java")
        );
    }
}

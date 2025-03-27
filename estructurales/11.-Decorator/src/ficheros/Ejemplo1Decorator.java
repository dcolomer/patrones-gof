package estructurales.decorator.ficheros;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.URL;

public class Ejemplo1Decorator {
	
   public static void main(String args[]) {
      if(args.length < 1) {
         System.err.println("Usar: " + "java Ejemplo1Decorator NombreFichero");
         System.exit(1);
      }      
      new Ejemplo1Decorator(args[0]);
   }
   
   public Ejemplo1Decorator(String filename) {
      try {
    	 URL url =  this.getClass().getResource(filename);
    	  
         //FileReader frdr = new FileReader(url.getFile());         
         //LineNumberReader lrdr = new LineNumberReader(frdr);      
         LineNumberReader lrdr = new LineNumberReader(new FileReader(url.getFile()));
         for(String linea; (linea = lrdr.readLine()) != null;) {
        	 // Mostrar numero de linea
            System.out.print(lrdr.getLineNumber() + ":\t");
            // Mostrar la linea
            mostrarLinea(linea);
         }
      }
      catch(java.io.FileNotFoundException fnfx) {
         fnfx.printStackTrace();
      }
      catch(java.io.IOException iox) {
         iox.printStackTrace();
      }
   }
   
   private void mostrarLinea(String s) {
      for (int c, i=0; i < s.length(); ++i) {
         c = s.charAt(i);
         if (c == '\t') {
        	 System.out.print("   ");
         } else {
        	 System.out.print((char)c);
         }
      }
      System.out.println();
   }
}
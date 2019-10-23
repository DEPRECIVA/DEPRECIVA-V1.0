package depreciva;

import java.util.ArrayList;

public class Multiplicacion {

    String n1, n2;
    int cantDecimales;

    public Multiplicacion() {

    }

    public Multiplicacion(String n1, String n2, int cantDecimales) {
        this.n1 = n1.replace(".", "");
        this.n2 = n2.replace(".", "");
        this.cantDecimales = cantDecimales;

    }

    String multiplicar() {
        ArrayList<String> productos = new ArrayList();
        String resultado = "", resAux = "";
        Suma r = new Suma();
        int dig = 0, dig1 = 0, dig2 = 0, res = 0, llevar = 0, cont = -1, cont2 = n2.length();

        for (int i = n2.length() - 1; i >= 0; i--) {

            cont++;

            for (int x = 0; x < cont; x++) {
                resAux += "0";
            }
            for (int j = n1.length() - 1; j >= 0; j--) {
                dig1 = Integer.parseInt(String.valueOf(n1.charAt(j)));
                dig2 = Integer.parseInt(String.valueOf(n2.charAt(i)));

                res = llevar + dig1 * dig2;
                dig = res % 10;
                llevar = res / 10;
                resAux += String.valueOf(dig);

            }

            resAux += String.valueOf(llevar);
            cont2--;
            for (int y = 0; y < cont2; y++) {
                resAux += "0";
            }
            for (int m = resAux.length() - 1; m >= 0; m--) {
                resultado += resAux.charAt(m);
            }

            llevar = 0;

            productos.add(resultado);

            resultado = "";
            resAux = "";

        }
        r.n1 = productos.get(0);

        for (int i = 1; i < n2.length(); i++) {

            r.n2 = productos.get(i);

            r.n1 = r.sumar();

        }
        resultado = r.n1;

        for (int i = 0; i < resultado.length(); i++) {
            resAux += resultado.charAt(i);
            if (i == resultado.length() - this.cantDecimales - 1) {
                resAux += ".";
            }

        }
        resultado = resAux;

        return resultado;
    }

}

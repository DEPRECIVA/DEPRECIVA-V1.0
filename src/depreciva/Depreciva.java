//DEPRECIVA : De precision variable es una calculadora con tal caracteristica  y  defectos , en busca de sentido.
//Autor: Vargas Juan José
//       Ramiro Murua
package depreciva;

import java.util.ArrayList;
import java.util.Scanner;

public class Depreciva {

    String n;
    int pocisiónPunto = -1, cantDecimales;
    static char operador;
    String numOrigin;  //usado para multiplicacion
    static String cifrasDecimales; //usada para la división 
    static int cont = 0;

    /*public static void main(String[] args) throws StringIndexOutOfBoundsException, InterruptedException {

        Depreciva n1 = new Depreciva();
        Depreciva n2 = new Depreciva();
        
      
        
        System.out.println("Ingrese el primer número (punto-> decimal '-+' signo) :");
        n1.ingresarNúmero();
        decorador("Filtrando", 800);
        System.out.println("n: " + n1.n);
        ingresarOperacion();

        System.out.println("Ingrese el segundo (punto-> decimal '-+' signo) :");
        n2.ingresarNúmero();
        decorador("Filtrando", 800);
        System.out.println("n: " + n2.n);
        if (operador == '/') {
            opcionesDivision(n1.n, n2.n);
        }

        System.out.println("");

        equilibrarPuntos(n1, n2);
        emparejarDecimales(n1, n2);

        System.out.println(n1.n);
        System.out.println(operador);
        System.out.println(n2.n);
        System.out.println("");

        
        System.out.println("Resultado:");
        System.out.println("");
        if (operador=='/')System.out.println("En algunas ocasiones la cantidad de decimales elegidos no es correcta");
        System.out.println(n1.operacionRelativa(n2));

    }*/
    
    public static void calcularTodo(Vista vistaprincipal) throws StringIndexOutOfBoundsException, InterruptedException {
        Depreciva n1 = new Depreciva();
        Depreciva n2 = new Depreciva();
        
        n1.ingresarNúmero(vistaprincipal.getValor1().getText());
        n2.ingresarNúmero(vistaprincipal.getValor2().getText());
        
        operador = vistaprincipal.getjComboBox1().getSelectedItem().toString().charAt(0);
        cifrasDecimales = vistaprincipal.getjComboBox2().getSelectedItem().toString();

        equilibrarPuntos(n1, n2);
        emparejarDecimales(n1, n2);
        
        String resul = "Resultado: " + n1.operacionRelativa(n2);
        vistaprincipal.getLabelResultado().setText(resul);
    }

    public void ingresarNúmero() {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        String aux = "";

        n = sc.nextLine().trim();

        for (int i = 0; i < n.length(); i++) {
            try {
                if (i == 0 && n.charAt(0) == '-') {  //Se respeta el signo en la primera posición
                    aux += "-";
                    continue;
                } else if (i == 0) {
                    aux += "+";
                }
                if (n.charAt(i) == '.' && !aux.contains(".")) { //Si pongo un punto después del decimal se acepta
                    aux += ".";
                    pocisiónPunto = aux.length() - 1;   //se descuenta la posición del signo
                    continue;
                }
                num = Integer.parseInt(String.valueOf(n.charAt(i)));
                aux += num;

            } catch (NumberFormatException e) {
                cont++;
            }
        }
        if (!n.contains(".")) {
            if (n.contains("-") && n.length() == 1) {
                n = "-0.";
                this.pocisiónPunto = 2;
            } else {
                n = aux + ".";
                this.pocisiónPunto = n.length() - 1;
            }
        } else {
            n = aux;
        }
        numOrigin = n;
    }
    
    public void ingresarNúmero(String numero) {
        int num = 0;
        String aux = "";

        n = numero;

        for (int i = 0; i < n.length(); i++) {
            try {
                if (i == 0 && n.charAt(0) == '-') {  //Se respeta el signo en la primera posición
                    aux += "-";
                    continue;
                } else if (i == 0) {
                    aux += "+";
                }
                if (n.charAt(i) == '.' && !aux.contains(".")) { //Si pongo un punto después del decimal se acepta
                    aux += ".";
                    pocisiónPunto = aux.length() - 1;   //se descuenta la posición del signo
                    continue;
                }
                num = Integer.parseInt(String.valueOf(n.charAt(i)));
                aux += num;

            } catch (NumberFormatException e) {
                cont++;
            }
        }
        if (!n.contains(".")) {
            if (n.contains("-") && n.length() == 1) {
                n = "-0.";
                this.pocisiónPunto = 2;
            } else {
                n = aux + ".";
                this.pocisiónPunto = n.length() - 1;
            }
        } else {
            n = aux;
        }
        numOrigin = n;
    }

    public void mostrarEstado() {
        System.out.println("NUMERO:  " + n);
        System.out.println("NEGATIVO  " + n.contains("-"));
        System.out.println("DECIMAL  " + n.contains("."));
        System.out.println("POCICION DEL PUNTO  " + pocisiónPunto);

        System.out.println("");
    }

    public static void decorador(String mensaje, int tiempo) throws InterruptedException {
        String limpiar = "\n\r";
        if (cont > 0) {
            String carga = "";
            System.out.print(mensaje);
            for (int i = 0; i < 3; i++) {
                limpiar += limpiar;
                carga += ".";
                Thread.sleep(tiempo);  //tiempo en milisegundos
                System.out.print(carga);
            }
        }
        cont = 0;
        System.out.println(limpiar);
    }

    public static void equilibrarPuntos(Depreciva n1, Depreciva n2) { //emparejar los puntos de forma vertical
        int apuntar = 0;

        String aux = "", signo = "";
        if (n1.pocisiónPunto > n2.pocisiónPunto) {

            apuntar = n1.pocisiónPunto - n2.pocisiónPunto;

            if (n2.n.charAt(0) == '-') {

                aux += "-";
                signo = aux;
            } else {

                aux += "+";
                signo = aux;
            }
            for (int i = 0; i < apuntar; i++) {
                aux += "0";
            }
            n2.n = n2.n.replace(signo, aux);
            aux = "";
        } else {

            apuntar = n2.pocisiónPunto - n1.pocisiónPunto;

            if (n1.n.charAt(0) == '-') {

                aux += "-";
                signo = aux;
            } else {

                aux += "+";
                signo = aux;
            }
            for (int i = 0; i < apuntar; i++) {
                aux += "0";
            }
            n1.n = n1.n.replace(signo, aux);

        }

    }

    public static void emparejarDecimales(Depreciva n1, Depreciva n2) {  //añadimos ceros atrás

        int cont = 0, cont2 = 0;
        String aux = "";
        boolean punto = false;
        for (int i = 0; i < n1.n.length(); i++) {
            if (n1.n.charAt(i) == '.') {
                punto = true;
            }
            if (punto) {
                cont++;
            }
        }
        n1.cantDecimales = cont - 1;

        punto = false;
        for (int i = 0; i < n2.n.length(); i++) {
            if (n2.n.charAt(i) == '.') {
                punto = true;
            }
            if (punto) {
                cont2++;
            }
        }
        n2.cantDecimales = cont2 - 1;

        if (cont > cont2) {

            for (int i = 0; i < (cont - cont2); i++) {
                aux += "0";
            }
            n2.n = n2.n + aux;
            aux = "";
        } else {

            for (int i = 0; i < (cont2 - cont); i++) {
                aux += "0";
            }
            n1.n = n1.n + aux;
        }

    }

    public static void ingresarOperacion() { //mas restrictivo, no filtra
        String operacion = "";

        do {
            try {
                System.out.println("¿Operación? (+,-,*,/)");
                operacion = new Scanner(System.in).nextLine().trim();

                if (operacion.charAt(0) == '-' || operacion.charAt(0) == '+' || operacion.charAt(0) == '*' || operacion.charAt(0) == '/') {
                    break;
                }
            } catch (Exception StringIndexOutOfBoundsException) {

            }
        } while (true);
        operador = operacion.charAt(0);

    }

    public static void opcionesDivision(String n, String n2) {
        if (n.equals(n2)) {
            return;    //si el dividendo y el divisor son iguales, no se pide cantidad de cifras
        }
        n2 = Depreciva.sacarPunto(n2);
        try {
            n2 = n2.substring(1, n2.length());
        } catch (StringIndexOutOfBoundsException e) {
            n2 = "0";
        }
        if (mayor(n2, "0") == 0) {
            return;
        }

        System.out.println("");

        do {

            System.out.println("Cantidad de Decimales ( * -> all(?  No muestra punto decimal)   ");
            cifrasDecimales = new Scanner(System.in).nextLine().trim();

            if (cifrasDecimales.equals("*")) {

                break;
            } else if (cifrasDecimales.contains("0") || cifrasDecimales.contains("1") || cifrasDecimales.contains("2") || cifrasDecimales.contains("3") || cifrasDecimales.contains("4") || cifrasDecimales.contains("5") || cifrasDecimales.contains("6") || cifrasDecimales.contains("7") || cifrasDecimales.contains("8") || cifrasDecimales.contains("9")) {

                break;
            }

        } while (true);
    }

    public String operacionRelativa(Depreciva n2) throws InterruptedException {
        String  aux1 = n.substring(1, n.length()), aux2 = n2.n.substring(1, n2.n.length()), result = "";
        boolean uno = false, dos = false;
        int auxCantDecimales = 0;

        if (n.contains("+") && n2.n.contains("+")) {  //Se elige el mayor entre dos positivos, n1 siempre será el mayor
            if (aux1.compareTo(aux2) < 0) {
                uno = false;
                dos = true;
            } else {
                uno = true;
                dos = false;
            }
        } else if (n.contains("-") && n2.n.contains("-")) {   //se elije el mayor entre dos negativos, el menor negativo
            if (aux1.compareTo(aux2) > 0) {
                uno = false;
                dos = true;
            } else {
                uno = true;
                dos = false;
            }
        } else if (n.contains("-") && n2.n.contains("+")) {
            if (aux1.compareTo(aux2) > 0) {
                uno = true;
                dos = false;
            } else if (aux1.compareTo(aux2) < 0) {
                uno = false;
                dos = true;
            }
        } else if (n.contains("+") && n2.n.contains("-")) {
            if (aux1.compareTo(aux2) > 0) {
                uno = true;
                dos = false;
            } else if (aux1.compareTo(aux2) < 0) {
                uno = false;
                dos = true;
            }
        } else {
            uno = true;
            dos = false;
        }

        if (this.cantDecimales >= n2.cantDecimales) {
            auxCantDecimales = this.cantDecimales; //para la resta
        } else {
            auxCantDecimales = n2.cantDecimales;
        }

//Combinaciones para suma
        if (n.contains("+") && operador == '+' && n2.n.contains("+")) {

            n = n.substring(1, n.length()); //se saca el signo
            n2.n = n2.n.substring(1, n2.n.length());
            Suma suma = new Suma(n, n2.n);
            result = "+" + suma.sumar();

        } else if (n.contains("-") && operador == '+' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            Suma suma = new Suma(n, n2.n); 
            result = "-"+suma.sumar();
            
        } else if (n.contains("+") && operador == '-' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            Suma suma = new Suma(n, n2.n);
            result = "+" + suma.sumar();
        } else if (n.contains("-") && operador == '-' && n2.n.contains("+")) {

            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            Suma suma = new Suma(n, n2.n);
             result = "-"+suma.sumar();
            //Combinaciones para resta
        } else if (n.contains("+") && operador == '-' && n2.n.contains("+")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            if (uno && dos == false) {
                Resta resta = new Resta(n, n2.n, auxCantDecimales);
                result = "+" + resta.restar();
            } else {
                Resta resta = new Resta(n2.n, n, auxCantDecimales);

                result = "-" + resta.restar();
            }

        } else if (n.contains("-") && operador == '-' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            if (uno && dos == false) {
                Resta resta = new Resta(n2.n, n, auxCantDecimales);
                result = "+" + resta.restar();
            } else if (uno == false && dos) {
                Resta resta = new Resta(n, n2.n, auxCantDecimales);

                result = "-" + resta.restar();
            }

        } else if (n.contains("+") && operador == '+' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            if (uno && dos == false) {
                Resta resta = new Resta(n, n2.n, auxCantDecimales);
                result = "+" + resta.restar();
            } else if (uno == false && dos) {
                Resta resta = new Resta(n2.n, n, auxCantDecimales);
                result = "-" + resta.restar();
            }
        } else if (n.contains("-") && operador == '+' && n2.n.contains("+")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            if (uno && dos == false) {
                Resta resta = new Resta(n, n2.n, auxCantDecimales);
                result = "-" + resta.restar();
            } else if (uno == false && dos) {
                Resta resta = new Resta(n2.n, n, auxCantDecimales);

                result = "+" + resta.restar();
            }

        }

        //Multiplicacion
        if (this.numOrigin.contains("-") && operador == '*' && n2.numOrigin.contains("-")) {
            this.numOrigin = this.numOrigin.substring(1, this.numOrigin.length());
            n2.numOrigin = n2.numOrigin.substring(1, n2.numOrigin.length());

            Multiplicacion multiplicacion = new Multiplicacion(this.numOrigin, n2.numOrigin, this.cantDecimales + n2.cantDecimales);

            result = "+" + multiplicacion.multiplicar();

        } else if (this.numOrigin.contains("+") && operador == '*' && n2.numOrigin.contains("-")) {
            this.numOrigin = this.numOrigin.substring(1, this.numOrigin.length());
            n2.numOrigin = n2.numOrigin.substring(1, n2.numOrigin.length());

            Multiplicacion multiplicacion = new Multiplicacion(this.numOrigin, n2.numOrigin, this.cantDecimales + n2.cantDecimales);

            result = "-" + multiplicacion.multiplicar();
        } else if (this.numOrigin.contains("-") && operador == '*' && n2.numOrigin.contains("+")) {
            this.numOrigin = this.numOrigin.substring(1, this.numOrigin.length());
            n2.numOrigin = n2.numOrigin.substring(1, n2.numOrigin.length());

            Multiplicacion multiplicacion = new Multiplicacion(this.numOrigin, n2.numOrigin, this.cantDecimales + n2.cantDecimales);

            result = "-" + multiplicacion.multiplicar();

        } else if (this.numOrigin.contains("+") && operador == '*' && n2.numOrigin.contains("+")) {
            this.numOrigin = this.numOrigin.substring(1, this.numOrigin.length());
            n2.numOrigin = n2.numOrigin.substring(1, n2.numOrigin.length());

            Multiplicacion multiplicacion = new Multiplicacion(this.numOrigin, n2.numOrigin, this.cantDecimales + n2.cantDecimales);

            result = "+" + multiplicacion.multiplicar();

        }

        //Division
        if (n.contains("+") && operador == '/' && n2.n.contains("+")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());

            División división = new División(n, n2.n, cifrasDecimales);

            result = "+" + división.dividir();

        } else if (n.contains("-") && operador == '/' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            División division = new División(n, n2.n, cifrasDecimales);

            result = "+" + division.dividir();
        } else if (n.contains("-") && operador == '/' && n2.n.contains("+")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            División division = new División(n, n2.n, cifrasDecimales);

            result = "-" + division.dividir();
        } else if (n.contains("+") && operador == '/' && n2.n.contains("-")) {
            n = n.substring(1, n.length());
            n2.n = n2.n.substring(1, n2.n.length());
            División división = new División(n, n2.n, cifrasDecimales);

            result = "-" + división.dividir();
        }

        return result;
    }

    public static String sacarPunto(String n) {
        String resAux = "";
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '.') {
                continue;
            }
            resAux += n.charAt(i);

        }
        n = resAux;

        return n;

    }

    public static String zeroZerosDelanteros(String n) {

        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '0') {
                n = n.substring(i, n.length());
                break;
            }

        }

        return n;
    }

    public static int mayor(String comparar, String con) {  //rellena y compara cual es el mayor(a comparar,con el que se compara)
        int cont = 0, cont2 = 0, tam = 0;
        boolean uno = false, dos = false;
        comparar = Depreciva.sacarPunto(comparar);
        con = Depreciva.sacarPunto(con);
        if (comparar.length() > con.length()) {
            tam = comparar.length() - con.length();
            uno = true;
            dos = false;
        } else if (comparar.length() < con.length()) {

            tam = con.length() - comparar.length();
            dos = true;
            uno = false;
        }

        if (uno) {
            for (int i = 0; i < tam; i++) {
                con = "0" + con;
            }
        } else if (dos) {
            for (int i = 0; i < tam; i++) {
                comparar = "0" + comparar;
            }
        }

        for (int i = 0; i < comparar.length(); i++) {
            if (Integer.parseInt(String.valueOf(comparar.charAt(i))) > Integer.parseInt(String.valueOf(con.charAt(i)))) {
                cont++;
                break;
            } else if (Integer.parseInt(String.valueOf(comparar.charAt(i))) < Integer.parseInt(String.valueOf(con.charAt(i)))) {
                cont2++;
                break;
            } else {
                cont = 0;
                cont2 = 0;
            }

        }
        if (cont > cont2) {
            return 1;
        } else if (cont2 > cont) {
            return -1;
        } else {
            return 0;
        }

    }

    public static String alinear(String comparar, String con) { //al de longitud mas pequeña lo rellena con ceros delanteros
        int tam = 0;
        boolean uno = false, dos = false;
        comparar = Depreciva.sacarPunto(comparar);
        con = Depreciva.sacarPunto(con);
        if (comparar.length() > con.length()) {
            tam = comparar.length() - con.length();
            uno = true;
            dos = false;
        } else if (comparar.length() < con.length()) {

            tam = con.length() - comparar.length();
            dos = true;
            uno = false;
        }

        if (uno) {
            for (int i = 0; i < tam; i++) {
                con = "0" + con;
            }
        } else if (dos) {
            for (int i = 0; i < tam; i++) {
                comparar = "0" + comparar;
            }
        }

        return comparar;

    }
}

/*qcd
    public void zeroZeros() {
        String aux = "";
        int cont = 0;

        for (int i = 0; i < n.length(); i++) {

            if (Integer.parseInt(String.valueOf(n.charAt(i))) == 0) {
                aux += "0";
                cont++;

            }
            if (Integer.parseInt(String.valueOf(n.charAt(i))) > 0) {

                break;
            }

        }
        n = n.replaceFirst(aux, "");
        aux = "";
        if (punto) {
            for (int i = n.length() - 1; i >= 0; i--) {
                aux += n.charAt(i);
            }

            n = aux;
            aux = "";

            for (int i = 0; i < this.pocisiónPunto; i++) {

                if (Integer.parseInt(String.valueOf(n.charAt(i))) == 0) {
                    aux += "0";
                    System.out.println("a");
                }
                if (Integer.parseInt(String.valueOf(n.charAt(i))) > 0) {
                    System.out.println("b");
                    break;
                }

            }
            n = n.replaceFirst(aux, "");

            aux = "";
            for (int i = n.length() - 1; i >= 0; i--) {
                aux += n.charAt(i);
            }
            if (this.pocisiónPunto == 0) {
                this.pocisiónPunto = 0;
            } else {
                this.pocisiónPunto = this.pocisiónPunto - cont;+

            }

            n = aux;

        }
    }
 */

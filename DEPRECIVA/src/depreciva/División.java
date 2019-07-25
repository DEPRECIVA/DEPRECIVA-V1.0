package depreciva;

import java.util.ArrayList;

public class División {

    String n1, n2;
    static String cifrasDecimales;

    public División() {

    }

    public División(String n1, String n2, String cifrasDecimales) {
        this.n1 = n1;
        this.n2 = n2;
        División.cifrasDecimales = cifrasDecimales;

    }

    public String dividir() throws InterruptedException {

        String auxN2 = n2;
        if (n1.compareTo(n2) == 0) {                  //1- dividendo y divisor son iguales

            return "1.";

        } else if (Depreciva.mayor(auxN2, "0") == 0) {               //2- si el divisor es cero

            return "Error!  No se puede dividir por cero (aún(?)";

        } else {

            boolean marca = false, punto2 = false, punto = false, llave2 = false, casinfin = false; //casi sin fin
            String cociente = "", aux = "", resultado = "", divisor = "", dividendo = "", resAux = "", acomodar = "", acomodar2 = "";
            int cont = 0, demora = 0;
            ArrayList<String> múltiplos = new ArrayList();
            Multiplicacion m = new Multiplicacion();
            Resta r = new Resta(), r2 = new Resta();
            Suma s = new Suma();

            dividendo = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(n1));
            divisor = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(n2));

            if (n1.compareTo(n2) > 0) {                     //3- si el dividendo es mayor que el divisor
                punto = true;
                for (int i = dividendo.length() - divisor.length() - 2; i >= 0; i--) {
                    divisor += "0";
                }

                resAux = divisor + "0";

                if (Depreciva.mayor(dividendo, resAux) == 1) {

                    divisor = resAux;
                } else if (Depreciva.mayor(dividendo, resAux) == 0) { //para 100/10 por ejemplo, sino daria 9
                    divisor = resAux;
                }

            } else if (n1.compareTo(n2) < 0) {                  //4- si es menor

                punto2 = true;

            }

            m.n1 = divisor;

            for (int i = 0; i < 10; i++) {                //creamos los multiplos del 1 al 9, sin puntos 
                m.n2 = String.valueOf(i);
                múltiplos.add(Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(m.multiplicar())));       //se le saca el punto

            }
            r.n1 = dividendo;

            if (cifrasDecimales.equals("*")) {
                casinfin = true;
            } else {
                s.n1 = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(cifrasDecimales));
                s.n2 = Depreciva.alinear("1", s.n1);

                cifrasDecimales = s.sumar();
            }

            cifrasDecimales = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(cifrasDecimales));  //era necesario usar ciertas funciones porque se pasaba un numero
            String divisorUno = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(n2));
            for (;;) {
                if (divisorUno.equals("1")) { // 5- si el divisor es 1 devuelve el dividendo
                    resultado = Depreciva.sacarPunto(n1);
                    break;
                }
                demora++;
                if (punto2 && llave2) {
                    resultado += ".";
                    punto2 = false;

                }
                llave2 = true;
                cont++;

                if (casinfin == false) {
                    if (Depreciva.mayor(cifrasDecimales, "0") == 0) {
                        break;

                    } else {

                        r2.n1 = Depreciva.zeroZerosDelanteros(cifrasDecimales);
                        aux = Depreciva.alinear("1", cifrasDecimales);
                        r2.n2 = aux;
                        cifrasDecimales = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(r2.restar()));

                    }

                }
                for (int j = 0; j < múltiplos.size(); j++) {

                    if (Depreciva.mayor(múltiplos.get(j), r.n1) <= 0) {

                        r.n2 = múltiplos.get(j);
                        cociente = String.valueOf(j);

                    }

                }

                acomodar = Depreciva.alinear(r.n1, r.n2);
                acomodar2 = Depreciva.alinear(r.n2, r.n1);

                r.n1 = acomodar;
                r.n2 = acomodar2;
                // System.out.println("dividendo " + r.n1 + " multiplo " + r.n2 + " resta " + r.restar() + " cociente " + cociente);
                if (Depreciva.mayor(r.restar(), r.n2) < 0) {

                    r.n1 = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(r.restar()) + "0");
                    r.n2 = "0" + r.n2;

                } else {
                    marca = true;
                }

                if (marca) {
                    r.n1 = Depreciva.zeroZerosDelanteros(Depreciva.sacarPunto(r.restar()) + "0");
                    r.n2 = "0" + r.n2;

                    marca = false;
                }

                resultado += cociente;

                if (cont % 1 == 0 && casinfin) {
                    Thread.sleep(300);
                    System.out.print(cociente); //si se habilitó la opcion de casi infinitos decimales, esto mostrara decimal a decimal cada 300 milisegundos
                    cont = 0;
                }
                if (demora % 1000 == 0) {
                    System.out.println("");
                    System.out.println("Esperez...");

                    System.out.println("");
                }

            }
            System.out.println("");

            if (punto) {
                resultado = colocarPuntoDividendoMayor(resultado, n1, n2);
            }

            return resultado;
        }
    }

    public static String colocarPuntoDividendoMayor(String resultado, String n1, String n2) {
        String aux = "";

        int tam = 0, dig = 0, dig2 = 0;

        n1 = Depreciva.sacarPunto(Depreciva.zeroZerosDelanteros(n1));
        n2 = Depreciva.sacarPunto(Depreciva.zeroZerosDelanteros(n2));
        tam = n1.length();
        if (n1.length() == n2.length()) {               //99994 / 9993 = 1.
            for (int i = 0; i < resultado.length(); i++) {
                if (i == 1) {
                    aux += ".";
                }
                aux += resultado.charAt(i);
            }
            resultado = aux;
        } else {

            dig = Integer.parseInt(String.valueOf(n1.charAt(0)));
            dig2 = Integer.parseInt(String.valueOf(n2.charAt(0)));
            if (dig2 > dig) { //si el primer digito del divisor en la primer posición es mayor al del divisor se descuenta 1 y asi de 1 en 1

                tam--;
                for (int j = 0; j < n2.length() - 1; j++) {
                    tam--;
                }
                for (int j = 0; j < resultado.length(); j++) {
                    if (j == tam) {
                        aux += ".";
                    }
                    aux += resultado.charAt(j);
                }
                resultado = aux;
            } else if (dig2 < dig) { //si el digito es menor en la primera posición no se descuenta esa posicion, si es igual tampoco

                for (int j = 0; j < n2.length() - 1; j++) {
                    tam--;
                }
                for (int j = 0; j < resultado.length(); j++) {
                    if (j == tam) {
                        aux += ".";
                    }
                    aux += resultado.charAt(j);
                }
                resultado = aux;

            } else { //si dig y dig2 posicion 0 son iguales

                for (int i = 1; i < n2.length(); i++) {

                    dig = Integer.parseInt(String.valueOf(n1.charAt(i)));
                    dig2 = Integer.parseInt(String.valueOf(n2.charAt(i)));

                    if (dig2 > dig) { //si el segundo digito es mayor a su equivalente con el dividendo se restan 2, y 1 en 1 en los isguientes

                        tam -= 2;
                        for (int k = 0; k < n2.length() - 4; k++) {
                            tam--;
                        }
                        for (int j = 0; j < resultado.length(); j++) {
                            if (j == tam) {
                                aux += ".";
                            }
                            aux += resultado.charAt(j);
                        }
                        resultado = aux;

                        break;
                    } else if (dig2 < dig) { // si aparece un digito menor, ya que no se va a restar 2 en ninguna parte, va de 1 en 1

                        tam--;
                        for (int k = 0; k < n2.length() - 2; k++) {
                            tam--;
                        }
                        for (int j = 0; j < resultado.length(); j++) {
                            if (j == tam) {
                                aux += ".";
                            }
                            aux += resultado.charAt(j);
                        }
                        resultado = aux;

                        break;
                    } else {        //Si el segundo digito es igual y el siguiente es mayor, primero se descuenta 1 , despues los 2, y va de 1 en 1 entra al otro if

                        tam--;
                        if (i == n2.length() - 1) {
                            for (int j = 0; j < resultado.length(); j++) {
                                if (j == tam) {
                                    aux += ".";
                                }
                                aux += resultado.charAt(j);
                            }
                            resultado = aux;

                        }
                    }
                }

            }

        }

        return resultado;
    }
}

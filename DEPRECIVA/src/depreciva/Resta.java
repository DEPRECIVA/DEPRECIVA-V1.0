package depreciva;

import java.util.LinkedList;

public class Resta {

    String n1, n2;
    int pocisiónPunto;

    public Resta(String n1, String n2, int pocisiónPunto) {
        this.n1 = n1;
        this.n2 = n2;
        this.pocisiónPunto = pocisiónPunto;
    }

    public Resta() {

    }

    String restar() {

        String resultado = "", resAux = "";
        LinkedList<Integer> num = new LinkedList(), num2 = new LinkedList(), r = new LinkedList();
        int dig = 0, dig1 = 0, dig2 = 0;
        n1 = Depreciva.sacarPunto(n1);
        n2 = Depreciva.sacarPunto(n2);
        for (int i = 0; i < n1.length(); i++) {

            num.add(Integer.parseInt(String.valueOf(n1.charAt(i))));
            num2.add(Integer.parseInt(String.valueOf(n2.charAt(i))));

        }

        for (int i = n1.length() - 1; i >= 0; i--) {
            dig1 = num.get(i);
            dig2 = num2.get(i);

            if (dig1 < dig2) {
                for (int j = i - 1; j >= 0; j--) {
                    if (num.get(j) > 0) {
                        num.set(j, num.get(j) - 1);
                        dig1 += 10;
                        dig = dig1 - dig2;

                        for (int k = i - 1; k > 0; k--) {
                            if (k == j) {
                                break;
                            }
                            if (num.get(k) == 0) {

                                num.set(k, 9);

                            }
                        }

                        break;
                    }
                }

            } else {
                dig = dig1 - dig2;
            }

            resAux += String.valueOf(dig);

        }
        for (int i = resAux.length() - 1; i >= 0; i--) {
            resultado += resAux.charAt(i);
        }
        resAux = "";
        for (int i = 0; i < resultado.length(); i++) {
            resAux += resultado.charAt(i);
            if (i == resultado.length() - this.pocisiónPunto - 1) {
                resAux += ".";
            }

        }
        resultado = resAux;

        return resultado;
    }

}

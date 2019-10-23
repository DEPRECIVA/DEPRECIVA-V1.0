package depreciva;

public class Suma {

    String n1, n2;
public Suma(){
    
}
    public Suma(String n1, String n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    String sumar() {
        String resultado = "", resto = "";
        int aux = 0, aux2 = 0, dig1 = 0, dig2 = 0;
        
        for (int i = n1.length() - 1; i >= 0; i--) {

            if (i > 0 && n1.charAt(i) == '.') {

                resultado += ".";
                continue;
            }
            dig1 = Integer.parseInt(String.valueOf(n1.charAt(i)));
            dig2 = Integer.parseInt(String.valueOf(n2.charAt(i)));
            aux2 = aux + dig1 + dig2;

            if (aux2 > 9) {
                aux2 = aux2 % 10;
                aux = 1;
                resto = String.valueOf(aux2);
                resultado += resto;
                if (i == 0) {
                    resultado += "1";

                }
            } else {
                aux = 0;
                resto = String.valueOf(aux2);
                resultado += resto;

            }

        }

        resto = "";

        for (int i = resultado.length() - 1; i >= 0; i--) {

            resto += resultado.charAt(i);

        }

        resultado = resto;
        return resultado;
    }

}

package acc.br.Desafio.FullStack;

import acc.br.Desafio.FullStack.utils.ValidaCNPJ;

public class testCNPJ {

    public static void main(String[] args) {

        boolean verdade = ValidaCNPJ.isCNPJ("78.433.263/0001-81");

        System.out.println(ValidaCNPJ.imprimeCNPJ("78.433.263/0001-81"));
    }
}

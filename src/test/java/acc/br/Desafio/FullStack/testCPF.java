package acc.br.Desafio.FullStack;

import acc.br.Desafio.FullStack.utils.ValidaCPF;

public class testCPF {
    public static void main(String[] args) {
        Boolean verdade = ValidaCPF.isCPF("07154501405");
        if(verdade)System.out.println(ValidaCPF.imprimeCPF("07154501405"));
    }
}

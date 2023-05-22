package acc.br.Desafio.FullStack;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class testCalcularIdade {

    public static void main(String[] args) {

        String dataNascimentoString = "2001-05-10";

        // Formato da data
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Converte a String para LocalDate
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatoData);

        // Data atual
        LocalDate dataAtual = LocalDate.now();

        // Calcula a diferença entre a data atual e a data de nascimento
        Period periodo = Period.between(dataNascimento, dataAtual);

        // Obtém a idade
        int idade = periodo.getYears();

        // Exibe a idade
        System.out.println("A idade é: " + idade + " anos" + dataAtual.toString());
    }
}

package com.example.iniflex.principal;

import com.example.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3.1 - Inserir todos os funcionarios na mesma ordem da tabela
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        IO.println("Funcionarios inseridos com sucesso! " + funcionarios.size());

        // 3.2 - Remove "João" da lista de funcionarios
        funcionarios.removeIf(funcionario -> funcionario.getName().equals("João") && funcionario.getFuncao().equals("Operador"));
        IO.println("Funcionarios após remoção " + funcionarios.size());

        // 3.3 - Imprime no console os funcionarios e suas informações formatadas
        for (Funcionario funcionario : funcionarios) {
            IO.println("Nome = " + funcionario.getName());
            IO.println("Data de Nascimento = " + formatoData.format(funcionario.getDataNascimento()));
            IO.println("Salario " + formatoMoeda.format(funcionario.getSalario()));
            IO.println("Função = " + funcionario.getFuncao());
            IO.println("--------------------------------");
        }

        // 3.4 - Atualiza valor de salario em +10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioReajustado = funcionario.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_EVEN);
            funcionario.setSalario(salarioReajustado);
            IO.println("Salario " + formatoMoeda.format(funcionario.getSalario()));
        }

        // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprime os funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            IO.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> IO.println(" - " + funcionario.getName()));
        });

        // 3.8 - Imprime aniversariantes do mês 10 e 12
        for (Funcionario funcionario : funcionarios) {
            int mesNascimento = funcionario.getDataNascimento().getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                IO.println("Nome = " + funcionario.getName() + " - Data de Nascimento = " + mesNascimento);
            }
        }

        // 3.9 - Imprime o funcionário mais velho
        Funcionario funcionarioMaisVelho = funcionarios.get(0);
        for (int i = 1; i < funcionarios.size(); i++) {
            Funcionario funcionarioAtual = funcionarios.get(i);

            if (funcionarioAtual.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = funcionarioAtual;
            }
        }
        int idadeFuncionarioMaisVelho = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        IO.println("Funcionário mais velho: " + funcionarioMaisVelho.getName() + " - Idade: " + idadeFuncionarioMaisVelho);

    }

}


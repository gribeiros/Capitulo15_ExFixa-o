package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		System.out.print("Caminho da pasta:/home/gustavo/Documentos/teste.csv" + "\n\n");
		String pasta = "//home//gustavo//Documentos//teste.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(pasta))) {
			List<Funcionario> list = new ArrayList<>();
			String linha = br.readLine();

			while (linha != null) {

				String[] arquivo = linha.split(",");
				String nome = arquivo[0];
				String email = arquivo[1];
				Double valor = Double.parseDouble(arquivo[2]);

				list.add(new Funcionario(nome, email, valor));

				linha = br.readLine();
			}
			System.out.print("Valor do salario para buscar:");
			int v = sc.nextInt();
			System.out.println();
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> sList = list.stream().filter(p -> v < p.getSalario()).map(p -> p.getEmail()).sorted(comp)
					.collect(Collectors.toList());

			sList.forEach(System.out::println);

			double soma = list.stream().filter(p -> p.getNome().toUpperCase().charAt(0) == 'M').map(x -> x.getSalario()).reduce(0.0,
					(x, y) -> x + y);

			System.out.println("\n"+"Soma dos salarios das pessoas que começam com (M): " + String.format("%.2f", soma));

		} catch (NullPointerException e) {
			System.out.println("Erro:" + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Erro:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro:" + e.getMessage());
		}
		sc.close();
	}

}

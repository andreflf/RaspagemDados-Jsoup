package com.Jsoup.raspagem;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		
		String url = JOptionPane.showInputDialog("Digte a url", "https://");
		
		String siteG1 = "https://g1.com.br";
		String siteUol = "https://uol.com.br";
		
		Integer opcao = 0;
		
		while(opcao != 9) {
		opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção:"
				 + "\n1 - Mudar site"
				 + "\n2 - Buscar Links"
				 + "\n3 - Buscar Parágrafo"
				 + "\n4 - Buscar Notícias do G1"
				 + "\n5 - Buscar Notícias do Uol"
				 + "\n9 - Sair"));
		
		//implemtar caso nao insiram nenhum valor
//		if(opcao == null)
//			JOptionPane.showMessageDialog(null, "Digite uma das opções.");
		
		switch(opcao) {
		case 1:
			url = JOptionPane.showInputDialog("Digte a url", url); //trás a url anteriormente digitada para editar
			break;
		case 2:
			String assunto = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todos os links basta deixar em branco.");
			Consultas.buscarLinks(url, assunto);
			break;
		case 3:
			Consultas.buscarParagrafos(url);
			break;
		case 4:
			String assuntoG1 = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todos os links basta deixar em branco.");
			Consultas.buscarNoticiasG1(siteG1, assuntoG1);
			break;
		case 5:
			String assuntoUol = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todos os links basta deixar em branco.");
			Consultas.buscarNoticiasUol(siteUol, assuntoUol);
			break;
		case 9:
			System.exit(0);
			break;
			default:
				JOptionPane.showMessageDialog(null, "Opcão Inválida.");
		}
		}
	}
}

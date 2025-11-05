package com.Jsoup.raspagem;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		
		String url = JOptionPane.showInputDialog("Digte a url", "https://");
		
		Integer opcao = 0;
		
		while(opcao != 9) {
		opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção:"
				 + "\n1 - Mudar site"
				 + "\n2 - Buscar Links"
				 + "\n3 - Buscar Parágrafo"
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
		case 9:
			System.exit(0);
			break;
			default:
				JOptionPane.showMessageDialog(null, "Opcão Inválida.");
		}
		}
	}
}

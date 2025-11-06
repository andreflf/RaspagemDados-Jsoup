package com.Jsoup.raspagem;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		String site="";
		String nomeArquivoCSV="";
		Integer opcao = 0;
		
		while(opcao != 9) {
		opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção:"
				 + "\n1 - Digite um site"
				 + "\n2 - Buscar Notícias do G1"
				 + "\n3 - Buscar Notícias do Uol"
				 + "\n9 - Sair"));
		
		//implemtar caso nao insiram nenhum valor
//		if(opcao == null)
//			JOptionPane.showMessageDialog(null, "Digite uma das opções.");
		
		switch(opcao) {
		case 1:
			String url = JOptionPane.showInputDialog("Digte a url:", "https://"); 
			//estrutura de sub menu da opçao digitar site
			while(opcao != 4) {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Site atual: " + url
						+ "\nDigite a opção:"
						+ "\n1 - Mudar Site atual" 
						+ "\n2 - Buscar Links do site"
						 + "\n3 - Buscar Parágrafos do site"
						 + "\n4 - Voltar"));
				
				switch(opcao) {
				case 1:
					url = JOptionPane.showInputDialog("Digte a url:", url); //trás a url anteriormente digitada para editar
					break;
					
				case 2:
					String assunto = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todos os links basta deixar em branco.");
					Consultas.buscarLinks(url, assunto);
					break;
					
				case 3:
					Consultas.buscarParagrafos(url);
					break;
					
				case 4: //faz apenas o break para voltar ao menu anterior
					break;
					default:
						JOptionPane.showMessageDialog(null, "Opcão Inválida.");
				}
			}
			break;
			
		case 2:
				site = "https://g1.com.br";
		
		while(opcao != 3) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(site
					+ "\nDigite a opção:"
					+ "\n1 - Buscar notícias mais atuais do G1" 
					+ "\n2 - Buscar notícias do G1 de forma paginada" 
					 + "\n3 - Voltar"));
			
			switch(opcao) {
			case 1:
				String assunto = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todas as notícias basta deixar em branco.");
				List<Noticia> noticiasMaisRecentes = Consultas.buscarNoticias(site, assunto);
				
				while(opcao != 2) {
					opcao = Integer.parseInt(JOptionPane.showInputDialog(site
							+ "\nDigite a opção:"
							+ "\n1 - Gerar arquivo .CSV ?" 
							 + "\n2 - Voltar"));
					
					switch(opcao) {
					case 1:
						nomeArquivoCSV = "noticias_G1.csv";
						Consultas.geraCSV(noticiasMaisRecentes, nomeArquivoCSV);
						break;
						
					case 2: //faz apenas o break para voltar ao menu anterior
						break;
						default:
							JOptionPane.showMessageDialog(null, "Opcão Inválida.");
					}
				}
				break;
				
			case 2: 
				int quantPaginas =  Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de páginas que deseja buscar: "));
				List<Noticia> noticiasPaginadas = Consultas.buscarNoticiasG1Paginado(quantPaginas);
				opcao = 0; //para entrar no sub menu
				while(opcao != 2) {
					opcao = Integer.parseInt(JOptionPane.showInputDialog(site
							+ "\nDigite a opção:"
							+ "\n1 - Gerar arquivo .CSV ?" 
							 + "\n2 - Voltar"));
					
					switch(opcao) {
					case 1:
						nomeArquivoCSV = "noticias_G1_paginada.csv";
						Consultas.geraCSV(noticiasPaginadas, nomeArquivoCSV);
						break;
						
					case 2: //faz apenas o break para voltar ao menu anterior
						break;
						default:
							JOptionPane.showMessageDialog(null, "Opcão Inválida.");
					}
				}
				
				break;
				
			case 3:
				break;//faz apenas o break para voltar ao menu anterior
				default:
					JOptionPane.showMessageDialog(null, "Opcão Inválida.");
			}
		}
				
			break;
		case 3:
			site = "https://uol.com.br";
			opcao = 0; //seta variável para entrar no sub menu por ser a mesma condicao do case e while
			while(opcao != 2) {
				opcao = Integer.parseInt(JOptionPane.showInputDialog(site
						+ "\nDigite a opção:"
						+ "\n1 - Buscar notícias mais atuais do Uol" 
						 + "\n2 - Voltar"));
				
				switch(opcao) {
				case 1:
					String assunto = JOptionPane.showInputDialog("Digite o assunto: \nobs: se quiser todas as notícias basta deixar em branco.");
					List<Noticia> noticiasMaisRecentes = Consultas.buscarNoticias(site, assunto);
					
					while(opcao != 2) {
						opcao = Integer.parseInt(JOptionPane.showInputDialog(site
								+ "\nDigite a opção:"
								+ "\n1 - Gerar arquivo .CSV ?" 
								 + "\n2 - Voltar"));
						
						switch(opcao) {
						case 1:
							nomeArquivoCSV = "noticias_UOL.csv";
							Consultas.geraCSV(noticiasMaisRecentes, nomeArquivoCSV);
							break;
							
						case 2: //faz apenas o break para voltar ao menu anterior
							break;
							default:
								JOptionPane.showMessageDialog(null, "Opcão Inválida.");
						}
					}
				case 2:
					break;//faz apenas o break para voltar ao menu anterior
					default:
						JOptionPane.showMessageDialog(null, "Opcão Inválida.");
				}
			}	
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

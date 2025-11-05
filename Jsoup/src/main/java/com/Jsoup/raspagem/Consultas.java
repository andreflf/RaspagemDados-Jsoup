package com.Jsoup.raspagem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //pra usar Document com Jsoup tem que ser o import da biblioteca do Jsoup (org.jsoup.nodes.Document) e nao o javax.swing.text.Document
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Consultas {

	public static void buscarLinks(String url, String assunto) {
		
		try {
			Document doc = Jsoup.connect(url).get(); //transforma a url em um Document
			Elements links = doc.select("a[href]"); //busca todos os links href do html da url e armazena em Elements
			for(Element elemento : links) {
				if(elemento.attr("href").contains(assunto)) {
					System.out.println("Link: " + elemento.attr("href")); //trás apenas os links desejados
				}
			}
			System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void buscarParagrafos(String url) {
		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements colecao = doc.select("p");
			for(Element paragrafo : colecao) {
				System.out.println("P: " + paragrafo.text());
			}
			
			System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void buscarNoticiasG1(String url, String assuntoG1) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements noticias = doc.select("a.feed-post-link"); //url e título estao entao do <a class=feed-post-link
			
			 for (Element noticia : noticias) {
				 if(noticia.text().contains(assuntoG1) || noticia.attr("href").contains(assuntoG1)) { //busca no título e no link da notícia
		            System.out.println("Título: " + noticia.text());
		            System.out.println("Link:   " + noticia.absUrl("href"));
		            System.out.println("----------------------------------");
				 }
		        }
			 System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void buscarNoticiasUol(String url, String assuntoUol) {
		try {
			Document doc = Jsoup.connect(url).get();
			Elements noticias = doc.select("a.hyperlink h3.title__element"); //acesso ao título das notícias que está em h3.title__element
			
			 for (Element noticia : noticias) {
				 
				 if(noticia.text().contains(assuntoUol)) { //busca no título da notícia
					
					 // sobe para o <a class="hyperlink"> onde tem o link
					  Element links = noticia.closest("a.hyperlink");
					  
		            System.out.println("Título: " + noticia.text());
		            System.out.println("Link:   " + links.absUrl("href"));
		            System.out.println("----------------------------------");
				 }
		        }
			 System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

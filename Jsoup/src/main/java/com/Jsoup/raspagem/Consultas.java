package com.Jsoup.raspagem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //pra usar Document com Jsoup tem que ser o import da biblioteca do Jsoup (org.jsoup.nodes.Document) e nao o javax.swing.text.Document
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Consultas {

	public static void buscarLinks(String url, String assunto) {
		
		try {
			Document doc = Jsoup.connect(url).get(); //transforma a url em um Document
			Elements links = doc.select("a[href]"); //busca todos os links href do html da ulr e armazena em Elements
			for(Element elemento : links) {
				//String conteudo = elemento.attr("href");
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
		
		
	}

}

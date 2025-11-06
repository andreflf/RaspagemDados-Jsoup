package com.Jsoup.raspagem;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //pra usar Document com Jsoup tem que ser o import da biblioteca do Jsoup (org.jsoup.nodes.Document) e nao o javax.swing.text.Document
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Consultas {
	

	public static Elements noticias;
	
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

	public static List<Noticia> buscarNoticias(String url, String assunto) {
		try {	
			List<Noticia> lista = new ArrayList<>(); //lista para armazenar as notícias
			Document doc = Jsoup.connect(url).get();
			
			if(url.contains("g1"))
			noticias = doc.select("a.feed-post-link"); //url e título estao dentro do <a class=feed-post-link na página do G1
			else
			noticias = doc.select("a.hyperlink h3.title__element"); //acesso ao título das notícias que está em h3.title__element na pág do Uol
			
			 for (Element noticia : noticias) {
				 if(noticia.text().contains(assunto) || noticia.attr("href").contains(assunto)) { //busca no título e no link da notícia
					 
					 String titulo = noticia.text(); //igual para o G1 e Uol
					 String link;
					 
					 if(url.contains("uol")) {
					 // sobe para o <a class="hyperlink"> onde tem os links na pág da Uol
						Element links = noticia.closest("a.hyperlink");
						link = links.absUrl("href"); 
					 }else
						 link = noticia.absUrl("href"); //links na pág do G1
					 
					 //Imprime no console e popula a lista
		            System.out.println("Título: " + titulo);
		            System.out.println("Link:   " + link);
		            System.out.println("----------------------------------");
		            lista.add(new Noticia (titulo, link));
				 }
		        }
			 System.out.println("\n");
			 return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void geraCSV(List<Noticia> noticias, String filename) throws Exception {
	    File f = new File(filename);
	    boolean existe = f.exists();


	    Path path = f.toPath();
	    try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
	            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
	    	
	    	 // BOM UTF-8 resolve problema de caracteres especiais no CSV
    	    bw.write("\uFEFF");
    	    
	    	if (!existe){
		        bw.write("titulo;link");
		        bw.newLine();
		    }

		    for (Noticia n : noticias){
		        bw.write("\"" + n.titulo.replace(";", ",") + "\";" + n.link);
		        bw.newLine();
		    }

		    System.out.println("\n\nArquivo gerado com sucesso!\n");
		    JOptionPane.showMessageDialog(null, "Arquivo " + filename + " gerado com sucesso"
		    		+ "\nna pasta do projeto.");
		    bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	    
	}
	

}

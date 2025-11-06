package com.Jsoup.raspagem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
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
	
	public static List<Noticia> buscarNoticiasG1Paginado(int qtdPaginas) throws IOException {
	    List<Noticia> lista = new ArrayList<>();

	    
	    //percorre a quantidade solicitada de páginas do G1
	    //url padrao é: https://g1.globo.com/ultimas-noticias/    https://g1.globo.com/ultimas-noticias/?page=2 ....
	    for (int i = 1; i <= qtdPaginas; i++) {
	        String url = "https://g1.globo.com/ultimas-noticias/";
	        if (i > 1) {
	            url += "?page=" + i; //incrementa o número da página
	        }

	        Document doc = Jsoup.connect(url).timeout(10000).get();
	        Elements noticias = doc.select("a.feed-post-link");

	        for (Element noticia : noticias) {
	            Element p = noticia.selectFirst("p");
	            if (p == null) 
	            	continue;

	            String titulo = p.text();
	            String link   = noticia.absUrl("href");

	            //Imprime no console e popula a lista
	            System.out.println("Título: " + titulo);
	            System.out.println("Link:   " + link);
	            System.out.println("----------------------------------");
	            lista.add(new Noticia(titulo, link));
	        }
	    }

	    return lista;
	}
	
	public static void geraCSV(List<Noticia> noticias, String nomeDoArquivo) throws Exception {
		
		File file = new File(nomeDoArquivo);// cria um objeto File que representa o arquivo no sistema de arquivos
	  
	    Path path = file.toPath(); // obtém o Path (NIO) correspondente ao File — usado pela API do pacote java.nio.file
	    
	    //Se quiser salvar em uma pasta específica:
	   // Files.createDirectories(Paths.get("data")); //criar uma pasta no diretório do projeto
	  //  Path path = Paths.get("data", filename); //criar o CSV dentro da pasta criada
	    
	    //abre um BufferedWriter usando Files.newBufferedWriter.
	    //try-with-resources garante que 'bw' será fechado automaticamente ao final do bloco.
	    try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
	            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) { //TRUNCATE_EXISTING sempre cria/sobrescreve o arquivo
	    	
	    	// escreve o BOM UTF-8 no início do arquivo, ajuda o Excel do Windows a reconhecer corretamente o encoding UTF-8.
    	    bw.write("\uFEFF");// resolve problema de caracteres especiais no CSV
    	    
    	   //escreve a linha de cabeçalho do CSV.
		        bw.write("Título da Notícia;Link");
		        bw.newLine();

	    	// percore a lista de notícias e escreve cada uma como uma linha do CSV.   
		    for (Noticia noticia : noticias){
		        bw.write("\"" + noticia.titulo.replace(";", ",") + "\";" + noticia.link); // troca ';' dentro do título por ',' para não quebrar o delimitador.
		        bw.newLine();
		    }

		    System.out.println("\n\nArquivo gerado com sucesso!\n");
		    JOptionPane.showMessageDialog(null, "Arquivo " + nomeDoArquivo + " gerado com sucesso"
		    		+ "\nna pasta do projeto.");
		    
		  //  bw.close(); // fecha explicitamente o writer (desnecessário aqui porque try-with-resources já fecha)
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	    
	}
	

}

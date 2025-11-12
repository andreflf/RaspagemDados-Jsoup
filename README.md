# 📰 Raspagem de Notícias com Jsoup

Projeto desenvolvido em **Java** utilizando a biblioteca **Jsoup** para realizar **web scraping** de notícias em portais brasileiros como **G1** e **UOL**, armazenando os dados em arquivos **CSV** e gerando uma **análise de frequência de palavras** (Word Count).

---

## 🚀 Funcionalidades

- 🔍 **Raspagem de dados (web scraping)** de uma página a escolha ou das páginas principais do G1 e UOL  
  - Captura **título** e **link** das matérias  
  - Permite coleta **paginada** (G1)  
- 📄 **Geração automática de arquivos CSV**  
  - Codificação **UTF-8 com BOM** para evitar problemas com acentuação  
  - Substituição de `;` por `,` para manter a integridade dos campos  
- 📊 **Análise de texto** (Word Count / TF-IDF simplificado)  
  - Conta a frequência de palavras nos títulos das notícias  
  - Remove **stopwords** (palavras não relevantes, como *esse, aquela, onde, isto, etc.*)
  - stopwords configuráveis via arquivo externo (stopwords.txt) 
  - Gera um **CSV com a frequência de cada termo**
- 🧹 Tratamento de duplicações e limpeza de texto (remoção de símbolos, pontuação, etc.)

---

## 🧠 Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|--------------|
| Linguagem | Java 17+ |
| Biblioteca de raspagem | [Jsoup](https://jsoup.org/) |
| Manipulação de arquivos | `java.nio.file`, `java.io` |
| Estruturas de dados | `List`, `Map`, `Set`, `Collections`, `JOptionPane` |
| IDE recomendada | Eclipse / IntelliJ IDEA |

---

```## 🧩 Estrutura do Projeto
RaspagemDados-Jsoup/
│
├── src/main/java/com/jsoup/raspagem/
│ ├── Main.java # Menu principal do programa usando JOptionPane
│ ├── Consultas.java # Métodos de raspagem, geração de CSVs, normalização etc.
│ ├── Noticia.java # Classe modelo para armazenar título e link
│
├── noticias_G1.csv # Arquivo CSV gerado com notícias do G1
├── noticias_G1_paginada.csv # Arquivo CSV gerado com notícias do G1 (paginada)
├── noticias_UOL.csv # Arquivo CSV gerado com notícias do UOL
├── Frequência_Noticias_Atuais_G1.csv # Frequência de palavras no G1
├── Frequencia_Noticias_Paginada_G1.csv# Frequência de palavras no G1 (paginada)
├── Frequência_Noticias_Atuais_UOL.csv # Frequência de palavras no UOL
└── README.md # Este arquivo


---

## ⚙️ Como Executar

1. **Clone o repositório**

   git clone https://github.com/andreflf/RaspagemDados-Jsoup.git
   cd RaspagemDados-Jsoup

2. Importe o projeto em sua IDE (STS, Eclipse ou IntelliJ)

3. Adicione a biblioteca Jsoup
Baixe o .jar em https://jsoup.org/download

4. Adicione ao classpath do projeto.

5. Execute o arquivo Main.java

6. Escolha a opção desejada no menu:
1 - Digite um site
2 - Buscar notícias do G1
3 - Buscar notícias do UOL
9 - Sair

7. Verifique os arquivos CSV gerados na pasta do projeto de acordo com a seleção realizada no menu.

8. 📈 Exemplo de Saída (Análise de Frequência)
Palavra	Ocorrências
brasil	7
governo	6
lula	5
corinthians	4
mulher	3
mariana	2

-------
🧪 Próximas Melhorias:

 Remover duplicatas de notícias (verificação por link)

 Implementar análise TF-IDF real

 Armazenar os dados em um banco (SQLite ou PostgreSQL)

 Criar visualização gráfica da frequência de palavras

 Adicionar interface web (Spring Boot + Thymeleaf)
-------
🧑‍💻 Autor
André Fialho
📧 andreferreira.lima@hotmail.com
📂 GitHub andreflf

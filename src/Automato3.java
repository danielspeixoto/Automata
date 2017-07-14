import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Automato2 {

    public static void main(String [] args) throws IOException{

        try {

            File f = new File("codfinal.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);

            Map<String, String> transicao = new HashMap<>();
            doc.getDocumentElement().normalize();

            //pegando numeros de estados (finais e iniciais) e os nomes dos estados

            NodeList n = doc.getElementsByTagName("structure");

            int numEstados = n.getLength()-1;

            System.out.println(numEstados);

            String estados = "";

            for(int i = 1;i < n.getLength();i++){

                if(i<n.getLength()-1) {
                    estados += doc.getElementsByTagName("name").item(i).getTextContent()+",";
                    //System.out.println(estados);
                }
                else {
                    estados += doc.getElementsByTagName("name").item(i).getTextContent();
                    //System.out.println(estados);

                }

            }
            System.out.println(estados);
            NodeList p = doc.getElementsByTagName("symbol");

            int numSimbolos = p.getLength();
            System.out.println(numSimbolos);

            String simbolos = "";

            for(int i = 0;i < p.getLength();i++){

                if(i < p.getLength()){
                    if(i == 0){
                        simbolos += "32"+",";
                    }
                    else{
                        String letter = (doc.getElementsByTagName("symbol").item(i).getTextContent());
                        simbolos += getChar(letter) + ",";
                    }
                }

                else {
                    simbolos += getChar(doc.getElementsByTagName("symbol").item(i).getTextContent());
                }

            }
            System.out.println(simbolos);

            NodeList q = doc.getElementsByTagName("structure");

            int numEstadosFinais = q.getLength()-2;
            System.out.println(numEstadosFinais);

            String estadosFinais = "";

            for(int i = 1;i < q.getLength();i++){
                if(doc.getElementsByTagName("name").item(i).getTextContent().equals("q0")){
                }
                if(i>1 && i<q.getLength()-1) {
                    estadosFinais+= doc.getElementsByTagName("name").item(i).getTextContent()+",";
                }
                else if(i == q.getLength()-1) {
                    estadosFinais+= doc.getElementsByTagName("name").item(i).getTextContent();
                }
            }
            System.out.println(estadosFinais);

            NodeList r = doc.getElementsByTagName("from");
            //String aux;
            int numTransicoes = r.getLength()/2;
            System.out.println("139");
		/*for(int i = 0;i < r.getLength()/2;i++){
			Node a = r.item(i);
			aux = a.getTextContent().trim();
			aux = aux.substring(0, 2);
			if(i==(r.getLength()/2)-1) {
				//System.out.print(aux);
			}
			else{
			//System.out.print(aux+",");
			}
		}*/
            //System.out.println();
            NodeList x = doc.getElementsByTagName("to");

            NodeList s = doc.getElementsByTagName("input");

            NodeList t = doc.getElementsByTagName("to");

            String aux;
            String aux3;
            int cont = 0;
            NodeList c = doc.getElementsByTagName("from");
            String resposta = "";
            for(int i = 0;i < r.getLength()/2;i++){
                Node a = x.item(i);
                Node y = c.item(cont);
                aux = a.getTextContent().trim();
                aux3 = y.getTextContent().trim();
                aux = aux.substring(0,2);
                aux3 = aux3.substring(0,2);

                Node b = s.item(cont);
                String [] simbolo = b.getTextContent().split(",");

                for(int j = 0;j < simbolo.length;j++){
                    simbolo[j] = String.valueOf(getChar(simbolo[j]));
                    transicao.put(simbolo[j], aux);
                    //System.out.println("de: " + aux3);
                    //System.out.println("lendo: "+simbolo[j]);
                    //System.out.println("vai para: "+transicao.get(simbolo[j]));
                    resposta += (aux3 + "," + simbolo[j] + "," + transicao.get(simbolo[j]) + "\n");
                }
                cont++;
            }
            System.out.println(resposta);
            String convert1 = Integer.toString(numEstados);
            String convert2 = Integer.toString(numSimbolos);
            String convert3 = Integer.toString(numEstadosFinais);
            String convert4 = Integer.toString(numTransicoes);
            PrintWriter pw = new PrintWriter("PATH.txt","UTF-8");
            pw.write(convert1);
            pw.println();
            pw.write(estados);
            pw.println();
            pw.write(convert2);
            pw.println();
            pw.write(simbolos);
            pw.println();
            pw.write(convert3);
            pw.println();
            pw.write(estadosFinais);
            pw.println();
            pw.write(convert4);
            pw.println();
            pw.write(resposta);
            pw.flush();
            pw.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static int getChar(String letter) {
        char readed;
        if(letter.equals(",")) {
            readed = '\n';
        } else if(letter.equals("")){
            readed = 32;
        } else {
            readed = (letter.toCharArray()[0]);
        }
        return readed;
    }
}

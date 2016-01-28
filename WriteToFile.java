package test1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class WriteToFile {
	private String path;
	private boolean append_to_file = false;
	
	public WriteToFile(String file_path){
		path = file_path;
	}
	
	public WriteToFile(String file_path, boolean append_value){
		path = file_path;
		append_to_file = append_value;
	}
	
	public void writeToFile( String textLine ) throws IOException {
		FileWriter write = new FileWriter( path , append_to_file);
		PrintWriter print_line = new PrintWriter( write );
	
		print_line.printf( "%s" + "%n" , textLine);
		print_line.close();
		
	}
	
	public void createFileHTML(String date) throws IOException {
		PrintStream write = new PrintStream( new File(path));
		write.println("<!DOCTYPE HTML><html><header><link href=\"test.css\" rel=\"stylesheet\"></header><body><div id=\"topbar\"><h2><img id=\"logo\" src=\"QA Logo.png\">  Screenshots taken on ");
		write.println(date);
		write.println("</h2></div><div id=\"images\">");
		write.println("</div></body></html>");
		write.close();
		
	}
	
	public void writeToFileCSV( String username, String password ) throws IOException {
		FileWriter write = new FileWriter( path , append_to_file);
		PrintWriter print_line = new PrintWriter( write );
		print_line.printf( "%s" + "%n" , username + "," + password);
		print_line.close();
		
	}
/*	
	public void createNewHtml(String date){
		
		StringBuilder htmlDoc = new StringBuilder();
		htmlDoc.append("<!DOCTYPE HTML><html><header><link href=\"test.css\" rel=\"stylesheet\"></header><body><div id=\"topbar\"><h2><img id=\"logo\" src=\"QA Logo.png\">  Screenshots taken on ");
		htmlDoc.append(date);
		htmlDoc.append("</h2></div><div id=\"images\"></div></body></html>");
		
		try {
			writeToFileHTML(htmlDoc.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/	
	public void insertNewLine(String fileName, String url){
		try{
			 File inFile = new File(path);
		     File outFile = new File("html.tmp");
		     
		     // input
		     FileInputStream fis  = new FileInputStream(path);
		     BufferedReader in = new BufferedReader
		         (new InputStreamReader(fis));

		     // output         
		     FileOutputStream fos = new FileOutputStream(outFile);
		     PrintWriter out = new PrintWriter(fos);

		     String thisLine = "";
		  
		     while ((thisLine = in.readLine()) != null) {

		       out.println(thisLine);
		       if(thisLine.contains("div id=\"images")) {
		    	   out.println("<div class=\"block\"><a href=\"" + fileName + "\"><img class=\"sc\" src=\""+ fileName +"\"></a>");
		    	   out.println("<a href=\"" + url + "\">" + url + "</a></div>");
		       		}
		       }
		    out.flush();
		    out.close();
		    in.close();
		    
		    inFile.delete();
		    outFile.renameTo(inFile);
		  }
		catch(Exception e){
			
		}
	}

}

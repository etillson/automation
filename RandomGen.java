package test1;
import java.util.Random;


public class RandomGen extends seleniumTest{
	
	public String randomId(){
		
		int randId = 1 + (int)(Math.random()* 999999999);
		return Integer.toString(randId);
		
	}
	public String randomId(int length){
		int temp = Integer.parseInt(concatenateDigits(length));
		int randId = 1 + (int)(Math.random()* temp);
		return Integer.toString(randId);
		
	}
	public String randomUsername(){
		String alphabet = 
		        new String("abcdefghijklmnopqrstuvwxyz"); 
		int n = alphabet.length(); 

		String result = new String(); 
		Random r = new Random(); 

		for (int i=0; i<10; i++) 
		    result = result + alphabet.charAt(r.nextInt(n)); 

		return result;
		}
	
	public String randomPassword(){
		String alphabet = 
		        new String("1234567890abcdefghijklmnopqrstuvwxyz!@#$%&*"); 
		int n = alphabet.length(); 

		String result = new String(); 
		Random r = new Random(); 

		for (int i=0; i<10; i++) 
		    result = result + alphabet.charAt(r.nextInt(n)); 

		return result;
		}
	
	public static String concatenateDigits(int digits) {
		   StringBuilder sb = new StringBuilder();
		   for (int k = 0; k<digits; k++) {
		     sb.append(9);
		   }
		   return sb.toString();
		}
}

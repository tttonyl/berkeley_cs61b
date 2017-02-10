import java.io.*;

public class Nuke2 {
    public static void main(String[] args) throws Exception {
	BufferedReader kbd;
	String inputLine;
	kbd = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please enter the string you want to nuke2");
	System.out.flush();
	inputLine = kbd.readLine();
	//	System.out.println(inputLine.substring(0,1) + inputLine.substring(2));
	System.out.println(inputLine.substring(2));
    }
}

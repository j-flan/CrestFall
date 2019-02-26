
public class Graphics {

	public String[] gameLogo =
		{
		"	 ______   ______    ______   ______   _________   ______   ________   __       __          ",
		"	/_____/\\ /_____/\\  /_____/\\ /_____/\\ /________/\\ /_____/\\ /_______/\\ /_/\\     /_/\\         ",
		"	\\:::__\\/ \\:::_ \\ \\ \\::::_\\/_\\::::_\\/_\\__.::.__\\/ \\::::_\\/_\\::: _  \\ \\\\:\\ \\    \\:\\ \\        ",
		"	 \\:\\ \\  __\\:(_) ) )_\\:\\/___/\\\\:\\/___/\\  \\::\\ \\    \\:\\/___/\\\\::(_)  \\ \\\\:\\ \\    \\:\\ \\       ",
		"	  \\:\\ \\/_/\\\\: __ `\\ \\\\::___\\/_\\_::._\\:\\  \\::\\ \\    \\:::._\\/ \\:: __  \\ \\\\:\\ \\____\\:\\ \\____  ",
		"	   \\:\\_\\ \\ \\\\ \\ `\\ \\ \\\\:\\____/\\ /____\\:\\  \\::\\ \\    \\:\\ \\    \\:.\\ \\  \\ \\\\:\\/___/\\\\:\\/___/\\ ",
		"	    \\_____\\/ \\_\\/ \\_\\/ \\_____\\/ \\_____\\/   \\__\\/     \\_\\/     \\__\\/\\__\\/ \\_____\\/ \\_____\\/ "
		};
	
	
	////This method prints a string array to the console 
	//It does not modify current screen content
	//It does not prepend a line-break
	public void printGraphic(String[] outputArray) {
		for(int i = 0; i < outputArray.length; i++) {
			System.out.println(outputArray[i]);
		}
	}


	
}
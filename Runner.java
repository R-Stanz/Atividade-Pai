public class Runner{

	public static void main(String[] args){

		System.out.println("The program has started");
		FileHandler dataSet 	= new FileHandler();
		FileHandler patients 	= new FileHandler("");
		System.out.println("Dataset and the patients data were" +
			           " called with default file addresses"+
				   "\n (documents/SintomasMenor.txt and"+
				   "documents/pacientes.txt)");
		System.out.println("Calling the tree builder");
		dataSet.buildTree();
		System.out.println("Tree builder done!");
		System.out.println("Calling the method to get the "+
				   "patients prognostics.");
		patients.getPrognostics();
		System.out.println("All done, please check the file on "+
				   "documents/pacientes.txt to check if"+
				   " its ok.");
	}
}

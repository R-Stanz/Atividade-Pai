import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.Writer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

// The right thing to do would be to have this class
// only as an interface and other separate a class 
// for dataset and another for the input file
public class FileHandler{

	private Boolean isDataSet;
	private String	fileAddress;
	private BT tree	= new BT();
	
	// Important for when reading the
	// 	patient symptoms
	private ArrayList<String> sicknessNames = new ArrayList<String>();
	private ArrayList<String> symptomNames 	= new ArrayList<String>();
	private Queue<Integer> symptomQueue;

	// Constructor for the dataset file
	FileHandler(){
		this.isDataSet 		= true;
		this.fileAddress 	= "documents/SintomasMenor.txt";
	}

	FileHandler(String address){
		this.isDataSet 		= false;
		if(address.equals(""))	this.fileAddress = "documents/pacientes.txt";
		else this.fileAddress 	= address;
	}

	public void getCopyTree(FileHandler withTree){
		if(this.isDataSet){
			System.out.println("This method is meant to be called"+
					   " by the dataset");
			return ;
		}
		else{
			this.tree 		= withTree.tree;
			this.sicknessNames 	= withTree.sicknessNames;
			this.symptomNames 	= withTree.symptomNames;
		}
	}

	public void buildTree(){
		if(!this.isDataSet){ 
			System.out.println("This method is intended only"+
							" to the dataset!");
			return;
		}
		else{
			try{
				File dataSet 		= new File(fileAddress);
				Scanner dataReader 	= new Scanner(dataSet);
				
				// Gets the number of sickness on the file
				//     (that may be on the first line)
				Integer numbOfSickness;
				if(dataReader.hasNextInt()) 
					numbOfSickness = dataReader.nextInt();
				else{
					System.out.println("File out of format!");
					return;
				}

				// Gets the number of symptoms on the file
				//     (that may be on the first line)
				Integer numbOfSymptoms;
				if(dataReader.hasNextInt()) 
					numbOfSymptoms = dataReader.nextInt();
				else{
					System.out.println("File out of format!");
					return;
				}

				// Starts the automatic process of building
				// the tree branches, also taking the names
				// of the symptoms and the sickness
				// (the names won't have much use but could
				// 	be used on a possible upgrade) 

				// Defining only a few markers beforehand
				Integer lineCount 		= 1;
				Integer lastSicknessLine 	= numbOfSickness;
				Integer lastSymptomLine		= lastSicknessLine + numbOfSymptoms;

				while(true){
					// Getting names of all sicknesses
					if(lineCount <= lastSicknessLine){
						// The first line is the
						//  rest of the #1 line
						if(lineCount == 1) dataReader.nextLine();
						String sickness = dataReader.nextLine();
						sicknessNames.add(sickness);
						lineCount += 1;
					}

					// Getting names of all symptoms
					else if(lineCount <= lastSymptomLine){
						String symptom 	= dataReader.next();
						symptomNames.add(symptom);
						lineCount += 1;
					}

					// Works on each prognostic
					// 	line-by-line
					// (Based on the file given
					//   to test this program)
					else if(dataReader.hasNextInt()){
						Integer prognostic 	= dataReader.nextInt();
						symptomQueue 		= new LinkedList<>();
						Integer symptom;
						for(Integer i = 0; i < numbOfSymptoms; i++){
							symptom 	= dataReader.nextInt();
							// Saves only the posite 
							//   symptoms indexes
							if(symptom == 1) symptomQueue.add(i);
						}
						tree.setABranch(symptomQueue, prognostic);
					}
					else break;
				}

				dataReader.close();
			}
			catch(FileNotFoundException e){
				System.out.println("Error while building the tree");
				e.printStackTrace();
			}
		}
	}

	// Its supposed to work almost identicaly 
	// 	  as the previous method.
	public void getPrognostics(){
		if(isDataSet){ 
			System.out.println("This method isn't meant to be used" +
						  " on the dataset.");
		}
		else{
			// A variable to append its content
			// 	at the end of the file
			String results = "";
			try{
				// The prognostic file may have a line with the
				// 	  pacients names and bellow it
				// 		their symptoms
				File symptomsFile	= new File(fileAddress);
				Scanner fileReader 	= new Scanner(symptomsFile);

				// To hold the prognostics on each patient
				HashSet<Integer> prognosticSet;

				// It must call the checkSymptomPath and than
				// use it on the result messages that will be
				// 	appended at the end of the file
				while(fileReader.hasNext()){
					// Gets only the name of the patient
					results 	+= "Pacient: " + fileReader.nextLine();

					// Start getting the prognostics
					results		+= "\nPrognostics:";
					symptomQueue 	 = new LinkedList<>();
					while(fileReader.hasNextInt()){
						Integer symptom = fileReader.nextInt();
						symptomQueue.add(symptom); 
					}
					prognosticSet = tree.checkSymptomPath(symptomQueue);
					System.out.println(prognosticSet.size());
					if(prognosticSet.isEmpty()) results += " Unknown";
					else{
						for(Integer index : prognosticSet){
							results += " " + sicknessNames.get(index);
						}
					}
				}
				fileReader.close();
			}
			catch(FileNotFoundException e){
				System.out.println("Error while trying to use the tree");
				e.printStackTrace();
			}
			try{
				// By the end of the input file must be appended
				//  the index of the prognostic and before that 
				// 	     the name of the patient
				//    (which are already stored at results)
				BufferedWriter output = new BufferedWriter(new FileWriter(fileAddress, true));
				output.write(results);
				output.close();

			}
			catch(IOException e){
				System.out.println("Error while writing the prognostics results. Exception " + e);
			}
		}
	}
}

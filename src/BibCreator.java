// -----------------------------------------------------
// Final Project (BibCreator)
// ©2022 Darryl Du, Hanen Hsu
// Written by: Darryl Du 2032901, Hanen Hsu 2033597
// -----------------------------------------------------
import java.util.Scanner; //scanner
import java.io.FileInputStream; //reading data
import java.io.FileOutputStream; //writing data
import java.io.BufferedReader; //read text file
import java.io.File; //file and directory pathnames
import java.io.FileNotFoundException; //exception for reading and writing
import java.io.FileReader; //used to read data (in characters) from files
import java.io.IOException;//exception
import java.io.PrintWriter;//print the formatted representation of objects to the text-output stream

public class BibCreator {

	static int invalid = 0, valid = 0;//how many valid or invalid files
	
	public static void deleteFile(int i)//DELETING 
	{
		File deleteFile = null;
		
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\src\\Files\\IEEE"+ i +".json");
		deleteFile.delete();
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\src\\Files\\ACM" + i + ".json");
		deleteFile.delete();
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\src\\Files\\NJ" + i + ".json");
		deleteFile.delete();
	}
	
	public static void processFilesForValidation(int i) throws FileInvalidException, FileNotFoundException
	{
		PrintWriter pwieee, pwacm, pwnj; 
		String authorIEEE=null;
        String authorACM=null;
        String authorNJ=null;
        String journal=null;
        String title=null;
        String year=null;
        String volume=null;
        String number=null;
        String pages=null;
        String keywords=null;
        String doi=null;
        String ISSN=null;
        String month=null;
        String ieee =null;
        String acm=null;
        String nj=null;
        String error=null;
        String line=null;
        int acmCounter= 0;
        try 
        {
        	 BufferedReader br = new BufferedReader(new FileReader("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\src\\Latex" + i + ".bib"));
        	 File ACMFile = new File("ACM" + i + ".json");
             File IEEEFile = new File("IEEE" + i + ".json");
             File NJFile = new File("NJ" + i + ".json");
             pwieee = new PrintWriter(new FileOutputStream("IEEE"+ i +".json", true));
     		 //create ACM file file and write to new file from reading acm string
     		 pwacm = new PrintWriter(new FileOutputStream("ACM"+ i +".json", true));
     		 //create NJ file file and write to new file from reading nj string
     		 pwnj = new PrintWriter(new FileOutputStream("NJ"+ i +".json", true));
	    	 while ((line = br.readLine()) != null) {
	                boolean lineError=true;
	                if (line.equals("")) 
	                {
	                    
	                } else if (line.contains("author={},")) {
	                	
	                    error = "author";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("journal={},")) {
	                	
	                    lineError=false;
	                    error = "journal";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("title={},")) {
	                	
	                    error = "title";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("year={},")) {
	                	
	                    error = "year";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("volume={},")) {
	                	
	                    error = "volume";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("number={},")) {
	                    error = "number";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("pages={},")) {
	                	
	                    error = "pages";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("keywords={},")) {
	                	
	                    error = "keywords";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("doi={},")) {
	                	
	                    error = "doi";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("ISSN={},")) {
	                	
	                    error = "ISSN";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("month={},")) {
	                	
	                    error = "month";
	                    lineError=false;
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("author={")&&lineError) {
	                	
	                    authorIEEE=line.substring(8,line.indexOf("}"));
	                    authorACM=line.substring(8,line.indexOf("}"));
	                    authorNJ=line.substring(8,line.indexOf("}"));
	                    authorIEEE=authorIEEE.replaceAll(" and",",");
	                    authorNJ=authorNJ.replaceAll("and","&");
	                    
	                    if (line.contains("and")) 
	                    {
	                        authorACM=authorACM.substring(0,authorACM.indexOf("and")-1)+" et al";
	                    }
	                    else
	                    {
	                        authorACM=authorACM+ "et al";
	                    }
	                    acmCounter++;
	                } else if (line.contains("journal={")&&lineError) {
	                	
	                    journal=line.substring(9,line.indexOf("}"));
	                    
	                } else if (line.contains("title={")&&lineError) {
	                	
	                    title=line.substring(7,line.indexOf("}"));
	                    
	                } else if (line.contains("year={")&&lineError) {
	                	
	                    year=line.substring(6,line.indexOf("}"));
	                    
	                } else if (line.contains("volume={")&&lineError) {
	                	
	                    volume=line.substring(8,line.indexOf("}"));
	                    
	                } else if (line.contains("number={")&&lineError) {
	                	
	                    number=line.substring(8,line.indexOf("}"));
	                    
	                } else if (line.contains("pages={")&&lineError) {
	                	
	                    pages=line.substring(7,line.indexOf("}"));
	                    
	                } else if (line.contains("keywords={")&&lineError) {
	                	
	                	keywords=line.substring(10,line.indexOf("}"));
	                    
	                } else if (line.contains("doi={")&&lineError) {
	                    doi=line.substring(5,line.indexOf("}"));
	                    
	                } else if (line.contains("ISSN={")&&lineError) {
	                	
	                	ISSN=line.substring(6,line.indexOf("}"));
	                    
	                } else if (line.contains("month={")&&lineError) {
	                	pwieee.println(authorIEEE + ". " + "\"" + title + "\"" + ", " + journal + ", vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".\n");
	                	pwacm.println("[" + acmCounter + "] " + authorACM + ". " + title + ". " + year + ". " + journal + ". " + volume + ", " + number + " (" + year + ")" + ", " + pages + ". DOI:https://doi.org/" + doi + ".\n");
	                	pwnj.println(authorNJ + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ")" + ".\n");
	            		pwieee.close();
	            		pwacm.close();
	            		pwnj.close();
	            		valid++;// counter for succesful files.
	                }
	    	 }
	    	 br.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("File Latex" + i + ".bib not found! Program shall terminate now.");
	            System.exit(0);
	            
		} catch (Exception e) {
			// TODO: handle exception
			invalid++;
            System.out.println("Error: Detected Empty Filed!");
            System.out.println("============================");
            System.out.println("\nProblem detected with file Latex" + i + ".bib");
            System.out.println(e.getMessage());
            System.out.println("File is Invalid: Field \"" + error + "\" is Empty. Processing has stopped at this point. Other empty fields may be present as well!\n");
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		
		System.out.println("Welcome to Bib Creator!");
			
		//VALIDATING N STUFF
		for(int i=1;i<11;i++) {
			try {
				processFilesForValidation(i);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		System.out.println("A total of " + invalid + " files were invalid, and could not be processed. All other " + valid + " files have been created.\n");

		
		System.out.print("\nPlease enter the name of one of the files that you need to review:");
		Scanner inputFile = new Scanner(System.in);
		String fileName = inputFile.next();
		
		try 
		{
			br = new BufferedReader(new FileReader(fileName));
			
			System.out.println("Here are the contents of the successfully created JSON File: " + fileName);
			String nextLine = br.readLine();
			
			while(nextLine!=null) 
			{
				System.out.println(nextLine);
				nextLine = br.readLine();
			}
			System.out.println("\nGoodbye! Hope you have enjoyed creating the needed files using BibCreator.");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Could not open input file. File does not exist; possibly it could not be created!\n");
			System.out.println("However, you will be allowed another chance to enter another file name.");
			System.out.print("Please enter the name of one of the files that you need to review: ");
			fileName = inputFile.next();
			try 
			{
				br = new BufferedReader(new FileReader(fileName));
				
				System.out.println("Here are the contents of the successfully created JSON File: " + fileName);
				String nextLine = br.readLine();
				
				while(nextLine!=null) 
				{
					System.out.println(nextLine);
					nextLine = br.readLine();
				}
				System.out.println("\nGoodbye! Hope you have enjoyed creating the needed files using BibCreator.");
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("\nCould not open input file again! Either file does not exist or could not be created.");
				System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
				System.exit(0);
			}
		}finally {
			inputFile.close();
		}
		System.out.print("Goodbye! Hope you enjoyed creating the needed files using BibCreator.");

		
	}

}

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

	static int invalid = 0, valid = 10;//how many valid or invalid files
	
	public static void deleteFile(int i)//DELETING 
	{
		File deleteFile = null;
		
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\IEEE"+ i +".json");
		deleteFile.delete();
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\ACM" + i + ".json");
		deleteFile.delete();
		deleteFile = new File("C:\\Java eclipse\\2032901_Du_2033597_Hsu\\NJ" + i + ".json");
		deleteFile.delete();
	}
	
	public static void processFilesForValidation(int i) throws FileInvalidException, FileNotFoundException
	{
		PrintWriter pwieee = null, pwacm = null, pwnj = null; 
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
        	 //create ACM file
        	 File ACMFile = new File("ACM" + i + ".json");
        	 //create IEEE file
             File IEEEFile = new File("IEEE" + i + ".json");
             //create NJ file 
             File NJFile = new File("NJ" + i + ".json");
             
             pwieee = new PrintWriter(new FileOutputStream("IEEE"+ i +".json", true));
     		
     		 pwacm = new PrintWriter(new FileOutputStream("ACM"+ i +".json", true));

     		 pwnj = new PrintWriter(new FileOutputStream("NJ"+ i +".json", true));
     		 //copy file onto string line 
	    	 while ((line = br.readLine()) != null) {
	    		   if (line.contains("author={},")) {//empty author

	                    error = "author";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("journal={},")) {//empty journal

	                    error = "journal";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("title={},")) {//empty title

	                    error = "title";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("year={},")) {//empty year

	                    error = "year";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("volume={},")) {//empty volume

	                    error = "volume";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("number={},")) {//empty number

	                    error = "number";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("pages={},")) {//empty pages

	                    error = "pages";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("keywords={},")) {//empty keywords

	                    error = "keywords";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("doi={},")) {//empty doi

	                    error = "doi";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("ISSN={},")) {//empty issn

	                    error = "ISSN";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("month={},")) {//empty month

	                    error = "month";
	                    throw new FileInvalidException();
	                    
	                } else if (line.contains("author={")) {
	                	
	                    authorIEEE = line.substring(8,line.indexOf("}"));
	                    authorACM = line.substring(8,line.indexOf("}"));
	                    authorNJ = line.substring(8,line.indexOf("}"));
	                    authorIEEE = authorIEEE.replaceAll(" and",",");
	                    authorNJ = authorNJ.replaceAll("and","&");
	                    
	                    if (line.contains("and")) 
	                    {
	                        authorACM = authorACM.substring(0,authorACM.indexOf("and")-1)+" et al";
	                    }
	                    else
	                    {
	                        authorACM = authorACM+ "et al";
	                    }
	                    acmCounter++;
	                } else if (line.contains("journal={" )) {
	                	
	                    journal = line.substring(9,line.indexOf("}"));
	                    
	                } else if (line.contains("title={")) {
	                	
	                    title = line.substring(7,line.indexOf("}"));
	                    
	                } else if (line.contains("year={")) {
	                	
	                    year = line.substring(6,line.indexOf("}"));
	                    
	                } else if (line.contains("volume={")) {
	                	
	                    volume = line.substring(8,line.indexOf("}"));
	                    
	                } else if (line.contains("number={")) {
	                	
	                    number = line.substring(8,line.indexOf("}"));
	                    
	                } else if (line.contains("pages={")) {
	                	
	                    pages = line.substring(7,line.indexOf("}"));
	                    
	                } else if (line.contains("keywords={")) {
	                	
	                	keywords = line.substring(10,line.indexOf("}"));
	                    
	                } else if (line.contains("doi={")) {
	                    doi = line.substring(5,line.indexOf("}"));
	                    
	                } else if (line.contains("ISSN={")) {
	                	
	                	ISSN = line.substring(6,line.indexOf("}"));
	                    
	                } else if (line.contains("month={")) {
	                	pwieee.println(authorIEEE + ". " + "\"" + title + "\"" + ", " + journal + ", vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".\n");
	                	pwacm.println("[" + acmCounter + "] " + authorACM + ". " + title + ". " + year + ". " + journal + ". " + volume + ", " + number + " (" + year + ")" + ", " + pages + ". DOI:https://doi.org/" + doi + ".\n");
	                	pwnj.println(authorNJ + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ")" + ".\n");
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
		        deleteFile(i);
		        
			}finally {
	            pwieee.close();
	            pwacm.close();
	            pwnj.close();
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
		valid = valid - invalid;
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

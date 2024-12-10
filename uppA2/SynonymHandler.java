/*SynonymHandler*/

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	private static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.

// min kod!!*****************************************************************
	public static String[] removeSynonymLine (String[] synonymData,
	    String word) throws IllegalArgumentException
	{
		int pWord = synonymLineIndex(synonymData, word); // ordet var på
    
        String[] synData = new String[synonymData.length - 1];
        for(int i = 0, j = 0; i < synonymData.length ; i++){
            if( i != pWord){
                // om i är inte ordet som ska raderas så tar den ordet och lägget till i synonymData
                // sen går till nästa position
                synData[j++] = synonymData[i];
            }
        }

        return synData ;
    }

    // getSynonyms returns synonyms in a given synonym line.
	private static String[] getSynonyms (String synonymLine)// så att vi kan jobba med bara synonymer
	{
        int position = synonymLine.indexOf('|');// vilken plats är den char
        String newSynonym = synonymLine.substring(position + 1).trim();// tar allt från efter streck synonym
        return newSynonym.split(","); //split varje gång den ser en comma
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym) throws IllegalArgumentException
	{
        int position = synonymLineIndex(synonymData, word); // hitta word; = position
        String[] synData = new String [synonymData.length + 1];// en element större än synonymData
        if (position == -1){ // -1 för att det kan inte bli -1 och då är den inte med i listan
            throw new IllegalArgumentException("Word" + word + "'not found");
        }/* Jag kanske måste inte göra det???***  */

        for (int i = 0; i < synonymData.length; i++){
            synData[i] = synonymData[i]; // kopierar
            if(i == position) {
                synonymData[position] += "," + synonym;
            }
        }
	}
// ***************************************************LABB#2************


    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.
    public static void removeSynonym (String[] synonymData,
    String word, String synonym)
    throws IllegalArgumentException, IllegalStateException{
    
    int index = synonymLineIndex(synonymData, word); 
    String synLine = getSynonymLine(synonymData, word); //hela syn o orden
    String [] synonyms = getSynonyms(synLine);// alla synonymer till ett ord
    String[] synData = new String [synonyms.length-1];// store after removing

    if (index == -1) {
        throw new IllegalArgumentException("ordet finns inte i  synonymData.");
    }
     if (synonymData.length == 1) {
        throw new IllegalStateException("kan inte ta bort den enda synonymen ");
    }

    int pos= -1;
    int i= 0;

   while(i<synonyms.length && pos==-1){
        if (synonyms[i].equalsIgnoreCase(synonym)) {
            pos = i; // hitta platsen där synonymen ska bort
            break;
        }
        i++;
        
     
    }

       if (i == -1) {
        throw new IllegalArgumentException("synonymen hittas inte");
        }// om synonymen finns ej

    
    String baraOrd = synLine.substring (0, synLine.indexOf("|")).trim();

   int a = 0;
    for (int m = 0; m < synonyms.length; m++) {
        if (m==pos) {
             continue;// skippar den som ska bort
         }
    synData[a++] = synonyms[m];
      
    }

  String nyaLine = baraOrd + "| ";// skriver bara ord
    for (int j = 0; j < synData.length; j++) {
    nyaLine += synData[j];
    if (j < synData.length - 1) {
        nyaLine += ", "; // lägger till synonymer efter ord
    }
}

synLine = nyaLine.trim();
synonymData[index] = synLine;// uppdaterar synonymdata
}
   

/*public static void removeSynonym(String[] synonymData, String word, String synonym)
        throws IllegalArgumentException, IllegalStateException {
    
    Step 1: Find the index of the synonym line for the given word
    int index = synonymLineIndex(synonymData, word);
    if (index == -1) {
        throw new IllegalArgumentException("Word '" + word + "' not found");
    }

    Step 2: Get synonyms for the word and ensure at least one will remain
    String[] synonyms = getSynonyms(synonymData[index]);
    if (synonyms.length == 1 && synonyms[0].trim().equalsIgnoreCase(synonym.trim())) {
        throw new IllegalStateException("Cannot remove the only synonym for word: " + word);
    }

    Step 3: Remove the synonym if it exists
    int position = -1;
    for (int i = 0; i < synonyms.length; i++) {
        if (synonyms[i].trim().equalsIgnoreCase(synonym.trim())) {
            position = i;
            break;
        }
    }
    if (position == -1) {
        throw new IllegalArgumentException("Synonym '" + synonym + "' not found for word '" + word + "'");
    }

    Step 4: Create a new array for the updated synonyms
    String[] synData = new String[synonyms.length - 1];
    for (int i = 0, j = 0; i < synonyms.length; i++) {
        if (i != position) {
            synData[j++] = synonyms[i].trim();
        }
    }

    Step 5: Update the synonym line and `synonymData`
    StringBuilder updatedLine = new StringBuilder(word + " |");
    for (String syn : synData) {
        updatedLine.append(" ").append(syn).append(",");
    }
    synonymData[index] = updatedLine.toString().replaceAll(",$", "");  // Remove trailing comma
}

*/
    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        for (int i = 1; i < strings.length; i++) {
            String aktuell = strings[i];
            int j = i - 1;
    
            while (j >= 0 && aktuell.compareToIgnoreCase(strings[j]) < 0) {
                strings[j+1] = strings[j];
                j--;
            }
    
            strings[j + 1] = aktuell; // om aktuell är större än föregående
        }
 	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
        String[] synonyms = getSynonyms(synonymLine); 
        sortIgnoreCase(synonyms);
// new codes:
        String formattedSynonyms = String.join(", ", synonyms);
        return (synonymLine.substring(0, synonymLine.indexOf("|")+1)+" " + formattedSynonyms);	
       
       // return (synonymLine.substring(0, synonymLine.indexOf("|")+1)+" " + synonyms);	
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        for (int i = 0; i < synonymData.length; i++) {
            synonymData[i] = sortSynonymLine(synonymData[i]);
        }
        sortIgnoreCase(synonymData);
    
    }
}

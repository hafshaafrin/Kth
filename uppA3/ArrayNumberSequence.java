// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}
// MIN KOD
	public int length ()
	{
		return this.numbers.length;
	}

public double upperBound()
{
	double max = numbers[0];
	for (int i = 0; i < numbers.length; i++){
		if (numbers[i] > max) {
			max = numbers[i];
		}
	}
	return max;
}


public double lowerBound()
{
	// skapa en double min som har första element i 0 plats. 
	double min = numbers[0];
	for (int i = 0; i < numbers.length; i++){
		// jämför varje element med min och om mindre dne blir min
		if (numbers[i] < min) {
			min = numbers[i];
		}
	}
	return min;
}// skriv ut min

public  double numberAt (int position)
throws IndexOutOfBoundsException{
	return numbers[position];
}

public  int positionOf (double number){

	for (int i = 0; i < numbers.length; i++)
		if (numbers[i] == number) 
		{
			return i;
		}

		return -1; // annrs illegal
}

public boolean isIncreasing (){
	for (int i = 0; i < numbers.length; i++)
	{
		if (numbers[i]< numbers[i + 1]){
			return true;
		}
	}

	return false; 
}

public boolean  isDecreasing(){
	for (int i = 0; i < numbers.length; i++)
	{
		if (numbers[i]> numbers[i + 1]){
			return true;
		}
	}

	return false; 
}

public boolean contains (double number){
	for (int i = 0; i < numbers.length; i++)
	{
		if( numbers[i] == number){
			return true;
		}
	}
	return false;
}


public void add (double number){
	double[] added = new double[numbers.length + 1];
	for (int i = 0; i < numbers.length; i++)
		added[i] = numbers[i];
	added[added.length - 1] = number;// sista position av min nya array lägger den till number
	numbers = added;// nya arrayn är min numbers

}


public void insert (int position, double number)
throws IndexOutOfBoundsException{
	double[] ins = new double[numbers.length];
	for (int i = 0; i < numbers.length; i++){
		ins[i] = numbers[i];
	}
	ins[position] = number;
	numbers = ins ;
	
	if ( position > numbers.length)
	{
		throw new IndexOutOfBoundsException ("positionen finns inte");
	}
	else if (position < 0)
	{
		{
			throw new IndexOutOfBoundsException ("positionen finns inte");
		}
	}


}

public void removeAt (int position)
throws IndexOutOfBoundsException, IllegalStateException{
	double[] rem = new double[numbers.length - 1];
	int k = 0;

	for (int i = 0 ; i < numbers.length; i++){
		
		if ( i != position){
		rem[i] = numbers[k++];
		}
	}
	numbers = rem ;
	
	if ( position > numbers.length)
	{
		throw new IndexOutOfBoundsException ("positionen finns inte");
	}
	else if (position < 0)
	{
		{
			throw new IndexOutOfBoundsException ("positionen finns inte");
		}
	}

	else if( numbers.length == 2){
		throw new IllegalStateException("bara två element!!");
	}
}


public  double[] asArray(){
	return numbers;

}


}
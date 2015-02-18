//Rahul Kapur rk2749
 public  class HuffmanNode {
        	
        	
        	public int frequency; //frequency of occurence of character
        	private String code; //Huffman code
        	public HuffmanNode rightElement;// right element
        	public HuffmanNode leftElement; // left element
        	public String theElement; // character stored in an element
        	
        	
        	HuffmanNode(String letter, int frequency) { //constructor that takes in the character and frequency
        		this(letter,frequency,null,null); //initializes below constructor with null values


        	}
        	
        	HuffmanNode(String theElement, int frequency, HuffmanNode leftElement, HuffmanNode rightElement)
        	
        	
            {
                this.theElement = theElement; 
                this.frequency= frequency;
                this.leftElement= leftElement;
                this.rightElement= rightElement;
                String code = this.code;
                
                
                
        		
        	}

			public int compareTo(HuffmanNode tmp, HuffmanNode tmp2) { //compare method of node frequncies to place in the binary tree
				if (tmp.frequency > tmp2.frequency) {
					return -1; //less than zero
				}
				if (tmp.frequency < tmp2.frequency) {
					return 1;
				}
				
				else //both are equal
					return 0;

				
			}

			public String getCode() {
				return code;//return huffman code
			}

			public void setCode(String code) {//edit huffman code
				this.code = code;
			}
			
	
			  
         
        }
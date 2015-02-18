import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
//Rahul Kapur rk2749

    // BinaryHeap class
    //
    // CONSTRUCTION: with optional capacity (that defaults to 100)
    //
    // ******************PUBLIC OPERATIONS*********************
    // void insert( x )       --> Insert x
    // Comparable deleteMin( )--> Return and remove smallest item
    // Comparable findMin( )  --> Return smallest item
    // boolean isEmpty( )     --> Return true if empty; else false
    // boolean isFull( )      --> Return true if full; else false
    // void makeEmpty( )      --> Remove all items
    // ******************ERRORS********************************
    // Throws Overflow if capacity exceeded

    /**
     * Implements a binary heap.
     * Note that all "matching" is based on the compareTo method.
     * @author Mark Allen Weiss
     */
    public class BinaryHeap
    {
    	public static HashMap<String, String> huffmanCodes = new HashMap<String, String>(); //store character and respective huffman code
    	public static HashMap<String, String> huffmanString = new HashMap<String, String>();//store character and respective huffman code
    	
        /**
         * Construct the binary heap.
         */
        public BinaryHeap( )
        {
            this( DEFAULT_CAPACITY );
        }

        /**
         * Construct the binary heap.
         * @param capacity the capacity of the binary heap.
         */
        public BinaryHeap( int capacity )
        {
            currentSize = 0;
            array = new HuffmanNode[ capacity + 1 ];//alter comparable to HuffmanNode
        }

        /**
         * Insert into the priority queue, maintaining heap order.
         * Duplicates are allowed.
         * @param x the item to insert.
         * @exception Overflow if container is full.
         */
        public void insert( HuffmanNode x ) throws Exception
        {
            if( isFull( ) )
                throw new Exception( );

                // Percolate up
            int hole = ++currentSize;
            for( ; hole > 1 && x.compareTo( array[ hole / 2 ], x ) < 0; hole /= 2 ) //uses compareto of HuffmanNode class
                array[ hole ] = array[ hole / 2 ];
            array[ hole ] = x;
        }

        /**
         * Find the smallest item in the priority queue.
         * @return the smallest item, or null, if empty.
         */
        public HuffmanNode findMin( )
        {
            if( isEmpty( ) )
                return null;
            return array[ 1 ];
        }

        /**
         * Remove the smallest item from the priority queue.
         * @return the smallest item, or null, if empty.
         */
        public HuffmanNode deleteMin( )
        {
            if( isEmpty( ) )
                return null;

            HuffmanNode minItem = findMin( );
            array[ 1 ] = array[ currentSize-- ];
            percolateDown( 1 );

            return minItem;
        }

        /**
         * Establish heap order property from an arbitrary
         * arrangement of items. Runs in linear time.
         */
        private void buildHeap( )
        {
            for( int i = currentSize / 2; i > 0; i-- )
                percolateDown( i );
        }

        /**
         * Test if the priority queue is logically empty.
         * @return true if empty, false otherwise.
         */
        public boolean isEmpty( )
        {
            return currentSize == 0;
        }

        /**
         * Test if the priority queue is logically full.
         * @return true if full, false otherwise.
         */
        public boolean isFull( )
        {
            return currentSize == array.length - 1;
        }

        /**
         * Make the priority queue logically empty.
         */
        public void makeEmpty( )
        {
            currentSize = 0;
        }

        private static final int DEFAULT_CAPACITY = 100;

        private int currentSize;      // Number of elements in heap
        private HuffmanNode [ ] array; // The heap array containing HuffmanNodes

        /**
         * Internal method to percolate down in the heap.
         * @param hole the index at which the percolate begins.
         */
        private void percolateDown( int hole )
        {
        	int child;
/* 2*/      HuffmanNode tmp = array[ hole ];

/* 3*/      for( ; hole * 2 <= currentSize; hole = child )
            {
/* 4*/          child = hole * 2;
/* 5*/          if( child != currentSize &&
/* 6*/                  array[ child + 1 ].compareTo( array[ child ] , array[child+1]) < 0 )
/* 7*/              child++;
/* 8*/          if( array[ child ].compareTo( tmp, array[child] ) < 0 )
/* 9*/              array[ hole ] = array[ child ];
                else
/*10*/              break;
            }
/*11*/      array[ hole ] = tmp;
        }
        
        
        
       private static void buildTable(HuffmanNode x, String code) { //Builds Table of characters and huffman codes
        	
        	if(x!=null){// element is not null
        	if (x.leftElement==null && x.rightElement==null) { //leaf node which contains a character
        		x.setCode(code);//add the code to it
				huffmanCodes.put( x.getCode(), x.theElement);// store the huffmanCode and character in a hashmap to retrieve when decoding
				huffmanString.put(  x.theElement, x.getCode());// store the  character and huffmanCode  in a hashmap to retrieve when encoding
        		System.out.println (x.theElement + " " + "=" + x.getCode());
        	}
        	
        		buildTable(x.leftElement, code+"0");//recurse the table with the left element and add a zero to the code each time
            	buildTable(x.rightElement, code+"1");//recurse the table with the right element and add a 1 to the code each time
        		
        	}
        	
        	
        	
        	
        }
       
       
       private static void printTree(HuffmanNode x) {
    	   
    	   if (x!= null) {
    		   
    		   System.out.println(x.theElement + "\n" + x.getCode());//print element and code
    		   printTree(x.leftElement);//recurse left element
    		   printTree(x.rightElement);//recurse right element
    	   }
    	   
    	   
    	   
       }
       
      

            // Test program
        public static void main( String [ ] args ) throws Exception  
        {
         
        	int nl =0;//new line counter
        	String fileName = args[0];//take in the input file
        	File file = new File(fileName); //create a file
				Scanner input = new Scanner(file);//create a scanner
				HashMap<String, Integer> characterList = new HashMap<String, Integer>();//store characters and frequencies
				while (input.hasNextLine()) {
					nl++;//increment counter
					String line = input.nextLine();//store the line in the string
					for (int i=0; i<line.length(); i++) {
						String character =  Character.toString(line.charAt(i));//store a character as a string
						if (character.equals(" ")) {// replace spaces with sp
							character = "sp";
						}
						if (characterList.containsKey(character) ) {
							characterList.put(character, characterList.get(character)+1); //add character to hasmap and increment frequency counter
						}
						
						else{
							characterList.put(character, 1);// add characters to hashmap
						}
						
					}

				}
				
				
				
				characterList.put("nl", nl-1);//add the newline and its counter to the hashmap
				int size = characterList.size();// size of character list
				BinaryHeap h = new BinaryHeap( size );// instantiate a binary heap
				for(String c : characterList.keySet()) {//loop through the characters in the hashmap
		            HuffmanNode node = new HuffmanNode(c, characterList.get(c));//store the character  and the frequency into the node
						h.insert(node);//insert nodes into binary heap 
						
				
					
		            
		        }
				
				
				for (int i = 1; i < size; i++) {
					HuffmanNode node2= h.deleteMin();//delete the min node from the heap
					HuffmanNode node3 = h.deleteMin();//delete the second min node from the heap
					HuffmanNode node1 = new HuffmanNode("T" + i, node2.frequency+node3.frequency); //create the parent node with letter T and i, and the frequency is the sum of the 2 deleted nodes
					node1.leftElement= node2;//store the the first deleted node as the left element
					node1.rightElement= node3;//store the the second deleted node as the right element
					h.insert(node1); //insert the node back into the heap to once again compare frequencies

					
				}
				
				HuffmanNode rootNode= h.deleteMin();//deleted the last remaining node which is the root
				String code= "";//create an empty string code
				buildTable(rootNode, code); // run build table with root node
				printTree(rootNode);
				
				
				
				
				
		
        	
        	System.out.println("Please enter a code of 0's and ones");//prompt users to enter a code
        	Scanner in = new Scanner (System.in);//input a scanner
        	String line = in.nextLine();//store the input as a string
        	String a ="";//empty string
        	String result = "";//result string
        	boolean print1 = true;
        	
        	for (int i =0; i < line.length(); i ++) {
        		a = a + line.charAt(i); //loop through the entered string and cancatinate
        		
        		if (huffmanCodes.containsKey(a)) {//check Huffman Code
        			if (!huffmanCodes.get(a).equals("nl") && !huffmanCodes.get(a).equals("sp")) {//check whether it is not nl or sp
        			result = result + huffmanCodes.get(a);//cancatinate result string using hashmap of huffman code
        			}
        			
        			if (huffmanCodes.get(a).equals("nl")) {//if nl create a newline
        				result = result + "\n";
        			}
        			
        			if (huffmanCodes.get(a).equals("sp")) {//if sp add a space character to result
            			result = result + " ";
        			}
        			
        			a="";
        		}
        		
        		else if (!huffmanCodes.containsKey(a) && i== line.length()-1 ) {//if there is an error print error
        			System.out.println("There is an error in the code");
        			print1= false;
        		}
        		
        		
        	} 
        	
        	if (print1==true) {// if no error print cancatinated result string
        		System.out.println(result);
        	}
        	
        	
        	System.out.println("Please enter a string to encode");//enter a string
        	Scanner enter = new Scanner (System.in);//create scanner
        	String line2 = enter.nextLine();
        	String result2 = "";
        	String a1 ="";
        	boolean print = true;
        	
        	for (int i =0; i < line2.length(); i ++) {
        		a1 = Character.toString(line2.charAt(i)); //store character
        		if (a1.equals(" ")) {
        			a1= "sp";
        		}
        		if (a1.equals("/n")){
        			a1="nl";
        		}
        		if (huffmanString.containsKey(a1)) {
        			result2 = result2 + huffmanString.get(a1);
        			
        		}
        		else if (huffmanString.containsKey("\n")) {//replace new line with nl
        			result2 = result2 + huffmanString.get("nl");
        		}
        		
        		else if (huffmanString.containsKey(" ")) {//replace space with sp
        			result2 = result2 + huffmanString.get("sp");
        		}
        		
        		else {
        			print=true;
        		}
        		
        		
        		
        	}
        	
        	if (print==true) {
        		System.out.println(result2); // print result
        	}
        	
        	else {
        		System.out.println("One of the alphabets is not in our list");
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
    }
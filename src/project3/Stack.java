package project3;

/** 
 *The Link class helps create a double ended linked list containing the country objects 
 *using next to connect to the next link.
 * 
 * @author <Kenniece Harris> 
 * @version <10/28/2022> 
 */ 
class Link
{
	 public Link next;
	 public Link prev;
	 public Country country;
	
	 public Link(Country country) {
		 
		 this.country = country;
	 }
	 
	 public void display() {
		 
		 System.out.println(country.getCountryData());
	 }
}

/** 
 *The Stack class creates a stack containing country objects that can either be pushed/popped 
 *from the stack as well as printing the stack using a double ended linked list.
 * 
 * @author <Kenniece Harris> 
 * @version <10/28/2022> 
 */ 
public class Stack {
	

	public Link first;
	public Link last;
	private Link prev;
	private int nItems;
	
/** 
* Sets the first and last items in the Linked list to null.
 */
	public Stack() {
		
		this.first = null;
		this.last = null;
		this.nItems = 0;
		
	}
	
/** 
* Stores country objects inside of the linked list. If the list is empty the first and last link
* becomes the new link, else the new link becomes the last link
* 
* @param  country, a country object from the country class.
 */	
	public void push(Country country) {
		
		Link newLink = new Link(country);
		
	    
		
		
		if(nItems == 0) {
			
			if(isEmpty()) {
				
				last = newLink;
				
				nItems++;
			}
			
			newLink.next = first;
			first = newLink;
			prev = first;
			
			
			
		}
		
		else {
		    if(isEmpty()) {
				 
				first = newLink;
				
				
			}
			
			else {
				prev = last;
				last.next = newLink;
				last = newLink;
				
				nItems++;
			}
		
		}
		
	}
	
/** 
* Removes the last link by setting it null and printing it out.
* 
* return temp, the last link that has been popped from the stack.
 */

	public Country pop() {
		

		Country temp = last.country;
		
		if(isEmpty()) {
			
			System.out.println("The stack is empty, cannot pop item from stack.");
			
			
		}
		
		else {
			
			last = prev;
			last.next = null;
			Link current = first;
			while(current.next != prev && current.next != null)
			{
				
				current = current.next;
				
			}
			
			prev = current;
			nItems--;
			
			
			
			}
		
		return temp;
	}
	
/** 
* Prints the stack recursively starting from the first link.
* 
* @param  head, the first link of the array.
 */
	public void printStack(Link head) {
	
		if(head == null) {
			return;
		}
		else {
			printStack(head.next);
			head.display();
		}
		
	}

/** 
* Checks if the first link is empty.
* 
* @param  empty, if first is null,  return true, else false
 */
	public boolean isEmpty() {
		
		boolean empty = false;
		
		if (first == null) {empty = true;}
		
		return empty;
	}

}

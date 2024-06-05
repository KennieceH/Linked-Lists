package project3;

/** 
 *The Link class helps create a doubly linked list of country objects by connecting the 
 * next link to the previous link and vice versa.
 * 
 * @author <Kenniece Harris> 
 * @version <10/28/2022> 
 */
class LinkPQ
{
	 public LinkPQ next;
	 public LinkPQ previous;
	 public Country country;
	
	 public LinkPQ(Country country) {
		 
		 this.country = country;
	 }
	 
	 public void display() {
		 
		 System.out.println(country.getCountryData());
	 }
}

/** 
 *The PriorityQ class allows country objects to be inserted or removed from the queue as 
 *well as deleted based on their death rates. This class utilizes a doubly linked list
 *to create the Priority Queue.
 * 
 * @author <Kenniece Harris> 
 * @version <10/28/2022> 
 */
public class PriorityQ {

	public LinkPQ first;
	public LinkPQ last;
	private int nItems;

/** 
 *Sets the first and last links in the list to null.
 * 
 * @author <Kenniece Harris> 
 * @version <10/2022/2022> 
 */
	public PriorityQ() {
		
		this.first = null;
		this.last = null;
		this.nItems = 0;
	}

/** 
 *Stores country objects inside of the queue if the first link is empty the the new link is 
 *set to the first and last link, else iterate through the links to find the last link and 
 *set the next link to the new link making it the last.
 * 
 * @param country, a country object.
 */
	public void insert(Country country) {
		
		LinkPQ newLink = new LinkPQ(country);
				
        if(isEmpty()) {
        	first = last = newLink;
        	first.previous = null;
        }
		
        else {
        	
        	LinkPQ temp = first;
        	
        	while(temp.next != null) {
        		temp = temp.next;
        	}
   
        	temp.next = newLink;
        	newLink.previous = temp;
        	newLink.next = null;
        	last = newLink;
        	
        	
        	
        }
				
	}
	
/** 
 *Deletes the first link in the priority queue by setting it to null and making the next
 *link the new first link in the list.
 * 
 * @return temp, the old first link in the list.
 */
	
	public String remove() {
		Country temp = first.country;
         if(isEmpty()) {
			
			return "The priority queue is empty, cannot remove item from priority queue.";
			
			
		}
         
         else {
        	 
 			first = first.next;
 			first.previous = null;
 			
 			return temp.getCountryData();
         }
         
         
	}

/** 
 *Deletes country objects from the priority queue based on their death rate starting
 *from the beginning of the interval to the end of the interval.
 * 
 * @param start, the beginning of the interval.
 * @param end, the end of the interval.
 */
	public void intervalDelete(int start, int end) {
		LinkPQ current = first;
		
	
		while(current != null) {
			
			if(current.country.getDeathRate() >= start && current.country.getDeathRate() <= end) {
				if(current == first) {
					first = current.next;
				}
				
				else {
					current.previous.next = current.next;
				}
				
				if(current == last) {
					last = current.previous;
				}
				
				else {
					current.next.previous = current.previous;
				}
			}
			
			current =  current.next;
		}
	}

/** 
 *Prints the priority queue recursively starting from the last link in the queue.
 * 
 * @param tail, the last link in the queue.
 */
	public void printPriorityQ(LinkPQ tail) {
		
		if(tail == null) {
			return;
		}
		
		else {
			printPriorityQ(tail.previous);
			tail.display();
		}
	}
	
/** 
 *Checks the first link in the priority queue to see if it is empty.
 * 
 * @return if first is null, return true, else false.
 */
	public boolean isEmpty() {
		
		return (first == null);
	}

}

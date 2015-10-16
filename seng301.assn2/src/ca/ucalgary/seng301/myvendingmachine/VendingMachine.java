/* Name: 	J. Daniel Gonzalez
 * UCID:	10058656
 * Class:	SENG 301
 * Ass:		2
 * 
 * Notes: Code from assignment 1 solution given out by instructor used in assignment 1
 */


package ca.ucalgary.seng301.myvendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.VendingMachineStoredContents;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinRack;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;

public class VendingMachine {

	//TODO: Potentially unnecessary declarations:
    private Map<Integer, ArrayList<Coin>> coins;
    private Map<Integer, Integer> valueToIndexMap = new TreeMap<>();

    
    //Required declarations
    private int[] coinKinds;
    private CoinRack cr;
    
	
		//the constructor for vending machine appropriately serves as the "construct" method
	public VendingMachine(List<Integer> coinKinds, int selectionButtonCount, int coinRackCapacity, int popCanRackCapacity, int receptacleCapacity){
			//error checking
		if(coinKinds == null || coinKinds.size() < 1)
		    throw new IllegalArgumentException("There must be at least one coin kind");
	
		if(selectionButtonCount < 1)
		    throw new IllegalArgumentException("There must be at least one selection button");
		
			//setting coin types 
		this.coinKinds = new int[coinKinds.size()];
		coins = new HashMap<>();
		int i = 0;
		for(Integer ck : coinKinds) {
		    Integer index = valueToIndexMap.get(ck);
		    if(index != null)
		    	throw new IllegalStateException("Coin kinds must have unique denominations");
		    if(ck <= 0)
		    	throw new IllegalArgumentException("Each coin kind must be postive");
	
		    valueToIndexMap.put(ck, i);
		    this.coinKinds[i++] = ck.intValue();
		}
		
		
			//setting coin rack capacity
		cr = new CoinRack(coinRackCapacity);
		
			//loading coins
		
		
			//setting pop can rack capacity
		
		
			//loading pop cans
		
		
			//setting receptable capacity
		
		
		
		
		//TODO: delete this
		
		 
		this.coinKinds = new int[coinKinds.size()];
		coins = new HashMap<>();
		int i = 0;
		for(Integer ck : coinKinds) {
		    Integer index = valueToIndexMap.get(ck);
		    if(index != null)
		    	throw new IllegalStateException("Coin kinds must have unique denominations");
		    if(ck <= 0)
		    	throw new IllegalArgumentException("Each coin kind must be postive");
	
		    valueToIndexMap.put(ck, i);
		    this.coinKinds[i++] = ck.intValue();
		}
	
		popKindNames = new String[buttonCount];
		for(int j = 0; j < popKindNames.length; j++)
		    popKindNames[j] = "";
	
		popKindCosts = new int[buttonCount];
		pops = new HashMap<>();
		
		
		
	}
	
    public List<Object> extract() {
		// TODO Replace this implementation
		return new ArrayList<>();
    }

    public void insert(int value) throws DisabledException {
	// TODO
    	
    }

    public void press(int value) {
	// TODO
    	
    }

    public void configure(List<String> popNames, List<Integer> popCosts) {
	// TODO
    	
    }

    public void load(List<Integer> coinCounts, List<Integer> popCanCounts) {
	// TODO
    	
    }

    public VendingMachineStoredContents unload() {
	// TODO Replace this implementation
		return new VendingMachineStoredContents();
    }
    
    
}

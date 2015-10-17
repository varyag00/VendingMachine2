
/* Name: 	J. Daniel Gonzalez
 * UCID:	10058656
 * Class:	SENG 301
 * Ass:		2
 * 
 * Sources: 	Code from the assignment 2 skeleton, as well as the assignment 1 solution given out by instructor was used in assignment 2
 */


package ca.ucalgary.seng301.myvendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.PopCan;
import ca.ucalgary.seng301.vendingmachine.VendingMachineStoredContents;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinChannel;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinRack;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinReceptacle;
import ca.ucalgary.seng301.vendingmachine.hardware.DeliveryChute;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;
import ca.ucalgary.seng301.vendingmachine.hardware.PopCanRack;

public class Temp {

	//TODO: Potentially unnecessary declarations:
    private Map<Integer, Integer> valueToIndexMap = new TreeMap<>();

    private Map<Integer, ArrayList<Coin>> coins;
    
    private String[] popKindNames;
    private int[] popKindCosts;
    private Map<Integer, ArrayList<PopCan>> pops;
    
    private DeliveryChute deliveryChute;
    private VendingMachineStoredContents vmStoredContents;
    private CoinChannel coinChannel;
    
    //Required declarations
    private int[] coinKinds;
    private CoinRack[] crArray; 			//array of coin racks,  TODO: connect each CoinRack to a CoinChannel (within Constructor)
    private PopCanRack[] pcArray;			//array of pop can racks, TODO: connect each PopCanRack to a PopCanChannel (within Constructor)
	private CoinReceptacle cReceptacle;		//a single coin receptable for the vending machine
    
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
			
			//setting coin rack capacity for each coin
		crArray = new CoinRack[coinKinds.size()]; 		
		for (CoinRack element : crArray){
			element = new CoinRack(coinRackCapacity);
		}	
		
			//setting pop can rack capacity
		pcArray = new PopCanRack[coinKinds.size()]; 		
		for (PopCanRack element : pcArray){
			element = new PopCanRack(popCanRackCapacity);
		}	
	
			//setting popCanNames, popKindCosts, and our buttons corresponding tol each 
		popKindNames = new String[selectionButtonCount];
		for(int j = 0; j < popKindNames.length; j++)
		    popKindNames[j] = "";
	
		popKindCosts = new int[selectionButtonCount];
		
		pops = new HashMap<>();
		
			//setting receptacle capacity
		cReceptacle = new CoinReceptacle(receptacleCapacity);
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

    	//DONE
    public void configure(List<String> popNames, List<Integer> popCosts) {
    		//error checking
		if(popNames == null || popNames.size() != popKindNames.length)
		    throw new IllegalArgumentException("The names list must be of identical size as the number of selection buttons");
	
		if(popCosts == null || popCosts.size() != popKindCosts.length)
		    throw new IllegalArgumentException("The costs list must be of identical size as the number of selection buttons");
	
		int i = 0;
		for(String name : popNames) {
		    if(name.equals("\"\""))
		    	throw new IllegalArgumentException("Names cannot have zero length");
		    popKindNames[i++] = name;
		}
		
		i = 0;
		for(Integer cost : popCosts){
			if (cost < 0)
				throw new IllegalArgumentException("Costs cannot be negative");

		    popKindCosts[i++] = cost.intValue();
		}
	}

    	//TODO: change this method to use coinracks and popcanracks
    public void load(List<Integer> coinCounts, List<Integer> popCanCounts) {
    	
		if(coinCounts == null || popCanCounts == null || coinCounts.size() < 0 || popCanCounts.size() < 0)
		    throw new IllegalArgumentException("Neither list may be empty");
	
		int ck = 0;
		for(Integer coinCount : coinCounts) {
		    ArrayList<Coin> coinList = coins.get(ck);
		    if(coinList == null) {
				coinList = new ArrayList<>();
				coins.put(ck, coinList);
		    }
	
		    for(int i = 0; i < coinCount; i++)
		    	coinList.add(new Coin(coinKinds[ck]));
	
		    ck++;
		}
    	
		//instantiating PopCans in our array of PopCans
		int pk = 0;
		for(Integer popCount : popCanCounts) {
		    ArrayList<PopCan> popList = pops.get(pk);
		    if(popList == null) {
				popList = new ArrayList<>();
				pops.put(pk, popList);
		    }
		    for(int i = 0; i < popCount; i++)
		    	popList.add(new PopCan(popKindNames[pk]));
	
		    pk++;
		}
    }

    public VendingMachineStoredContents unload() {
	// TODO Replace this implementation
    	

    	// Must update vmStoredContents.unusedCoinsForChange  		--DONE
    	// vmStoredContents.unsoldPopCans
    	// vmStoredContents.paymentCoinsInStorageBin
    	
		//ArrayList<Object> list = new ArrayList<>();

    	vmStoredContents = new VendingMachineStoredContents(); 
    	
			//adds unsold popcans to vmStoredContents
		List<List<Coin>> unusedCoinList;
		unusedCoinList = new ArrayList<List<Coin>>();
	
		for(CoinRack cr : this.crArray){
			unusedCoinList.add(cr.unloadWithoutEvents());
		}
		vmStoredContents.unusedCoinsForChange = unusedCoinList;

			//adds unsold popcans to vmStoredContents
		List<List<PopCan>> unsoldPopCanList;
		unsoldPopCanList = new ArrayList<List<PopCan>>();
	
		for(PopCanRack pcr : this.pcArray){
			unsoldPopCanList.add(pcr.unloadWithoutEvents());
		}
		vmStoredContents.unsoldPopCans = unsoldPopCanList;
		
		List<Coin> unusedPaymentCoinsInStorageBin;
    	
		return vmStoredContents;
    }
    
    
}


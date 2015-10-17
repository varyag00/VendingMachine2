package ca.ucalgary.seng301.myvendingmachine;

import java.util.ArrayList;
import java.util.List;

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.PopCan;
import ca.ucalgary.seng301.vendingmachine.VendingMachineStoredContents;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinRack;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;
import ca.ucalgary.seng301.vendingmachine.hardware.PopCanRack;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class VendingMachineLogic {
		
	private VendingMachine vm;
    private VendingMachineStoredContents vmStoredContents;

    	//sets the corresponding vending machine this class' methods will act upon
	public VendingMachineLogic(VendingMachine vm){
		this.vm = vm;
	}
    
    	//TODO
    public void press(int value){
    	
    }
    
    	//TODO
    public void insert(int value) throws DisabledException {
    
    }
    
		//TODO
    public void load(List<Integer> coinCounts, List<Integer> popCanCounts) {
    	
		if(coinCounts == null || popCanCounts == null || coinCounts.size() < 0 || popCanCounts.size() < 0)
		    throw new IllegalArgumentException("Neither list may be empty");
    	
    	//loading coins
		int[] coinCountsArray = new int[coinCounts.size()];
		int i = 0;
    	for (Integer count : coinCounts){
    		coinCountsArray[i] = count;
    		i++;
    	}
    	vm.loadCoins(coinCountsArray);
    	
    	//loading pops
		int[] popCanCountsArray = new int[popCanCounts.size()];
		i = 0;
    	for (Integer count : popCanCounts){
    		popCanCountsArray[i] = count;
    		i++;
    	}
    	vm.loadCoins(popCanCountsArray);	
    }
	
    	//TODO
    public VendingMachineStoredContents unload() {
    	// TODO Replace this implementation
        	
    	vmStoredContents = new VendingMachineStoredContents(); 
    	
		//adds unused coins to vmStoredContents
		List<List<Coin>> unusedCoinList;
		unusedCoinList = new ArrayList<List<Coin>>();

    	for (int i = 0; i < vm.getNumberOfCoinRacks(); i++){
    		unusedCoinList.add(vm.getCoinRack(i).unloadWithoutEvents());
    	}
		vmStoredContents.unusedCoinsForChange = unusedCoinList;

		//adds unusedPaymentCoinsInStorageBin to vmStoredContents
		List<Coin> paymentCoinsInStorageBin = vm.getStorageBin().unloadWithoutEvents();
		vmStoredContents.paymentCoinsInStorageBin = paymentCoinsInStorageBin;
		
		//adds unsold popcans to vmStoredContents
		List<List<PopCan>> unsoldPopCanList;
		unsoldPopCanList = new ArrayList<List<PopCan>>();

    	for (int i = 0; i < vm.getNumberOfPopCanRacks(); i++){
    		unsoldPopCanList.add(vm.getPopCanRack(i).unloadWithoutEvents());
    	}
		vmStoredContents.unsoldPopCans = unsoldPopCanList;
		
		return vmStoredContents;
    }
    
    
    
	//TODO
	public List<Object> extract(){
		return new ArrayList<>();
	}



}

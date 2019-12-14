package acptTests;

import bridgeProject.BridgeProject;
import bridgeProject.BridgeProxy;
import bridgeProject.BridgeReal;

public abstract class Driver {

	public static BridgeProject getBridge() {
		BridgeProxy bridge = new BridgeProxy();
		bridge.setRealBridge(new BridgeReal()); // add real bridge here
		return bridge;
	}	
}
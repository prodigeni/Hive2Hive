package org.hive2hive.core.network.data.futures;

import net.tomp2p.futures.BaseFutureAdapter;
import net.tomp2p.futures.FuturePut;
import net.tomp2p.peers.Number160;

import org.hive2hive.core.network.data.DataManager;
import org.hive2hive.core.network.data.IPutListener;
import org.hive2hive.core.network.data.NetworkContent;

public class FuturePutUserMessageListener extends BaseFutureAdapter<FuturePut> {

	public FuturePutUserMessageListener(String locationKey, Number160 contentKey,
			NetworkContent content, IPutListener listener, DataManager dataManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void operationComplete(FuturePut future) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

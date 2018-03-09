package com.blockchain.service;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import com.blockchain.entity.Block;
import com.blockchain.util.GsonUtil;

/**
 * 广播线程
 * */
public class BroadcastThread extends Thread {

	private Socket socket = null;
	private List<Block> blockChain;

	public BroadcastThread(Socket socket, List<Block> blockChain) {
		this.socket = socket;
		this.blockChain = blockChain;
	}

	@Override
	public void run() {
		for (;;) {
			PrintWriter pw = null;
			try {
				Thread.sleep(30000);
				System.out.println("\n------------broadcast-------------\n");
				pw = new PrintWriter(socket.getOutputStream());
				// 发送到其他结点
				pw.write(GsonUtil.getGson().toJson(blockChain));
				pw.write("------------broadcast-------------\n");
				pw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

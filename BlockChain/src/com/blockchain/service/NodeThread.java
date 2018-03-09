package com.blockchain.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import com.blockchain.entity.Block;
import com.blockchain.util.Common;
import com.blockchain.util.GsonUtil;

/**
 * 结点链通讯线程
 * */
public class NodeThread extends Thread {

	private Socket socket = null;
	private List<Block> blockChain = null;

	public NodeThread(Socket socket, List<Block> blockChain) {
		this.socket = socket;
		this.blockChain = blockChain;
	}

	/**
	 * 如果有别的链比你长，就用比你长的链作为当前链
	 * 
	 * @param newBlocks
	 * */
	public void replaceChain(List<Block> newBlocks) {
		if (newBlocks.size() > blockChain.size()) {
			blockChain = newBlocks;
		}
	}

	@Override
	public void run() {
		InetAddress address = socket.getInetAddress();
		System.out.println("New collected,node IP：" + address.getHostAddress() + " ,port：" + socket.getPort());
		BufferedReader br = null;
		PrintWriter pw = null;
		try {// 读取连接结点发送的信息
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
			pw.write("please enter a new number(data):\n");
			pw.flush();

			String data = null;
			while ((data = br.readLine()) != null) {
				try {// 根据data创建区块
					Block lastBlock = blockChain.get(blockChain.size() - 1);
					Block newBlock = Common.generateBlock(lastBlock, data);
					if (Common.isBlockValid(newBlock, lastBlock)) {
						blockChain.add(newBlock);
						pw.write("Success!\n");
						pw.write(GsonUtil.getGson().toJson(blockChain));
					} else {
						pw.write("HTTP 500: Invalid Block Error\n");
						System.out.println("HTTP 500: Invalid Block Error\n");
						continue;
					}
					System.out.println("add new block with vac：" + data);
				} catch (Exception e) {
					pw.write("not a number! \n");
					e.printStackTrace();
				}
				pw.write("Please enter a new number(vac):" + "\n");
				pw.flush();// 调用flush()方法将缓冲输出
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("node closed:" + address.getHostAddress() + ",port:" + socket.getPort());
			try {// 关闭资源
				if (pw != null) {
					pw.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

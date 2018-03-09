package com.blockchain.entity;

/**
 * 区块
 * */
public class Block {
	
	private int index;//区块编号
	private String timeStamp;//区块生成时间
	private String data;//交易信息
	private String myHash;//自己的hash值
	private String preHash;//上个区块的Hash
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMyHash() {
		return myHash;
	}
	public void setMyHash(String myHash) {
		this.myHash = myHash;
	}
	public String getPreHash() {
		return preHash;
	}
	public void setPreHash(String preHash) {
		this.preHash = preHash;
	}
	
	@Override
	public String toString() {
		return "Block [index=" + index + ", timeStamp=" + timeStamp + ", data=" + data + ", myHash=" + myHash + ", preHash=" + preHash + "]";
	}

}

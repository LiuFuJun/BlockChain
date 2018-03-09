package com.blockchain.util;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import com.blockchain.entity.Block;

public class Common {

	/**
	 * 计算区块的hash值
	 * 
	 * @param block 区块
	 * @return
	 * */
	public static String calculateHash(Block block) {
		String record = (block.getIndex()) + block.getTimeStamp() + block.getData() + block.getPreHash();
		MessageDigest digest = DigestUtils.getSha256Digest();
		byte[] hash = digest.digest(StringUtils.getBytesUtf8(record));
		return Hex.encodeHexString(hash);
	}

	/**
	 * 区块的生成
	 * 
	 * @param oldBlock
	 * @param data
	 * @return
	 * */
	public static Block generateBlock(Block oldBlock, String data) {
		Block newBlock = new Block();
		newBlock.setIndex(oldBlock.getIndex() + 1);
		newBlock.setTimeStamp(TimeUtil.getFullFormatStandardTime());
		newBlock.setData(data);
		newBlock.setPreHash(oldBlock.getMyHash());
		newBlock.setMyHash(calculateHash(newBlock));
		return newBlock;
	}

	/**
	 * 校验区块的合法性（有效性）
	 * 
	 * @param newBlock
	 * @param oldBlock
	 * @return
	 * */
	public static boolean isBlockValid(Block newBlock, Block oldBlock) {
		if (oldBlock.getIndex() + 1 != newBlock.getIndex()) {
			return false;
		}
		if (!oldBlock.getMyHash().equals(newBlock.getPreHash())) {
			return false;
		}
		if (!calculateHash(newBlock).equals(newBlock.getMyHash())) {
			return false;
		}
		return true;
	}

}

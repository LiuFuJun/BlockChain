package com.blockchain.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.blockchain.entity.Block;
import com.blockchain.entity.BlockChain;
import com.blockchain.util.Common;

public class BlockOperating {

	public static final List<Block> getBlockData() {
		return BlockChain.blocks;
	}

	public static final String addBlockData(String data) {
		try {
			if (StringUtils.isEmpty(data)) {
				return "data is null";
			}
			Block lastBlock = BlockChain.blocks.get(BlockChain.blocks.size() - 1);
			Block newBlock = Common.generateBlock(lastBlock, data);
			if (Common.isBlockValid(newBlock, lastBlock)) {
				BlockChain.blocks.add(newBlock);
				return "success";
			} else {
				return "HTTP 500: Invalid Block Error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}

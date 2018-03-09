package com.blockchain.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blockchain.entity.Block;
import com.blockchain.entity.BlockChain;
import com.blockchain.service.BlockOperating;
import com.blockchain.util.Common;
import com.blockchain.util.TimeUtil;

@Controller
@RequestMapping(value = "/block")
public class BlockController extends BaseController {

	static {
		Block block = new Block();
		block.setIndex(0);
		block.setTimeStamp(TimeUtil.getFullFormatStandardTime());
		block.setData("创世区块");
		block.setPreHash("");
		block.setMyHash(Common.calculateHash(block));
		BlockChain.blocks.add(block);
	}

	@RequestMapping(value = "/findAllBlocks")
	public void findAllBlocks(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Block> blocks = BlockOperating.getBlockData();
			super.outJson(response, blocks);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addBlock")
	public void addBlock(HttpServletRequest request, HttpServletResponse response) {
		try {
			String data = request.getParameter("data");
			String msg = BlockOperating.addBlockData(data);
			super.outString(response, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

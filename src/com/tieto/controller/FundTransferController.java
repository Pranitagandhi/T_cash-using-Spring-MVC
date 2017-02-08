package com.tieto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tieto.Bean.FundTransfer;
import com.tieto.dao.FundTransferDao;

@Controller
public class FundTransferController {
	@Autowired
	FundTransferDao dao;

	@RequestMapping("/FundTForm")
	public ModelAndView showform() {
		return new ModelAndView("FundTForm", "command", new FundTransfer());
	}

	@RequestMapping(value = "/Submit", method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("emp") FundTransfer ft) {
		if(dao.Submit(ft)==1)
		{
		String message = "Fund is Transferred successfully...";
		return new ModelAndView("view","message",message);
		}
		else
		{
			String message = "Fund is Insuficient to transfer...";
			return new ModelAndView("view","message",message);
		}
		
	}

	@RequestMapping("/view")
	public ModelAndView view() {
		
		return new ModelAndView("redirect:/view");
	}

}


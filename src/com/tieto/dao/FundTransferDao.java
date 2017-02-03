package com.tieto.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.tieto.Bean.FundTransfer;

public class FundTransferDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int Submit(FundTransfer p) {
		String number = p.getAmount();
		int result = Integer.parseInt(number);
		String p2 = p.getUser2();
		String sql = "update fund1 set amount=amount+" + result + " where name1='" + p2 + "'";
		update1(p);
		return template.update(sql);
	}

	public int update1(FundTransfer p1) {
		String pa = p1.getUser1();
		String number = p1.getAmount();
		int result = Integer.parseInt(number);
		String sql = "update fund1 set amount=amount-" + result + " where name1='" + pa + "'";
		return template.update(sql);
	}

}

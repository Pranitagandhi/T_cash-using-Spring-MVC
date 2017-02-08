package com.tieto.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import com.tieto.Bean.FundTransfer;

public class FundTransferDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public int Submit1(FundTransfer p) {
		String number = p.getAmount();
		int result = Integer.parseInt(number);
		String p2 = p.getUser2();
		//credit(p);
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

	public int Submit(FundTransfer p2) {

		String pc = p2.getAmount();
		int result = Integer.parseInt(pc);
		System.out.println(result);
		String p3 = p2.getUser1();
		System.out.println(p3);
		int sql = template.queryForInt("select amount from fund1 where name1='" + p3 + "'");
		if (sql >= result) {
			Submit1(p2);
			credit(p2);
			debit(p2);
			return 1;
		} else {
			System.out.println("insufficcient funds");
			return 2;
		}
	}

	public int credit(FundTransfer p1) {
		String n1 = p1.getAmount();
		int result = Integer.parseInt(n1);
		String p2 = p1.getUser2();
		String sql = "insert into transaction1 values(DEFAULT,'"+p2+"',CURRENT_TIMESTAMP(),"+n1+",0)";
		return template.update(sql);
	}

	public int debit(FundTransfer p1) {
		String n1 = p1.getAmount();
		int result = Integer.parseInt(n1);
		String p2 = p1.getUser1();
		String sql = "insert into transaction1 values(DEFAULT,'"+p2+"',CURRENT_TIMESTAMP(),0,"+n1+")";
		return template.update(sql);
	}

}

package com.lime.board.vo;

public class BoardVO {
	String profitCost ,bigGroup, middleGroup ,smallGroup ,detailGroup ,regDate ,writer ,accountSeq;
	int transactionMoney;
	public String getProfitCost() {
		return profitCost;
	}
	public void setProfitCost(String profitCost) {
		this.profitCost = profitCost;
	}
	public String getBigGroup() {
		return bigGroup;
	}
	public void setBigGroup(String bigGroup) {
		this.bigGroup = bigGroup;
	}
	public String getMiddleGroup() {
		return middleGroup;
	}
	public void setMiddleGroup(String middleGroup) {
		this.middleGroup = middleGroup;
	}
	public String getSmallGroup() {
		return smallGroup;
	}
	public void setSmallGroup(String smallGroup) {
		this.smallGroup = smallGroup;
	}
	public String getDetailGroup() {
		return detailGroup;
	}
	public void setDetailGroup(String detailGroup) {
		this.detailGroup = detailGroup;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(String accountSeq) {
		this.accountSeq = accountSeq;
	}
	public int getTransactionMoney() {
		return transactionMoney;
	}
	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	@Override
	public String toString() {
		return "BoardVO [profitCost=" + profitCost + ", bigGroup=" + bigGroup + ", middleGroup=" + middleGroup
				+ ", smallGroup=" + smallGroup + ", detailGroup=" + detailGroup + ", regDate=" + regDate + ", writer="
				+ writer + ", accountSeq=" + accountSeq + ", transactionMoney=" + transactionMoney + "]";
	}
	
	
}

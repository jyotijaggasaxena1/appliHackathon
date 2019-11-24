package com.applitools.utils;

/**
 * Populate transactions using builder pattern.
 * 
 * @author jyotisaxena
 *
 */
public class Transactions {
	private String status;
	private String transactionDate;
	private String description;
	private String category;
	private float amount;

	public static class Builder {
		private String status;
		private String transactionDate;
		private String description;
		private String category;
		private float amount;

		public Builder(String description) {
			this.description = description;
		}

		public Builder withDate(String date) {
			this.transactionDate = date;
			return this;
		}

		public Builder withCategory(String category) {
			this.category = category;
			return this;
		}

		public Builder withAmount(float amount) {
			this.amount = amount;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withStatus(String status) {
			this.status = status;
			return this;
		}

		public Transactions build() {
			Transactions transactions = new Transactions();
			transactions.status = this.status;
			transactions.transactionDate = this.transactionDate;
			transactions.category = this.category;
			transactions.description = this.description;
			transactions.amount = this.amount;

			return transactions;

		}

	}

	private Transactions() {

	}

	public String getStatus() {
		return status;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public float getAmount() {
		return amount;
	}

}

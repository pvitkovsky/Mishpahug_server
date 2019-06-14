package application.configurations.dbloader;

//TODO: profile?
public enum MPHEntity {
	USER {
		public String dataFile() {
			return "data_user.csv";
		}
	},
	CITY {
		public String dataFile() {
			return "cities.csv";
		}
	},
	EVENT {
		public String dataFile() {
			return "data_event.csv";
		}
	},
	MARRIAGE {
		public String dataFile() {
			return "marriage_status.csv";
		}
	},
	GENDER {
		public String dataFile() {
			return "gender.csv";
		}
	},
	ADDRESS {
		public String dataFile() {
			return "streets.csv";
		}
	},
	KICHENTYPES {
		public String dataFile() {
			return "foods.csv";
		}
	},
	RELIGION {
		public String dataFile() {
			return "religions.csv";
		}
	},
	GUESTS {
		public String dataFile() {
			return "data_blank.csv";
		}
	},
	TEMPLATE {
		public String dataFile() {
			return "data_temp.csv";
		}
	},
	HOLIDAYS {
		public String dataFile() {
			return "holidays.csv";
		}
	},
	LOGS {
		public String dataFile() {
			return "data_blank.csv";
		}
	};

	public abstract String dataFile();
}
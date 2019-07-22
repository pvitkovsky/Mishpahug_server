package application.configurations.dbloader;

//TODO: profile?
public enum MPHEntity {
	USER {
		public String dataFile() {
			return "data_user.csv";
		}
	},
	EVENT {
		public String dataFile() {
			return "data_event.csv";
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
	},
	CHOICES {
		public String dataFile() {
			return "data_blank.csv";
		}
	};

	public abstract String dataFile();
}
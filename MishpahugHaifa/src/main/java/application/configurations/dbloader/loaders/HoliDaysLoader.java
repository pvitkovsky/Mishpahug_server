package application.configurations.dbloader.loaders;

import application.configurations.dbloader.LoaderDependencies;
import application.entities.properties.HoliDayEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

import javax.transaction.Transactional;

/**
 * Loads ds
 */
@Slf4j
@Transactional
public class HoliDaysLoader implements ILoader{
	
	@Autowired 
	LoaderDependencies data;

	private BufferedReader br;
	
	public HoliDaysLoader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void load() {
		try {
			String detail;
			while ((detail = br.readLine()) != null) {
				String[] data = detail.split("/");
				String[] types = data[3].split("!");
				String[] dateSplit = data[2].split("-");
				HoliDayEntity holiDayEntity = new HoliDayEntity();
				holiDayEntity.setName(data[0]);
				LocalDate localDate = LocalDate.of(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));
				holiDayEntity.setDate(localDate);
				holiDayEntity.setDescription(data[1]);
				log.debug("DBLoadTest -> HolidayLoader -> holidayentity = " + holiDayEntity);
				this.data.holyDayRepository.save(holiDayEntity);
			}
			log.debug("DBLoadTest -> HolidaysLoader -> In repository " + this.data.holyDayRepository.findAll().size() + " records");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
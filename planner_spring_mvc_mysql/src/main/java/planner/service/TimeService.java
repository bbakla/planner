package planner.service;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
	
	public String[] getMonthsOfYear(){
		return new String[]{"January", "February", "March", "April", "May", "June",
		                    "July", "August", "September", "October", "November", "December"};
	}
	
	public String[] getDaysOfWeek() {
		return new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	}
	
	

}

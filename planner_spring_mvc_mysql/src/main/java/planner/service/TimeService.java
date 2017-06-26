package planner.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
	
	public String[] getMonthsOfYear(){
		return new String[]{"January", "February", "March", "April", "May", "June",
		                    "July", "August", "September", "October", "November", "December"};
	}
	
	

}

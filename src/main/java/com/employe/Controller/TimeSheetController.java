package com.employe.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employe.Entity.TimeSheet;
import com.employe.Service.ITimeSheetService;



@RestController
@RequestMapping("/api/timesheet")
public class TimeSheetController {

	@Autowired
	private ITimeSheetService timesheetservice;

	@PostMapping("/save")
	public TimeSheet saveTimeSheet (@RequestBody TimeSheet timesheet) {
		return timesheetservice.saveTimeSheet(timesheet);

	}



	@GetMapping("/list")
	public List<TimeSheet> getTimeSheets(){
		return timesheetservice.TimeSheetList();
	}

	@PutMapping("/update/{id}")
	public TimeSheet updateTimeSheet(@RequestBody TimeSheet timesheet, @PathVariable int id) {
		timesheet.setId(id);
		return timesheetservice.updateTimeSheet(timesheet, id);
	}


	@DeleteMapping("/delete/{id}")
	public boolean deleteTimesheet(@PathVariable int id) {
		 timesheetservice.deleteTimeSheetById(id);
		 return true;
	}


}

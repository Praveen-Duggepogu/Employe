package com.employe.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employe.Dao.TimesheetDao;
import com.employe.Entity.TimeSheet;

@Service
public class TimeSheetService implements ITimeSheetService{

	@Autowired
	private TimesheetDao timesheetdao;


	@Override
	public TimeSheet saveTimeSheet(TimeSheet timesheet) {

		return timesheetdao.saveAndFlush(timesheet);
	}

	@Override
	public List<TimeSheet> TimeSheetList() {

		return timesheetdao.findAll();
	}

	@Override
	public TimeSheet updateTimeSheet(TimeSheet timesheet, int id) {
		timesheet.setId(id);
		return timesheetdao.saveAndFlush(timesheet);
	}

	@Override
	public boolean deleteTimeSheetById(int Id) {
		timesheetdao.deleteById(Id);
		return true;

	}

}

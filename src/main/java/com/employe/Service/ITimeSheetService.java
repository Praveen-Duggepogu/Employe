package com.employe.Service;

import java.util.List;

import com.employe.Entity.TimeSheet;

public interface ITimeSheetService {


	    public TimeSheet saveTimeSheet(TimeSheet timesheet);
		public  List<TimeSheet> TimeSheetList();
		public TimeSheet updateTimeSheet(TimeSheet timesheet, int id);
	    public boolean deleteTimeSheetById(int Id);


}

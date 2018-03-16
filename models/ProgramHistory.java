package models;
import java.io.*;
import java.sql.*;
import java.util.*;

import mappers.*;
public class ProgramHistory {
	ProgramHistoryMapper historyMapper = new ProgramHistoryMapper();
	public String result = "";
	public void displayProgramHistory(Connection conn) throws Exception {
			historyMapper.displayProgramHistory(conn);
			result = historyMapper.result;
	}
	
	public void executeQuery(Connection conn) throws Exception {
			historyMapper.executeQuery(conn);
			result = historyMapper.result;
	}
}

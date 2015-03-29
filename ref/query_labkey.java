package labkey_interface;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.labkey.remoteapi.CommandException;
import org.labkey.remoteapi.Connection;
import org.labkey.remoteapi.query.Filter;
import org.labkey.remoteapi.query.SelectRowsCommand;
import org.labkey.remoteapi.query.SelectRowsResponse;


public class query_labkey {
	
		public String labkey;
		public String user;
		public String password;
		public String query;
		public String output;
		public static Connection cn;
	
	    public query_labkey(String labkey, String user, String password, String query, String output) throws IOException, CommandException{
	    	
	    	this.labkey = labkey;
	    	this.user = user;
	    	this.password = password;
	    	this.query = query;
	    	this.output = output;

	    	query_labkey.cn = new Connection(this.labkey, this.user, this.password);
	    	query_fun(this.query, this.output);
	    	
	    }
	    
	    public static void query_fun(String query_search, String output) throws IOException, CommandException{
	    	SelectRowsCommand cmd = new SelectRowsCommand("lists", "CCC Data");
		    cmd.addFilter("Description", query_search, Filter.Operator.valueOf("CONTAINS"));
		    SelectRowsResponse response = cmd.execute(cn, "project/CCC");
		    
		    PrintWriter writer = new PrintWriter(output, "UTF-8");
		    for(Map<String,Object> row : response.getRows()){
		    	writer.println(row.get("CCC_DID")+"\t"+row.get("Description"));
		    }
		    writer.close();
	    }


	
		public static void main(String[] args) throws IOException, CommandException{
			String url = args[0];
			String user = args[1];
			String password = args[2];
			String query = args[3];
			String output = args[4];
			new query_labkey(url, user, password, query, output);
	    }

}

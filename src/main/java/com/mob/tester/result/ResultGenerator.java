package com.mob.tester.result;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.google.common.io.Files;
import com.mob.tester.dto.StepResult;

public class ResultGenerator {

	
	
	 public static void generateReport() throws IOException{
		
		
		for(String scenario:ResultHolder.scenarioResultHolder.keySet()) {
			ResultHolder.scenarioResultHolder.get(scenario);
		}
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<!DOCTYPE HTML>\n"
				+ "<html>\n"
				+ "   <head>\n"
				+ "      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
				+ "      <title>Final Result</title>\n"
				+ "      <link href=\"./resources/style.css\" rel=\"stylesheet\">\n"
				+ "      <link href=\"https://fonts.googleapis.com/css?family=Muli:300,400,600,700\" rel=\"stylesheet\">\n"
				+ "   </head>\n"
				+ "   <body>\n"
				+ "      <div class=\"body_content\">\n"
				+ "      <div class=\"page_container\">\n"
				+ "         <div class=\"row_base\">\n"
				+ "            <h3 class=\"flex\"> <span class=\"font16 text-primary\">Execution Summary Report</span> </h3>\n"
				+ "         </div>\n"
				+ "         <div class=\"row_base\">\n"
				+ "          \n"
				+ "            <div class=\"row_base\">");
		
		for(String scenario:ResultHolder.scenarioResultHolder.keySet()) {
			ResultHolder.scenarioResultHolder.get(scenario);
		
		//htmlBuilder.append(" <h3 class=\"flex\"><span class=\"font16 text-primary\">Test Scenario Name: <span> "+scenario+"</span></span></h3>");
		htmlBuilder.append("    <div class=\"row_base\">\n"
				+ "               <h3 class=\"flex\"><span class=\"font16 text-primary\">Test Scenario Name: <span> "+scenario+" </span></span></h3>\n"
				+ "            </div>");
		
		htmlBuilder.append("<div class=\"row_base\">\n"
				+ "               <table>\n"
				+ "                  <thead>\n"
				+ "                     <tr>\n"
				+ "                        <th> #Step</th>\n"
				+ "                        <th>Step Description</th>\n"
				+ "                        <th>Expected Data</th>\n"
				+ "                        <th>Actual Data</th>\n"
				+ "                        <th>Status</th>\n"
				+ "                        <th>ScreenShot</th>\n"
				+ "                     </tr>\n"
				+ "                  <tbody>");
		int stepCount=1;
		for(StepResult steps:ResultHolder.scenarioResultHolder.get(scenario)) {
			htmlBuilder.append("<tr name=\"stepRow\">\n"
					+ "                     <td>"+stepCount+"</td>\n"
					+ "                     <td>"+steps.getStepDesc()+"</td>\n"
					+ "                     <td>"+steps.getExpectedData()+"</td>\n"
					+ "                     <td>"+steps.getActualData()+" </td>\n"
					+ "                     <td ><b>"+steps.getStatus()+"</b></td>\n"
					+ "                     <td align=\"center\"><a href=\""+steps.getScreenshotPath()+"\" target=\"_blank\">screenshot</a></td>\n"
					+ "                     </tr> ");
			
		}
		htmlBuilder.append(" </tbody>\n"
				+ "               </table>\n"
				+ "            </div>");
		}
		htmlBuilder.append("   \n"
				+ "         </div>\n"
				+ "      </div>\n"
				+ "      <div class=\"footer\">\n"
				+ "         <div class=\"page_container\">\n"
				+ "            <div class=\"row_base\"> <span class=\"text-right\">Copyright 2022. All Right Reserved.</span> </div>\n"
				+ "         </div>\n"
				+ "      </div>\n"
				+ "   </body>\n"
				+ "</html>");
		//htmlBuilder.append("<body><p>Look at my body!</p></body>");
		///htmlBuilder.append("</html>");
		String html = htmlBuilder.toString();
		Files.write(html.getBytes(),new File(System.getProperty("user.dir")+"/result.html"));
        System.out.println(html);
	}
	
	
	
	
}

package com.yash.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.yash.dao.EventsDAO;
import com.yash.model.Events;

@WebServlet("/generatePDF")
public class PDFGeneratorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Events> events = EventsDAO.selectAllEvents();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=events.pdf");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            
           

            for (Events event : events) {
                Paragraph eventDetails = new Paragraph("Event ID: " + event.getEvent_id());
                eventDetails.add("Event Name: " + event.getEvent_name() + "\n");
                eventDetails.add("Event Date: " + event.getEvent_date() + "\n");
                eventDetails.add("Start Time: " + event.getStart_time() + "\n");
                eventDetails.add("End Time: " + event.getEnd_time() + "\n");
                eventDetails.add("Description: " + event.getDescription() + "\n");

                document.add(eventDetails);
                document.add(new Paragraph("\n"));
            }

            document.close();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
package eu.isdc.internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

public class FirstServlet extends HttpServlet {

	@Override
	public void service(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		final ApplicationContext context = (ApplicationContext) getServletContext()
				.getAttribute(
						"org.springframework.web.context.WebApplicationContext.ROOT");
		getServletContext().getAttributeNames();
		final SessionFactory sf = context.getBean(SessionFactory.class);

		request.getRequestURL();
		request.getParameter("name");

		RepositoryDB repo=new RepositoryDB();
		
		repo.registerUser("Eliza","parola", new Date());
		
		final PrintWriter writer = response.getWriter();
		writer.println("The date is: " + new Date());
	}
}

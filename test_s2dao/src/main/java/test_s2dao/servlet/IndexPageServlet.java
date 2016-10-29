package test_s2dao.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import test_s2dao.entity.BlogEntity;
import test_s2dao.service.H2Service;

@WebServlet("")
public class IndexPageServlet extends HttpServlet {

	// @Resource
	// protected JdbcManager jdbcManager;
	//
	public void init() {
		System.out.println("init");
		SingletonS2ContainerFactory.init();
	}

	public void destroy() {
		SingletonS2ContainerFactory.destroy();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			req.setCharacterEncoding("SHIFT-JIS");
			pw.println("test");
			// Main2 m = new Main2();
			// pw.println(m.aaa());
			SingletonS2ContainerFactory.init();
			H2Service service = (H2Service) SingletonS2Container.getComponent("h2Service");
			if (null == service) {
				pw.println("service is null");
			}
			ArrayList<BlogEntity> entityList = service.settei(10);
			for (BlogEntity entity : entityList) {
				pw.println("id1:" + " title:" + entity.getTitle());
			}

		} catch (Exception e) {
			e.printStackTrace();
			pw.println(e);
		} finally {
			pw.close();
		}

	}
}

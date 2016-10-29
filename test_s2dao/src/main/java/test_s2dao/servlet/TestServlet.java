package test_s2dao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.servlet.S2ContainerServlet;

import test_s2dao.entity.BlogEntity;
import test_s2dao.service.H2Service;

@WebServlet("/2")
public class TestServlet extends S2ContainerServlet {
	@Resource
	protected H2Service h2Service;

	public void init() {
		System.out.println("init");
		super.init();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			resp.setContentType("text/plain; charset=UTF-8");
			pw.println("test");
			if (null == h2Service) {
				pw.println("service is null");
				SingletonS2ContainerFactory.init();
				S2Container s2c = getContainer();
				h2Service = (H2Service) s2c.getComponent(H2Service.class);
			}
			ArrayList<BlogEntity> entityList = h2Service.settei(10);
			for (BlogEntity entity : entityList) {
				pw.println("id10:" + " title:" + entity.getTitle());
			}

		} catch (Exception e) {
			e.printStackTrace();
			pw.println(e);
		} finally {
			pw.close();
		}

	}
}

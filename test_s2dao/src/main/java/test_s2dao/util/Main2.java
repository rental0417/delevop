package test_s2dao.util;

import java.util.ArrayList;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import test_s2dao.entity.BlogEntity;
import test_s2dao.service.H2Service;

public class Main2 {

	public static void main(String[] args) {

		Main2 aa = new Main2();
		System.out.println(aa.aaa());

		SingletonS2ContainerFactory.destroy();
	}

	public String aaa() {

		// SingletonS2ContainerFactory.setConfigPath("app2.dicon");
		// //●こうやると初期化時に読み込むdiconを変更することができる（多分initメソッドを実行するとこの設定が実行されるんだろう）
		SingletonS2ContainerFactory.init(); // 「app.dicon」を読み込む。●これを省略できる方法が知りたい。Webアプリは自動的に呼び出すらしいが。
		H2Service aaa = (H2Service) SingletonS2Container.getComponent("h2Service"); // Smart
																					// deployしたときの名前がこれらしい。

		ArrayList<BlogEntity> entityList = aaa.settei(3);
		SingletonS2ContainerFactory.destroy();
		for (BlogEntity entity : entityList) {
			return entity.getTitle();
		}
		return null;
	}
}

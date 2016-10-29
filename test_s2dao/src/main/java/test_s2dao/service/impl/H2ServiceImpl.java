package test_s2dao.service.impl;


import java.util.ArrayList;

import javax.annotation.Resource;

import test_s2dao.dao.H2tableDao;
import test_s2dao.entity.BlogEntity;
import test_s2dao.entity.H2TableBean;
import test_s2dao.service.H2Service;

public class H2ServiceImpl implements H2Service {

	@Resource
	private H2tableDao h2tableDao;

	//public Logger log = org.seasar.framework.log.Logger.getLogger(this.getClass());//●これってなんなんだろう。

	@Override
	public ArrayList<BlogEntity> settei(Integer id) {
		ArrayList<BlogEntity> entityList= h2tableDao.selectByIdAndGetTitle(id);
		return entityList;
	}

	@Override
	public int setteiMId(String a) {
		return 1;
	}

	//●あとでprivateにしてテストを書いてみる。いやpublicとprivateで書いてみよう。
	//●ここで、このメソッドの中に、インジェクションのやつがあったから、外に追い出したんだな。ようやく原因を突き止めた。ようやく解決策を考える段階にいけた。
	//●エラーにもならないからちゃんと知識がないとなんで動かないのかわからない。慎重に調査すること。
	//●しかもこのメソッドは、インタフェース側で実装されていない。インジェクションをするのなら、インタフェース側にもメソッドを書いておかないとなのに。mokurokuもまったく同じことをやっていた。だからインジェクションが行われてなかったんだ。
	public H2TableBean privatetest(Integer id){
		H2TableBean bean = h2tableDao.selectById(id);
		return bean;
	}

	//●フィールドのインジェクションを排除したメソッドを作った。●変わりに引数に材料を事前につめないといけなくなった。クラスのインジェクションされるフィールドが多いほどこのやり方だとしんどくなってくる。やっぱり、インジェクションに逆らわないほうがいいかな。
	public H2TableBean privatetest2(H2TableBean bean){
		return bean;
	}
}

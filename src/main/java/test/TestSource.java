package test;

import java.util.Properties;

public abstract class TestSource {

	/*
	 * 测试数据
	 */
	public Properties property = new Properties();
	
/*
 * @param fileName 文件名
 */
	public abstract void init(String fileName);
}

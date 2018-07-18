package cn.hfbin.seckill.redis;

public abstract class BasePrefix implements KeyPrefix{


	private String prefix;

	public BasePrefix( String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		String className = getClass().getSimpleName();
		return className+":" + prefix;
	}

}

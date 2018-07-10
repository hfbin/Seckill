package cn.hfbin.seckill.exception;


import cn.hfbin.seckill.result.CodeMsg;

public class HfbinException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMsg cm;
	
	public HfbinException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}
	public CodeMsg getCm() {
		return cm;
	}

}

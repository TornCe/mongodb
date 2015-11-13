package com.hf.mongodb;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class MongoValidator extends Validator {
	protected void validate(Controller controller) {
		validateRequiredString("name", "nameMsg", "请输入姓名!");
		// 验证所需的字符串 验证blog.title 在html中写${titleMsg!}的地方会弹出"请输入Blog标题!"
	}

	@Override
	protected void handleError(Controller controller) {
		// TODO Auto-generated method stub

		String actionKey = getActionKey();
		if (actionKey.equals("/save"))
			controller.render("add.html");
		else if (actionKey.equals("/update"))
			controller.render("edit.html");
		
	}
}

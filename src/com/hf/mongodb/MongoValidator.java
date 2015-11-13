package com.hf.mongodb;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class MongoValidator extends Validator {
	protected void validate(Controller controller) {
		validateRequiredString("name", "nameMsg", "����������!");
		// ��֤������ַ��� ��֤blog.title ��html��д${titleMsg!}�ĵط��ᵯ��"������Blog����!"
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

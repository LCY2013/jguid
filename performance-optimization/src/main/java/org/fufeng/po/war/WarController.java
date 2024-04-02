package org.fufeng.po.war;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/war")
public class WarController {

	@RequestMapping("/hello")
	public String hello() {
		String str = "";
		for(int i=0;i<10;i++) {
			str += i;
		}
		return str;
	}

}

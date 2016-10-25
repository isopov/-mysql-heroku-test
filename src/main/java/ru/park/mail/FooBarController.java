package ru.park.mail;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooBarController {

	private final JdbcTemplate template;

	public FooBarController(JdbcTemplate template) {
		this.template = template;
	}

	@RequestMapping("/url")
	public String url() {
		return System.getenv("CLEARDB_DATABASE_URL");
	}

	@RequestMapping("/foo")
	public void foo(@RequestParam String foo) {
		template.update("insert into foobar(foobar) values(?)", foo);
	}

	@RequestMapping()
	public List<String> list() {
		return template.queryForList("select foobar from foobar", String.class);
	}

}

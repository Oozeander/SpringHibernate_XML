package com.oozeander;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oozeander.model.collection_enum_map.Gender;
import com.oozeander.model.collection_enum_map.User;
import com.oozeander.model.crud.Person;
import com.oozeander.model.embedded.Gamer;
import com.oozeander.model.embedded.GamerId;
import com.oozeander.model.embedded.GamerInformation;
import com.oozeander.model.inheritance.EpicGamesAccount;
import com.oozeander.model.inheritance.SteamAccount;
import com.oozeander.model.lob.Avatar;
import com.oozeander.model.lob.Subscriber;
import com.oozeander.model.mapping.Identity;
import com.oozeander.model.mapping.Professor;
import com.oozeander.model.mapping.Skill;
import com.oozeander.model.mapping.Student;
import com.oozeander.service.collection_enum_map.UserService;
import com.oozeander.service.crud.PersonService;
import com.oozeander.service.embedded.GamerService;
import com.oozeander.service.inheritance.AccountService;
import com.oozeander.service.lob.SubscriberService;
import com.oozeander.service.mapping.ProfessorService;
import com.oozeander.service.mapping.SkillService;
import com.oozeander.service.mapping.StudentService;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
		ctx.registerShutdownHook();

		// CRUD
		PersonService personService = ctx.getBean(PersonService.class);
		List<Person> persons = Arrays.asList(new Person("Billel", "KETROUCI", 24),
				new Person("El Bakay", "BOURAJOINI", 27), new Person("Meriem", "KECHEROUD", 25),
				new Person("Océane", "AKIYEMI", 24));
		persons.stream().forEach(personService::save);
		System.out.println(personService.get());
		personService.update(1L, new Person("Oozeander", "Billel KETROUCI", 24));
		System.out.println(personService.get(1L));
		personService.delete(4L);
		System.out.println(personService.get());

		// ONE TO ONE / MANY TO ONE / MANY TO MANY
		StudentService studentService = ctx.getBean(StudentService.class);
		Identity identity = new Identity("Billel", "KETROUCI", 24);
		List<Skill> skills = Arrays.asList(new Skill("JAVA", 16), new Skill("Hibernate", 16), new Skill("Spring", 14),
				new Skill("Angular", 11));
		Set<Professor> teachers = new HashSet<>(Arrays.asList(new Professor("English"), new Professor("Japanese")));
		Student student = new Student(identity, skills, teachers);
		identity.setStudent(student);
		skills.stream().forEach(skill -> skill.setStudent(student));
		teachers.stream().forEach(prof -> prof.setStudents(new HashSet<>(List.of(student))));

		studentService.save(student);
		System.out.println(studentService.get() + "\n" + ctx.getBean(SkillService.class).get() + "\n"
				+ ctx.getBean(ProfessorService.class).get());

		// COLLECTION / ENUM / MAP
		UserService userService = ctx.getBean(UserService.class);
		userService
				.save(new User(
						List.of("billel.ketrouci@soprasteria.com", "billel.ketrouci@gmail.com",
								"billel.ketrouci@outlook.fr"),
						List.of(new Skill("JAVA", 16), new Skill("Angular", 11)), Gender.MALE,
						Map.of("Java", 16, "Angular", 11)));
		System.out.println(userService.get());

		// EMBEDDED ID / EMBEDDED COMPONENT
		GamerService gamerService = ctx.getBean(GamerService.class);
		gamerService.save(new Gamer(new GamerId("11706818", "Oozeander", "XXXXXXXXX"),
				new GamerInformation(new HashSet<>(Arrays.asList("Rocket League", "Dark Souls 2", "Tales Of Berseria",
						"DB FighterZ", "MGS V : The Phantom Pain")))));
		System.out.println(gamerService.get(new GamerId("11706817", "Oozeander", "XXXXXXXXX")) + "\n"
				+ gamerService.get(new GamerId("11706818", "Oozeander", "XXXXXXXXX")));

		// INHERITANCE
		AccountService accountService = ctx.getBean(AccountService.class);
		Arrays.asList(new SteamAccount(2), new EpicGamesAccount(1)).stream().forEach(accountService::save);
		System.out.println(accountService.get());

		// LOB
		SubscriberService subscriberService = ctx.getBean(SubscriberService.class);
		subscriberService.save(new Subscriber(1L, "Billel", "KETROUCI", getAvatar("D:\\meuf.jpg")));
		System.out.println(subscriberService.get(1L));
		subscriberService.setAvatar(1L, "C:\\Users\\bille\\Desktop");

		ctx.close();
	}

	private static Avatar getAvatar(String path) {
		Avatar avatar = new Avatar(FilenameUtils.getName(path), FilenameUtils.getExtension(path));
		File file = new File(path);
		byte[] img = new byte[(int) file.length()];
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(img);
			inputStream.close();
			StringBuilder fileSize = new StringBuilder();
			fileSize.append(Files.size(Paths.get(path)) / 1024).append(" Ko");
			avatar.setFileSize(fileSize.toString());
			avatar.setFileData(BlobProxy.generateProxy(img));
		} catch (IOException ex) {
			System.out.println("Error in Avatar loading : " + ex.getLocalizedMessage());
		}
		return avatar;
	}
}
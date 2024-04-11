/*
 * 
 */
package com.jeff.puc.messages;

import java.util.Locale;

import com.jeff.puc.dto.ContactDTO;
import com.jeff.puc.dto.StudentDTO;
import com.jeff.puc.dto.TeacherDTO;
import com.jeff.puc.dto.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailMessages.
 */
public class EmailMessages {

	/**
	 * Creates the title.
	 *
	 * @param user the user
	 * @return the string
	 */
	public static String createTitle(UserDTO user) {
		return user.getName() + " seu cadastro foi recebido!";
	}

	/**
	 * Creates the title.
	 *
	 * @param student the student
	 * @return the string
	 */
	public static String createTitle(StudentDTO student) {
		return student.getName() + " seu cadastro foi recebido!";
	}

	/**
	 * Creates the title.
	 *
	 * @param teacher the teacher
	 * @return the string
	 */
	public static String createTitle(TeacherDTO teacher) {
		return teacher.getName() + " seu cadastro foi recebido!";
	}

	/**
	 * Creates the title contact.
	 *
	 * @param contact the contact
	 * @return the string
	 */
	public static String createTitleContact(ContactDTO contact) {
		return contact.getName() + " o seu contato foi recebido!";
	}

	/**
	 * Message to new user logo.
	 *
	 * @param user the user
	 * @return the string
	 */
	public static String messageToNewUserLogo(UserDTO user) {
		return

		"<div>Olá " + user.getName() + "! Seja muito bem-vindo(a) ao IdiomaPlus. " + "<br>"
				+ "Os seus dados e sua senha de acesso estão logo abaixo:</div>" + "<br>"
				+ "<div>==============================================================</div>"
				+ "<div><strong>Nome: <strong>" + user.getName() + "</div>" + "<div><strong>Usuário: <strong>"
				+ user.getUsername() + "</div>" + "<div><strong>E-mail: <strong>" + user.getEmail() + "</div>"
				+ "<div><strong>Password: <strong>" + user.getPassword() + "</div>"
				+ "<div>=============================================================</div>" + "<br>";
	}

	/**
	 * Message to new user logo student.
	 *
	 * @param student the student
	 * @return the string
	 */
	public static String messageToNewUserLogoStudent(StudentDTO student) {
		return

		"<div>Olá " + student.getName() + "! Seja muito bem-vindo(a) ao IdiomaPlus. " + "<br>"
				+ "Os dados cadastrados estão logo abaixo:</div>" + "<br>"
				+ "<div>==============================================================</div>"
				+ "<div><strong>Nome: <strong>" + student.getName() + "</div>" + "<div><strong>Telefone: <strong>"
				+ student.getPhone() + "</div>" + "<div><strong>E-mail: <strong>" + student.getEmail() + "</div>"
				+ "<div><strong>Mensalidade: <strong>" + student.getFees() + "</div>"
				+ "<div>=============================================================</div>" + "<br>";
	}

	/**
	 * Message to new user logo teacher.
	 *
	 * @param teacher the teacher
	 * @return the string
	 */
	public static String messageToNewUserLogoTeacher(TeacherDTO teacher) {
		return

		"<div>Olá " + teacher.getName() + "! Seja muito bem-vindo(a) ao IdiomaPlus. " + "<br>"
				+ "Os dados cadastrados estão logo abaixo:</div>" + "<br>"
				+ "<div>==============================================================</div>"
				+ "<div><strong>Nome: <strong>" + teacher.getName() + "</div>" + "<div><strong>Telefone: <strong>"
				+ teacher.getPhone() + "</div>" + "<div><strong>E-mail: <strong>" + teacher.getEmail() + "</div>"
				+ "<div><strong>Mensalidade: <strong>" + teacher.getSalary() + "</div>"
				+ "<div>=============================================================</div>" + "<br>";
	}

	/**
	 * Creates the title senha.
	 *
	 * @param user the user
	 * @return the string
	 */
	public static String createTitleSenha(UserDTO user) {
		return user.getName().toLowerCase(Locale.ROOT) + ", seguem seus dados de acesso!";
	}

	/**
	 * Recuperar dados.
	 *
	 * @param user the user
	 * @return the string
	 */
	public static String recuperarDados(UserDTO user) {
		return

		"<div>Olá " + user.getName() + "! Olá seguem os dados solicitados. " + "<br>"
				+ "Os seus dados e sua senha de acesso estão logo abaixo:</div>" + "<br>"
				+ "<div>==============================================================</div>"
				+ "<div><strong>Nome: <strong>" + user.getName() + "</div>" + "<div><strong>Usuário: <strong>"
				+ user.getUsername() + "</div>" + "<div><strong>E-mail: <strong>" + user.getEmail() + "</div>"
				+ "<div><strong>Password: <strong> Para trocar a senha <a href=http://idiomaplusfront.s3-website-sa-east-1.amazonaws.com/trocarSenha/"
				+ user.getId() + "> Clique aqui</a>" + "</div>"
				+ "<div>=============================================================</div>" + "<br>";
	}

	/**
	 * Dados contact.
	 *
	 * @param contact the contact
	 * @return the string
	 */
	public static String dadosContact(ContactDTO contact) {
		return

		"<div>Olá o Usuário(a) " + contact.getName() + " enviou uma mensagem. " + "<br>"
				+ "Os seus dados estão logo abaixo:</div>" + "<br>"
				+ "<div>==============================================================</div>"
				+ "<div><strong>Nome: <strong>" + contact.getName() + "</div>" + "<div><strong>Usuário: <strong>"
				+ contact.getEmail() + "</div>" + "<div><strong>E-mail: <strong>" + contact.getSubject() + "</div>"
				+ "<div><strong>Mensagem: <strong>" + contact.getMessage() + "</div>"
				+ "<div>Retornaremos o mais rápido possível</div>"
				+ "<div>=============================================================</div>" + "<br>";
	}
}

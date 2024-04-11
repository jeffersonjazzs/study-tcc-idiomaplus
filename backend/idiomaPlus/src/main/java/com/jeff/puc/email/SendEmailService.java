/*
 * 
 */
package com.jeff.puc.email;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

// TODO: Auto-generated Javadoc
/**
 * The Class SendEmailService.
 */
@Service
@Slf4j
public class SendEmailService {
	
	/** The sender. */
	@Value("${default.sender}")
	private String sender;
	
	/** The envio email do java. */
	private final JavaMailSender envioEmailDoJava;

	/**
	 * Instantiates a new send email service.
	 *
	 * @param javaMailSender the java mail sender
	 */
	public SendEmailService(final JavaMailSender javaMailSender) {
		this.envioEmailDoJava = javaMailSender;
	}

	/**
	 * Enviar email com anexo.
	 *
	 * @param para the para
	 * @param titulo the titulo
	 * @param conteudo the conteudo
	 * @param logo the logo
	 * @throws MessagingException the messaging exception
	 */
	public void enviarEmailComAnexo(String para, String titulo, String conteudo, String logo)
			throws MessagingException {
		log.info("Enviando E-mail com anexo");
		var mensagem = envioEmailDoJava.createMimeMessage();
		var helper = new MimeMessageHelper(mensagem, true);
		helper.setFrom(sender);
		helper.setTo(para);
		helper.setSubject(titulo);
		helper.setText(conteudo, true);
		helper.addAttachment("Logogrande.jpg", new ClassPathResource(logo));
		envioEmailDoJava.send(mensagem);
		log.info("Email com anexo enviado com sucesso");
	}
}

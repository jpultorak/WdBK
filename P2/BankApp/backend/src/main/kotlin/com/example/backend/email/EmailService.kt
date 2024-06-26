package com.example.backend.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

import java.nio.charset.StandardCharsets.UTF_8

@Service
class EmailService (
    private val mailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine
) {

    @Async
    fun sendEmail(
        to: String,
        username: String,
        emailTemplate: EmailTemplateName?,
        confirmationUrl: String,
        activationCode: String,
        subject: String
    ) {

        val templateName : String = emailTemplate?.name ?: "confirm-email"
        val mimeMessage = mailSender.createMimeMessage()

        val helper = MimeMessageHelper(
            mimeMessage,
            MULTIPART_MODE_MIXED,
            UTF_8.name()
        )

        val properties: MutableMap<String, Any> = HashMap()

        properties["username"] = username
        properties["confirmationUrl"] = confirmationUrl
        properties["activation_code"] = activationCode

        val context = Context()
        context.setVariables(properties)

        helper.setFrom("janpultorak@wp.pl")
        helper.setTo(to)
        helper.setSubject(subject)

        val template = templateEngine.process(templateName, context)

        helper.setText(template, true)

        mailSender.send(mimeMessage)
    }
}
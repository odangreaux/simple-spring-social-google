package grails.plugin.simplespringsocial.google.config

import javax.inject.Inject
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionFactory
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.connect.support.ConnectionFactoryRegistry
import org.springframework.social.google.api.Google
import org.springframework.social.google.api.impl.GoogleTemplate
import org.springframework.social.google.connect.GoogleConnectionFactory

@Configuration
class GoogleSpringConfig {
	@Inject
	ConnectionFactoryRegistry connectionFactoryRegistry
	@Inject
	ConnectionRepository connectionRepository

	@Bean
	ConnectionFactory googleConnectionFactory() {
		println "Configuring SimpleSpringSocialGoogle"

		// gets required parameters
		String clientId = ConfigurationHolder.config.grails.plugin.simplespringsocial.google.clientId
		String clientSecret = ConfigurationHolder.config.grails.plugin.simplespringsocial.google.clientSecret

		// checks the parameters
		assert clientId, "You must configure the Google clientId into Config.groovy with the following parameter : grails.plugin.simplespringsocial.google.clientId"
		assert clientSecret, "You must configure the Google clientSecret into Config.groovy with the following parameter : grails.plugin.simplespringsocial.google.clientSecret"

		ConnectionFactory googleConnectionFactory  = new GoogleConnectionFactory(clientId, clientSecret)
		connectionFactoryRegistry.addConnectionFactory(googleConnectionFactory)

		return googleConnectionFactory
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public Google google() {
		Connection<Google> connection = connectionRepository.findPrimaryConnection(Google)
		connection != null ? connection.getApi() : new GoogleTemplate()
	}
}

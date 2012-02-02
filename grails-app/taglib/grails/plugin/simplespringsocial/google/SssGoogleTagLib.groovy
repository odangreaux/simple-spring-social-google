package grails.plugin.simplespringsocial.google

class SssGoogleTagLib {
	static namespace = "sss"

	/**
	 * Creates a link to connect to Google.
	 * The content of this tag is reused as the content of the generated <a> tag.
	 *
	 * @attr id The id of the link
	 * @attr id The name of the link
	 */
	def googleConnectLink = {attrs, body ->
		out << "<a href='"+createLink(controller: "sssGoogle", action: "login") + "'"
		if(attrs.id) out << " id='$attrs.id'"
		if(attrs.name) out << " name='$attrs.name'"
		out << ">"
		out << body()
		out << "</a>"
	}
}

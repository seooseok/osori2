package com.osori.cave.configuration

import com.osori.cave.domain.navigation.infrastructure.UriPart
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.support.WebBindingInitializer
import java.beans.PropertyEditorSupport

@Configuration
class WebBindConfiguration {

    @Bean
    fun bindingInitializer(): WebBindingInitializer {
        return WebBindingInitializer { binder, _ ->
            binder.registerCustomEditor(UriPart.DepthType::class.java, DepthTypeEditor())
            binder.registerCustomEditor(RequestMethod::class.java, RequestMethodEditor())
        }
    }
}

class DepthTypeEditor : PropertyEditorSupport() {
    override fun getAsText(): String {
        return super.getValue().toString()
    }

    override fun setAsText(text: String) {
        super.setValue(UriPart.DepthType.valueOf(text.trim().toUpperCase()))
    }
}

class RequestMethodEditor : PropertyEditorSupport() {
    override fun getAsText(): String {
        return super.getValue().toString()
    }

    override fun setAsText(text: String) {
        super.setValue(RequestMethod.valueOf(text.trim().toUpperCase()))
    }
}

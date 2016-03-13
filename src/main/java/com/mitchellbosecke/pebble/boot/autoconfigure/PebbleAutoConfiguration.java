package com.mitchellbosecke.pebble.boot.autoconfigure;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.extension.Extension;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.spring.PebbleViewResolver;

@Configuration
@ConditionalOnClass(PebbleEngine.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(PebbleProperties.class)
public class PebbleAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(name = "pebbleLoader")
    public static class DefaultLoaderConfiguration {

        @Autowired
        private PebbleProperties properties;

        @Bean
        public Loader<?> pebbleLoader() {
            ClasspathLoader loader = new ClasspathLoader();
            loader.setCharset(properties.getEncoding().name());
            return loader;
        }

    }

    @Configuration
    @ConditionalOnMissingBean(name = "pebbleEngine")
    public static class PebbleDefaultConfiguration {

        @Autowired
        private PebbleProperties properties;

        @Autowired
        private Loader<?> pebbleLoader;

        @Autowired(required = false)
        private List<Extension> extensions;

        @Bean
        public PebbleEngine pebbleEngine() {
            PebbleEngine.Builder builder = new PebbleEngine.Builder();
            builder.loader(pebbleLoader);
            if (extensions != null && !extensions.isEmpty()) {
                builder.extension(extensions.toArray(new Extension[extensions.size()]));
            }
            if (!properties.isCache()) {
                builder.templateCache(null);
                builder.tagCache(null);
            }
            return builder.build();
        }

    }

    @Configuration
    @ConditionalOnWebApplication
    @ConditionalOnClass({ Servlet.class })
    public static class PebbleViewResolverConfiguration {

        @Autowired
        private PebbleProperties properties;

        @Autowired
        private PebbleEngine pebbleEngine;

        @Bean
        @ConditionalOnMissingBean(name = "pebbleViewResolver")
        public PebbleViewResolver pebbleViewResolver() {
            PebbleViewResolver pvr = new PebbleViewResolver();
            pvr.setPebbleEngine(pebbleEngine);
            pvr.setPrefix(properties.getPrefix());
            pvr.setSuffix(properties.getSuffix());

            pvr.setContentType(properties.getContentType().toString());
            pvr.setCharacterEncoding(properties.getEncoding().name());
            pvr.setOrder(Ordered.LOWEST_PRECEDENCE - 5);

            return pvr;
        }

    }

}
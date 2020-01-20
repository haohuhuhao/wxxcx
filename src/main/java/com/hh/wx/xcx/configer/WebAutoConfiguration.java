package com.hh.wx.xcx.configer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hh.wx.xcx.argumentResolver.RequestModelArgumentResolver;
import com.hh.wx.xcx.converter.CustomLongConverter;
import com.hh.wx.xcx.interceptor.LoginInterceptor;

@Configuration
public class WebAutoConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	//@Autowired 
	//private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
//	@Autowired
//	private EnumConverterFactory enumConverterFactory;
	

   /* @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }*/

	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();

		//创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //过滤并修改配置返回内容
        fastJsonConfig.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //Boolean字段如果为null,输出为falseJ,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //是否输出值为null的字段,默认为false。
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat

        );
        
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonConverter);
        
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//      builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//      builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        builder.serializerByType(Long.class,new CustomLongConverter());
        builder.serializerByType(Long.TYPE,new CustomLongConverter());
        builder.serializerByType(long.class,new CustomLongConverter());
        converters.add(0,new MappingJackson2HttpMessageConverter(builder.build()));

    }
	
	@PostConstruct
    public void addConversionConfig() {
        /*ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            //添加字符串转换为list集合的转换机器
            genericConversionService.addConverter(new DateConverterConfig());
            //添加字符串转换为日期类型的字符串
        }*/
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor(redisTemplate));//.addPathPatterns("/intercept/**");
		registration.addPathPatterns("/**");
		registration.excludePathPatterns("/user/regist","/user/login","/wxUser/**","/quartz/**");
		
		//registry.addInterceptor(new WxLoginInterceptor(redisTemplate)).addPathPatterns("/**/wxUser/**").excludePathPatterns("/**/wxUser/login");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	/* (non-Javadoc)
	 * @see 自定义requestBody
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new RequestModelArgumentResolver());
		//WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}

	/*@Override
	public void addFormatters(FormatterRegistry registry) {
		
		registry.addConverterFactory(enumConverterFactory);
	}*/
	
	
	

}

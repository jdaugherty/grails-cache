/* Copyright 2012-2013 SpringSource.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.cache;

import org.codehaus.groovy.transform.GroovyASTTransformationClass;
import org.grails.datastore.gorm.transform.GormASTTransformationClass;

import java.lang.annotation.*;

/**
 * Indicates that a method (or all methods on a class) trigger(s)
 * aCache#put(Object, Object) operation. As opposed to {@link Cacheable} annotation,
 * this annotation does not cause the target method to be skipped - rather it
 * always causes the method to be invoked and its result to be placed into the cache.
 *
 * @author Jeff Brown
 * @author Graeme Rocher
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@GroovyASTTransformationClass("org.grails.datastore.gorm.transform.OrderedGormTransformation")
@GormASTTransformationClass("org.grails.plugin.cache.compiler.CachePutTransformation")
public @interface CachePut {

	/**
	 * Name of the caches in which the update takes place.
	 * <p>May be used to determine the target cache (or caches), matching the
	 * qualifier value.
	 */
	String[] value();

	/**
	 * A closure for computing the key dynamically.
	 * <p>Default is null, meaning all method parameters are considered as a key.
	 */
	Class[] key() default {};
}

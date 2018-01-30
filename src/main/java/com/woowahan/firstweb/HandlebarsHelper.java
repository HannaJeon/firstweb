package com.woowahan.firstweb;

import com.github.jknack.handlebars.Options;
import com.google.common.base.Objects;

import java.io.IOException;

@pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper
public class HandlebarsHelper {
	public CharSequence equals(final Object obj1, final Options options) throws IOException {
		Object obj2 = options.param(0);
		return Objects.equal(obj1, obj2) ? options.fn() : options.inverse();
	}
}

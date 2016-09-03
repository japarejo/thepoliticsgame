package simpleproject;

import java.util.List;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.common.collect.Lists;

public final class HolaMundo {

	public static void main(String[] args) {
		List<String> params = Lists.newArrayList(args);
		String name = "World";
		if (params.size() > 1)
			name = params.get(1);
		String greeting = "Hello " + name + "!";
		System.out.println(FigletFont.convertOneLine(greeting));
	}

}

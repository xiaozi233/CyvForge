package mcpk.functions.sneaksprint;

import java.util.ArrayList;
import java.util.HashMap;

import mcpk.Player;
import mcpk.functions.Function;
import mcpk.utils.Arguments;

public class FunctionSneakSprint extends Function {

	@Override
	public String[] names() {
		// TODO Auto-generated method stub
		return new String[] {"sneaksprint", "sns", "crouchsprint", "cs"};
	}

	@Override
	public void run(Player player, int duration, float facing, ArrayList<Character> modifiers, HashMap<String, Double> effects)
			throws DurationException {
		Arguments args = new Arguments();
		args.replace("duration", Math.abs(duration));
		args.replace("facing", facing);
		if (duration > 0) args.replace("forward", 1);
		else if (duration < 0) args.replace("forward", -1);

		checkModifiers(modifiers, args, duration);
		checkEffects(effects, args, duration);
		
		args.replace("sneaking", true);
		args.replace("sprinting", true);
		player.move(args);
		
	}

}

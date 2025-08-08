package mcpk.functions.sneak;

import java.util.ArrayList;
import java.util.HashMap;

import mcpk.Player;
import mcpk.functions.Function;
import mcpk.utils.Arguments;

public class FunctionSneak45 extends Function {

	@Override
	public String[] names() {
		return new String[] {"sn45", "c45", "sneak45", "crouch45"};
	}

	@Override
	public void run(Player player, int duration, float facing, ArrayList<Character> modifiers, HashMap<String, Double> effects) throws InvalidKeypressException {
		Arguments args = new Arguments();
		args.replace("duration", Math.abs(duration));
		args.replace("facing", (float) (facing + 45));
		if (duration > 0) args.replace("forward", 1);
		else if (duration < 0) args.replace("forward", -1);
		
		checkNoModifiers(modifiers);
		checkEffects(effects, args, duration);
		
		args.replace("sneaking", true);
		args.replace("strafing", 1);
		player.move(args);
		
	}

	
	
}

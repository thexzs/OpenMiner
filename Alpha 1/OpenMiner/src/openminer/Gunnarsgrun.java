package openminer;

import org.powerbot.script.Area;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Hud;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.TilePath;
import org.powerbot.script.rt6.DepositBox.Amount;

public class Gunnarsgrun extends Task<ClientContext> {

	public static final Tile[] PATH = {
			new Tile(3092, 3499, 0),
			new Tile(3092, 3501, 0),
			new Tile(3089, 3502, 0),
			new Tile(3087, 3501, 0),
			new Tile(3083, 3501, 0),
			new Tile(3078, 3500, 0),
			new Tile(3077, 3497, 0),
			new Tile(3075, 3494, 0),
			new Tile(3074, 3491, 0),
			new Tile(3073, 3487, 0),
			new Tile(3073, 3484, 0),
			new Tile(3072, 3482, 0),
			new Tile(3071, 3478, 0),
			new Tile(3071, 3475, 0),
			new Tile(3072, 3472, 0),
			new Tile(3072, 3470, 0),
			new Tile(3072, 3466, 0),
			new Tile(3070, 3463, 0),
			new Tile(3069, 3461, 0),
			new Tile(3069, 3457, 0),
			new Tile(3068, 3453, 0),
			new Tile(3070, 3451, 0),
			new Tile(3070, 3447, 0),
			new Tile(3070, 3444, 0),
			new Tile(3071, 3440, 0),
			new Tile(3072, 3437, 0),
			new Tile(3073, 3435, 0),
			new Tile(3075, 3432, 0),
			new Tile(3078, 3430, 0),
			new Tile(3080, 3425, 0),
			new Tile(3082, 3423, 0),
			new Tile(3082, 3422, 0)
    };
	
	public static final Tile[] PATH2 = {
			new Tile(3092, 3501, 0),
			new Tile(3089, 3501, 0),
			new Tile(3087, 3501, 0),
			new Tile(3083, 3500, 0),
			new Tile(3079, 3498, 0),
			new Tile(3076, 3496, 0),
			new Tile(3072, 3492, 0),
			new Tile(3071, 3487, 0),
			new Tile(3071, 3485, 0),
			new Tile(3071, 3481, 0),
			new Tile(3071, 3477, 0),
			new Tile(3071, 3473, 0),
			new Tile(3071, 3469, 0),
			new Tile(3072, 3462, 0),
			new Tile(3070, 3457, 0),
			new Tile(3069, 3454, 0),
			new Tile(3069, 3450, 0),
			new Tile(3069, 3445, 0),
			new Tile(3068, 3441, 0),
			new Tile(3068, 3437, 0),
			new Tile(3068, 3433, 0),
			new Tile(3068, 3427, 0),
			new Tile(3068, 3423, 0),
			new Tile(3068, 3417, 0),
			new Tile(3068, 3413, 0),
			new Tile(3070, 3408, 0),
			new Tile(3072, 3405, 0),
			new Tile(3074, 3402, 0),
			new Tile(3077, 3398, 0)
    };
	
	TilePath pathToCoals = new TilePath(ctx, PATH);
    TilePath pathToDepositBoxFromCoals = new TilePath(ctx, PATH).reverse();
    TilePath pathToClay = new TilePath(ctx, PATH2);
    TilePath pathToDepositBoxFromClay = new TilePath(ctx, PATH2).reverse();
    
	public Gunnarsgrun(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return (OpenMiner.INTERFACE_SELECTION_LOCATION == "Gunnarsgrun");
	}

	private int[] uncutID = {1617, 1618, 1619, 1620, 1621, 1622, 1623, 1624, 1625, 1626, 1627, 1628, 1629, 1630, 1631, 1632};
	
	@Override
	public void execute() {
				
		final State state = getState();
        if (state == null) {
            return;
        }

        if ((OpenMiner.INTERFACE_SELECTION_ROX == "Coal rocks") || (OpenMiner.INTERFACE_SELECTION_ROX == "Tin ore rocks")) {
        	area = areaofcoals;
        }
        
        if (OpenMiner.INTERFACE_SELECTION_ROX == "Clay rocks") {
        	area = areaofclay;
        }
        switch (state) {
        
        case WALK_TO_DEPOSITBOXFROMCOALS:
        	
        	OpenMiner.SCRIPT_STATE = "Walking to Deposit";
        	pathToDepositBoxFromCoals.traverse();        	
        	int b = Random.nextInt(1, 10);
			switch(b){
			  case 1: ctx.camera.angle(Random.nextInt(0, 359));
			  case 2: ctx.camera.pitch(Random.nextInt(50, 100));
			  case 3: ctx.camera.angle(Random.nextInt(0, 359));
			  case 4: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 30), ctx.input.getLocation().y + Random.nextInt(0, 40));
			  case 5: 
			  case 6: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 50), ctx.input.getLocation().y + Random.nextInt(0, 90));
			  case 7: 
			  case 8: ctx.camera.angle(Random.nextInt(0, 359));
			  case 9: ctx.camera.pitch(Random.nextInt(0, 50));
			  case 10: ctx.camera.pitch(Random.nextInt(60, 100));
			}
			
        	break;
        case WALK_TO_GUNNARSGRUNCOALS: 
        	OpenMiner.SCRIPT_STATE = "Walking to Coals";
        	pathToCoals.traverse();
        	break;
        case MINE:
        	GameObject rock = ctx.objects.nearest().poll();
    		if(rock.inViewport()) {
    			if (!ctx.hud.opened(Hud.Window.BACKPACK)){
    				ctx.hud.opened(Hud.Window.BACKPACK);
    			}
    			OpenMiner.SCRIPT_STATE = "Mining";
    			rock.interact("Mine");
    		}
    		
    		int c = Random.nextInt(1, 10);
			switch(c){
			  case 1: ctx.camera.angle(Random.nextInt(0, 359));
			  case 2: ctx.camera.pitch(Random.nextInt(50, 100));
			  case 3: ctx.camera.angle(Random.nextInt(0, 359));
			  case 4: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 30), ctx.input.getLocation().y + Random.nextInt(0, 40));
			  case 5: 
			  case 6: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 50), ctx.input.getLocation().y + Random.nextInt(0, 90));
			  case 7: 
			  case 8: ctx.camera.angle(Random.nextInt(0, 359));
			  case 9: ctx.camera.pitch(Random.nextInt(0, 50));
			  case 10: ctx.camera.pitch(Random.nextInt(60, 100));
			}
			break;
        case DEPOSIT:
        	ctx.depositBox.open();
    		if (ctx.depositBox.opened()) {
    			ctx.depositBox.depositInventory();
    			OpenMiner.SCRIPT_STATE = "Deposited All";
    		}
    		else if (!ctx.depositBox.opened()) {
    			OpenMiner.SCRIPT_STATE = "Opening deposit box";
    			ctx.depositBox.open();
    		}
    		if (ctx.backpack.select().count() < 2) {
    			OpenMiner.SCRIPT_STATE = "Closing deposit box";
    			ctx.depositBox.close();
    		}
    		else {
    			OpenMiner.SCRIPT_STATE = "Waiting to Deposit";
    		}
        break;
        case WAIT:
        	OpenMiner.SCRIPT_STATE = "Waiting";
        	int d = Random.nextInt(1, 10);
			switch(d){
			  case 1: ctx.camera.angle(Random.nextInt(0, 359));
			  case 2: ctx.camera.pitch(Random.nextInt(50, 100));
			  case 3: ctx.camera.angle(Random.nextInt(0, 359));
			  case 4: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 30), ctx.input.getLocation().y + Random.nextInt(0, 40));
			  case 5: 
			  case 6: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 50), ctx.input.getLocation().y + Random.nextInt(0, 90));
			  case 7: 
			  case 8: ctx.camera.angle(Random.nextInt(0, 359));
			  case 9: ctx.camera.pitch(Random.nextInt(0, 50));
			  case 10: ctx.camera.pitch(Random.nextInt(60, 100));
			}
			break;
        case ERROR:
        	OpenMiner.SCRIPT_STATE = "Error";
        	break;
        case M1D1:
        	
        	String str = OpenMiner.INTERFACE_SELECTION_ROX;
        	String substr = str.substring(0, str.lastIndexOf(" "));
        	        	
        	if (!area.contains(ctx.players.local())) {
        		if ((OpenMiner.INTERFACE_SELECTION_ROX == "Coal rocks") || (OpenMiner.INTERFACE_SELECTION_ROX == "Tin ore rocks")) {
        			pathToCoals.traverse();
        		}
        		if (OpenMiner.INTERFACE_SELECTION_ROX == "Clay rocks") {
        			pathToClay.traverse();
        		}
        	}
        	
        	if (area.contains(ctx.players.local())) {
        		if (ctx.backpack.select().name(substr).count() > 0) {
        			for(Item i : ctx.backpack.select().name(substr)) {
        			    i.interact("Drop"); }
        		}
        		if (ctx.backpack.select().name(substr).count() < 1) {
        			if (!ctx.objects.select().name(OpenMiner.INTERFACE_SELECTION_ROX).isEmpty()) {
            			if (!(ctx.players.local().animation() == -1)) {
                        	OpenMiner.SCRIPT_STATE = "Waiting";
                        }
            			if (ctx.players.local().animation() == -1) {
            				GameObject rock3 = ctx.objects.nearest().poll();
            	    		if(rock3.inViewport()) {
            	    			if (!ctx.hud.opened(Hud.Window.BACKPACK)){
            	    				ctx.hud.opened(Hud.Window.BACKPACK);
            	    			}
            	    			OpenMiner.SCRIPT_STATE = "Mining";
            	    			rock3.interact("Mine");
            	    		}
            	    		int x = Random.nextInt(1, 10);
            				switch(x){
            				  case 1: ctx.camera.angle(Random.nextInt(0, 359));
            				  case 2: ctx.camera.pitch(Random.nextInt(50, 100));
            				  case 3: ctx.camera.angle(Random.nextInt(0, 359));
            				  case 4: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 30), ctx.input.getLocation().y + Random.nextInt(0, 40));
            				  case 5: 
            				  case 6: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 50), ctx.input.getLocation().y + Random.nextInt(0, 90));
            				  case 7: 
            				  case 8: ctx.camera.angle(Random.nextInt(0, 359));
            				  case 9: ctx.camera.pitch(Random.nextInt(0, 50));
            				  case 10: ctx.camera.pitch(Random.nextInt(60, 100));
            				}
            			}
            		}
        		}
        		
        	}
        	
        	break;
        case WALK_TO_GUNNARSGRUNCLAY:
        	OpenMiner.SCRIPT_STATE = "Walking to Clay";
        	pathToClay.traverse();
        	break;
        case WALK_TO_DEPOSITBOXFROMCLAY:
        	OpenMiner.SCRIPT_STATE = "Walking to Deposit";
        	pathToDepositBoxFromClay.traverse();        	
        	int e = Random.nextInt(1, 10);
			switch(e){
			  case 1: ctx.camera.angle(Random.nextInt(0, 359));
			  case 2: ctx.camera.pitch(Random.nextInt(50, 100));
			  case 3: ctx.camera.angle(Random.nextInt(0, 359));
			  case 4: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 30), ctx.input.getLocation().y + Random.nextInt(0, 40));
			  case 5: 
			  case 6: ctx.input.move(ctx.input.getLocation().x - Random.nextInt(0, 50), ctx.input.getLocation().y + Random.nextInt(0, 90));
			  case 7: 
			  case 8: ctx.camera.angle(Random.nextInt(0, 359));
			  case 9: ctx.camera.pitch(Random.nextInt(0, 50));
			  case 10: ctx.camera.pitch(Random.nextInt(60, 100));
			}
        	break;
        }
        		
	}
	
	private final Area areaofcoals = new Area(
			new Tile(3077, 3422, 0),
			new Tile(3078, 3417, 0),
			new Tile(3086, 3417, 0),
			new Tile(3086, 3423, 0),
			new Tile(3081, 3424, 0)
	);
	
	private final Area areaofclay = new Area(
			new Tile(3077, 3404, 0),
			new Tile(3086, 3403, 0),
			new Tile(3088, 3394, 0),
			new Tile(3081, 3393, 0),
			new Tile(3075, 3397, 0)
	);
	
	public Area area;
			
	private State getState() {
		if (OpenMiner.INTERFACE_SELECTION_MODE == "Banking") {
		if ((OpenMiner.INTERFACE_SELECTION_ROX == "Coal rocks") || (OpenMiner.INTERFACE_SELECTION_ROX == "Tin ore rocks")) {
        	area = areaofcoals;
        }
        
        if (OpenMiner.INTERFACE_SELECTION_ROX == "Clay rocks") {
        	area = areaofclay;
        }
        
		if (ctx.backpack.select().count() < 28) {
           	if (!area.contains(ctx.players.local())) {
           		if ((OpenMiner.INTERFACE_SELECTION_ROX == "Coal rocks") || (OpenMiner.INTERFACE_SELECTION_ROX == "Tin ore rocks")) {
        			return State.WALK_TO_GUNNARSGRUNCOALS;
           		}
           		if (OpenMiner.INTERFACE_SELECTION_ROX == "Clay rocks") {
           			return State.WALK_TO_GUNNARSGRUNCLAY; 
           		}
        	}
        	if (area.contains(ctx.players.local())) {
        		if (!ctx.objects.select().name(OpenMiner.INTERFACE_SELECTION_ROX).isEmpty()) {
        			if (!(ctx.players.local().animation() == -1)) {
                    	return State.WAIT;
                    }
        			if (ctx.players.local().animation() == -1) {
        				return State.MINE;
        			}
        		}
        		if (ctx.objects.select().name(OpenMiner.INTERFACE_SELECTION_ROX).isEmpty()) {
        			return State.WAIT;
        		}
        	}
        	if (!ctx.backpack.select().id(uncutID).isEmpty()) {
        		return State.DEPOSIT;
        	}
        }
        if (ctx.backpack.select().count() == 28) {
        	if (ctx.depositBox.nearest().tile().distanceTo(ctx.players.local()) < 3) {
        		return State.DEPOSIT;
        	}
        	if (ctx.depositBox.nearest().tile().distanceTo(ctx.players.local()) > 2) {
        		if ((OpenMiner.INTERFACE_SELECTION_ROX == "Coal rocks") || (OpenMiner.INTERFACE_SELECTION_ROX == "Tin ore rocks")) {
        			return State.WALK_TO_DEPOSITBOXFROMCOALS;
        		}
        		if (OpenMiner.INTERFACE_SELECTION_ROX == "Clay rocks") {
        			return State.WALK_TO_DEPOSITBOXFROMCLAY; 
        		}
        	}
        }
		}
        if (OpenMiner.INTERFACE_SELECTION_MODE == "Powermining") {
        	return State.M1D1;
        }
        
        return State.ERROR;
        
    }

    private enum State {
        WALK_TO_GUNNARSGRUNCOALS, MINE, WALK_TO_DEPOSITBOXFROMCOALS, DEPOSIT, WAIT, M1D1, ERROR, WALK_TO_GUNNARSGRUNCLAY, WALK_TO_DEPOSITBOXFROMCLAY
    }
	
}

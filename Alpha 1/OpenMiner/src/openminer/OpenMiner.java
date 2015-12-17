package openminer;

import org.powerbot.script.*;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.*;

@Script.Manifest(
        name = "OpenMiner",
        description = "Open Concept Miner Beta 1"
)
//implements MessageListener so we can react based on incoming game messages
//PaintListener to draw a paint
public class OpenMiner extends PollingScript<ClientContext> implements MessageListener, PaintListener {

	public static String INTERFACE_SELECTION_LOCATION = "";
    public static String INTERFACE_SELECTION_MODE = "";
    public static String INTERFACE_SELECTION_ROX = "";
    public static int[] INTERFACE_SELECTION_OREID = {};
    public static String SCRIPT_STATE;
    
    public static String SCRIPT_VERSION = "Beta 1.1";
	private List<Task> taskList = new ArrayList<Task>();
	private int startLvl = ctx.skills.level(Constants.SKILLS_MINING);
	private int startExp = ctx.skills.experience(Constants.SKILLS_MINING);
	public int mined = 0;
	public int miningexp = 0;
	private int oreexp = 18;
	
	
    @Override
    public void start() {
    	taskList.addAll(Arrays.asList(new Gunnarsgrun(ctx)));
    	SCRIPT_STATE = "Tasks added.";
    	String[] args = null;
    	Interface.main(args);
    }
    
    public static void setLocation(String val) {
   	 INTERFACE_SELECTION_LOCATION = val;
   	 SCRIPT_STATE = "Location set.";
   	 
    }
    
    
    @Override
    public void poll() {
    	for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }    	
    }


    @Override
    public void messaged(MessageEvent m) {
    	String msg = m.text().toString();
		if(msg.contains("You manage to mine")) {
		mined++;
		miningexp = miningexp + oreexp;
		System.out.println("[xzs] +1 ore");
		}
    }

    @Override
    public void repaint(Graphics g) {
    	
		int expGained = ctx.skills.experience(Constants.SKILLS_MINING) - startExp;
		int lvlGained = ctx.skills.level(Constants.SKILLS_MINING) - startLvl;
		int skill = Constants.SKILLS_MINING;
		int expToNext = ctx.skills.experienceAt(ctx.skills.realLevel(skill) + 1) - ctx.skills.experience(skill);
		int timeelapseint = (int) getRuntime() / 1000;
		int expPhr = expGained * 360000;
		int timeinint = (int) getRuntime();
		
    	Font font = new Font("Verdana", Font.BOLD, 14);
		Color cyanish = new Color(19, 87, 90, 150);
		Font font2 = new Font("Verdana", 0, 12);
    	
		g.setFont(font);
		g.setColor(Color.white);
		g.drawRect(2, 99, 218, 208);
		g.setColor(cyanish);
		g.fillRect(3, 100, 216, 206);
		g.setColor(Color.white);
		g.drawString("Open Miner by xzs", 10, 115);
		g.drawLine(12, 118, 214, 118);
		g.setFont(font2);
		g.drawString("Location: " + INTERFACE_SELECTION_LOCATION, 8, 132);
		g.drawString("Mining Mode: " + INTERFACE_SELECTION_MODE, 8, 145);
		g.drawString("Rock Selected: " + INTERFACE_SELECTION_ROX, 8, 158);
		g.drawLine(12, 161, 214, 161);
		g.drawString("Mining Exp Gained: " + expGained, 8, 175);
		g.drawString("Mining Level Gained: " + lvlGained + "(" + ctx.skills.level(Constants.SKILLS_MINING) + ")", 8, 188);
		g.drawString("EXP to Next Level: " + expToNext, 8, 201);
		g.drawString("Total Coals Mined: " + mined + ".", 8, 214);
		g.drawString("EXP Per Hour: " + (expPhr / timeinint) + " xp/hr.", 8, 227);
		g.drawLine(12, 230, 214, 230);
		g.drawString("Script State: " + SCRIPT_STATE, 8, 244); 
		g.drawString("Total time running: " + timeelapseint + "s.", 8, 257);

		Point mousenow = ctx.input.getLocation();
		int mousenowy = mousenow.y + 100;
		int mousenowx = mousenow.x;
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(mousenowx - 1000, mousenowy, mousenowx + 1000, mousenowy);
		g2.drawLine(mousenowx,  mousenowy - 1000,  mousenowx,  mousenowy +1000);
		
		g3.setColor(cyanish);
		g3.setStroke(new BasicStroke(1));
		g3.drawLine(mousenowx - 1000, mousenowy, mousenowx + 1000, mousenowy);
		g3.drawLine(mousenowx,  mousenowy - 1000,  mousenowx,  mousenowy +1000);

    }
}